package com.sky.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.github.pagehelper.Page;
import com.sky.annotation.AutoFill;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Setmeal;
import com.sky.enumeration.OperationType;
import com.sky.vo.DishItemVO;
import com.sky.vo.SetmealVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface SetmealMapper {

    /**
     * 根据分类id查询套餐的数量
     * @param id
     * @return
     */
    @DS("slave")
    @Select("select count(id) from setmeal where category_id = #{categoryId}")
    Integer countByCategoryId(Long id);

    /**
     * 插入一个套餐数据
     * @param setmeal
     */
    @DS("master")
    @AutoFill(value = OperationType.INSERT)
    void save(Setmeal setmeal);

    /**
     * 更新一个套餐数据
     * @param setmeal 要更新的套餐数据
     */
    @DS("master")
    @AutoFill(value = OperationType.UPDATE)
    void update(Setmeal setmeal);

    /**
     * 根据id查询套餐数据
     * @param id 套餐id
     * @return 套餐数据
     */
    @DS("slave")
    Setmeal getById(Long id);

    /**
     * 根据id删除套餐数据
     * @param id 套餐id
     */
    @DS("master")
    void deleteById(Long id);

    /**
     * 分页查询套餐数据
     * @param setmealPageQueryDTO
     * @return
     */
    @DS("slave")
    Page<SetmealVO> pageQuery(SetmealPageQueryDTO setmealPageQueryDTO);


    /**
     * 动态条件查询套餐
     * @param setmeal
     * @return
     */
    @DS("slave")
    List<Setmeal> list(Setmeal setmeal);

    /**
     * 根据套餐id查询菜品选项
     * @param setmealId
     * @return
     */
    @DS("slave")
    @Select("select sd.name, sd.copies, d.image, d.description " +
            "from setmeal_dish sd left join dish d on sd.dish_id = d.id " +
            "where sd.setmeal_id = #{setmealId}")
    List<DishItemVO> getDishItemBySetmealId(Long setmealId);

    /**
     * 根据条件统计套餐数量
     * @param map
     * @return
     */
    @DS("slave")
    Integer countByMap(Map map);

}
