package com.Bootcamp.BankClient.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Bootcamp.BankClient.domain.ClientProfile;
import com.Bootcamp.BankClient.repository.ClientProfileRepository;
import com.Bootcamp.BankClient.service.IClientProfileService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service

public class ClientProfileService implements IClientProfileService {
	@Autowired
	private ClientProfileRepository clientProfileRepository;

	@Override
	public Flux<ClientProfile> findAll() throws Exception {

		return clientProfileRepository.findAll();

	}

	@Override
	public Mono<ClientProfile> findById(String id) throws Exception {

		return clientProfileRepository.findById(id).switchIfEmpty(Mono.error(RuntimeException::new));

	}

	@Override
	public Mono<ClientProfile> create(ClientProfile clientProfile) throws Exception {
		return clientProfileRepository.save(clientProfile);

	}

	@Override
	public Mono<ClientProfile> update(String id, ClientProfile clientProfile) throws Exception {

		return clientProfileRepository.findById(id).switchIfEmpty(Mono.error(RuntimeException::new))
				.flatMap(t -> clientProfileRepository.save(clientProfile));

	}

	@Override
	public Mono<Void> deleteById(String id) throws Exception {
		return clientProfileRepository.findById(id).switchIfEmpty(Mono.error(RuntimeException::new))
				.flatMap(t -> clientProfileRepository.deleteById(id));
	}
}
