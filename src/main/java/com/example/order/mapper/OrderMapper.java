package com.example.order.mapper;

import com.example.order.dto.OrderDto;
import com.example.order.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderMapper {
    OrderMapper INSTANCE= Mappers.getMapper(OrderMapper.class);

    public Order mapOrderDToToOrder(OrderDto orderDto);
    public OrderDto mapOrderToOrderDto(Order order);
}
