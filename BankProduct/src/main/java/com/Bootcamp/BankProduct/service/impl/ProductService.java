package com.Bootcamp.BankProduct.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Bootcamp.BankProduct.domain.Product;
import com.Bootcamp.BankProduct.repository.ProductRepository;
import com.Bootcamp.BankProduct.service.IProductService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {

	
	@Autowired
	private ProductRepository repository;

	@Override
	public Flux<Product> findAll() throws Exception {
		return repository.findAll()
				.switchIfEmpty(Mono.error(RuntimeException::new));
	}

	@Override
	public Mono<Product> findById(String id) throws Exception {
		return repository.findById(id)
				.switchIfEmpty(Mono.error(RuntimeException::new));
	}

	@Override
	public Mono<Product> findProductByDescription(String description) throws Exception {
		return repository.findProductByDescription(description)
				.switchIfEmpty(Mono.error(RuntimeException::new));
	}

	@Override
	public Flux<Product> findProductByCodeProduct(String codeProduct) throws Exception {
		return repository.findProductByCodeProduct(codeProduct)
				.switchIfEmpty(Mono.error(RuntimeException::new));
	}

	@Override
	public Mono<Product> create(Product product) throws Exception {
		return repository.findProductByCodeProduct(product.getCodeProduct())
				.take(0).singleOrEmpty()
				.switchIfEmpty(repository.save(product));
	}

	@Override
	public Mono<Product> update(String id, Product product) throws Exception {
		return repository.findById(id)
				.switchIfEmpty(Mono.error(RuntimeException::new))
				.flatMap(t -> repository.save(product));
	}

	@Override
	public Mono<Void> deleteById(String id) throws Exception {
		return repository.deleteById(id);
	}
}
