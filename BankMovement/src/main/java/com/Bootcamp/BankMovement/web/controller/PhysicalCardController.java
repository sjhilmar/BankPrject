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

import com.Bootcamp.BankMovement.domain.PhysicalCard;
import com.Bootcamp.BankMovement.service.IPhysicalCardService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/physicalCard")
@Slf4j
public class PhysicalCardController {
	@Autowired
	private final IPhysicalCardService service;

	@GetMapping("all/")
	@Operation(summary = "Get list of physical Card")
	public Flux<PhysicalCard> getAll() throws Exception {
		log.info("getAll" + "OK");
		log.debug(HttpStatus.OK.toString());
		return service.findAll();
	}

	@GetMapping(path = { "byId/{id}" }, produces = { "application/json" })
	public ResponseEntity<Mono<PhysicalCard>> getById(@PathVariable("id") String id) throws Exception {
		Mono<PhysicalCard> response = service.findById(id);
		log.info("getById" + "OK");
		log.debug(id);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping(path = "/create")
	public Mono<PhysicalCard> create(@RequestBody PhysicalCard physicalCard) throws Exception {
			log.info("create" + "OK");
			log.debug(physicalCard.toString());
			return service.create(physicalCard);
	}

	@PutMapping(path = { "update/{id}" }, produces = { "application/json" })
	public Mono<PhysicalCard> update(@PathVariable("id") String id, @RequestBody PhysicalCard physicalCard)
			throws Exception {
		log.info("update" + "OK");
		log.debug(id + "/" + physicalCard.toString());
		return service.update(id, physicalCard);
	}

	
	@DeleteMapping({ "delete/{id}" })
	public void deleteById(@PathVariable("id") String id) throws Exception {
		service.deleteById(id).subscribe();
		log.info("deleteById" + "OK");
		log.debug(id);
	}
	
	@Operation(summary = "get a physical card by number card ")
	@GetMapping(path = { "findByNumberCard/{numberCard}" }, produces = { "application/json" })
	public ResponseEntity<Mono<PhysicalCard>> findByNumberCard(@PathVariable("numberCard") String numberCard) throws Exception {
		Mono<PhysicalCard> response = service.findByNumberCard(numberCard);
		log.info("getById" + "OK");
		log.debug(numberCard);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
