package com.mariavasquez.franchis.system.domain.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class Product {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Long franchiseId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
