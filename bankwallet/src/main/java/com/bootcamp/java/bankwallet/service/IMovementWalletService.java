package com.bootcamp.java.bankwallet.service;

import com.bootcamp.java.bankwallet.domain.MovementWallet;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IMovementWalletService {
	Flux<MovementWallet> findAll() throws Exception;

	Mono<MovementWallet> findById(String id) throws Exception;
			
	Mono<MovementWallet> create(String phoneNumber,MovementWallet movement) throws Exception;

	Mono<MovementWallet> update(String id, MovementWallet movement) throws Exception;

	Mono<Void> deleteById(String id) throws Exception;
}
