package com.Bootcamp.BankMovement.web.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.Bootcamp.BankMovement.domain.ClientProduct;
import com.Bootcamp.BankMovement.service.IClientProductService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/clientProduct")
@Slf4j
public class ClientProductController {

	@Autowired
	private final IClientProductService clientProductService;

	@GetMapping()
	@Operation(summary = "Get list of clientProducts")
	public Flux<ClientProduct> getAll() throws Exception {
		log.info("getAll" + "OK");
		log.debug(HttpStatus.OK.toString());
		return clientProductService.findAll();
	}

	@GetMapping(path = { "{id}" }, produces = { "application/json" })
	public ResponseEntity<Mono<ClientProduct>> getById(@PathVariable("id") String id) throws Exception {
		Mono<ClientProduct> response = clientProductService.findById(id);
		log.info("getById" + "OK");
		log.debug(id);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping(path = "/create")
	public Mono<ClientProduct> create(@RequestBody ClientProduct clientProductModel) throws Exception {
			log.info("create" + "OK");
			log.debug(clientProductModel.toString());
			return clientProductService.create(clientProductModel);
	}

	@PutMapping(path = { "{id}" }, produces = { "application/json" })
	public Mono<ClientProduct> update(@PathVariable("id") String id, @RequestBody ClientProduct clientProductModel)
			throws Exception {
		log.info("update" + "OK");
		log.debug(id + "/" + clientProductModel.toString());
		return clientProductService.update(id, clientProductModel);
	}

	@DeleteMapping({ "{id}" })
	public void deleteById(@PathVariable("id") String id) throws Exception {
		clientProductService.deleteById(id).subscribe();
		log.info("deleteById" + "OK");
		log.debug(id);
	}

}
