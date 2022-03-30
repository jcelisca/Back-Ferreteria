package com.sofka.Ferreteria.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "cliente")
public class Cliente {

    @Id
    private String id;
    private String nombreCliente;
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

    public String getNombre() {
        return nombreCliente;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombreCliente = nombre;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

}
