package com.Bootcamp.BankMovement.service;

import com.Bootcamp.BankMovement.domain.Movement;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;



public interface IMovementService {
	Flux<Movement> findAll() throws Exception;

	Flux<Movement> findAllByClientProductId(String id) throws Exception;

	Mono<Movement> findById(String id) throws Exception;
	
	Mono<Movement> create(Movement movement) throws Exception;

	Mono<Movement> update(String id, Movement movement) throws Exception;

	Mono<Void> deleteById(String id) throws Exception;
	
	
}
