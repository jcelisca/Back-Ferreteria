package com.sofka.Ferreteria.service;

import com.sofka.Ferreteria.model.Volante;
import com.sofka.Ferreteria.repository.VolanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@Service
public class VolanteService {

    @Autowired
    private VolanteRepository volanteRepository;

    public Flux<Volante> findAll(){
        return volanteRepository.findAll();
    }

    public Mono<Volante> save(Volante volante) {
        volante.setFecha(LocalDate.now());
        return volanteRepository.save(volante);
    }

    public Mono<Volante> findById(String  id){
        return volanteRepository.findById(id);
    }

    public Flux<Volante> findByDocumentoProveedor(int documentoProveedor){
        return volanteRepository.findByDocumentoProveedor(documentoProveedor);
    }
}
