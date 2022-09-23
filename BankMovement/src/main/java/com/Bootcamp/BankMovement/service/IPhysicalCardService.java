package com.Bootcamp.BankMovement.service;

import com.Bootcamp.BankMovement.domain.PhysicalCard;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IPhysicalCardService  {
	Flux<PhysicalCard> findAll() throws Exception;

	Mono<PhysicalCard> findById(String id) throws Exception;

	Mono<PhysicalCard> create(PhysicalCard physicalCard) throws Exception;

	Mono<PhysicalCard> update(String id, PhysicalCard physicalCardt) throws Exception;

	Mono<Void> deleteById(String id) throws Exception;
	
	Mono<PhysicalCard> findByNumberCard(String numberCard) throws Exception;
}
