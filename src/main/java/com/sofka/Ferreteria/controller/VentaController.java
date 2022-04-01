package com.sofka.Ferreteria.controller;

import com.sofka.Ferreteria.model.Venta;
import com.sofka.Ferreteria.service.InventarioService;
import com.sofka.Ferreteria.service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class VentaController {

    @Autowired
    private VentaService ventaService;

    @Autowired
    private InventarioService inventarioService;

    @GetMapping("/venta")
    public Flux<Venta> findAllVenta(){
        return ventaService.findAll();
    }

    @PostMapping("/venta")
    @ResponseStatus(HttpStatus.CREATED)
    public Flux<Object> save(@RequestBody Venta venta){
        return Flux.fromIterable(venta.getArticulos())
                .flatMap(inventario ->Flux.merge(ventaService.save(venta),inventarioService.update(inventario)));
    }

    @GetMapping("/venta/{id}/id")
    public Mono<Venta> findById(@PathVariable("id") String id){
        return ventaService.findById(id);
    }

    @GetMapping("/venta/{nombreCliente}/nombre")
    public Flux<Venta> findByNombreCliente(@PathVariable("nombreCliente") String nombreCliente){
        return ventaService.findByNombreCliente(nombreCliente);
    }
}
