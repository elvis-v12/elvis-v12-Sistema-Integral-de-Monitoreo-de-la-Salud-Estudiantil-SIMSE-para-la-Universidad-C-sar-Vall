/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistema_de_monitoreo_salud_alumno.model;
import java.util.Date;

/**
 *
 * @author ELVIS
 */

public class EstadoSalud {
    private int idEstadoSalud;
    private Alumno alumno;
    private Date fecha;
    private String descripcion;

    public int getIdEstadoSalud() {
        return idEstadoSalud;
    }

    public void setIdEstadoSalud(int idEstadoSalud) {
        this.idEstadoSalud = idEstadoSalud;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}

