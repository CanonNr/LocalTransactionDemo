package com.lksun.service1.dto;

import lombok.Data;

@Data
public class OrderMqMessage {

    public Integer orderId;

    public Integer goodId;

    public static OrderMqMessage OrderMqMessage(Integer orderId,Integer goodId){
        OrderMqMessage orderMqMessage = new OrderMqMessage();
        orderMqMessage.setOrderId(orderId);
        orderMqMessage.setGoodId(goodId);

        return orderMqMessage;
    }


}
