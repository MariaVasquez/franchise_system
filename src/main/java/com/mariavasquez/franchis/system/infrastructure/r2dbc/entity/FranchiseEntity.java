package com.mariavasquez.franchis.system.infrastructure.r2dbc.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table("franchises")
public class FranchiseEntity {

    @Id
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
