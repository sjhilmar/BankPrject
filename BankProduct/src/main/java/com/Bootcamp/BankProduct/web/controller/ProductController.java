package com.Bootcamp.BankProduct.web.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.Bootcamp.BankProduct.domain.Product;
import com.Bootcamp.BankProduct.service.IProductService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/product")
@Slf4j
public class ProductController {
    @Autowired
    private final IProductService productService;

    @GetMapping()
    @Operation(summary = "Get List Of Products")
    @ResponseStatus(HttpStatus.OK)
    public Flux<Product> getAll() throws Exception {
        
        log.info("getAll" + "OK");
        log.debug(HttpStatus.OK.toString());
        return  productService.findAll();
    }

    
    @GetMapping(path = { "{id}" }, produces = { "application/json" })
    @Operation(summary = "Get Product by Id")
    public ResponseEntity<Mono<Product>> getById(@PathVariable("id") String id) throws Exception{
        Mono<Product> response = productService.findById(id);
        log.info("getById" + "OK");
        log.debug(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    
    @GetMapping(path = { "/codeProduct" }, produces = { "application/json" })
    @Operation(summary = "Get product by id")
    @ResponseStatus(HttpStatus.OK)
    public Flux<Product> getByCodeProduct(@RequestParam String codeProduct) throws Exception{
        
        log.info("getByCodeProduct" + "OK");
        log.debug(codeProduct);
        return  productService.findProductByCodeProduct(codeProduct);
    }
    
   
    @GetMapping(path = { "/" }, produces = { "application/json" })
    @Operation(summary = "Get products by description")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Product> getByDescription(@RequestParam String description) throws Exception{
        
        log.info("getByDescription" + "OK");
        log.debug(description);
        return  productService.findProductByDescription(description);
    }

    
    @PostMapping()
    @Operation(summary = "Create product")
    public Mono<Product> create(@RequestBody Product product) throws Exception {
        log.info("create" + "OK");
        log.debug(product.toString());
        return productService.create(product);
    }

    @PutMapping(path = { "{id}" }, produces = { "application/json" })
    @Operation(summary = "Modificar productos")
    public Mono<Product> update(@PathVariable("id") String id, @RequestBody Product product) throws Exception {
        
        log.info("update" + "OK");
        log.debug(id + "/" + product.toString());
        return productService.update(id, product);
    }

    @DeleteMapping({ "{id}" })
    @Operation(summary = "delete product by id")
    public void deleteById(@PathVariable("id") String id) throws Exception {
        productService.deleteById(id).subscribe();
        log.info("delete by id ok");
        log.debug(id);
    }
}

