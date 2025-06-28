package com.mariavasquez.franchis.system.application.usecase;

import com.mariavasquez.franchis.system.application.mapper.BranchMapper;
import com.mariavasquez.franchis.system.application.usecase.interfaces.CreateBranchToFranchiseUseCase;
import com.mariavasquez.franchis.system.domain.model.Branch;
import com.mariavasquez.franchis.system.domain.port.BranchRepository;
import com.mariavasquez.franchis.system.domain.port.FranchiseRepository;
import com.mariavasquez.franchis.system.infrastructure.web.dto.request.BranchRequestDto;
import com.mariavasquez.franchis.system.infrastructure.web.dto.response.BranchResponseDto;
import com.mariavasquez.franchis.system.shared.constants.ResponseCode;
import com.mariavasquez.franchis.system.shared.exception.CustomException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreateBranchToFranchiseUseCaseImpl implements CreateBranchToFranchiseUseCase {
    private final FranchiseRepository franchiseRepository;
    private final BranchRepository branchRepository;
    private final BranchMapper branchMapper;

    @Override
    public Mono<BranchResponseDto> execute(BranchRequestDto branchRequestDto) {
        log.info("Init save branch to franchise {}", branchRequestDto.getName());
        Branch branch = branchMapper.toModel(branchRequestDto);
        branch.setCreatedAt(LocalDateTime.now());
        branch.setUpdatedAt(LocalDateTime.now());
        return validateExistsBranch(branch.getName())
                .then(validateNotExistsFranchise(branch.getFranchiseId()))
                .then(branchRepository.save(branch))
                .map(branchMapper::toDto)
                .doOnError(e -> log.error("Error in save branch {}", branch.getName(), e))
                .onErrorMap(e -> (e instanceof CustomException) ? e : new CustomException(ResponseCode.DATABASE_ERROR));
    }

    private Mono<Void> validateNotExistsFranchise(Long id){
        return franchiseRepository.findById(id)
                .hasElement()
                .flatMap(exist ->{
                    if (!exist) return Mono.error(new CustomException(ResponseCode.FRANCHISE_NOT_FOUND));
                    return Mono.empty();
                });
    }

    private Mono<Void> validateExistsBranch(String name){
        return branchRepository.findByName(name)
                .hasElement()
                .flatMap(exist ->{
                    if (exist) return Mono.error(new CustomException(ResponseCode.BRANCH_EXIST, name));
                    return Mono.empty();
                });
    }
}
