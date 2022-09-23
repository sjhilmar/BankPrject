package com.bootcamp.java.bankwallet.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.bootcamp.java.bankwallet.domain.WalletClient;

import reactor.core.publisher.Mono;

public interface WalletClientReposiroty extends ReactiveMongoRepository<WalletClient, String>{
	Mono<WalletClient> findByPhoneNumber(String phoneNumber);
	Mono<WalletClient> findByDocumentNumber(String documentNumber);
}
