package com.sky.service;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.result.PageResult;
import com.sky.vo.DishVO;

import java.util.List;

public interface DishService {
    /**
     * 新增菜品
     * @param dishDTO
     */
    void saveWithFlavor(DishDTO dishDTO);

    /**
     *
     * @param dishPageQueryDTO
     * @return
     */
    PageResult pageQuery(DishPageQueryDTO dishPageQueryDTO);

    /**
     * 批量删除菜品
     * @param ids 要删除的菜品id
     */
    void deleteBatch(List<Long> ids);

    /**
     * 根据id查询菜品及其口味数据
     * @param id 菜品id
     * @return 菜品VO查询结果
     */
    DishVO getByIdWithFlavor(Long id);

    /**
     * 修改菜品及其对应口味
     * @param dishDTO 修改菜品
     */
    void updateWithFlavor(DishDTO dishDTO);

    /**
     * 根据分类id查询菜品
     * @param categoryId 分类id
     * @return 查询结果
     */
    List<Dish> getByCategoryId(Long categoryId);
}
