package com.sofka.Ferreteria.controller;

import com.sofka.Ferreteria.model.Cliente;
import com.sofka.Ferreteria.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @DeleteMapping("/cliente/{id}/delete")
    private Mono<ResponseEntity<Cliente>> deleteCliente(@PathVariable("id") String id) {
        return clienteService.delete(id)
                .flatMap(cliente -> Mono.just(ResponseEntity.ok(cliente)))
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
    }

    @GetMapping("/cliente/{id}/id")
    public Mono<Cliente> findById(@PathVariable("id") String id){
        return clienteService.findById(id);
    }

    @GetMapping("/cliente/{documentoIdentidad}/documento")
    public Flux<Cliente> findByDocumentoIdentidad(@PathVariable("documentoIdentidad") String documentoIdentidad){
        return clienteService.findByDocumentoIdentidad(documentoIdentidad);
    }

    @PutMapping("/cliente/{id}/update")
    private Mono<ResponseEntity<Cliente>> updateCliente(@PathVariable("id") String id, @RequestBody Cliente cliente) {
        return clienteService.updateCliente(id, cliente)
                .flatMap(cliente1 -> Mono.just(ResponseEntity.ok(cliente1)))
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));

    }
}
