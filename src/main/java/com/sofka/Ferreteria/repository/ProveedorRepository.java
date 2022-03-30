package com.sofka.Ferreteria.repository;

import com.sofka.Ferreteria.model.Proveedor;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProveedorRepository extends ReactiveMongoRepository<Proveedor, String> {
}
