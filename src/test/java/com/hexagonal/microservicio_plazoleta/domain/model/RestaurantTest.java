package com.hexagonal.microservicio_plazoleta.domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {

    @Test
    void shouldSetAndGetIdCorrectly() {
        Restaurant restaurant = new Restaurant();
        restaurant.setId(1L);
        assertEquals(1L, restaurant.getId());
    }

    @Test
    void shouldSetAndGetNameCorrectly() {
        Restaurant restaurant = new Restaurant();
        restaurant.setName("Maravilla");
        assertEquals("Maravilla", restaurant.getName());
    }

    @Test
    void shouldSetAndGetNitCorrectly() {
        Restaurant restaurant = new Restaurant();
        restaurant.setNit(123456789);
        assertEquals(123456789, restaurant.getNit());
    }

    @Test
    void shouldSetAndGetAddressCorrectly() {
        Restaurant restaurant = new Restaurant();
        restaurant.setAddress("Calle 23 #23-34");
        assertEquals("Calle 23 #23-34", restaurant.getAddress());
    }

    @Test
    void shouldSetAndGetPhoneCorrectly() {
        Restaurant restaurant = new Restaurant();
        restaurant.setPhone("+573005698325");
        assertEquals("+573005698325", restaurant.getPhone());
    }

    @Test
    void shouldSetAndGetUrlLogoCorrectly() {
        Restaurant restaurant = new Restaurant();
        restaurant.setUrlLogo("https://example.com/logo.png");
        assertEquals("https://example.com/logo.png", restaurant.getUrlLogo());
    }

    @Test
    void shouldSetAndGetOwnerIdCorrectly() {
        Restaurant restaurant = new Restaurant();
        restaurant.setOwnerId(10L);
        assertEquals(10L, restaurant.getOwnerId());
    }
}
