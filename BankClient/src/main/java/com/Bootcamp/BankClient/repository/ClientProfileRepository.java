package com.Bootcamp.BankClient.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.Bootcamp.BankClient.domain.ClientProfile;

public interface ClientProfileRepository extends ReactiveMongoRepository<ClientProfile, String> {
	

}
