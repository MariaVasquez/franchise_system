package com.mariavasquez.franchis.system.domain.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class BranchProduct {
    private Long id;
    private Long branchId;
    private Long productId;
    private Integer stock;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
