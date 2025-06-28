package com.mariavasquez.franchis.system.domain.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class Branch {
    private Long id;
    private String name;
    private String address;
    private Long franchiseId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
