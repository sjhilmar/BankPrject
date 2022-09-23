package com.bootcamp.java.bankwallet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bootcamp.java.bankwallet.domain.WalletClient;
import com.bootcamp.java.bankwallet.service.IWalletClientService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/wallet")
@Slf4j
public class WalletClientController {
	
	@Autowired
	private IWalletClientService service;
	
	@GetMapping("/findAll")
	@Operation(summary = "find all Wallet client")
	@ResponseStatus(HttpStatus.OK)
	public Flux<WalletClient>findAll() throws Exception{
		log.info("findALL");
		log.debug(HttpStatus.OK.toString());
		return service.findAll();
	}
	
	@GetMapping(path = { "{id}" }, produces = { "application/json" })
	@Operation(summary =  "find wallet client by id")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Mono<WalletClient>> findById(@PathVariable("id") String id) throws Exception {
		Mono<WalletClient> client= service.findById(id);
		HttpStatus status=(client!=null)?HttpStatus.OK:HttpStatus.NOT_FOUND;
		log.info("getById OK");
		log.debug(status.toString());
		return new ResponseEntity<Mono<WalletClient>>(client,status);

	}
	@PostMapping("/create")
	@Operation(summary = "create a new wallet client")
	@ResponseStatus(HttpStatus.CREATED)
	public Mono<WalletClient> create(@RequestBody WalletClient client) throws Exception {
		log.info("Create ok");
		log.debug(client.toString());
		return service.create(client);

	}
	@PutMapping(path = { "update/{id}" }, produces = { "application/json" })
	@Operation(summary = "update a wallet client")
	@ResponseStatus(HttpStatus.OK)
	public Mono<WalletClient> update(@PathVariable("id") String id, @RequestBody WalletClient client) throws Exception {
		log.info("update OK");
		log.debug(id + "/" + client.toString());
		return service.update(id, client);

	}
	@DeleteMapping({ "delete/{id}" })
	@Operation(summary = "delete a wallet client")
	@ResponseStatus(HttpStatus.OK)
	public void deleteById(@PathVariable("id") String id) throws Exception {
		log.info("deleteById OK");
		log.debug(id);
		service.deleteById(id).subscribe();
		
	}
	
	@GetMapping(path = {"findByPhone/{numberPhone}"},produces = {"application/json"})
	@Operation(summary = "get wallet client by number phone")
	public ResponseEntity<Mono<WalletClient>> findByNumberPhone(@PathVariable("numberPhone") String numberPhone)throws Exception{
		Mono<WalletClient> client = service.findByPhoneNumber(numberPhone);
		HttpStatus status=(client!=null)?HttpStatus.OK: HttpStatus.NOT_FOUND;
		log.info(status.toString());
		log.debug(status.toString());
		return new ResponseEntity<>(client,status);
	}
	

}
