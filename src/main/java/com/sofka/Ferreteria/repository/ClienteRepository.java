package com.sofka.Ferreteria.repository;

import com.sofka.Ferreteria.model.Cliente;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface ClienteRepository extends ReactiveMongoRepository<Cliente, String> {
    Flux<Cliente> findByDocumentoIdentidad(String documentoIdentidad);
}
