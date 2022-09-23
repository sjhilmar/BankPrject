package com.Bootcamp.BankMovement.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.Bootcamp.BankMovement.domain.PhysicalCard;

import reactor.core.publisher.Mono;

public interface PhysicalCardRepository extends ReactiveMongoRepository<PhysicalCard, String> {

	Mono<PhysicalCard> findByNumberCard(String numberCard);
}
