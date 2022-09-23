package com.Bootcamp.BankMovement.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Bootcamp.BankMovement.domain.ClientProduct;
import com.Bootcamp.BankMovement.service.IClientProductService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/reports")
@Slf4j
public class ReportController {

	@Autowired
	private final IClientProductService clientProductService;
	
	@Operation(summary = "get list of a client products")
	@GetMapping(path = {"clientProduct/findAllbyClientId/{clientId}"}, produces = {"application/json"})
	public Flux<ClientProduct>findAllByClientId(@PathVariable("clientId") String clientId) throws Exception{
		log.info("findAllByClientId");
		log.debug(clientId);
		return clientProductService.findAllByClientId(clientId);
	}
	
	@Operation(summary = "get list of client products by Product code")
	@GetMapping(path = {"clientProduct/findAllbyCodeProduct/{codeProduct}"}, produces = {"application/json"})
	public Flux<ClientProduct>findAllByCodeProduct(@PathVariable("codeProduct") String codeProduct) throws Exception{
		log.info("findAllByCodeProduct");
		log.debug(codeProduct);
		return clientProductService.findAllByCodeProduct(codeProduct);
	}
	
	
}
