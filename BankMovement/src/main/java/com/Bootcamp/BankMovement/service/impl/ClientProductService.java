package com.Bootcamp.BankMovement.service.impl;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Bootcamp.BankMovement.domain.ClientProduct;
import com.Bootcamp.BankMovement.repository.ClientProductRepository;
import com.Bootcamp.BankMovement.service.IClientProductService;
import com.Bootcamp.BankMovement.util.Constants;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ClientProductService implements IClientProductService {

	@Autowired
	private final ClientProductRepository clientProductRepository;

	@Override
	public Flux<ClientProduct> findAll() throws Exception {
		return clientProductRepository.findAll();

	}

	@Override
	public Mono<ClientProduct> findById(String id) throws Exception {
		return clientProductRepository.findById(id)
				.switchIfEmpty(Mono.error(() -> new Throwable("No se encontraron datos")));
	}

	@Override
	public Mono<ClientProduct> create(ClientProduct clientProductModel) throws Exception {

	
	
		try {

			if (clientProductModel.getClientType().equalsIgnoreCase(Constants.CLIENT_TYPE_PERSON)) {
				// Validamos que no tenga el tipo de producto repetido para registrar
				if (clientProductModel.getCodeProduct().equals(Constants.CODE_PRODUCT_SAVINGS_ACCOUNT)
						|| clientProductModel.getCodeProduct().equalsIgnoreCase(Constants.CODE_PRODUCT_CURRENT_ACCOUNT)
						|| clientProductModel.getCodeProduct()
								.equalsIgnoreCase(Constants.CODE_PRODUCT_FIXED_TERM_SAVING_ACCOUNT)) {

					Flux<ClientProduct> clientProducts = clientProductRepository.findAll()
							.filter(t -> t.getClientId().equalsIgnoreCase(clientProductModel.getClientId()))
							.filter(t -> t.getCodeProduct().equalsIgnoreCase(clientProductModel.getCodeProduct()));
					return clientProducts.collectList().flatMap(t -> {

						if (t.isEmpty())
							return clientProductRepository.save(clientProductModel);
						else
							return Mono.error(new Throwable("Customer already has this product"));
					});

				} else {
					return clientProductRepository.save(clientProductModel);
				}
			}

			// Cliente persona vip
			if (clientProductModel.getClientType().equalsIgnoreCase(Constants.CLIENT_TYPE_VIP)) {

				if (clientProductModel.getCodeProduct().equalsIgnoreCase(Constants.CODE_PRODUCT_SAVINGS_ACCOUNT)) {
					Flux<ClientProduct> clientProducts = clientProductRepository.findAll()
							.filter(t -> t.getClientId().equalsIgnoreCase(clientProductModel.getClientId()))
							.filter(t -> t.getCodeProduct().equalsIgnoreCase(clientProductModel.getCodeProduct()));

					return clientProducts.collectList().flatMap(t -> {
						if (t.isEmpty()) {

							return clientProductRepository.save(clientProductModel);
						} else {

							return Mono.error(new Throwable("Customer already has this product"));
						}

					});

				} else if (clientProductModel.getCodeProduct().equalsIgnoreCase(Constants.CODE_PRODUCT_CREDIT_CARD)) {

					Flux<ClientProduct> clientProducts = clientProductRepository.findAll()
							.filter(t -> t.getClientId().equalsIgnoreCase(clientProductModel.getClientId()))
							.filter(t -> t.getCodeProduct().equalsIgnoreCase(Constants.CODE_PRODUCT_SAVINGS_ACCOUNT));

					return clientProducts.collectList().flatMap(t -> {
						if (!t.isEmpty()) {
							for (ClientProduct element : t) {
								if (element.getBalance() > 0) {
									return clientProductRepository.save(clientProductModel);
								} else {
									return Mono.error(new Throwable("Customer has Saving Account but he doesn't balance"));
								}
								
							}
								return Mono.just(clientProductModel);
						} else {
							return Mono.error(new Throwable("Customer doesn't has Saving Account"));
						}

					});
				}

			}
			//cliente empresa
			if (clientProductModel.getClientType().equals(Constants.CLIENT_TYPE_BUSINESS)) {
				
				return Mono.just(clientProductModel)
						.filter(t -> !Objects.equals(t.getCodeProduct(), Constants.CODE_PRODUCT_SAVINGS_ACCOUNT))
						.filter(t -> !Objects.equals(t.getCodeProduct(), Constants.CODE_PRODUCT_FIXED_TERM_SAVING_ACCOUNT))
						.switchIfEmpty(Mono.error(new Throwable("Business Client can't have  a saving account or fixed term saving account")))
						.flatMap(clientProductRepository::save);

			}
			//cliente empresa vip
			if (clientProductModel.getClientType().equalsIgnoreCase(Constants.CLIENT_TYPE_PYME)) {
								
					if (clientProductModel.getCodeProduct().equals(Constants.CODE_PRODUCT_CURRENT_ACCOUNT_WITHOUT_MAINTENANCE_COMMISSION)) {
						Flux<ClientProduct> value = clientProductRepository.findAllByClientId(clientProductModel.getClientId())
								.filter(t ->t.getCodeProduct().equalsIgnoreCase(Constants.CODE_PRODUCT_CREDIT_CARD))
								.switchIfEmpty(Mono.error(new Throwable("Business client should have a credit card")));
						
						return value.collectList()
								.flatMap(t -> clientProductRepository.save(clientProductModel));	
					}else {
						return Mono.just(clientProductModel)
								.filter(t -> !Objects.equals(t.getCodeProduct(), Constants.CODE_PRODUCT_SAVINGS_ACCOUNT))
								.filter(t -> !Objects.equals(t.getCodeProduct(), Constants.CODE_PRODUCT_FIXED_TERM_SAVING_ACCOUNT))
								.switchIfEmpty(Mono.error(new Throwable("Business Client can't have  a saving account or fixed term saving account")))
								.flatMap(clientProductRepository::save);
					}
						
			}

		} catch (Exception e) {
			throw new Exception(e);
		}
		return Mono.just(clientProductModel);

	}

	@Override
	public Mono<ClientProduct> update(String id, ClientProduct clientProduct) throws Exception {
		return clientProductRepository.findById(id).switchIfEmpty(Mono.error(() -> new Throwable("Data not Found")))
				.flatMap(t -> clientProductRepository.save(clientProduct));

	}

	@Override
	public Mono<Void> deleteById(String id) throws Exception {
		return clientProductRepository.deleteById(id);
	}

	@Override
	public Flux<ClientProduct> findAllByClientId(String clientId) throws Exception {
		return clientProductRepository.findAllByClientId(clientId)
				.switchIfEmpty(Flux.error(() -> new Throwable("Data not Found")));
	}

	@Override
	public Flux<ClientProduct> findAllByCodeProduct(String codeProduct) throws Exception {
		return clientProductRepository.findAllByCodeProduct(codeProduct)
				.switchIfEmpty(Flux.error(() -> new Throwable("Data not Found")));
	}

}
