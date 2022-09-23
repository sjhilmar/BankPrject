package com.Bootcamp.BankClient.service;

import com.Bootcamp.BankClient.domain.Proxy;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IProxyService {

    Flux<Proxy> findAll() throws Exception;

    Mono<Proxy> findById(String id) throws Exception;

    Flux<Proxy> findByFullName(String fullName) throws Exception;

    Mono<Proxy> findByClientId(String clientId) throws Exception;

    Mono<Proxy> create(Proxy proxy) throws Exception;

    Mono<Proxy> update(String id, Proxy proxy) throws Exception;

   Mono<Void> deleteById(String id) throws Exception;



}
