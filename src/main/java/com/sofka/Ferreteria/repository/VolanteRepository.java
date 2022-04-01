package com.sofka.Ferreteria.repository;

import com.sofka.Ferreteria.model.Volante;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VolanteRepository extends ReactiveMongoRepository<Volante, String> {
}
