package com.sky.service;

import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Setmeal;
import com.sky.result.PageResult;
import com.sky.vo.DishItemVO;

import java.util.List;

/**
 * 套餐管理服务
 */
public interface SetmealService {
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

    /**
     * 条件查询
     * @param setmeal
     * @return
     */
    List<Setmeal> list(Setmeal setmeal);

    /**
     * 根据id查询菜品选项
     * @param id
     * @return
     */
    List<DishItemVO> getDishItemById(Long id);
}
