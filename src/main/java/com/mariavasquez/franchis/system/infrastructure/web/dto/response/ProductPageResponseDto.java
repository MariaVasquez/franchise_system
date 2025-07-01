package com.mariavasquez.franchis.system.infrastructure.web.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class ProductPageResponseDto {
    private int page;
    private int size;
    private long total;
    private List<ProductResponseDto> products;
}
