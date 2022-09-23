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

import com.Bootcamp.BankClient.domain.ClientProfile;
import com.Bootcamp.BankClient.service.IClientProfileService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v3/clientProfile")
@Slf4j
public class ClientProfileController {
@Autowired
	private IClientProfileService clientProfileService;

	
	@GetMapping()
	@Operation(summary = "Get list of ClientProfiles")
	@ResponseStatus(HttpStatus.OK)
	public Flux<ClientProfile> getAll() throws Exception {

		log.info("getAll" + "OK");
		log.debug(HttpStatus.OK.toString());
		return clientProfileService.findAll();
	}

	
	@GetMapping(path = { "{id}" }, produces = { "application/json" })
	public ResponseEntity<Mono<ClientProfile>> getById(@PathVariable("id") String id) throws Exception {
		Mono<ClientProfile> response = clientProfileService.findById(id);
		log.info("getById" + "OK");
		log.debug(id);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping(path = "/create")
	@ResponseStatus(HttpStatus.CREATED)
	public Mono<ClientProfile> create(@RequestBody ClientProfile clientProfile) throws Exception {
		log.info("Create OK");
		log.debug(clientProfile.toString());
		return clientProfileService.create(clientProfile);

	}


	@PutMapping(path = { "{id}" }, produces = { "application/json" })
	public Mono<ClientProfile> update(@PathVariable("id") String id, @RequestBody ClientProfile ClientProfile)
			throws Exception {

		log.info("update" + "OK");
		log.debug(id + "/" + ClientProfile.toString());
		return clientProfileService.update(id, ClientProfile);
	}

	@DeleteMapping({ "{id}" })
	public void deleteById(@PathVariable("id") String id) throws Exception {
		clientProfileService.deleteById(id).subscribe();
		log.info("deleteById" + "OK");
		log.debug(id);
	}

}
