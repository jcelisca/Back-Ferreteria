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
    private String nombreCliente;
    private String asesor;
    private Double total;

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getId() {
        return id;
    }

    public List<Inventario> getArticulos() {
        return articulos;
    }

    public LocalDate getDate() {
        return date;
    }

    public Double getTotal() {
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

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getAsesor() {
        return asesor;
    }

    public void setAsesor(String asesor) {
        this.asesor = asesor;
    }
}
