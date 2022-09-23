package com.Bootcamp.BankMovement.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.Bootcamp.BankMovement.domain.ClientProduct;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ClientProductRepository extends ReactiveMongoRepository<ClientProduct, String> {
    Flux<ClientProduct> findAllByClientId(String clientId);
    Flux<ClientProduct> findAllByCodeProduct(String codeProduct);
    Mono<ClientProduct> findByAccountNumber(String accountNumber);

}
