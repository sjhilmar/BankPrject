package com.Bootcamp.BankClient.service;

import com.Bootcamp.BankClient.domain.Client;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IClientService {
	Flux<Client> findAll() throws Exception;

	Mono<Client> findById(String id) throws Exception;
	
	Flux<Client>  findByDocumentNumber(String documentNumber) throws Exception;
		
	Mono<Client> create(Client clientModel) throws Exception;

	Mono<Client> update(String id, Client clientModel) throws Exception;

	Mono<Void> deleteById(String id) throws Exception;
	
}
