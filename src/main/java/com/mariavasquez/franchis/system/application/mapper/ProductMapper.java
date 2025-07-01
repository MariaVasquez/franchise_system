package com.mariavasquez.franchis.system.application.mapper;

import com.mariavasquez.franchis.system.domain.model.Product;
import com.mariavasquez.franchis.system.infrastructure.r2dbc.entity.ProductEntity;
import com.mariavasquez.franchis.system.infrastructure.web.dto.request.ProductRequestDto;
import com.mariavasquez.franchis.system.infrastructure.web.dto.response.ProductResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product toModel(ProductRequestDto dto);
    ProductResponseDto toDto(Product model);
    ProductEntity toEntity(Product model);
    Product toDomain(ProductEntity entity);
    Product fromDto(ProductResponseDto dto);
}
