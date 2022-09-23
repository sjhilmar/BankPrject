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

import com.bootcamp.java.bankwallet.domain.MovementWallet;
import com.bootcamp.java.bankwallet.service.IMovementWalletService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/walletMovement")
@Slf4j
public class MovementWalletController {
	
	@Autowired
	private IMovementWalletService service;
	
	@GetMapping("/findAll")
	@Operation(summary = "find all Wallet movement")
	@ResponseStatus(HttpStatus.OK)
	public Flux<MovementWallet>findAll() throws Exception{
		log.info("findALL");
		log.debug(HttpStatus.OK.toString());
		return service.findAll();
	}
	
	@GetMapping(path = { "{id}" }, produces = { "application/json" })
	@Operation(summary =  "find wallet movement by id")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Mono<MovementWallet>> findById(@PathVariable("id") String id) throws Exception {
		Mono<MovementWallet> client= service.findById(id);
		HttpStatus status=(client!=null)?HttpStatus.OK:HttpStatus.NOT_FOUND;
		log.info("getById OK");
		log.debug(status.toString());
		return new ResponseEntity<>(client,status);

	}
	@PostMapping(path = { "create/{phoneNumber}" }, produces = {"application/json"})
	@Operation(summary = "create a new wallet movement")
	@ResponseStatus(HttpStatus.CREATED)
	public Mono<MovementWallet> create(@PathVariable("phoneNumber") String phoneNumber, @RequestBody MovementWallet client) throws Exception {
		log.info("Create ok");
		log.debug(client.toString());
		return service.create(phoneNumber,client);

	}
	@PutMapping(path = { "update/{id}" }, produces = { "application/json" })
	@Operation(summary = "update a wallet movement")
	@ResponseStatus(HttpStatus.OK)
	public Mono<MovementWallet> update(@PathVariable("id") String id, @RequestBody MovementWallet client) throws Exception {
		log.info("update OK");
		log.debug(id + "/" + client.toString());
		return service.update(id, client);

	}
	@DeleteMapping({ "delete/{id}" })
	@Operation(summary = "delete a wallet movement")
	@ResponseStatus(HttpStatus.OK)
	public void deleteById(@PathVariable("id") String id) throws Exception {
		log.info("deleteById OK");
		log.debug(id);
		service.deleteById(id).subscribe();
		
	}
}
