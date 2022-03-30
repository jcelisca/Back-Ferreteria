package com.sofka.Ferreteria.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "proveedor")
public class Proveedor {

    @Id
    private String id;
    private String nombreProveedor;
    private String direccion;
    private List<Inventario> articulos;

    public String getId() {
        return id;
    }

    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public String getDireccion() {
        return direccion;
    }

    public List<Inventario> getArticulos() {
        return articulos;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setArticulos(List<Inventario> articulos) {
        this.articulos = articulos;
    }
}
