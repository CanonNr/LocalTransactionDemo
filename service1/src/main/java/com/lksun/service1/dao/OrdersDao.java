package com.lksun.service1.dao;

import com.lksun.service1.entity.Order;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrdersDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);
}