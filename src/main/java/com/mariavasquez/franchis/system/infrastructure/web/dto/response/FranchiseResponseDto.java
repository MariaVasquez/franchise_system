package com.mariavasquez.franchis.system.infrastructure.web.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class FranchiseResponseDto {
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
