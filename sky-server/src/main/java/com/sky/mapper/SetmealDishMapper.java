package com.sky.mapper;

import com.sky.annotation.AutoFill;
import com.sky.entity.SetmealDish;
import com.sky.enumeration.OperationType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper
public interface SetmealDishMapper {
    /**
     * 根据菜品id查询套餐id
     * @param dishIds
     * @return
     */
    List<Long> getSetmealIdsByDishId(List<Long> dishIds);

    /**
     * 批量加入套餐菜品数据,应自动填充参数
     * @param setmealDishes setmealDishes
     */
    void insertBatch(List<SetmealDish> setmealDishes);

    /**
     * 根据套餐id删除套餐菜品数据
     * @param id id
     */
    void deleteBySetmealId(Long id);

    /**
     * 根据菜品id查询套餐菜品数据
     * @param id id
     * @return List<SetmealDish>
     */
    int countByDishId(Long id);
}
