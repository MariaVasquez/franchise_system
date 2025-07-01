package com.mariavasquez.franchis.system.infrastructure.cache;

import com.mariavasquez.franchis.system.domain.port.ProductRepository;
import com.mariavasquez.franchis.system.domain.port.RedisCacheService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.IntStream;

@Component
@RequiredArgsConstructor
@Slf4j
public class ProductCacheCleaner {

    private final RedisCacheService redisCacheService;
    private final ProductRepository productRepository;

    public Mono<Void> deleteAllProductPages() {
        int pageSize = 10;

        return productRepository.getTotalProduct()
                .defaultIfEmpty(0L)
                .flatMapMany(total -> {
                    int totalPages = (int) Math.ceil((double) total / pageSize);
                    List<String> keys = IntStream.range(0, totalPages)
                            .mapToObj(page -> String.format("products::page:%d_size:%d", page, pageSize))
                            .toList();
                    return Flux.fromIterable(keys);
                })
                .flatMap(redisCacheService::delete)
                .doOnNext(deleted -> log.info("¿Clave eliminada? {}", deleted))
                .then();
    }
}
