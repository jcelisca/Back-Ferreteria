package com.sofka.Ferreteria.controller;

import com.sofka.Ferreteria.model.Venta;
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

    @GetMapping("/venta")
    public Flux<Venta> findAllVenta(){
        return ventaService.findAll();
    }

    @PostMapping("/venta")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Venta> save (@RequestBody Venta venta){
        return ventaService.save(venta);
    }
}
