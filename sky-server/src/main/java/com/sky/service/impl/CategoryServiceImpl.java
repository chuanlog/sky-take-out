package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.constant.StatusConstant;
import com.sky.context.BaseContext;
import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.mapper.CategoryMapper;
import com.sky.result.PageResult;
import com.sky.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * 修改分类
     * @param categoryDTO
     */
    @Override
    public void update(CategoryDTO categoryDTO) {
        Category category = Category.builder()
                .id(categoryDTO.getId())
                .type(categoryDTO.getType())
                .name(categoryDTO.getName())
                .sort(categoryDTO.getSort())
                .build();
        categoryMapper.update(category);
    }

    /**
     * 分页查询
     * @param categoryPageQueryDTO
     * @return
     */
    @Override
    public PageResult pageQuery(CategoryPageQueryDTO categoryPageQueryDTO) {
        PageHelper.startPage(categoryPageQueryDTO.getPage(), categoryPageQueryDTO.getPageSize());
        Page<Category> page = categoryMapper.pageQuery(categoryPageQueryDTO);
        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    public void save(CategoryDTO categoryDTO) {
        Category category = Category.builder()
                .type(categoryDTO.getType())//分类类型
                .name(categoryDTO.getName())//分类名称
                .sort(categoryDTO.getSort())//分类顺序
                .status(StatusConstant.ENABLE)//状态
                .createTime(LocalDateTime.now())//创建时间
                .updateTime(LocalDateTime.now())//修改时间
                .createUser(BaseContext.getCurrentId())//创建人
                .build();
        categoryMapper.insert(category);

    }
}
