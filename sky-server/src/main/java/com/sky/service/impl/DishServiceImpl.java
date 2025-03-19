package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.constant.MessageConstant;
import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.entity.DishFlavor;
import com.sky.mapper.DishFlavorMapper;
import com.sky.mapper.DishMapper;
import com.sky.mapper.SetmealDishMapper;
import com.sky.result.PageResult;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.sky.exception.DeletionNotAllowedException;

import java.util.List;

@Service
@Slf4j
public class DishServiceImpl implements DishService {
    @Autowired
    private DishFlavorMapper dishFlavorMapper;

    @Autowired
    private DishMapper dishMapper;

    @Autowired
    private SetmealDishMapper setmealDishMapper;

    /**
     * 新增菜品
     *
     * @param dishDTO 菜品数据
     */
    @Override
    @Transactional
    public void saveWithFlavor(DishDTO dishDTO) {
        //插入菜品数据
        Dish dish = new Dish();
        BeanUtils.copyProperties(dishDTO, dish);
        dishMapper.insert(dish);
        //插入口味数据
        List<DishFlavor> flavors = dishDTO.getFlavors();
        Long dishId = dish.getId();
        if (flavors != null && !flavors.isEmpty()) {
            flavors.forEach((dishFlavor) -> {
                dishFlavor.setDishId(dishId);
            });
        }

    }

    /**
     * 分页查询
     *
     * @param dishPageQueryDTO 分页查询条件
     * @return 分页查询结果
     */
    @Override
    public PageResult pageQuery(DishPageQueryDTO dishPageQueryDTO) {
        PageHelper.startPage(dishPageQueryDTO.getPage(), dishPageQueryDTO.getPageSize());
        Page<DishVO> page = dishMapper.pageQuery(dishPageQueryDTO);
        return new PageResult(page.getTotal(), page.getResult());
    }

    /**
     * 批量删除菜品
     *
     * @param ids 要删除的菜品id
     */
    @Override
    @Transactional//删除菜品时，同时删除菜品口味数据,操作涉及多张表，需要开启事务
    public void deleteBatch(List<Long> ids) {
        //判断当前菜品是否在起售中，如果在起售中，则不能删除
        for (Long id : ids) {
            Dish dish = dishMapper.getById(id);
            if (dish.getStatus() == 1) {
                throw new DeletionNotAllowedException(MessageConstant.DISH_ON_SALE);
            }
        }

        //判断当前菜品是否关联套餐，如果关联套餐，则不能删除
        List<Long> setmealIds = setmealDishMapper.getSetmealIdsByDishId(ids);
        if (setmealIds != null && !setmealIds.isEmpty()) {
            throw new DeletionNotAllowedException(MessageConstant.DISH_BE_RELATED_BY_SETMEAL);
        }

        //批量删除菜品数据
        dishMapper.deleteByIds(ids);
        //批量删除菜品口味数据
        dishFlavorMapper.deleteByDishIds(ids);
    }
}
