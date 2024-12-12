package com.hexagonal.microservicio_plazoleta.application.handler;

import com.hexagonal.microservicio_plazoleta.application.dto.OrderRequest;
import com.hexagonal.microservicio_plazoleta.application.dto.OrderResponse;
import com.hexagonal.microservicio_plazoleta.application.mapper.IOrderMapper;
import com.hexagonal.microservicio_plazoleta.domain.api.IOrderServicePort;
import com.hexagonal.microservicio_plazoleta.domain.model.Order;
import com.hexagonal.microservicio_plazoleta.infrastructure.utils.OrderStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderHandlerTest {

    private IOrderServicePort orderServicePort;
    private IOrderMapper orderMapper;
    private OrderHandler orderHandler;

    @BeforeEach
    void setUp() {
        orderServicePort = mock(IOrderServicePort.class);
        orderMapper = mock(IOrderMapper.class);
        orderHandler = new OrderHandler(orderServicePort, orderMapper);
    }

    @Test
    void createOrder_shouldCallServiceWithCorrectArguments() {
        OrderRequest orderRequest = new OrderRequest();

        orderHandler.createOrder(1L, orderRequest);

        verify(orderServicePort, times(1)).createOrder(1L, orderRequest);
    }

    @Test
    void createOrderForUser_shouldCallServiceAndReturnId() {
        OrderRequest orderRequest = new OrderRequest();
        when(orderServicePort.createOrderAndGetId(1L, orderRequest)).thenReturn(100L);

        Long result = orderHandler.createOrderForUser(1L, orderRequest);

        assertNotNull(result);
        assertEquals(100L, result);
        verify(orderServicePort, times(1)).createOrderAndGetId(1L, orderRequest);
    }

    @Test
    void getOrdersByStatus_shouldReturnMappedPage() {
        PageRequest pageRequest = PageRequest.of(0, 10);
        Order order = new Order();
        OrderResponse orderResponse = new OrderResponse();
        Page<Order> orderPage = new PageImpl<>(List.of(order));

        when(orderServicePort.getOrdersByStatus(OrderStatus.PENDING, 0, 10, 1L)).thenReturn(orderPage);
        when(orderMapper.toResponseDto(order)).thenReturn(orderResponse);

        Page<OrderResponse> result = orderHandler.getOrdersByStatus(OrderStatus.PENDING, 0, 10, 1L);

        assertEquals(1, result.getContent().size());
        assertEquals(orderResponse, result.getContent().get(0));
        verify(orderServicePort, times(1)).getOrdersByStatus(OrderStatus.PENDING, 0, 10, 1L);
        verify(orderMapper, times(1)).toResponseDto(order);
    }

    @Test
    void assignOrder_shouldCallServiceWithCorrectArguments() {
        orderHandler.assignOrder(1L, 2L, 3L);

        verify(orderServicePort, times(1)).assignOrder(1L, 2L, 3L);
    }
}
