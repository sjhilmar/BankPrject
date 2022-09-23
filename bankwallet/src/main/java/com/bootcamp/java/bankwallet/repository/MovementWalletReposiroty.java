package com.bootcamp.java.bankwallet.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.bootcamp.java.bankwallet.domain.MovementWallet;

public interface MovementWalletReposiroty extends  ReactiveMongoRepository<MovementWallet, String>  {

}
