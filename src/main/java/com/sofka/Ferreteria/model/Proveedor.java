package com.sofka.Ferreteria.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "proveedor")
public class Proveedor {

    @Id
    private String id;
    private String nombreProveedor;
    private String celullar;
    private int documentoIdentidad;

    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public String getCelullar() {
        return celullar;
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

    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }

    public void setCelullar(String celullar) {
        this.celullar = celullar;
    }

    public void setDocumentoIdentidad(int documentoIdentidad) {
        this.documentoIdentidad = documentoIdentidad;
    }
}
