package com.sofka.Ferreteria.service;

import com.sofka.Ferreteria.model.Inventario;
import com.sofka.Ferreteria.model.Venta;
import com.sofka.Ferreteria.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VentaService {

    @Autowired
    private VentaRepository ventaRepository;

    public Double suma(List<Inventario> inventario){
        return inventario.stream().collect(Collectors.summingDouble(i->i.getPrecioUnidad()*i.getCantidad()));
    }

    /*public Mono<Venta> save(Venta venta) {
        Flux.fromIterable(venta.getArticulos())
                .map(inventario -> inventarioService.update(inventario));
        venta.setDate(LocalDate.now());
        venta.setTotal(suma(venta.getArticulos()));
        inventarioService.update(venta.getArticulos().get(0));
        return ventaRepository.save(venta);
    }*/

    public Mono<Venta> save(Venta venta) {
        venta.setDate(LocalDate.now());
        venta.setTotal(suma(venta.getArticulos()));
        return ventaRepository.save(venta);
    }

    public Flux<Venta> findAll(){
        return ventaRepository.findAll();
    }
}
