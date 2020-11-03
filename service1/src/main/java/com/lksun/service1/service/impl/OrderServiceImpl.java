package com.lksun.service1.service.impl;

import com.lksun.service1.dao.OrdersDao;
import com.lksun.service1.entity.Order;
import com.lksun.service1.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    private static final int  STATUS_OFF = 0;

    private static final int  STATUS_ON = 1;

    @Autowired
    OrdersDao ordersDao;

    @Override
    public Order UpdateStatus(Order order) throws Exception {
        if (order.getStatus() != STATUS_OFF){
            throw new Exception("不存在可操作订单...");
        }else{
            order.setStatus(STATUS_ON);
            ordersDao.updateByPrimaryKey(order);
            return order;
        }
    }

    @Override
    public void pushOrderToMq(Order order) {

    }
}
