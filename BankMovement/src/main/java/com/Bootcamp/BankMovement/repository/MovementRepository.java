package com.Bootcamp.BankMovement.repository;


import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.Bootcamp.BankMovement.domain.Movement;

import reactor.core.publisher.Flux;

public interface MovementRepository extends ReactiveMongoRepository<Movement, String> {
    Flux<Movement> findAllByClientProductId(String clientId);
}
