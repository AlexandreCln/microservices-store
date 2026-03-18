package fr.microservices_store.catalog_service.domain;

import java.math.BigDecimal;

// Object returned by the product API
public record Product(String code, String name, String description, String imageUrl, BigDecimal price) {}
