package com.hexagonal.microservicio_plazoleta.domain.api;

import com.hexagonal.microservicio_plazoleta.application.dto.OwnerResponse;
import com.hexagonal.microservicio_plazoleta.infrastructure.adapters.feign.UsersClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UsersClientServiceTest {

    private UsersClient usersClient;
    private UsersClientService usersClientService;

    @BeforeEach
    void setUp() {
        usersClient = mock(UsersClient.class);
        usersClientService = new UsersClientService(usersClient);
    }

    @Test
    void shouldValidateOwnerSuccessfully() {
        Long ownerId = 1L;
        OwnerResponse expectedResponse = new OwnerResponse();
        expectedResponse.setId(ownerId);
        expectedResponse.setName("John Doe");

        when(usersClient.validateOwner(ownerId)).thenReturn(expectedResponse);

        OwnerResponse actualResponse = usersClientService.validateOwner(ownerId);

        assertNotNull(actualResponse);
        assertEquals(expectedResponse.getId(), actualResponse.getId());
        assertEquals(expectedResponse.getName(), actualResponse.getName());
        verify(usersClient, times(1)).validateOwner(ownerId);
    }

    @Test
    void shouldThrowExceptionIfClientFails() {
        Long ownerId = 1L;

        when(usersClient.validateOwner(ownerId)).thenThrow(new RuntimeException("Client error"));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            usersClientService.validateOwner(ownerId);
        });

        assertEquals("Client error", exception.getMessage());
        verify(usersClient, times(1)).validateOwner(ownerId);
    }
}
