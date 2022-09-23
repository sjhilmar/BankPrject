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

import com.Bootcamp.BankClient.domain.Proxy;
import com.Bootcamp.BankClient.service.IProxyService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v3/proxy")
@Slf4j
public class ProxyController {

    @Autowired
    private IProxyService proxyService;

    @GetMapping()
    // @Operation(summary = "Get List of Proxies")
    public Flux<Proxy> getAll() throws Exception {
    	log.info("Get All ok");
    	log.debug(HttpStatus.OK.toString());
    	return proxyService.findAll();
    }

    @GetMapping(path = { "{id}" }, produces = { "application/json" })
    public ResponseEntity<Mono<Proxy>> getById(@PathVariable("id") String id) throws Exception{
        Mono<Proxy> response = proxyService.findById(id);
        log.info("getById" + "OK");
        log.debug(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Proxy> create(@RequestBody Proxy proxyModel) throws Exception {
        log.info("create" + "OK");
        log.debug(proxyModel.toString());
        return proxyService.create(proxyModel);
    }

    @PutMapping(path = { "{id}" }, produces = { "application/json" })
    @ResponseStatus(HttpStatus.OK)
    public Mono<Proxy> update(@PathVariable("id") String id, @RequestBody Proxy proxyModel) throws Exception {
        log.info("update" + "OK");
        log.debug(id + "/" + proxyModel.toString());
        return proxyService.update(id, proxyModel);
        
    }

    @DeleteMapping({ "{id}" })
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@PathVariable("id") String id) throws Exception {
        proxyService.deleteById(id).subscribe();
        log.info("deleteById" + "OK");
        log.debug(id);
    }

    @GetMapping(path = { "byName/{fullName}" }, produces = { "application/json" })
    @ResponseStatus(HttpStatus.OK)
    public Flux<Proxy> getByFullName(@PathVariable("fullName") String fullName) throws Exception{
       
        log.info("getByFullName" + "OK");
        log.debug(fullName);
       return proxyService.findByFullName(fullName);
    }

    @GetMapping("byClientId/{clientId}")
    public ResponseEntity<Mono<Proxy>> getProxiesByClientId(String clientId) throws Exception {
        Mono<Proxy> response = proxyService.findByClientId(clientId);
        log.info("getProxiesByClientId" + "OK");
        log.debug(response.toString());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
