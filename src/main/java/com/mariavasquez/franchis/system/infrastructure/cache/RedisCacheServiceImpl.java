package com.mariavasquez.franchis.system.infrastructure.cache;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mariavasquez.franchis.system.domain.port.RedisCacheService;
import com.mariavasquez.franchis.system.shared.constants.ResponseCode;
import com.mariavasquez.franchis.system.shared.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class RedisCacheServiceImpl implements RedisCacheService {

    private final ReactiveRedisTemplate<String, String> redisTemplate;
    private final ObjectMapper objectMapper;

    public RedisCacheServiceImpl(@Qualifier("jsonRedisTemplate") ReactiveRedisTemplate<String, String> redisTemplate,
                                 ObjectMapper objectMapper) {
        this.redisTemplate = redisTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public <T> Mono<T> get(String key, TypeReference<T> typeReference) {
        return redisTemplate.opsForValue().get(key)
                .flatMap(json -> Mono.fromCallable(() ->
                        objectMapper.readValue(json, typeReference)
                ).onErrorMap(JsonProcessingException.class, e ->
                        new CustomException(ResponseCode.UNEXPECTED_ERROR, e.getMessage())
                ));
    }

    @Override
    public <T> Mono<Void> set(String key, T value) {
        return Mono.fromCallable(() -> objectMapper.writeValueAsString(value))
                .flatMap(json -> redisTemplate.opsForValue().set(key, json).then())
                .onErrorMap(JsonProcessingException.class, e -> e);
    }

    @Override
    public Mono<Boolean> delete(String key) {
        return redisTemplate.delete(key).map(count -> count > 0);
    }
}
