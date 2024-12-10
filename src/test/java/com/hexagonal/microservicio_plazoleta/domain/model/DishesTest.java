package com.hexagonal.microservicio_plazoleta.domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DishesTest {

    @Test
    void shouldCreateDishesUsingNoArgsConstructor() {
        Dishes dishes = new Dishes();

        assertNull(dishes.getId());
        assertNull(dishes.getName());
        assertNull(dishes.getPrice());
        assertNull(dishes.getDescription());
        assertNull(dishes.getImageUrl());
        assertNull(dishes.getCategory());
        assertNull(dishes.getRestaurantId());
        assertTrue(dishes.isActive());
        assertNull(dishes.getOwnerId());
    }

    @Test
    void shouldCreateDishesUsingAllArgsConstructor() {
        Dishes dishes = new Dishes(1L, "Dish Name", 10.00, "Delicious dish",
                "http://example.com/image.jpg", "Category", 2L, 3L);

        assertEquals(1L, dishes.getId());
        assertEquals("Dish Name", dishes.getName());
        assertEquals(10.00, dishes.getPrice());
        assertEquals("Delicious dish", dishes.getDescription());
        assertEquals("http://example.com/image.jpg", dishes.getImageUrl());
        assertEquals("Category", dishes.getCategory());
        assertEquals(2L, dishes.getRestaurantId());
        assertEquals(3L, dishes.getOwnerId());
        assertTrue(dishes.isActive());
    }

    @Test
    void shouldSetAndGetValues() {
        Dishes dishes = new Dishes();

        dishes.setId(1L);
        dishes.setName("Dish Name");
        dishes.setPrice(20.00);
        dishes.setDescription("Updated description");
        dishes.setImageUrl("http://example.com/new-image.jpg");
        dishes.setCategory("Updated Category");
        dishes.setRestaurantId(3L);
        dishes.setOwnerId(4L);
        dishes.setActive(false);

        assertEquals(1L, dishes.getId());
        assertEquals("Dish Name", dishes.getName());
        assertEquals(20.00, dishes.getPrice());
        assertEquals("Updated description", dishes.getDescription());
        assertEquals("http://example.com/new-image.jpg", dishes.getImageUrl());
        assertEquals("Updated Category", dishes.getCategory());
        assertEquals(3L, dishes.getRestaurantId());
        assertEquals(4L, dishes.getOwnerId());
        assertFalse(dishes.isActive());
    }
}
