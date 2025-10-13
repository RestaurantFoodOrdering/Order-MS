package com.example.order.service;
import com.example.order.dto.OrderDto;
import com.example.order.dto.OrderDtoFE;
import com.example.order.dto.Userdto;
import com.example.order.entity.Order;
import com.example.order.mapper.OrderMapper;
import com.example.order.repo.OrderRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
public class OrderServiceTest {

    @Mock
    private OrderRepo orderRepo;

    @Mock
    private SequenceGenerator sequenceGenerator;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private OrderService orderService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveOrderInDb_shouldSaveOrderAndReturnOrderDTO() {
        // Arrange
        OrderDtoFE orderDetails = new OrderDtoFE();
        Integer newOrderId = 1;
        Userdto userDTO = new Userdto();
        Order orderToBeSaved = new Order(newOrderId, orderDetails.getFooditemlist(), orderDetails.getRestaurant(), userDTO);
      OrderDto orderDTOExpected = OrderMapper.INSTANCE.mapOrderToOrderDto(orderToBeSaved);

        when(sequenceGenerator.genrateNextOrderId()).thenReturn(newOrderId);
        when(restTemplate.getForObject(anyString(), eq(Userdto.class))).thenReturn(userDTO);
        when(orderRepo.save(orderToBeSaved)).thenReturn(orderToBeSaved);

        // Act
        OrderDto orderDTOActual = orderService.saveOrderInDb(orderDetails);

        // Assert
        verify(sequenceGenerator, times(1)).genrateNextOrderId();
        assertDoesNotThrow(() -> orderService.saveOrderInDb(orderDetails));
    }
}
