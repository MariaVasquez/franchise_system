package com.mariavasquez.franchis.system.infrastructure.r2dbc.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table("branch_products")
public class BranchProductEntity {
    @Id
    private Long id;
    private Long branchId;
    private Long productId;
    private Integer stock;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
