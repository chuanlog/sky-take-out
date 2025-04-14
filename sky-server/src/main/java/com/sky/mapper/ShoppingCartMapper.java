package com.sky.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.sky.entity.ShoppingCart;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ShoppingCartMapper {
    /**
     * 条件查询
     * @param shoppingCart 查询条件
     * @return 购物车数据列表
     */
    @DS("slave")
    List<ShoppingCart> list(ShoppingCart shoppingCart);

    /**
     * 更新购物车
     * @param shoppingCart 更新购物车数据
     */
    @DS("master")
    void update(ShoppingCart shoppingCart);

    /**
     * 在购物车中插入一条数据
     * @param shoppingCart 购物车数据
     */
    @DS("master")
    void insert(ShoppingCart shoppingCart);

    /**
     * 按条件删除
     * @param shoppingCart 购物车条件
     */
    @DS("master")
    void delete(ShoppingCart shoppingCart);

    /**
     * 根据用户id删除
     * @param userId 用户id
     */
    @DS("master")
    void deleteByUserId(Long userId);
}
