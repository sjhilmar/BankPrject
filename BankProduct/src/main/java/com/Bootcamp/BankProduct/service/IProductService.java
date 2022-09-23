package com.Bootcamp.BankProduct.service;

import com.Bootcamp.BankProduct.domain.Product;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface IProductService {
	
	Flux<Product> findAll() throws Exception;

    Mono<Product> findById(String id) throws Exception;

    Mono<Product> findProductByDescription(String description) throws Exception;
    
    Flux<Product> findProductByCodeProduct(String codeProduct) throws Exception;

    Mono<Product> create(Product product) throws Exception;

    Mono<Product> update(String id, Product product) throws Exception;

    Mono<Void> deleteById(String id) throws Exception;
}
