package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryMapper {
    /**
     * 修改分类的泛用接口
     * @param category 修改的分类
     */
    void update(Category category);

    /**
     * 分页查询分类
     * @param categoryPageQueryDTO 查询条件
     * @return 分页查询结果
     */
    Page<Category> pageQuery(CategoryPageQueryDTO categoryPageQueryDTO);

    /**
     * 向表中插入新的分类
     * @param category 新的分类
     */
    void insert(Category category);

    /**
     * 根据id删除分类
     * @param id 分类id
     */
    void deleteById(Long id);

    /**
     * 根据类型查询分类
     * @param type 分类类型
     * @return 查询结果列表
     */
    List<Category> list(Integer type);
}
