package com.sofka.Ferreteria.controller;

import com.sofka.Ferreteria.model.Cliente;
import com.sofka.Ferreteria.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/cliente")
    public Flux<Cliente> findAllCliente(){
        return clienteService.findAll();
    }

    @PostMapping("/cliente")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Cliente> save (@RequestBody Cliente cliente){
        return clienteService.save(cliente);
    }
}
