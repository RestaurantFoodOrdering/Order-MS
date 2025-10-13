package com.example.order.controller;
import com.example.order.dto.OrderDto;
import com.example.order.dto.OrderDtoFE;
import com.example.order.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
public class orderControllerTest {

    @Mock
    private OrderService orderService;

    @InjectMocks
    private orderController orderController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveOrder_shouldReturnCreatedStatus() {
        // Arrange
        OrderDtoFE orderDetails = new OrderDtoFE();
        OrderDto orderSavedInDB = new OrderDto();
        when(orderService.saveOrderInDb(orderDetails)).thenReturn(orderSavedInDB);

        // Act
        ResponseEntity<OrderDto> response = orderController.saveOrder(orderDetails);

        // Assert
        verify(orderService, times(1)).saveOrderInDb(orderDetails);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(orderSavedInDB, response.getBody());
    }
}
