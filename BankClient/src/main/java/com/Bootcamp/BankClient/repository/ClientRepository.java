package com.Bootcamp.BankClient.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.Bootcamp.BankClient.domain.Client;

import reactor.core.publisher.Flux;

public interface ClientRepository  extends ReactiveMongoRepository<Client, String> {

	Flux<Client> findByDocumentNumber(String documentNumber);

}
