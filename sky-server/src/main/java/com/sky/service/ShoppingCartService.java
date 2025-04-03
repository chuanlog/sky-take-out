package com.sky.service;

import com.sky.dto.ShoppingCartDTO;

/**
 * 购物车业务相关接口
 */
public interface ShoppingCartService {
    /**
     * 填加购物车
     * @param shoppingCartDTO 待添加内容
     */
    void add(ShoppingCartDTO shoppingCartDTO);
}
