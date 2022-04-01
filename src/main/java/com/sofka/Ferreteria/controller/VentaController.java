package com.sofka.Ferreteria.controller;

import com.sofka.Ferreteria.model.Venta;
import com.sofka.Ferreteria.service.InventarioService;
import com.sofka.Ferreteria.service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

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
                .flatMap(inventario ->Flux.merge(inventarioService.update(inventario), ventaService.save(venta)));
    }
}
