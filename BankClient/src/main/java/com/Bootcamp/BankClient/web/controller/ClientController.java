package com.Bootcamp.BankClient.web.controller;

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

import com.Bootcamp.BankClient.domain.Client;
import com.Bootcamp.BankClient.service.IClientService;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController

@RequestMapping("/v3/client")
@Slf4j
public class ClientController {
	@Autowired
	private IClientService clientService;

	@GetMapping()
	@ResponseStatus(HttpStatus.OK)
	public Flux<Client> getAll() throws Exception {
		log.info("getAll OK");
		log.debug(HttpStatus.OK.toString());
		return clientService.findAll();
		
//		Flux<Client> response = clientService.findAll();
//		log.info("getAll" + "OK");
//		log.debug(response.toString());
//		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@GetMapping(path = { "{id}" }, produces = { "application/json" })
	public ResponseEntity<Mono<Client>> findById(@PathVariable("id") String id) throws Exception {
		Mono<Client> client= clientService.findById(id);
		HttpStatus status=(client!=null)?HttpStatus.OK:HttpStatus.NOT_FOUND;
		log.info("getById OK");
		log.debug(status.toString());
		return new ResponseEntity<Mono<Client>>(client,status);
//		Mono<Client> response = clientService.findById(id);
//		log.info("getById" + "OK");
//		log.debug(id);
//		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping(path = { "/find/{document}" }, produces = { "application/json" })
	@ResponseStatus(HttpStatus.OK)
	public Flux<Client> getByDocumentNumber(@PathVariable("document") String document) throws Exception {
		log.info("getByDocumentNumber" + "OK");
		log.debug(document);
		return clientService.findByDocumentNumber(document);
		
//		Flux<Client> response = clientService.findByDocumentNumber(document);
//		log.info("getByNameAndDescription" + "OK");
//		log.debug(response.toString());
//		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/create")
	@ResponseStatus(HttpStatus.CREATED)
	public Mono<Client> create(@RequestBody Client client) throws Exception {
		log.info("Create ok");
		log.debug(client.toString());
		return clientService.create(client);
//		Mono<Client> response = clientService.create(client);
//		log.info("create" + "OK");
//		log.debug(client.toString());
//		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PutMapping(path = { "update/{id}" }, produces = { "application/json" })
	@ResponseStatus(HttpStatus.OK)
	public Mono<Client> update(@PathVariable("id") String id, @RequestBody Client client) throws Exception {
		log.info("update OK");
		log.debug(id + "/" + client.toString());
		return clientService.update(id, client);
//		Mono<Client> response = clientService.update(id, client);
//		log.info("update" + "OK");
//		log.debug(id + "/" + client.toString());
//		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}

	@DeleteMapping({ "delete/{id}" })
	@ResponseStatus(HttpStatus.OK)
	public void deleteById(@PathVariable("id") String id) throws Exception {
		clientService.deleteById(id).subscribe();
		log.info("deleteById OK");
		log.debug(id);
	}
}
