package com.mariavasquez.franchis.system.domain.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Franchise {
    private Long id;
    private String name;
    private String description;
    private String ownerName;
    private String email;
    private String phone;
    private String address;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
