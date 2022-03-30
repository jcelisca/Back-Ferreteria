package com.sofka.Ferreteria.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "inventario")
public class Inventario {

    @Id
    private String id;
    private String nombreArticulo;
    private int disponible;
    private String idProveedor;

    public String getId() {
        return id;
    }

    public String getNombreArticulo() {
        return nombreArticulo;
    }

    public int getDisponible() {
        return disponible;
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

    public void setDisponible(int disponible) {
        this.disponible = disponible;
    }

    public void setIdProveedor(String idProveedor) {
        this.idProveedor = idProveedor;
    }
}
