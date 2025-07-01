package com.mariavasquez.franchis.system.application.usecase;

import com.mariavasquez.franchis.system.application.mapper.FranchiseMapper;
import com.mariavasquez.franchis.system.application.usecase.interfaces.CreateFranchiseUseCase;
import com.mariavasquez.franchis.system.domain.model.Franchise;
import com.mariavasquez.franchis.system.domain.port.FranchiseRepository;
import com.mariavasquez.franchis.system.infrastructure.web.dto.request.FranchiseRequestDto;
import com.mariavasquez.franchis.system.infrastructure.web.dto.response.FranchiseResponseDto;
import com.mariavasquez.franchis.system.shared.constants.ResponseCode;
import com.mariavasquez.franchis.system.shared.exception.CustomException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreateFranchiseUseCaseImpl implements CreateFranchiseUseCase {
    private final FranchiseMapper franchiseMapper;
    private final FranchiseRepository franchiseRepository;

    @Override
    @Transactional
    public Mono<FranchiseResponseDto> execute(FranchiseRequestDto franchiseRequestDto) {
        Franchise franchise = franchiseMapper.toModel(franchiseRequestDto);
        log.info("Init save franchise {}", franchise.getName());
        franchise.setCreatedAt(LocalDateTime.now());
        franchise.setUpdatedAt(LocalDateTime.now());
        return validateNotExists(franchise.getName())
                .then(franchiseRepository.save(franchise))
                .map(franchiseMapper::toDto)
                .doOnError(e -> log.error("Error in save franchise {}", franchise.getName(), e))
                .onErrorMap(e -> (e instanceof CustomException) ? e : new CustomException(ResponseCode.DATABASE_ERROR));
    }

    private Mono<Void> validateNotExists(String name) {
        return franchiseRepository.findByName(name)
                .hasElement()
                .flatMap(exists -> Boolean.TRUE.equals(exists)
                        ? Mono.error(new CustomException(ResponseCode.FRANCHISE_EXIST, name))
                        : Mono.empty()
                );
    }
}
