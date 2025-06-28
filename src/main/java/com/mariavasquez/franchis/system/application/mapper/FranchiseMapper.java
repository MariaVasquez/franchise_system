package com.mariavasquez.franchis.system.application.mapper;

import com.mariavasquez.franchis.system.domain.model.Franchise;
import com.mariavasquez.franchis.system.infrastructure.r2dbc.entity.FranchiseEntity;
import com.mariavasquez.franchis.system.infrastructure.web.dto.request.FranchiseRequestDto;
import com.mariavasquez.franchis.system.infrastructure.web.dto.response.FranchiseResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FranchiseMapper {
    Franchise toModel(FranchiseRequestDto dto);
    FranchiseResponseDto toDto(Franchise model);
    FranchiseEntity toEntity(Franchise model);
    Franchise toDomain(FranchiseEntity entity);
}
