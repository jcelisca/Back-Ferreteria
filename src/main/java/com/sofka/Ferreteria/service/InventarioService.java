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
    public Mono<Inventario> findById(String id){
        return inventarioRepository.findById(id);
    }
    public Flux<Inventario> findByNombreArticulo(String nombreArticulo){
        return inventarioRepository.findByNombreArticulo(nombreArticulo);
    }

    public Mono<Inventario> update(Inventario inventario){
        return inventarioRepository.findById(inventario.getId())
                .flatMap(inventario1 -> {
                    if(inventario1.getCantidad()-inventario.getCantidad() < 0){
                        inventario.setCantidad(0);
                    }else{
                        inventario.setCantidad(inventario1.getCantidad()-inventario.getCantidad());
                    }
                    return save(inventario);
                });
    }

    public Mono<Inventario> agregarInventario(Inventario inventario) {
            return inventarioRepository.findById(inventario.getId())
                    .flatMap(inventario1 -> {
                        inventario.setId(inventario1.getId());
                        inventario.setCantidad(inventario1.getCantidad() + inventario.getCantidad());
                        return save(inventario);
                    });
    }
}
