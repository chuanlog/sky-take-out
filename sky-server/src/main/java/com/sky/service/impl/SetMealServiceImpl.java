package com.sky.service.impl;

import com.sky.dto.SetmealDTO;
import com.sky.entity.Setmeal;
import com.sky.entity.SetmealDish;
import com.sky.mapper.SetmealDishMapper;
import com.sky.mapper.SetmealMapper;
import com.sky.service.SetMealService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SetMealServiceImpl implements SetMealService {

    @Autowired
    SetmealMapper setmealMapper;

    @Autowired
    SetmealDishMapper setmealDishMapper;
    /**
     * 新增套餐
     * @param setmealDTO setmealDTO
     */
    @Override
    @Transactional
    public void saveWithDish(SetmealDTO setmealDTO) {
        Setmeal setmeal = new Setmeal();
        BeanUtils.copyProperties(setmealDTO, setmeal);
        setmeal.setStatus(0);// 默认为停售
        List<SetmealDish> setmealDishes = setmealDTO.getSetmealDishes();

        setmealMapper.save(setmeal);//自动返回填回setmeal

        // 为setmealDish设置setmealId
        for (SetmealDish setmealDish : setmealDishes) {
            setmealDish.setSetmealId(setmeal.getId());
        }
        setmealDishMapper.insertBatch(setmealDishes);
    }

    /**
     * 修改套餐
     * @param setMealDTO setMealDTO
     */
    @Override
    @Transactional
    public void updateWithDish(SetmealDTO setMealDTO) {
        //删除该套餐下的所有菜品
        setmealDishMapper.deleteBySetmealId(setMealDTO.getId());
        // 修改套餐
        Setmeal setmeal = new Setmeal();
        BeanUtils.copyProperties(setMealDTO, setmeal);
        setmealMapper.update(setmeal);

        // 修改菜品
        List<SetmealDish> setmealDishes = setMealDTO.getSetmealDishes();
        for (SetmealDish setmealDish : setmealDishes) {
            setmealDish.setSetmealId(setMealDTO.getId());
        }
        setmealDishMapper.insertBatch(setmealDishes);
    }
}
