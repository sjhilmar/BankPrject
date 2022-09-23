package com.Bootcamp.BankMovement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Bootcamp.BankMovement.domain.ClientProduct;
import com.Bootcamp.BankMovement.domain.Movement;
import com.Bootcamp.BankMovement.repository.ClientProductRepository;
import com.Bootcamp.BankMovement.repository.MovementRepository;
import com.Bootcamp.BankMovement.service.IMovementService;
import com.Bootcamp.BankMovement.util.Constants;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class MovementService implements IMovementService {
	@Autowired
	private final MovementRepository repository;
	// private final MovementMapper mapper;
	// private final ClientProductMapper clientProductMapper;
	@Autowired
	private final ClientProductRepository clientProductRepository;

	@Override
	public Flux<Movement> findAll() throws Exception {
		return repository.findAll();
	}

	@Override
	public Flux<Movement> findAllByClientProductId(String id) throws Exception {
		return repository.findAllByClientProductId(id);

	}

	@Override
	public Mono<Movement> findById(String id) throws Exception {
		return repository.findById(id).switchIfEmpty(Mono.error(() -> new Throwable("Data don't found")));

	}

	@Override
	public Mono<Movement> create(Movement movement) throws Exception {

		return clientProductRepository.findById(movement.getClientProductId())
				.switchIfEmpty(Mono.error(new Exception("This product doesn't exists for this client")))
				.flatMap(t -> {
					if (movement.getType().equalsIgnoreCase(Constants.MOVEMENT_TYPE_DEPOSIT)) {

						t.setBalance(movement.getAmount() + t.getBalance());
						clientProductRepository.save(t).subscribe();
						return repository.save(movement);

					} else if (movement.getType().equalsIgnoreCase(Constants.MOVEMENT_TYPE_WITHDRAWAL)) {
						if (movement.getAmount()<= t.getBalance()) {

							if (movement.getReason().equalsIgnoreCase(Constants.MOVEMENT_TYPE_REASON_TRANSFER) || movement.getReason().equalsIgnoreCase(Constants.MOVEMENT_TYPE_REASON_THIRDS_TRANSFER)) {
								//movimiento principal
								t.setBalance(t.getBalance()-movement.getAmount());
								clientProductRepository.save(t).subscribe();
								
								//movimiento secunadrio
								Mono<ClientProduct>otherCp= clientProductRepository.findByAccountNumber(movement.getDestinedNumberAccount())
										.switchIfEmpty(Mono.error(new Throwable("Number Account not found")));
								
								otherCp.flatMap(x -> {
									x.setBalance(movement.getAmount() + x.getBalance());
									 return clientProductRepository.save(x);
								}).subscribe();
																
								//movimento principal
								return repository.save(movement);
																
							} else {
								t.setBalance(t.getBalance()-movement.getAmount());
								clientProductRepository.save(t).subscribe();
								repository.save(movement);
							}
						} else {
							return Mono.error(new Exception("There is not enough balance"));
						}
					} else {
						Mono.error(new Exception("The value of Column type is incorret"));
					}

					return repository.save(movement);
					
				});


	}

	@Override
	public Mono<Movement> update(String id, Movement movement) throws Exception {

		return repository.findById(id).switchIfEmpty(Mono.error(() -> new Throwable("No existe movimiento")))
				.flatMap(t -> repository.save(movement));

	}

	@Override
	public Mono<Void> deleteById(String id) throws Exception {
		return repository.deleteById(id);

	}

}
