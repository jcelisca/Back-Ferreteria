package com.sofka.Ferreteria.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "proveedor")
public class Proveedor {

    @Id
    private String id;
    private String nombreProveedor;
    private String direccion;
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public String getDireccion() {
        return direccion;
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

}
