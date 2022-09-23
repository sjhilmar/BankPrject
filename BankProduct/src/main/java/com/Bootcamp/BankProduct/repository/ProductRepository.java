package com.Bootcamp.BankProduct.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.Bootcamp.BankProduct.domain.Product;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface ProductRepository extends ReactiveMongoRepository<Product, String> {

     Mono<Product> findProductByDescription(String description);
     	Flux<Product>findProductByCodeProduct(String codeProduct);
}
