package com.Bootcamp.BankMovement.service.impl;

import java.math.BigDecimal;

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
//    private final ClientProductMapper clientProductMapper;

	@Override
	public Flux<ClientProduct> findAll() throws Exception {
		return clientProductRepository.findAll();

	}

	@Override
	public Mono<ClientProduct> findById(String id) throws Exception {
//        Optional<ClientProduct> clientProduct = clientProductRepository.findById(id);
//        if(clientProduct.isPresent()) return clientProductMapper.clientProductToClientProductModel(clientProduct.get());
//        else throw new Exception("No se encontraron datos");
		return clientProductRepository.findById(id)
				.switchIfEmpty(Mono.error(() -> new Throwable("No se encontraron datos")));
	}

	@Override
	public Mono<ClientProduct> create(ClientProduct clientProductModel) throws Exception {
		//boolean action = false;
		String message = "";
		try {
			//ClientProduct clientProduct = new ClientProduct();
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
						if (t.isEmpty()) {
							
							return clientProductRepository.save(clientProductModel);
						} else {
							
							return Mono.error(new Throwable("Customer already has this product"));
						}

					});
					// List<ClientProduct> clientProducts=
					// clientProductRepository.findAllByClientId(clientProductModel.getClientId());
//                    Optional<ClientProduct> optional = clientProducts.stream()
//                            .filter(x -> clientProductModel.getCodeProduct().equals(x.getCodeProduct()))
//                            .findFirst();
//                    if(optional.isEmpty()){
//                        clientProduct = clientProductRepository.save(clientProductMapper.clientProductModelToClientProduct(clientProductModel));
//                        action=true;
//                    }
//                    else{
//                        message="No se puedo registrar, el cliente ya cuenta con este producto.";
//                    }
				}
			}
			if (clientProductModel.getClientType().equalsIgnoreCase(Constants.CLIENT_TYPE_VIP)) {

				if (clientProductModel.getCodeProduct().equalsIgnoreCase(Constants.CLIENT_TYPE_PERSON)) {
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

//                    List<ClientProduct>  clientProducts= clientProductRepository.findAllByClientId(clientProductModel.getClientId());
//                    Optional<ClientProduct> optional = clientProducts.stream()
//                            .filter(x -> clientProductModel.getCodeProduct().equals(x.getCodeProduct()))
//                            .findFirst();
//
//
//                    if(optional.isEmpty()){
//                        clientProduct = clientProductRepository.save(clientProductMapper.clientProductModelToClientProduct(clientProductModel));
//                        action=true;
//                    }
//                    else{
//                        message="No se puedo registrar, el cliente ya cuenta con este producto.";
//                    }
				} else if (clientProductModel.getCodeProduct().equalsIgnoreCase(Constants.CODE_PRODUCT_CREDIT_CARD)) {

					Flux<ClientProduct> clientProducts = clientProductRepository.findAll()
							.filter(t -> t.getClientId().equalsIgnoreCase(clientProductModel.getClientId()))
							.filter(t -> t.getCodeProduct().equalsIgnoreCase(clientProductModel.getCodeProduct()))
							.switchIfEmpty(Mono.error(new Throwable("Customer has not Saving Account")))
							.filter(t -> t.getCodeProduct().equalsIgnoreCase(Constants.CODE_PRODUCT_SAVINGS_ACCOUNT))
							.switchIfEmpty(Mono.error(new Throwable("Customer already has this product")))
							.filter(t -> t.getBalance() != BigDecimal.ZERO);

					return clientProducts.collectList().flatMap(t -> {
						if (t.isEmpty()) {
							return Mono
									.error(new Throwable("Customer doesn't has Saving Account and he doesn't balance"));
						}
						return clientProductRepository.save(clientProductModel);
					});

//                    List<ClientProduct>  clientProducts= clientProductRepository.findAllByClientId(clientProductModel.getClientId());
//                    Optional<ClientProduct> optional = clientProducts.stream()
//                            .filter(x -> clientProductModel.getCodeProduct().equals(x.getCodeProduct()))
//                            .findFirst();

//                    if(optional.isEmpty()){
//                        Optional<ClientProduct> optionalClientProd = clientProducts.stream()
//                                .filter(x -> x.getCodeProduct().equals("0001"))
//                                .findFirst();
//
//
//                        if(!optionalClientProd.isEmpty()) {
//
//                            ClientProduct info=   optionalClientProd.get();
//                            if(info.getBalance().compareTo(BigDecimal.ZERO) == 0){
//                                message="No se puedo registrar, el cliente no cuenta con Cuenta de ahorros no tiene saldo.";
//                            }
//                            else{
//                                clientProduct = clientProductRepository.save(clientProductMapper.clientProductModelToClientProduct(clientProductModel));
//                                action=true;
//                            }
//
//                        }
//                        else{
//                            message="No se puedo registrar, el cliente no cuenta con Cuenta de ahorros.";
//                        }
//
//
//                    }else{
//                        message="No se puedo registrar, el cliente ya cuenta con este producto.";
//                    }
					// --------------
				} else {
					message = "No se puedo registrar, el cliente no puede tener el producto.";
				}

			}
			if (clientProductModel.getClientType().equals(Constants.CLIENT_TYPE_BUSINESS)) {

				return clientProductRepository.save(clientProductModel);
//                return clientProductModel ;

			}
			if (clientProductModel.getClientType().equalsIgnoreCase(Constants.CLIENT_TYPE_PYME)) {
				if (clientProductModel.getCodeProduct().equalsIgnoreCase(Constants.CODE_PRODUCT_CURRENT_ACCOUNT)) {
					Flux<ClientProduct> clientProducts = clientProductRepository.findAll()
							.filter(t ->t.getClientId().equalsIgnoreCase(clientProductModel.getClientId()) )
							.filter(t ->t.getCodeProduct().equalsIgnoreCase(clientProductModel.getCodeProduct()));
					return clientProducts.collectList().flatMap(t ->{
						if(t.isEmpty()) {
							return clientProductRepository.save(clientProductModel);
						}else {
							
							return Mono.error(new Throwable("Customer already has this product"));
						}
					});
//					List<ClientProduct> clientProducts = clientProductRepository
//							.findAllByClientId(clientProductModel.getClientId());
//					Optional<ClientProduct> optional = clientProducts.stream()
//							.filter(x -> clientProductModel.getCodeProduct().equals(x.getCodeProduct())).findFirst();
//					if (optional.isEmpty()) {
//						clientProduct = clientProductRepository
//								.save(clientProductMapper.clientProductModelToClientProduct(clientProductModel));
//						action = true;
//					} else {
//						message = "No se puedo registrar, el cliente ya cuenta con este producto.";
//					}
				} else {
					message = "No se puedo registrar, el cliente no puede tener el producto.";
				}
			}

//			if (!action)
//				throw new Exception(message == "" ? "No se puedo registrar" : message);
////                return clientProductMapper.clientProductToClientProductModel(clientProduct);

		} catch (Exception e) {
			throw new Exception(message == "" ? "No se puedo registrar" : message);
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
