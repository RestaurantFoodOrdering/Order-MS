package com.example.order.service;

import com.example.order.mapper.OrderMapper;
import com.example.order.dto.OrderDto;
import com.example.order.dto.OrderDtoFE;
import com.example.order.dto.Userdto;
import com.example.order.entity.Order;
import com.example.order.repo.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {
    @Autowired
    private OrderRepo orderRepo;
     @Autowired
    private SequenceGenerator sequenceGenerator;
      @Autowired
     private RestTemplate restTemplate;


    public OrderDto saveOrderInDb(OrderDtoFE orderDtoFE) {
        Integer newOrderId= sequenceGenerator.genrateNextOrderId();
        Userdto userdto=fetchDetailsFromUserId(orderDtoFE.getUserid());
        Order ordertosaved=new Order(newOrderId,orderDtoFE.getFooditemlist(),orderDtoFE.getRestaurant(),userdto);
        orderRepo.save(ordertosaved);
        return OrderMapper.INSTANCE.mapOrderToOrderDto(ordertosaved);
    }

    private Userdto fetchDetailsFromUserId(Integer userid) {
      return restTemplate.getForObject("http://USER-SERVICE/user/fetchById/"+userid, Userdto.class);
    }
}
