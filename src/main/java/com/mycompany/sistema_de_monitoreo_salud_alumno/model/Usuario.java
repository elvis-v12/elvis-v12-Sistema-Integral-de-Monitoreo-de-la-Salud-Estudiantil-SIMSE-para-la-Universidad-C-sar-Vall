/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistema_de_monitoreo_salud_alumno.model;

/**
 *
 * @author ELVIS
 */
public class Usuario {
    private int idUsuario;
    private String codigo;
    private String contrasena;

    public Usuario(int idUsuario, String codigo, String contrasena) {
        this.idUsuario = idUsuario;
        this.codigo = codigo;
        this.contrasena = contrasena;
    }
public Usuario(String codigo, String contrasena) {
    this.codigo = codigo;
    this.contrasena = contrasena;
}

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

   
}
