package com.sofka.Ferreteria.repository;

import com.sofka.Ferreteria.model.Inventario;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventarioRepository extends ReactiveMongoRepository<Inventario, String> {
}
