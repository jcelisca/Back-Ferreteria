package com.sofka.Ferreteria.repository;

import com.sofka.Ferreteria.model.Proveedor;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface ProveedorRepository extends ReactiveMongoRepository<Proveedor, String> {
    Flux<Proveedor> findByDocumentoIdentidad(String documentoIdentidad);
}
