package com.sky.mapper;

import com.sky.entity.DishFlavor;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DishFlavorMapper {
    /**
     * 批量插入口味数据
     * @param dishFlavorList
     */
     void insertBatch(List<DishFlavor> dishFlavorList);

    /**
     * 根据菜品id删除对应的口味数据
     * @param id 菜品id
     */
    @Delete("delete from dish_flavor where dish_id = #{id}")
    void deleteByDishId(Long id);

    /**
     * 根据菜品id批量删除对应的口味数据
     * @param ids 菜品id集合
     */
    void deleteByDishIds(List<Long> ids);

    /**
     * 查询一个菜品的所有口味
     * @param id 菜品id
     * @return 所有口味
     */
    List<DishFlavor> getByDishId(Long id);

}
