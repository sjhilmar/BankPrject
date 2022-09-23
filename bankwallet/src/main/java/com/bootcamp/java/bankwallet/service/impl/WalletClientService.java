package com.bootcamp.java.bankwallet.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootcamp.java.bankwallet.domain.WalletClient;
import com.bootcamp.java.bankwallet.repository.WalletClientReposiroty;
import com.bootcamp.java.bankwallet.service.IWalletClientService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@Service
@RequiredArgsConstructor
public class WalletClientService implements IWalletClientService {
	@Autowired
	private WalletClientReposiroty repository;

	@Override
	public Flux<WalletClient> findAll() throws Exception {
		return repository.findAll();
	}

	@Override
	public Mono<WalletClient> findById(String id) throws Exception {

		return repository.findById(id).switchIfEmpty(Mono.error(() ->  new Throwable("Data not exists")));
	}

	@Override
	public Mono<WalletClient> create(WalletClient client) throws Exception {
		Flux<WalletClient> valida= repository.findAll()
				.filter(t ->t.getDocumentNumber().equalsIgnoreCase(client.getDocumentNumber()))
				.filter(t ->t.getPhoneNumber().equalsIgnoreCase(client.getPhoneNumber()));
		
		return valida.collectList()
				.flatMap(t -> {
				if (t.isEmpty()) {
					return repository.save(client);
				}else {
					return Mono.error(new Throwable("There are data already exists like Document number or Phone Number created"));
				}
			});
						
	}

	@Override
	public Mono<WalletClient> update(String id, WalletClient client) throws Exception {
		return repository.findById(id)
				.switchIfEmpty(Mono.error(new Exception("Client not exist")))
				.flatMap(t ->repository.save(client));
	}

	@Override
	public Mono<Void> deleteById(String id) throws Exception {

		return repository.deleteById(id);
		
	}

	@Override
	public Mono<WalletClient> findByPhoneNumber(String phoneNumber) throws Exception {

		return repository.findByPhoneNumber(phoneNumber)
				.switchIfEmpty(Mono.error(new Exception("Phone number doesn't exists")));
	}

	@Override
	public Mono<WalletClient> findByDocumentNumber(String documentNumber) throws Exception {
		return repository.findByDocumentNumber(documentNumber)
				.switchIfEmpty(Mono.error(new Exception("Document number doesn't exists")));
	}

}
