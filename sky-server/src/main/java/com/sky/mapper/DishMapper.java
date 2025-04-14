package com.sky.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.github.pagehelper.Page;
import com.sky.annotation.AutoFill;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.enumeration.OperationType;
import com.sky.vo.DishVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface DishMapper {

    /**
     * 根据分类id查询菜品数量
     * @param categoryId
     * @return
     */
    @DS("slave")
    @Select("select count(id) from dish where category_id = #{categoryId}")
    Integer countByCategoryId(Long categoryId);

    /**
     * 插入新菜品
     * @param dish
     */
    @DS("master")
    @AutoFill(value = OperationType.INSERT)
    void insert(Dish dish);

    /**
     * 分页查询菜品
     * @param dishPageQueryDTO
     * @return
     */
    @DS("slave")
    Page<DishVO> pageQuery(DishPageQueryDTO dishPageQueryDTO);

    /**
     * 根据id查询菜品
     * @param id 菜品id
     * @return 查询结果
     */
    @DS("slave")
    @Select("select * from dish where id = #{id}")
    Dish getById(Long id);

    /**
     * 删除菜品
     * @param id 菜品id
     */
    @DS("master")
    @Delete("delete from dish where id = #{id}")
    void deleteById(Long id);

    /**
     * 批量删除菜品
     * @param ids 菜品id集合
     */
    @DS("master")
    void deleteByIds(List<Long> ids);

    /**
     * 更新菜品信息
     * @param dish 新的菜品信息
     */
    @DS("master")
    @AutoFill(value = OperationType.UPDATE)
    void update(Dish dish);

    /**
     * 根据分类id查询菜品
     * @param categoryId 分类id
     * @return 查询结果
     */
    @DS("slave")
    List<Dish> getByCategoryId(Long categoryId);

    /**
     * 根据条件查询菜品数据
     * @param dish 查询条件
     * @return 菜品数据
     */
    @DS("slave")
    List<Dish> list(Dish dish);

    /**
     * 根据条件统计菜品数量
     * @param map
     * @return
     */
    @DS("slave")
    Integer countByMap(Map map);


}
