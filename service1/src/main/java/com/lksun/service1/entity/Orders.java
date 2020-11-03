package com.lksun.service1.entity;

import java.io.Serializable;
import lombok.Data;

@Data
public class Orders implements Serializable {
    private Integer id;

    private Integer orderId;

    private Integer status;

    private static final long serialVersionUID = 1L;
}