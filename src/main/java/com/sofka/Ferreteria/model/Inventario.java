package com.sofka.Ferreteria.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "inventario")
public class Inventario {

    @Id
    private String id;
    private String nombreArticulo;
    private int cantidad;
    private String idProveedor;

    public String getId() {
        return id;
    }

    public String getNombreArticulo() {
        return nombreArticulo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public String getIdProveedor() {
        return idProveedor;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNombreArticulo(String nombreArticulo) {
        this.nombreArticulo = nombreArticulo;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setIdProveedor(String idProveedor) {
        this.idProveedor = idProveedor;
    }

}
