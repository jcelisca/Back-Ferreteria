package com.sofka.Ferreteria.service;

import com.sofka.Ferreteria.model.Cliente;
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

    public Mono<Proveedor> findById(String id){
        return proveedorRepository.findById(id);
    }

    public Flux<Proveedor> findByDocumentoIdentidad(String documentoIdentidad){
        return proveedorRepository.findByDocumentoIdentidad(documentoIdentidad);
    }

    public Mono<Proveedor> updateProveedor(String id, Proveedor proveedor) {
        return proveedorRepository.findById(id)
                .flatMap(proveedor1 -> {
                    proveedor.setId(id);
                    return save(proveedor);
                })
                .switchIfEmpty(Mono.empty());
    }
}
