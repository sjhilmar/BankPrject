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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.Bootcamp.BankMovement.domain.Movement;
import com.Bootcamp.BankMovement.service.IMovementService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/movement")
@Slf4j
public class MovementController {

	@Autowired
	private final IMovementService movementService;

	/**
	 * Get list of movements
	 * 
	 * @return
	 * @throws Exception
	 */
	@GetMapping()
	@Operation(summary = "Get list of movements")
	@ResponseStatus(HttpStatus.OK)
	public Flux<Movement> getAll() throws Exception {

		log.info("getAll" + "OK");
		log.debug(HttpStatus.OK.toString());
		return movementService.findAll();
	}

	/**
	 * Get Movement by id
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@GetMapping(path = { "{id}" })
	@Operation(summary = "Get list of movements")
	@ResponseStatus(HttpStatus.OK)
	public Flux<Movement> findAllByClientProductId(@PathVariable("id") String id) throws Exception {

		log.info("getAll" + "OK");
		log.debug(HttpStatus.OK.toString());
		return movementService.findAllByClientProductId(id);
	}

	/**
	 * Get Movement by id
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@GetMapping(path = { "{id}" }, produces = { "application/json" })
	public ResponseEntity<Mono<Movement>> getById(@PathVariable("id") String id) throws Exception {
		Mono<Movement> response = movementService.findById(id);
		log.info("getById" + "OK");
		log.debug(id);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * Create Movement
	 * 
	 * @param movementModel
	 * @return
	 * @throws Exception
	 */
	@PostMapping(path = "/create")
	@ResponseStatus(HttpStatus.CREATED)
	public Mono<Movement> create(@RequestBody Movement movement) throws Exception {

		log.info("create" + "OK");
		log.debug(movement.toString());
		return movementService.create(movement);

	}

	/**
	 * Update Movement by id
	 * 
	 * @param id
	 * @param movementModel
	 * @throws Exception
	 */
	@PutMapping(path = { "{id}" }, produces = { "application/json" })
	public Mono<Movement> update(@PathVariable("id") String id, @RequestBody Movement movement) throws Exception {

		log.info("update" + "OK");
		log.debug(id + "/" + movement.toString());
		return movementService.update(id, movement);
	}

	/**
	 * Delete Movement by id
	 * 
	 * @param id
	 * @throws Exception
	 * @author aangulom
	 */
	@DeleteMapping({ "{id}" })
	public void deleteById(@PathVariable("id") String id) throws Exception {
		log.info("deleteById" + "OK");
		log.debug(id);
		movementService.deleteById(id).subscribe();
		
	}

}
