package com.sky.service;

import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.result.PageResult;

import java.util.List;

/**
 * 分类管理相关业务
 */
public interface CategoryService {
    /**
     * 修改分类
     * @param categoryDTO 修改分类的参数
     */
    void update(CategoryDTO categoryDTO);

    /**
     * 分类分页查询
     * @param categoryPageQueryDTO 查询参数
     * @return 分页查询结果
     */
    PageResult pageQuery(CategoryPageQueryDTO categoryPageQueryDTO);

    /**
     * 增加新的分类
     * @param categoryDTO 新增的分类
     */
    void save(CategoryDTO categoryDTO);

    /**
     * 根据id删除分类
     * @param id 分类id
     */
    void deleteById(Long id);

    /**
     * 设置启售停售
     * @param status 启售停售状态
     * @param id 分类id
     */
    void startOrStop(Integer status, Long id);

    /**
     * 根据类型查询分类
     * @param type
     * @return
     */
    List<Category> list(Integer type);
}
