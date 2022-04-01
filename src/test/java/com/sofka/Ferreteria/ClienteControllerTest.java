package com.sofka.Ferreteria;

import com.sofka.Ferreteria.controller.ClienteController;
import com.sofka.Ferreteria.model.Cliente;
import com.sofka.Ferreteria.service.ClienteService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

@WebFluxTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ClienteController.class, Cliente.class, ClienteService.class})
public class ClienteControllerTest {

    @MockBean
    private ClienteService clienteService;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    @DisplayName("GET / mostrar todos los clientes")
    void mostrarClientes() {
        Cliente cliente = new Cliente();
        cliente.setId("tyuy4");
        cliente.setNombreCliente("Marcos");
        Flux<Cliente> list = Flux.just(cliente);

        when(clienteService.findAll()).thenReturn(list);

        webTestClient.get()
                .uri("/cliente")
                .exchange()
                .expectStatus().isOk()
                .expectBody().jsonPath("$",hasSize(1));

    }

    @Test
    @DisplayName("GET / mostrar cliente por ID")
    void mostrarClientePorId(){
        Cliente cliente = new Cliente();
        cliente.setId("tyuy4");
        cliente.setNombreCliente("Marcos");

        webTestClient.get()
                .uri("/cliente/{id}/id","tyuy4")
                .exchange()
                .expectStatus().isOk()
                .expectBody().jsonPath("$.nombreCliente",is("Marcos"));

    }

    @Test
    @DisplayName("POST / agregar cliente")
    void agregarCliente() {
        Cliente cliente = new Cliente();
        cliente.setId("tyuy4");
        cliente.setNombreCliente("Marcos");
        Mono<Cliente> mono = Mono.just(cliente);

        webTestClient.post()
                .uri("/cliente")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(mono), Cliente.class)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(String.class)
                .value(response->{
                    Assertions.assertEquals("tyuy4",cliente.getId());
                });

    }

    @Test
    @DisplayName("GET / buscar por documento")
    void buscarPorDocumento(){
        Cliente cliente = new Cliente();
        cliente.setId("tyuy4");
        cliente.setNombreCliente("Marcos");
        cliente.setDocumentoIdentidad(44566789);

        webTestClient.get()
                .uri("/cliente/{documentoIdentidad}/documento",44566789)
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .value(response->{
                    Assertions.assertEquals("tyuy4",cliente.getId());
                    Assertions.assertEquals("Marcos",cliente.getNombreCliente());
                    Assertions.assertEquals(44566789,cliente.getDocumentoIdentidad());
                });

    }
}
