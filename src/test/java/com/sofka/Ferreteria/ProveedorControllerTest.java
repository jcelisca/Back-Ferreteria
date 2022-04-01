package com.sofka.Ferreteria;

import com.sofka.Ferreteria.controller.ProveedorController;
import com.sofka.Ferreteria.model.Proveedor;
import com.sofka.Ferreteria.service.ProveedorService;
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
@ContextConfiguration(classes = {ProveedorController.class, Proveedor.class, ProveedorService.class})
public class ProveedorControllerTest {

    @MockBean
    private ProveedorService proveedorService;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    @DisplayName("GET / mostrar todos los proveedores")
    void mostrarProveedores(){
        Proveedor proveedor = new Proveedor();
        proveedor.setId("hhgg6");
        proveedor.setNombreProveedor("Martin");
        Proveedor proveedor2 = new Proveedor();
        proveedor2.setId("ioio46");
        proveedor2.setNombreProveedor("Juan");
        Flux<Proveedor> list = Flux.just(proveedor,proveedor2);

        when(proveedorService.findAll()).thenReturn(list);

        webTestClient.get()
                .uri("/proveedor")
                .exchange()
                .expectStatus().isOk()
                .expectBody().jsonPath("$",hasSize(2));
    }

    @Test
    @DisplayName("GET / mostrar proveedor por ID")
    void mostrarProveedorPorId(){
        Proveedor proveedor = new Proveedor();
        proveedor.setId("hhgg6");
        proveedor.setNombreProveedor("Martin");

        webTestClient.get()
                .uri("/proveedor/{id}/id","hhgg6")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .value(response->{
                    Assertions.assertEquals("hhgg6", proveedor.getId());
                    Assertions.assertEquals("Martin",proveedor.getNombreProveedor());
                });

    }

    @Test
    @DisplayName("POST / agregar proveedor")
    void agregarProveedor() {
        Proveedor proveedor = new Proveedor();
        proveedor.setId("hhgg6");
        proveedor.setNombreProveedor("Martin");
        Mono<Proveedor> mono = Mono.just(proveedor);

        webTestClient.post()
                .uri("/proveedor")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(mono), Proveedor.class)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(String.class)
                .value(response->{
                    Assertions.assertEquals("hhgg6", proveedor.getId());
                    Assertions.assertEquals("Martin",proveedor.getNombreProveedor());
                });
    }

    @Test
    @DisplayName("GET / buscar por documento")
    void buscarPorDocumento(){
        Proveedor proveedor = new Proveedor();
        proveedor.setId("hhgg6");
        proveedor.setNombreProveedor("Martin");
        proveedor.setDocumentoIdentidad(44566789);

        webTestClient.get()
                .uri("/proveedor/{documentoIdentidad}/documento",44566789)
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .value(response->{
                    Assertions.assertEquals("hhgg6", proveedor.getId());
                    Assertions.assertEquals("Martin",proveedor.getNombreProveedor());
                    Assertions.assertEquals(44566789,proveedor.getDocumentoIdentidad());
                });

    }

}
