package com.mariavasquez.franchis.system.infrastructure.r2dbc.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table("product")
public class ProductEntity {
    @Id
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;

    @Column("franchise_id")
    private UUID franchiseId;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
