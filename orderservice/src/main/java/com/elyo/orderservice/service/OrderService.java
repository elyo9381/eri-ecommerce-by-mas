package com.elyo.orderservice.service;


import com.elyo.orderservice.dto.OrderDto;
import com.elyo.orderservice.entity.OrderEntity;
import org.springframework.data.domain.jaxb.SpringDataJaxb;

public interface OrderService {

    OrderDto createOrder(OrderDto orderDto);
    OrderDto getOrderByOrderId(String orderId);
    Iterable<OrderEntity> getOrdersByUsesId(String userId);
}
