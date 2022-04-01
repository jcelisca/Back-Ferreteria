package com.sofka.Ferreteria.controller;

import com.sofka.Ferreteria.model.Volante;
import com.sofka.Ferreteria.service.InventarioService;
import com.sofka.Ferreteria.service.VolanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class VolanteController {

    @Autowired
    private VolanteService volanteService;

    @Autowired
    private InventarioService inventarioService;

    @GetMapping("/volante")
    public Flux<Volante> findAllVolante(){
        return volanteService.findAll();
    }

    @PostMapping("/volante")
    @ResponseStatus(HttpStatus.CREATED)
    public Flux<Object> nuevoVolante(@RequestBody Volante volante){
        return Flux.fromIterable(volante.getProductos())
                .flatMap(inventario ->Flux.merge(inventarioService.save(inventario), volanteService.save(volante)));
    }

    @PostMapping("/volante/agregar")
    @ResponseStatus(HttpStatus.CREATED)
    public Flux<Object> agregarInventario(@RequestBody Volante volante){
        return Flux.fromIterable(volante.getProductos())
                .flatMap(inventario ->Flux.merge(inventarioService.agregarInventario(inventario), volanteService.save(volante)));
    }

    @GetMapping("/volante/{id}/id")
    public Mono<Volante> findById(@PathVariable("id") String id){
        return volanteService.findById(id);
    }

    @GetMapping("/volante/{documentoProveedor}/documento")
    public Flux<Volante> findByDocumentoProveedor(@PathVariable("documentoProveedor") int documentoProveedor){
        return volanteService.findByDocumentoProveedor(documentoProveedor);
    }
}
