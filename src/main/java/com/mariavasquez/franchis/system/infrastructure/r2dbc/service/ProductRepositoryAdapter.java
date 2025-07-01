package com.mariavasquez.franchis.system.infrastructure.r2dbc.service;

import com.mariavasquez.franchis.system.application.mapper.ProductMapper;
import com.mariavasquez.franchis.system.domain.model.Product;
import com.mariavasquez.franchis.system.domain.port.ProductRepository;
import com.mariavasquez.franchis.system.infrastructure.r2dbc.repository.ProductReactiveRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryAdapter implements ProductRepository {
    private final ProductReactiveRepository productReactiveRepository;
    private final ProductMapper mapper;

    @Override
    public Mono<Product> save(Product franchise) {
        return productReactiveRepository.save(mapper.toEntity(franchise))
                .map(mapper::toDomain);
    }

    @Override
    public Mono<Product> findByName(String name) {
        return productReactiveRepository.findByName(name)
                .map(mapper::toDomain);
    }

    @Override
    public Mono<Product> findById(Long id) {
        return productReactiveRepository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public Mono<Void> delete(Long id) {
        return productReactiveRepository.deleteById(id);
    }

    @Override
    public Flux<Product> findAll(int page, int size) {
        int offset = page * size;
        return productReactiveRepository.findAllByPage(offset, size)
                .map(mapper::toDomain);
    }

    @Override
    public Mono<Long> getTotalProduct() {
        return productReactiveRepository.count();
    }
}
