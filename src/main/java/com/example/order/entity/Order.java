package com.example.order.entity;

import com.example.order.dto.FoodItemsDto;
import com.example.order.dto.Restaurant;
import com.example.order.dto.Userdto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("order")
public class Order {
    private Integer orderId;
    private List<FoodItemsDto> foodItemsDtoList;
    private Restaurant restaurant;
    private Userdto userDto;
}
