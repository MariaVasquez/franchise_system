package com.mariavasquez.franchis.system.infrastructure.r2dbc.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table("branch")
public class BranchEntity {
    @Id
    private Long id;
    private String name;
    private String address;

    @Column("franchise_id")
    private Long franchiseId;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
