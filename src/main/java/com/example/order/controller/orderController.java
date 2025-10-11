package com.example.order.controller;

import com.example.order.dto.OrderDto;
import com.example.order.dto.OrderDtoFE;
import com.example.order.dto.Restaurant;
import com.example.order.entity.Sequence;
import com.example.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@CrossOrigin
public class orderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/saveOrder")
    public ResponseEntity<OrderDto> saveOrder(@RequestBody OrderDtoFE orderDtoFE)
    {
        OrderDto ordersavedDto=orderService.saveOrderInDb(orderDtoFE);
       return new ResponseEntity<>(ordersavedDto, HttpStatus.OK);
    }






}
