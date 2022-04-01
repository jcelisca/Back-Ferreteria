package com.sofka.Ferreteria;

import com.sofka.Ferreteria.controller.VentaController;
import com.sofka.Ferreteria.model.Inventario;
import com.sofka.Ferreteria.model.Venta;
import com.sofka.Ferreteria.service.InventarioService;
import com.sofka.Ferreteria.service.VentaService;
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
@ContextConfiguration(classes = {Venta.class, VentaController.class, VentaService.class, InventarioService.class})
public class VentaControllerTest {

    @MockBean
    private VentaService ventaService;

    @MockBean
    private InventarioService inventarioService;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    @DisplayName("GET / mostrar todos las ventas")
    void mostrarVentas(){
        Venta venta = new Venta();
        venta.setId("uoihkjn5");
        venta.setNombreCliente("Jose");
        venta.setAsesor("Manuel");
        Venta venta2 = new Venta();
        venta2.setId("uoihkjn5");
        venta2.setNombreCliente("Jose");
        venta2.setAsesor("Manuel");

        webTestClient.get()
                .uri("/venta")
                .exchange()
                .expectStatus().isOk()
                .expectBody().jsonPath("$",hasSize(2));
    }

    @Test
    @DisplayName("GET / mostrar venta por ID")
    void mostrarVentaPorId(){
        Venta venta = new Venta();
        venta.setId("uoihkjn5");
        venta.setNombreCliente("Jose");
        venta.setAsesor("Manuel");

        webTestClient.get()
                .uri("/venta/{id}/id","uoihkjn5")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .value(response->{
                    Assertions.assertEquals("uoihkjn5", venta.getId());
                    Assertions.assertEquals("Jose", venta.getNombreCliente());
                    Assertions.assertEquals("Manuel", venta.getAsesor());
                });
    }

    @Test
    @DisplayName("POST / agregar venta")
    void agregarVenta() {

        Inventario inventario = new Inventario();
        inventario.setNombreArticulo("Martillo");
        inventario.setCantidad(1);
        inventario.setPrecioUnidad(9000.0);

        List<Inventario> list = new ArrayList<>();
        list.add(inventario);
        Venta venta = new Venta();
        venta.setId("uoihkjn5");
        venta.setNombreCliente("Jose");
        venta.setAsesor("Manuel");
        venta.setArticulos(list);


        webTestClient.post()
                .uri("/venta")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(venta), Venta.class)
                .exchange()
                .expectBody(String.class)
                .value(response->{
                    Assertions.assertEquals("uoihkjn5", venta.getId());
                    Assertions.assertEquals("Jose", venta.getNombreCliente());
                    Assertions.assertEquals("Manuel", venta.getAsesor());
                });
    }
}
