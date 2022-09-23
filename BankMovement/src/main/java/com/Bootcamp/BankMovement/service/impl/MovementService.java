package com.Bootcamp.BankMovement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Bootcamp.BankMovement.domain.Movement;
import com.Bootcamp.BankMovement.repository.ClientProductRepository;
import com.Bootcamp.BankMovement.repository.MovementRepository;
import com.Bootcamp.BankMovement.service.IMovementService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class MovementService implements IMovementService {
	@Autowired
	private final MovementRepository repository;
	//private final MovementMapper mapper;
	//private final ClientProductMapper clientProductMapper;
	@Autowired
	private final ClientProductRepository clientProductRepository;

	@Override
	public Flux<Movement> findAll() throws Exception {
		return repository.findAll();
	}

	@Override
	public Flux<Movement> findAllByClientProductId(String id) throws Exception {
		return  repository.findAllByClientProductId(id);
		
	}

	@Override
	public Mono<Movement> findById(String id) throws Exception {
		return repository.findById(id)
				.switchIfEmpty(Mono.error(() -> new Throwable("No se encontraron datos")));
		
	}

	@Override
	public Mono<Movement> create(Movement movement) throws Exception {
		//validar si existe clientProduct
		return repository.save(movement);

	}

	@Override
	public Mono<Movement> update(String id, Movement movement) throws Exception {
		
		return repository.findById(id)
				.switchIfEmpty(Mono.error(() -> new Throwable("No existe movimiento")))
				.flatMap(t -> repository.save(movement));
		
//		Optional<Movement> movementOptional = repository.findById(id);
//		if (movementOptional.isPresent()) {
//			Movement movementToUpdate = movementOptional.get();
//			mapper.update(movementToUpdate, movementModel);
//			repository.save(movementToUpdate);
//		}else {
//			throw new Exception("N se encontraron datos");
//		}
		
	}

	@Override
	public Mono<Void> deleteById(String id) throws Exception {
		return repository.deleteById(id);
		
	}

}
