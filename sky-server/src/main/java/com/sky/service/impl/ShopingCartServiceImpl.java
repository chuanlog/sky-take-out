package com.sky.service.impl;

import com.sky.context.BaseContext;
import com.sky.dto.ShoppingCartDTO;
import com.sky.entity.Dish;
import com.sky.entity.Setmeal;
import com.sky.entity.ShoppingCart;
import com.sky.mapper.DishMapper;
import com.sky.mapper.SetmealMapper;
import com.sky.mapper.ShoppingCartMapper;
import com.sky.service.ShoppingCartService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ShopingCartServiceImpl implements ShoppingCartService {
    @Autowired
    private ShoppingCartMapper shoppingCartMapper;
    @Autowired
    private DishMapper dishMapper;
    @Autowired
    private SetmealMapper setmealMapper;


    /**
     * 添加购物车
     *
     * @param shoppingCartDTO 待添加内容
     */
    @Override
    public void add(ShoppingCartDTO shoppingCartDTO) {
        // 构造购物车对象并复制属性
        ShoppingCart shoppingCart = new ShoppingCart();
        BeanUtils.copyProperties(shoppingCartDTO, shoppingCart);
        Long userId = BaseContext.getCurrentId();
        shoppingCart.setUserId(userId);

        // 查找当前购物车中是否已存在相同商品
        List<ShoppingCart> existingCartItems = shoppingCartMapper.list(shoppingCart);
        if (existingCartItems != null && !existingCartItems.isEmpty()) {
            // 如果存在，则增加数量并更新
            shoppingCart = existingCartItems.get(0);
            shoppingCart.setNumber(shoppingCart.getNumber() + 1);
            shoppingCartMapper.update(shoppingCart);
            return;
        }

        // 如果不存在相同商品，设置默认数量为1，并初始化其他字段
        shoppingCart.setNumber(1);
        shoppingCart.setCreateTime(LocalDateTime.now());

        // 根据是否是菜品还是套餐，设置购物车商品信息
        setCartItemDetails(shoppingCartDTO, shoppingCart);

        // 插入到购物车
        shoppingCartMapper.insert(shoppingCart);
    }

    /**
     * 获取购物车信息
     *
     * @return 购物车信息
     */
    @Override
    public List<ShoppingCart> showShopingCart() {
        Long userId = BaseContext.getCurrentId();
        return shoppingCartMapper.list(ShoppingCart.builder().userId(userId).build());
    }

    /**
     * 清空购物车
     */
    @Override
    public void clean() {
        //构造查询条件
        Long id = BaseContext.getCurrentId();
        ShoppingCart shoppingCart = ShoppingCart.builder().userId(id).build();
        //删除
        shoppingCartMapper.delete(shoppingCart);
    }

    @Override
    public void sub(ShoppingCartDTO shoppingCartDTO) {
        //构造查询条件
        Long id = BaseContext.getCurrentId();
        ShoppingCart shoppingCart=new ShoppingCart();
        BeanUtils.copyProperties(shoppingCartDTO,shoppingCart);
        shoppingCart.setUserId(id);
        List<ShoppingCart> list=shoppingCartMapper.list(shoppingCart);
        shoppingCart=list.get(0);
        if (shoppingCart.getNumber()==1){
            shoppingCartMapper.delete(shoppingCart);
        }else {
            shoppingCart.setNumber(shoppingCart.getNumber()-1);
            shoppingCartMapper.update(shoppingCart);
        }
    }

    /**
     * 设置购物车商品信息
     *
     * @param shoppingCartDTO 购物车DTO
     * @param shoppingCart    购物车对象
     */
    private void setCartItemDetails(ShoppingCartDTO shoppingCartDTO, ShoppingCart shoppingCart) {
        // 判断是菜品还是套餐，并设置相应的商品信息
        Long dishId = shoppingCartDTO.getDishId();
        if (dishId != null) {
            // 设置菜品信息
            Dish dish = dishMapper.getById(dishId);
            shoppingCart.setName(dish.getName());
            shoppingCart.setImage(dish.getImage());
            shoppingCart.setAmount(dish.getPrice());
        } else {
            // 设置套餐信息
            Setmeal setmeal = setmealMapper.getById(shoppingCartDTO.getSetmealId());
            shoppingCart.setName(setmeal.getName());
            shoppingCart.setImage(setmeal.getImage());
            shoppingCart.setAmount(setmeal.getPrice());
        }
    }

}
