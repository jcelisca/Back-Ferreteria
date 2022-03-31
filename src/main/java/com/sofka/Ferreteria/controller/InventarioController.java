package com.sofka.Ferreteria.controller;

import com.sofka.Ferreteria.model.Cliente;
import com.sofka.Ferreteria.model.Inventario;
import com.sofka.Ferreteria.service.InventarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class InventarioController {

    @Autowired
    private InventarioService inventarioService;

    @GetMapping("/inventario")
    public Flux<Inventario> findAllInventario(){
        return inventarioService.findAll();
    }

    @PostMapping("/inventario")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Inventario> save (@RequestBody Inventario inventario){
        return inventarioService.save(inventario);
    }

    
}