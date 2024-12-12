package com.hexagonal.microservicio_plazoleta.infrastructure.output.jpa.adapter;

import com.hexagonal.microservicio_plazoleta.infrastructure.utils.OrderStatus;
import com.hexagonal.microservicio_plazoleta.domain.model.Order;
import com.hexagonal.microservicio_plazoleta.infrastructure.output.jpa.entity.OrderEntity;
import com.hexagonal.microservicio_plazoleta.infrastructure.output.jpa.mapper.OrderEntityMapper;
import com.hexagonal.microservicio_plazoleta.infrastructure.output.jpa.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.*;

import java.util.List;
import java.util.Optional;

import static com.hexagonal.microservicio_plazoleta.constants.ValidationConstants.ORDER_NOT_FOUND;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderJpaAdapterTest {

    private OrderRepository orderRepository;
    private OrderEntityMapper orderEntityMapper;
    private OrderJpaAdapter orderJpaAdapter;

    @BeforeEach
    void setUp() {
        orderRepository = mock(OrderRepository.class);
        orderEntityMapper = mock(OrderEntityMapper.class);
        orderJpaAdapter = new OrderJpaAdapter(orderRepository, orderEntityMapper);
    }

    @Test
    void saveAndReturnId_shouldSaveAndReturnId() {
        Order order = new Order();
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(1L);

        when(orderEntityMapper.toEntity(order)).thenReturn(orderEntity);
        when(orderRepository.save(orderEntity)).thenReturn(orderEntity);

        Long result = orderJpaAdapter.saveAndReturnId(order);

        assertEquals(1L, result);
        verify(orderRepository, times(1)).save(orderEntity);
    }

    @Test
    void existsByUserIdAndStatuses_shouldReturnTrueIfExists() {
        when(orderRepository.existsByUserIdAndStatusIn(1L, List.of(OrderStatus.PENDING))).thenReturn(true);

        boolean result = orderJpaAdapter.existsByUserIdAndStatuses(1L, List.of(OrderStatus.PENDING));

        assertTrue(result);
        verify(orderRepository, times(1)).existsByUserIdAndStatusIn(1L, List.of(OrderStatus.PENDING));
    }

    @Test
    void deleteOrder_shouldDeleteIfExists() {
        when(orderRepository.existsById(1L)).thenReturn(true);

        orderJpaAdapter.deleteOrder(1L);

        verify(orderRepository, times(1)).deleteById(1L);
    }

    @Test
    void deleteOrder_shouldThrowExceptionIfNotExists() {
        when(orderRepository.existsById(1L)).thenReturn(false);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                orderJpaAdapter.deleteOrder(1L)
        );

        assertEquals(ORDER_NOT_FOUND + 1L, exception.getMessage());
        verify(orderRepository, never()).deleteById(anyLong());
    }

    @Test
    void findOrdersByStatus_shouldReturnPagedOrders() {
        PageRequest pageRequest = PageRequest.of(0, 10);
        OrderEntity orderEntity = new OrderEntity();
        Order order = new Order();
        Page<OrderEntity> orderEntityPage = new PageImpl<>(List.of(orderEntity));

        when(orderRepository.findByStatusAndRestaurantId(OrderStatus.PENDING, 1L, pageRequest))
                .thenReturn(orderEntityPage);
        when(orderEntityMapper.toDomainWithBasicFields(orderEntity)).thenReturn(order);

        Page<Order> result = orderJpaAdapter.findOrdersByStatus(OrderStatus.PENDING, 0, 10, 1L);

        assertEquals(1, result.getContent().size());
        assertEquals(order, result.getContent().get(0));
        verify(orderRepository, times(1))
                .findByStatusAndRestaurantId(OrderStatus.PENDING, 1L, pageRequest);
    }

    @Test
    void findById_shouldReturnOrderIfExists() {
        OrderEntity orderEntity = new OrderEntity();
        Order order = new Order();

        when(orderRepository.findById(1L)).thenReturn(Optional.of(orderEntity));
        when(orderEntityMapper.toDomainWithBasicFields(orderEntity)).thenReturn(order);

        Order result = orderJpaAdapter.findById(1L);

        assertNotNull(result);
        assertEquals(order, result);
        verify(orderRepository, times(1)).findById(1L);
    }

    @Test
    void findById_shouldThrowExceptionIfNotExists() {
        when(orderRepository.findById(1L)).thenReturn(Optional.empty());

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                orderJpaAdapter.findById(1L)
        );

        assertEquals(ORDER_NOT_FOUND + 1L, exception.getMessage());
        verify(orderRepository, times(1)).findById(1L);
    }

    @Test
    void save_shouldSaveOrder() {
        Order order = new Order();
        OrderEntity orderEntity = new OrderEntity();

        when(orderEntityMapper.toEntity(order)).thenReturn(orderEntity);

        orderJpaAdapter.save(order);

        verify(orderRepository, times(1)).save(orderEntity);
    }
}
