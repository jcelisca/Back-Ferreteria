package com.sofka.Ferreteria.repository;

import com.sofka.Ferreteria.model.Inventario;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface InventarioRepository extends ReactiveMongoRepository<Inventario, String> {
    Flux<Inventario> findByNombreArticulo(String nombreArticulo);
}
