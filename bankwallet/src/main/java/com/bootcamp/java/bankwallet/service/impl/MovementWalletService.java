package com.bootcamp.java.bankwallet.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.bootcamp.java.bankwallet.domain.MovementWallet;
import com.bootcamp.java.bankwallet.repository.MovementWalletReposiroty;
import com.bootcamp.java.bankwallet.repository.WalletClientReposiroty;
import com.bootcamp.java.bankwallet.service.IMovementWalletService;
import com.bootcamp.java.bankwallet.util.Constants;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class MovementWalletService implements IMovementWalletService {
	@Autowired
	private MovementWalletReposiroty repository;
	@Autowired
	private WalletClientReposiroty clientRepository;

	@Override
	public Flux<MovementWallet> findAll() throws Exception {
		return repository.findAll();
	}

	@Override
	public Mono<MovementWallet> findById(String id) throws Exception {

		return repository.findById(id).switchIfEmpty(Mono.error(new Exception("Movement didn´t find")));
	}
/*
 * Finding all clients and filter by phone number
 * Adding data to movment
 * Testing if a deposit or withdrawal
 * saving data*/
	@Override
	public Mono<MovementWallet> create(String phoneNumber, MovementWallet movement) throws Exception {
		return clientRepository.findAll().filter(t -> t.getPhoneNumber().equalsIgnoreCase(phoneNumber))
				.switchIfEmpty(Mono.error(new Exception("Number Phone doesn't exists")))
				.collectList()
				.flatMap(t -> {
					if (!t.isEmpty()) {
						movement.setWalletClient(t.get(0));
						//t.get(0).setBalance(movement.getAmount());
						return Mono.just(t.get(0));
					} else {
						return Mono.error(new Exception(HttpStatus.BAD_REQUEST.toString()));
					}
				}).flatMap(t ->{
					if (movement.getType().equalsIgnoreCase(Constants.MOVEMENT_TYPE_DEPOSIT)) {
						t.setBalance(movement.getAmount()+ t.getBalance());
						clientRepository.save(t).subscribe();
						return repository.save(movement);
					}else if (movement.getType().equalsIgnoreCase(Constants.MOVEMENT_TYPE_WITHDRAWAL)) {
						if (movement.getAmount()<= t.getBalance()) {
							t.setBalance(t.getBalance()-movement.getAmount());
							clientRepository.save(t).subscribe();
							return repository.save(movement);
						}else {
							return Mono.error(new Exception("Amount is higher than banlance"));
						}
					}else{
						return Mono.error(new Exception(HttpStatus.BAD_REQUEST.toString()));
					}
					
				});
	}

	@Override
	public Mono<MovementWallet> update(String id, MovementWallet movement) throws Exception {

		return repository.findById(id).switchIfEmpty(Mono.error(new Exception("Movement didn´t exists")))
				.flatMap(t -> repository.save(movement));
	}

	@Override
	public Mono<Void> deleteById(String id) throws Exception {
		return repository.deleteById(id);
	}

}
