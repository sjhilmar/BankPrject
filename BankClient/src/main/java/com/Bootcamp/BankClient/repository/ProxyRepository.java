package com.Bootcamp.BankClient.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.Bootcamp.BankClient.domain.Proxy;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProxyRepository extends ReactiveMongoRepository<Proxy, String> {

    Flux<Proxy> findProxyByFullName(String fullName);

    Mono<Proxy> findProxyByClientId(String clientId);

}
