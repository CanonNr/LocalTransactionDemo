package com.lksun.service1.controller;

import com.alibaba.fastjson.JSON;
import com.lksun.service1.common.ResponseTemplate;
import com.lksun.service1.dao.OrdersDao;
import com.lksun.service1.dto.OrderMqMessage;
import com.lksun.service1.entity.Order;
import com.lksun.service1.service.impl.OrderServiceImpl;
import com.lksun.service1.service.impl.RabbitMQServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.xml.crypto.Data;
import java.util.Date;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrdersDao orderDao;

    @Autowired
    OrderServiceImpl orderServiceImpl;

    @Autowired
    RabbitMQServiceImpl rabbitMQService;

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
        OrderMqMessage orderMqMessage = OrderMqMessage.OrderMqMessage(order.getId(), order.getGoodId());
        String msg = JSON.toJSON(orderMqMessage).toString();
        System.out.println(msg);
        rabbitMQService.sendMsg(msg);
        // 发送到MQ
        return ResponseTemplate.success(null);
    }

    @GetMapping("test")
    public void test(){
        String msg = "hello lksun - "+ System.currentTimeMillis();
        rabbitMQService.sendMsg(msg);
        System.out.println(msg);
    }
}
