package com.sky.service;

import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.vo.SetmealVO;

import java.util.List;

/**
 * 套餐管理服务
 */
public interface SetMealService {
    /**
     * 新增套餐
     * @param setmealDTO setmealDTO
     */
    void saveWithDish(SetmealDTO setmealDTO);

    /**
     * 修改套餐
     * @param setMealDTO setMealDTO
     */
    void updateWithDish(SetmealDTO setMealDTO);

    /**
     * 批量删除套餐
     * @param ids
     */
    void deleteBatch(List<Long> ids);

    /**
     * 分页查询
     * @param setmealPageQueryDTO
     * @return
     */
    PageResult pageQuery(SetmealPageQueryDTO setmealPageQueryDTO);

    /**
     * 起售停售
     * @param status 状态
     * @param id 菜品id
     */
    void startOrStop(Integer status, Long id);
}
