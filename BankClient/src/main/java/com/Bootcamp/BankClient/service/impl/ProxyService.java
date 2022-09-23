package com.Bootcamp.BankClient.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Bootcamp.BankClient.domain.Proxy;
import com.Bootcamp.BankClient.repository.ProxyRepository;
import com.Bootcamp.BankClient.service.IProxyService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service

public class ProxyService implements IProxyService {
	
	
@Autowired
    private ProxyRepository proxyRepository;
    
    @Override
    public Flux<Proxy> findAll() throws Exception {
        return proxyRepository.findAll(); 
    }

    @Override
    public Mono<Proxy>  findById(String id) throws Exception {
        return proxyRepository.findById(id).switchIfEmpty(Mono.error(RuntimeException::new));
    }

    @Override
    public Flux<Proxy>  findByFullName(String fullName) throws Exception {
    	return proxyRepository.findProxyByFullName(fullName);
    	
    	
//        Optional<Proxy> proxy = proxyRepository.findProxyByFullName(fullName);
//        if(proxy.isPresent())	return proxyMapper.proxyToProxyModel(proxy.get());
//        else throw new Exception("No se encontraron datos");
    }

    @Override
    public Mono<Proxy> findByClientId(String clientId) throws Exception {
        return proxyRepository.findProxyByClientId(clientId);
    }

    @Override
    public Mono<Proxy> create(Proxy proxy) throws Exception {
    	Flux<Proxy> var= proxyRepository.findAll()
    			.filter(t -> t.getId().equals(proxy.getId()));
    	return var.collectList().flatMap(list -> {
    		if(list.size()> 0) return Mono.error(new Throwable("El proxy ya existe"));
    		return proxyRepository.save(proxy);
    	}); 
    	
//        if (!proxyRepository.findProxyByFullName(proxyModel.getFullName()).isPresent()){
//            Proxy proxy = proxyRepository.save(proxyMapper.proxyModelToProxy(proxyModel));
//            return proxyMapper.proxyToProxyModel(proxy);
//        }
//        else throw new Exception("El apoderado ya existe");
    }

    @Override
    public Mono<Proxy> update(String id, Proxy proxyModel) throws Exception {
        return proxyRepository.findById(id)
        		.switchIfEmpty(Mono.error(RuntimeException::new))
        		.flatMap(t -> proxyRepository.save(proxyModel) );
    }

    @Override
    public Mono<Void> deleteById(String id) throws Exception {
        return proxyRepository.deleteById(id);
    }
}
