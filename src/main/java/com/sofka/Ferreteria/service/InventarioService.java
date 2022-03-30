package com.sofka.Ferreteria.service;

import com.sofka.Ferreteria.model.Inventario;
import com.sofka.Ferreteria.repository.InventarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class InventarioService {

    @Autowired
    private InventarioRepository inventarioRepository;

    public Mono<Inventario> save(Inventario inventario) {
        return inventarioRepository.save(inventario);
    }

    public Mono<Inventario> delete(String id) {
        return inventarioRepository
                .findById(id)
                .flatMap(p -> inventarioRepository.deleteById(p.getId()).thenReturn(p));
    }

    public Flux<Inventario> findAll(){
        return inventarioRepository.findAll();
    }

    public void update(String id, int cantidad){
        Inventario inv = new Inventario();
        inventarioRepository.findById(id)
                .flatMap(inventario -> {
                    inv.setId(id);
                    inv.setIdProveedor(inventario.getIdProveedor());
                    inv.setNombreArticulo(inventario.getNombreArticulo());
                    inv.setCantidad(inv.getCantidad()-cantidad);
                    return save(inv);
                });
    }
}
