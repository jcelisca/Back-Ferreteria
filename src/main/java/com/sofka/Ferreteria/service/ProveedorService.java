package com.sofka.Ferreteria.service;

import com.sofka.Ferreteria.model.Proveedor;
import com.sofka.Ferreteria.repository.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProveedorService {

    @Autowired
    private ProveedorRepository proveedorRepository;

    public Mono<Proveedor> save(Proveedor  proveedor) {
        return proveedorRepository.save(proveedor);
    }

    public Mono<Proveedor> delete(String id) {
        return proveedorRepository
                .findById(id)
                .flatMap(p -> proveedorRepository.deleteById(p.getId()).thenReturn(p));
    }

    public Flux<Proveedor> findAll(){
        return proveedorRepository.findAll();
    }
}
