package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.constant.MessageConstant;
import com.sky.constant.StatusConstant;
import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.entity.DishFlavor;
import com.sky.exception.DeletionNotAllowedException;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    /**
     * 根据id查询菜品数据
     *
     * @param id 菜品id
     * @return 查询结果
     */
    @Override
    public DishVO getByIdWithFlavor(Long id) {
        //查询菜品数据
        Dish dish = dishMapper.getById(id);
        //查询口味数据
        List<DishFlavor> dishFlavorList = dishFlavorMapper.getByDishId(id);
        //组合返回
        DishVO dishVO = new DishVO();
        BeanUtils.copyProperties(dish, dishVO);
        dishVO.setFlavors(dishFlavorList);
        return dishVO;
    }

    @Override
    @Transactional
    public void updateWithFlavor(DishDTO dishDTO) {
        //更新菜品表
        Dish dish = new Dish();
        BeanUtils.copyProperties(dishDTO, dish);
        dishMapper.update(dish);

        //更新口味表
        List<DishFlavor> flavors = dishDTO.getFlavors();
        if (flavors != null && !flavors.isEmpty()) {
            //删除当前菜品对应的口味数据
            dishFlavorMapper.deleteByDishId(dishDTO.getId());
            //重新批量插入口味数据
            flavors.forEach((dishFlavor) -> {
                dishFlavor.setDishId(dishDTO.getId());
            });
            dishFlavorMapper.insertBatch(flavors);
        }
    }

    /**
     * 根据分类id查询菜品
     *
     * @param categoryId 分类id
     * @return 菜品列表
     */
    @Override
    public List<Dish> getByCategoryId(Long categoryId) {
        List<Dish> dishes = dishMapper.getByCategoryId(categoryId);
        return dishes;
    }

    /**
     * 条件查询菜品和口味
     *
     * @param dish
     * @return
     */
    @Override
    public List<DishVO> listWithFlavor(Dish dish) {
        List<Dish> dishList = dishMapper.list(dish);

        List<DishVO> dishVOList = new ArrayList<>();

        for (Dish d : dishList) {
            DishVO dishVO = new DishVO();
            BeanUtils.copyProperties(d, dishVO);

            //根据菜品id查询对应的口味
            List<DishFlavor> flavors = dishFlavorMapper.getByDishId(d.getId());

            dishVO.setFlavors(flavors);
            dishVOList.add(dishVO);
        }

        return dishVOList;
    }

    @Override
    public void startOrStop(Integer status, Long id) {

        if (Objects.equals(status, StatusConstant.ENABLE)) {
            // 停售菜品，需要判断是否关联套餐，如果关联套餐，则无法停售
            // 查询当前菜品是否关联套餐
            int count = setmealDishMapper.countByDishId(id);
            if (count > 0) {
                // 当前菜品关联套餐，不能停售
                throw new DeletionNotAllowedException(MessageConstant.DISH_BE_RELATED_BY_SETMEAL);
            }
            // 修改菜品状态
            Dish dish = Dish.builder()
                    .id(id)
                    .status(status)
                    .build();
            dishMapper.update(dish);
        } else {
            // 修改菜品状态
            Dish dish = Dish.builder()
                    .id(id)
                    .status(status)
                    .build();
            dishMapper.update(dish);
        }
    }
}
