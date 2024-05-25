package com.mycompany.sistema_de_monitoreo_salud_alumno.model;
import java.util.ArrayList;

public class Proveedor {
    private int idProveedor;
    private String nif;
    private String telefono;
    private String tipo_producto;
    private String encargado;
    
    // Constructor
    public Proveedor(int idProveedor, String nif, String telefono, String tipo_producto, String encargado) {
        this.idProveedor = idProveedor;
        this.nif = nif;
        this.telefono = telefono;
        this.tipo_producto = tipo_producto;
        this.encargado = encargado;
    }
    public Proveedor(String nif, String telefono, String tipo_producto, String encargado) {
        this.nif = nif;
        this.telefono = telefono;
        this.tipo_producto = tipo_producto;
        this.encargado = encargado;
    }

    // Getters y setters
    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getTipo_producto() {
        return tipo_producto;
    }

    public void setTipo_producto(String tipo_producto) {
        this.tipo_producto = tipo_producto;
    }

    public String getEncargado() {
        return encargado;
    }

    public void setEncargado(String encargado) {
        this.encargado = encargado;
    }
    
}
