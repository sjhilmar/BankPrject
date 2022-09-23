package com.Bootcamp.BankClient.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Bootcamp.BankClient.domain.Client;
import com.Bootcamp.BankClient.repository.ClientRepository;
import com.Bootcamp.BankClient.service.IClientService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service

public class ClientService implements IClientService {
	@Autowired
	private ClientRepository clientRepository;

	@Override
	public Flux<Client> findAll() throws Exception {

		return clientRepository.findAll().switchIfEmpty(Mono.error(RuntimeException::new));
	}

	@Override
	public Mono<Client> findById(String id) throws Exception {

		return clientRepository.findById(id).switchIfEmpty(Mono.error(RuntimeException::new));

	}

	@Override
	public Mono<Client> create(Client client) throws Exception {
		Flux<Client> valida = clientRepository.findAll()
				.filter(t -> t.getDocumentNumber().equals(client.getDocumentNumber()));
		return valida.collectList().flatMap(list -> {
			if (!list.isEmpty()) {
				return Mono.error(new Throwable("El cliente ya existe"));
			}
			return clientRepository.save(client);
		});

	}

	@Override
	public Mono<Client> update(String id, Client client) throws Exception {
		return clientRepository.findById(id).switchIfEmpty(Mono.error(() -> new Throwable("No existe cliente")))
				.flatMap(t -> clientRepository.save(client));
	}

	@Override
	public Mono<Void> deleteById(String id) throws Exception {
		return clientRepository.deleteById(id);
	}

	@Override
	public Flux<Client> findByDocumentNumber(String documentNumber) throws Exception {

		return clientRepository.findByDocumentNumber(documentNumber);
	}
}
