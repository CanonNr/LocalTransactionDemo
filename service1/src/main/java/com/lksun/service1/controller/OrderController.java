package com.lksun.service1.controller;

import com.lksun.service1.common.ResponseTemplate;
import com.lksun.service1.dao.OrdersDao;
import com.lksun.service1.entity.Order;
import com.lksun.service1.service.impl.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrdersDao orderDao;

    @Autowired
    OrderServiceImpl orderServiceImpl;

    @GetMapping(value = "")
    @Transactional(rollbackFor = Exception.class)
    public ResponseTemplate order (Order requestOrder){
        // 订单id
        Integer order_id = requestOrder.getId();
        // 查询到相应的订单信息
        Order order = orderDao.selectByPrimaryKey(order_id);
        if (order == null){
            return ResponseTemplate.failed("没有找到该订单");
        }

        try{
            // 业务处理修改状态
            orderServiceImpl.UpdateStatus(order);
            // 写入事件表 貌似不用也可以 先省略
            // TODO
        }catch (Exception e){
            return ResponseTemplate.failed(e.getMessage());
        }

        // 发送到MQ
        return ResponseTemplate.success(null);
    }
}
