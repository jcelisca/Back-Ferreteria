package com.sofka.Ferreteria.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Document(collection = "volante")
public class Volante {

    @Id
    private String id;
    private List<Inventario> productos;
    private int documentoProveedor;
    private LocalDate fecha;
    private String nombreProveedor;

    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }

    public String getId() {
        return id;
    }

    public List<Inventario> getProductos() {
        return productos;
    }

    public int getDocumentoProveedor() {
        return documentoProveedor;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setProductos(List<Inventario> productos) {
        this.productos = productos;
    }

    public void setDocumentoProveedor(int documentoProveedor) {
        this.documentoProveedor = documentoProveedor;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
}
