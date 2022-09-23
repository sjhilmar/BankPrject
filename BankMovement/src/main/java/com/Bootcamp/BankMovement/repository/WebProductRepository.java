//package com.Bootcamp.BankMovement.repository;
//
//import com.Bootcamp.BankMovement.common.Product;
//import org.springframework.stereotype.Repository;
//import org.springframework.web.reactive.function.client.WebClient;
//
//import java.util.List;
//
//@Repository
//public class WebProductRepository {
//
//    private final  WebClient webClient = WebClient.create("http://localhost:3000");
//
//    public Product findAllByClientId(){
//        Product listProducts = webClient.get().uri("/api/provincias").
//                retrieve().bodyToMono(Product.class).
//                block();
//        return listProducts;
//    }
//}
