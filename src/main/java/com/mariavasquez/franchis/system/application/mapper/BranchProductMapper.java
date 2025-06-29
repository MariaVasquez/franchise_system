package com.mariavasquez.franchis.system.application.mapper;

import com.mariavasquez.franchis.system.domain.model.BranchProduct;
import com.mariavasquez.franchis.system.infrastructure.r2dbc.entity.BranchProductEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BranchProductMapper {
    BranchProductEntity toEntity(BranchProduct model);
    BranchProduct toDomain(BranchProductEntity entity);
}
