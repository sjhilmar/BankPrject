package com.Bootcamp.BankMovement.service;

import com.Bootcamp.BankMovement.domain.ClientProduct;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IClientProductService {

	Flux<ClientProduct> findAll() throws Exception;

	Mono<ClientProduct> findById(String id) throws Exception;

	Mono<ClientProduct> create(ClientProduct clientProduc) throws Exception;

	Mono<ClientProduct> update(String id, ClientProduct clientProduct) throws Exception;

	Mono<Void> deleteById(String id) throws Exception;
	
	Flux<ClientProduct> findAllByClientId(String clientId) throws Exception;
	
    Flux<ClientProduct> findAllByCodeProduct(String codeProduct) throws Exception;
    
    Mono<ClientProduct> findByAccountNumber(String accountNumber) throws Exception;
    
}
