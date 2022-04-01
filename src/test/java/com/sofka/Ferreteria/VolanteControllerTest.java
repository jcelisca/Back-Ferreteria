package com.sofka.Ferreteria;

import com.sofka.Ferreteria.controller.VolanteController;
import com.sofka.Ferreteria.model.Inventario;
import com.sofka.Ferreteria.model.Venta;
import com.sofka.Ferreteria.model.Volante;
import com.sofka.Ferreteria.service.InventarioService;
import com.sofka.Ferreteria.service.VolanteService;
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
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;

@WebFluxTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {Volante.class, VolanteController.class, VolanteService.class, InventarioService.class})
public class VolanteControllerTest {

    @MockBean
    private VolanteService volanteService;

    @MockBean
    private InventarioService inventarioService;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    @DisplayName("GET / mostrar todos los volantes")
    void mostrarVolantes(){
        Volante volante = new Volante();
        volante.setId("ouyoi6");
        volante.setDocumentoProveedor(6844654);

        webTestClient.get()
                .uri("/volante")
                .exchange()
                .expectStatus().isOk()
                .expectBody().jsonPath("$",hasSize(1));
    }

    @Test
    @DisplayName("GET / mostrar volante por ID")
    void mostrarVolantePorId(){
        Volante volante = new Volante();
        volante.setId("ouyoi6");
        volante.setDocumentoProveedor(6844654);

        webTestClient.get()
                .uri("/volante/{id}/id","ouyoi6")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .value(response->{
                    Assertions.assertEquals("ouyoi6", volante.getId());
                    Assertions.assertEquals(6844654, volante.getDocumentoProveedor());
                });
    }

    @Test
    @DisplayName("POST / agregar volante")
    void agregarVolante() {

        Inventario inventario = new Inventario();
        inventario.setNombreArticulo("Martillo");
        inventario.setCantidad(10);
        inventario.setPrecioUnidad(9000.0);

        List<Inventario> list = new ArrayList<>();
        list.add(inventario);
        Volante volante = new Volante();
        volante.setId("ouyoi6");
        volante.setDocumentoProveedor(6844654);
        volante.setProductos(list);


        webTestClient.post()
                .uri("/volante")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(volante), Volante.class)
                .exchange()
                .expectBody(String.class)
                .value(response->{
                    Assertions.assertEquals("ouyoi6", volante.getId());
                    Assertions.assertEquals(6844654, volante.getDocumentoProveedor());
                });
    }
}
