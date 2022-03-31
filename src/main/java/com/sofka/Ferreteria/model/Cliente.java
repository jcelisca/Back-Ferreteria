package com.sofka.Ferreteria.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "cliente")
public class Cliente {

    @Id
    private String id;
    private String nombreCliente;
    private String celular;
    private int documentoIdentidad;


    public String getNombreCliente() {
        return nombreCliente;
    }

    public String getCelular() {
        return celular;
    }

    public int getDocumentoIdentidad() {
        return documentoIdentidad;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public void setDocumentoIdentidad(int documentoIdentidad) {
        this.documentoIdentidad = documentoIdentidad;
    }
}
