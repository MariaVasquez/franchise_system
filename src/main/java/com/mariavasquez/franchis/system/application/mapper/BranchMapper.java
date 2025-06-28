package com.mariavasquez.franchis.system.application.mapper;

import com.mariavasquez.franchis.system.domain.model.Branch;
import com.mariavasquez.franchis.system.infrastructure.r2dbc.entity.BranchEntity;
import com.mariavasquez.franchis.system.infrastructure.web.dto.request.BranchRequestDto;
import com.mariavasquez.franchis.system.infrastructure.web.dto.response.BranchResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BranchMapper {
    Branch toModel(BranchRequestDto dto);
    BranchResponseDto toDto(Branch model);
    BranchEntity toEntity(Branch model);
    Branch toDomain(BranchEntity entity);
}
