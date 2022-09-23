package com.bootcamp.java.bankwallet.service;

import com.bootcamp.java.bankwallet.domain.WalletClient;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IWalletClientService {

	Flux<WalletClient> findAll() throws Exception;

	Mono<WalletClient> findById(String id) throws Exception;
			
	Mono<WalletClient> create(WalletClient client) throws Exception;

	Mono<WalletClient> update(String id, WalletClient client) throws Exception;

	Mono<Void> deleteById(String id) throws Exception;
	
	Mono<WalletClient> findByPhoneNumber(String phoneNumber) throws Exception;
	
	Mono<WalletClient> findByDocumentNumber(String documentNumber)  throws Exception;;
	
}
