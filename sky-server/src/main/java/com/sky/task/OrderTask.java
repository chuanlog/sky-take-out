package com.sky.task;

import com.sky.entity.Orders;
import com.sky.mapper.OrderMapper;
import com.sky.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Component
@Slf4j
public class OrderTask {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderMapper orderMapper;

    /**
     * 定时任务，用于取消超时订单
     */
    @Scheduled(cron = "0 0/1 * * * ?")// 每分钟执行一次
    public void cancelOrder(){
        log.info("定时取消订单");
        long now = System.currentTimeMillis();

        // 1. 获取当前时间前的所有订单ID（即已到期）(到期时间score<当前时间)
        Set<Object> expiredOrderIds = redisTemplate.opsForZSet().rangeByScore("order:delay", 0, now);

        // 2. 根据订单ID查询订单数据，判断订单状态，如果状态为待支付，则取消订单
        if (expiredOrderIds != null && expiredOrderIds.size() > 0){
            for (Object expiredOrderId : expiredOrderIds) {
                Long orderId = Long.parseLong(expiredOrderId.toString());
                Orders orders = orderMapper.getById(orderId);
                if (orders != null && Objects.equals(orders.getStatus(), Orders.PENDING_PAYMENT)){
                    // 调用订单服务取消订单,因为原因为支付超时，所以采用用户取消
                    orderService.userCancelById(orderId);
                }
                // 4. 处理完后从 ZSet 移除
                redisTemplate.opsForZSet().remove("order:delay", orderId);
            }
        }
    }

    /**
     * 处理一直处于派送中状态的订单
     */
    @Scheduled(cron = "0 0 1 * * ?")  //每天凌晨一点触发一次
    //@Scheduled(cron = "0/5 * * * * ?")
    public void processDeliveryOrder() {
        log.info("定时处理处于派送中的订单：{}", LocalDateTime.now());

        //下单时间小于等于当前时间往前推一个小时
        LocalDateTime time = LocalDateTime.now().plusMinutes(-60);

        //查出上一个工作日一直处于派送中的订单
        List<Orders> ordersList = orderMapper.getByStatusAndOrderTimeLT(Orders.DELIVERY_IN_PROGRESS, time);
        //判断一下获得的集合
        if (ordersList != null && ordersList.size() > 0) {
            for (Orders orders : ordersList) {
                //修改订单状态
                orders.setStatus(Orders.COMPLETED);
                orderMapper.update(orders);
            }
        }
    }


}
