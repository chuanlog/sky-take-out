package com.sky.service;

import com.sky.dto.SetmealDTO;

/**
 * 套餐管理服务
 */
public interface SetMealService {
    /**
     * 新增套餐
     * @param setmealDTO setmealDTO
     */
    void saveWithDish(SetmealDTO setmealDTO);
}
