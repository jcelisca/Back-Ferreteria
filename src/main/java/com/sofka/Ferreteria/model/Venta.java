package com.sofka.Ferreteria.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Document(collection = "venta")
public class Venta {

    @Id
    private String id;
    private List<Inventario> articulos;
    private LocalDate date;
    private String total;

    public String getId() {
        return id;
    }

    public List<Inventario> getArticulos() {
        return articulos;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getTotal() {
        return total;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setArticulos(List<Inventario> articulos) {
        this.articulos = articulos;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
