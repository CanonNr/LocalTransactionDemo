package com.lksun.service1.service;

import com.lksun.service1.entity.Order;

public interface OrderService {
    public Order UpdateStatus(Order order) throws Exception;
    public void pushOrderToMq(Order order);
}
