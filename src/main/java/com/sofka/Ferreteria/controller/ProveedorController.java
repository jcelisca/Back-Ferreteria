package com.sofka.Ferreteria.controller;

import com.sofka.Ferreteria.model.Proveedor;
import com.sofka.Ferreteria.service.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class ProveedorController {

    @Autowired
    private ProveedorService proveedorService;

    @GetMapping("/proveedor")
    public Flux<Proveedor> findAllProveedor(){
        return proveedorService.findAll();
    }

    @PostMapping("/proveedor")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Proveedor> save(@RequestBody Proveedor proveedor){
        return proveedorService.save(proveedor);
    }
}
