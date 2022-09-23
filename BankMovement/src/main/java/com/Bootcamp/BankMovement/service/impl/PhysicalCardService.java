package com.Bootcamp.BankMovement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Bootcamp.BankMovement.domain.PhysicalCard;
import com.Bootcamp.BankMovement.repository.PhysicalCardRepository;
import com.Bootcamp.BankMovement.service.IPhysicalCardService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class PhysicalCardService implements IPhysicalCardService {
	@Autowired
	private final PhysicalCardRepository repository;

	@Override
	public Flux<PhysicalCard> findAll() throws Exception {
		return repository.findAll();
	}

	@Override
	public Mono<PhysicalCard> findById(String id) throws Exception {
		return repository.findById(id).switchIfEmpty(Mono.error(() -> new Throwable("Data not found")));
	}

	@Override
	public Mono<PhysicalCard> create(PhysicalCard physicalCard) throws Exception {
		return repository.findByNumberCard(physicalCard.getNumberCard())
				.switchIfEmpty(Mono.just(physicalCard)
						.flatMap(t -> repository.save(t)));
	}

	@Override
	public Mono<PhysicalCard> update(String id, PhysicalCard physicalCardt) throws Exception {
		return repository.findById(id)
				.switchIfEmpty(Mono.error(new Exception("Physical Card doesn't exists")))
				.flatMap(t -> repository.save(physicalCardt) );
	}

	@Override
	public Mono<Void> deleteById(String id) throws Exception {
		return repository.deleteById(id);
		
	}

	@Override
	public Mono<PhysicalCard> findByNumberCard(String numberCard) throws Exception {
		return repository.findByNumberCard(numberCard).switchIfEmpty(Mono.error(() -> new Throwable("Number card doesn't exists")));
	}

}
