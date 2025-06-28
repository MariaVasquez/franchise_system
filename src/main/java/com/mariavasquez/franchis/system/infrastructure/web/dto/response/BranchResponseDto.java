package com.mariavasquez.franchis.system.infrastructure.web.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class BranchResponseDto {
    private Long id;
    private String name;
    private String address;
    private Long franchiseId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
