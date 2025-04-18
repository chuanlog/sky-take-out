package com.sky.service;

import com.sky.dto.*;
import com.sky.result.PageResult;
import com.sky.vo.OrderPaymentVO;
import com.sky.vo.OrderStatisticsVO;
import com.sky.vo.OrderSubmitVO;
import com.sky.vo.OrderVO;

public interface OrderService {

    /**
     * 用户下单
     * @param ordersSubmitDTO
     * @return
     */
    OrderSubmitVO submitOrder(OrdersSubmitDTO ordersSubmitDTO);

    /**
     * 订单支付
     * @param ordersPaymentDTO
     * @return
     */
    OrderPaymentVO payment(OrdersPaymentDTO ordersPaymentDTO) throws Exception;

    /**
     * 支付成功，修改订单状态
     * @param outTradeNo
     */
    void paySuccess(String outTradeNo);

    /**
     * 分页查询不同状态历史订单
     * @param page
     * @param pageSize
     * @param status  订单状态 1待付款 2待接单 3已接单 4派送中 5已完成 6已取消
     * @return
     */
    PageResult pageQuery4User(int page, int pageSize, Integer status);

    /**
     * 历史订单详情
     * @param id 订单id
     * @return
     */
    OrderVO details(Long id);

    /**
     * 用户取消订单
     * @param id 订单id
     */
    void userCancelById(Long id);

    /**
     * 再来一单
     * @param id 订单id
     */
    void repetition(Long id);

    /**
     * 条件搜索订单
     * @param orderPageQueryDTO 查询条件
     * @return
     */
    PageResult conditionSearch(OrdersPageQueryDTO orderPageQueryDTO);

    /**
     * 统计订单状态
     * @return OrderStatisticsVO
     */
    OrderStatisticsVO statistics();

    /**
     * 接单
     * @param ordersConfirmDTO 接单参数
     */
    void confirm(OrdersConfirmDTO ordersConfirmDTO);

    /**
     * 拒单
     * @param ordersRejectionDTO
     */
    void rejection(OrdersRejectionDTO ordersRejectionDTO) throws Exception;

    /**
     * 取消订单
     * @param ordersCancelDTO
     */
    void cancel(OrdersCancelDTO ordersCancelDTO) throws Exception;

    /**
     * 派送订单
     * @param id
     */
    void delivery(Long id);

    /**
     * 完成订单
     * @param id
     */
    void complete(Long id);

    /**
     * 订单催单
     * @param id
     */
    void reminder(Long id);
}
