package com.sofka.Ferreteria;

import com.sofka.Ferreteria.controller.InventarioController;
import com.sofka.Ferreteria.model.Inventario;
import com.sofka.Ferreteria.service.InventarioService;
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
import static org.mockito.Mockito.when;

@WebFluxTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {InventarioController.class, Inventario.class, InventarioService.class})
public class InventarioControllerTest {

    @MockBean
    private InventarioService inventarioService;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    @DisplayName("GET / mostrar inventario")
    void mostrarInventario(){
        Inventario inventario = new Inventario();
        inventario.setId("tytuu8");
        inventario.setNombreArticulo("Martillo");
        inventario.setCantidad(12);
        inventario.setPrecioUnidad(9000.0);
        Flux<Inventario> list = Flux.just(inventario);

        when(inventarioService.findAll()).thenReturn(list);

        webTestClient.get()
                .uri("/inventario")
                .exchange()
                .expectStatus().isOk()
                .expectBody().jsonPath("$",hasSize(1));
    }

    @Test
    @DisplayName("GET / mostrar inventario por ID")
    void mostrarInventarioPorId(){
        Inventario inventario = new Inventario();
        inventario.setId("tytuu8");
        inventario.setNombreArticulo("Martillo");
        inventario.setCantidad(12);
        inventario.setPrecioUnidad(9000.0);

        webTestClient.get()
                .uri("/inventario/{id}/id","tytuu8")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .value(response->{
                    Assertions.assertEquals("tytuu8", inventario.getId());
                    Assertions.assertEquals("Martillo", inventario.getNombreArticulo());
                    Assertions.assertEquals(12, inventario.getCantidad());
                    Assertions.assertEquals(9000.0, inventario.getPrecioUnidad());
                });
    }

    @Test
    @DisplayName("POST / agregar inventario")
    void agregarInventario() {
        Inventario inventario = new Inventario();
        inventario.setId("tytuu8");
        inventario.setNombreArticulo("Martillo");
        inventario.setCantidad(12);
        inventario.setPrecioUnidad(9000.0);
        Mono<Inventario> mono = Mono.just(inventario);

        webTestClient.post()
                .uri("/inventario")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(mono), Inventario.class)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(String.class)
                .value(response->{
                    Assertions.assertEquals("tytuu8", inventario.getId());
                    Assertions.assertEquals("Martillo", inventario.getNombreArticulo());
                    Assertions.assertEquals(12, inventario.getCantidad());
                    Assertions.assertEquals(9000.0, inventario.getPrecioUnidad());
                });
    }

    @Test
    @DisplayName("GET / buscar por nombre de articulo")
    void buscarPorNombreArticulo(){
        Inventario inventario = new Inventario();
        inventario.setId("tytuu8");
        inventario.setNombreArticulo("Martillo");
        inventario.setCantidad(12);
        inventario.setPrecioUnidad(9000.0);

        webTestClient.get()
                .uri("/inventario/{nombreArticulo}/nombre","Martillo")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .value(response->{
                    Assertions.assertEquals("tytuu8", inventario.getId());
                    Assertions.assertEquals("Martillo", inventario.getNombreArticulo());
                    Assertions.assertEquals(12, inventario.getCantidad());
                    Assertions.assertEquals(9000.0, inventario.getPrecioUnidad());
                });

    }
}
