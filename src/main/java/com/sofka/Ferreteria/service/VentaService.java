package com.sofka.Ferreteria.service;

import com.sofka.Ferreteria.model.Venta;
import com.sofka.Ferreteria.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class VentaService {

    @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private InventarioService inventarioService;

    public Mono<Venta> save(Venta venta) {
        Flux.fromIterable(venta.getArticulos())
                .flatMap(inventario ->{
                    inventarioService.update(inventario.getIdProveedor(), inventario.getCantidad());
                    return Mono.empty();
                });
        return ventaRepository.save(venta);
    }

    public Flux<Venta> findAll(){
        return ventaRepository.findAll();
    }
}
