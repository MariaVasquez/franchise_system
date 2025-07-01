package com.mariavasquez.franchis.system.application.usecase;

import com.mariavasquez.franchis.system.application.usecase.interfaces.GetTopStockedProductsByBranchUseCase;
import com.mariavasquez.franchis.system.domain.port.BranchProductRepository;
import com.mariavasquez.franchis.system.domain.port.FranchiseRepository;
import com.mariavasquez.franchis.system.domain.port.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class GetTopStockedProductsByBranchUseCaseImpl implements GetTopStockedProductsByBranchUseCase {
    private final ProductRepository productRepository;
    private final BranchProductRepository branchProductRepository;
    private final FranchiseRepository franchiseRepository;

    @Override
    public Mono<Void> execute() {
        return null;
    }
}
