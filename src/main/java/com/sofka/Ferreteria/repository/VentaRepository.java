package com.sofka.Ferreteria.repository;

import com.sofka.Ferreteria.model.Venta;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface VentaRepository extends ReactiveMongoRepository<Venta, String>{
    Flux<Venta> findByNombreCliente(String nombreCliente);
}
