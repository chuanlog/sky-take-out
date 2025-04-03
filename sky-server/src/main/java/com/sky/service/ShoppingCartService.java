package com.sky.service;

import com.sky.dto.ShoppingCartDTO;
import com.sky.entity.ShoppingCart;

import java.util.List;

/**
 * 购物车业务相关接口
 */
public interface ShoppingCartService {
    /**
     * 填加购物车
     * @param shoppingCartDTO 待添加内容
     */
    void add(ShoppingCartDTO shoppingCartDTO);

    /**
     * 查看购物车列表
     *
     * @return 购物车列表
     */
    List<ShoppingCart> showShopingCart();

    /**
     * 清空购物车
     */
    void clean();

    /**
     * 删除购物车中一个商品
     * @param shoppingCartDTO 待删除的商品
     */
    void sub(ShoppingCartDTO shoppingCartDTO);
}
