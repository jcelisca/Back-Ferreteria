package com.sofka.Ferreteria.controller;

import com.sofka.Ferreteria.model.Proveedor;
import com.sofka.Ferreteria.service.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@CrossOrigin(origins = "*", allowedHeaders = "*")
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

    @DeleteMapping("/proveedor/{id}/delete")
    private Mono<ResponseEntity<Proveedor>> deleteProveedor(@PathVariable("id") String id) {
        return proveedorService.delete(id)
                .flatMap(proveedor -> Mono.just(ResponseEntity.ok(proveedor)))
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
    }

    @GetMapping("/proveedor/{id}/id")
    public Mono<Proveedor> findById(@PathVariable("id") String id){
        return proveedorService.findById(id);
    }

    @GetMapping("/proveedor/{documentoIdentidad}/documento")
    public Flux<Proveedor> findByDocumentoIdentidad(@PathVariable("documentoIdentidad") String documentoIdentidad){
        return proveedorService.findByDocumentoIdentidad(documentoIdentidad);
    }

    @PutMapping("/proveedor/{id}/update")
    private Mono<ResponseEntity<Proveedor>> updateProveedor(@PathVariable("id") String id, @RequestBody Proveedor proveedor) {
        return proveedorService.updateProveedor(id, proveedor)
                .flatMap(proveedor1 -> Mono.just(ResponseEntity.ok(proveedor1)))
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));

    }
}
