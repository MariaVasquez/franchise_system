package com.mariavasquez.franchis.system.infrastructure.web.dto.response;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseDto {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Long franchiseId;
    private Long branchId;
    private int stock;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
