package com.sofka.Ferreteria.service;

import com.sofka.Ferreteria.model.Inventario;
import com.sofka.Ferreteria.model.Volante;
import com.sofka.Ferreteria.repository.VolanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.LocalDate;

@Service
public class VolanteService {

    @Autowired
    private VolanteRepository volanteRepository;

    @Autowired
    private InventarioService inventarioService;

    public Flux<Volante> findAll(){
        return volanteRepository.findAll();
    }

    public Mono<Volante> save(Volante volante) {
        Flux.fromIterable(volante.getProductos())
                .flatMap(inventario -> inventarioService.update(inventario));
        volante.setFecha(LocalDate.now());
        return volanteRepository.save(volante);
    }
}
