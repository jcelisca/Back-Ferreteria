package com.sofka.Ferreteria.service;

import com.sofka.Ferreteria.model.Cliente;
import com.sofka.Ferreteria.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Mono<Cliente> save(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Mono<Cliente> delete(String id) {
        return clienteRepository
                .findById(id)
                .flatMap(p -> clienteRepository.deleteById(p.getId()).thenReturn(p));
    }

    public Flux<Cliente> findAll(){
        return clienteRepository.findAll();
    }

    public Mono<Cliente> findById(String id){
        return clienteRepository.findById(id);
    }

}
