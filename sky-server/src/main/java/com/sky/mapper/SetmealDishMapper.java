package com.sky.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.sky.entity.SetmealDish;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SetmealDishMapper {
    /**
     * 根据菜品id查询套餐id
     * @param dishIds
     * @return
     */
    @DS("slave")
    List<Long> getSetmealIdsByDishId(List<Long> dishIds);

    /**
     * 批量加入套餐菜品数据,应自动填充参数
     * @param setmealDishes setmealDishes
     */
    @DS("master")
    void insertBatch(List<SetmealDish> setmealDishes);

    /**
     * 根据套餐id删除套餐菜品数据
     * @param id id
     */
    @DS("master")
    void deleteBySetmealId(Long id);

    /**
     * 根据菜品id查询套餐菜品数据
     * @param id id
     * @return List<SetmealDish>
     */
    @DS("slave")
    int countByDishId(Long id);
}
