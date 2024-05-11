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
public class Traslado {
    private int idTraslado;
    private Alumno alumno;
    private Enfermera enfermera;
    private Date fechaInicio;
    private Date fechaFin;

    // Constructor
    public Traslado(int idTraslado, Alumno alumno, Enfermera enfermera, Date fechaInicio, Date fechaFin) {
        this.idTraslado = idTraslado;
        this.alumno = alumno;
        this.enfermera = enfermera;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    // Getters y Setters
    public int getIdTraslado() {
        return idTraslado;
    }

    public void setIdTraslado(int idTraslado) {
        this.idTraslado = idTraslado;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public Enfermera getEnfermera() {
        return enfermera;
    }

    public void setEnfermera(Enfermera enfermera) {
        this.enfermera = enfermera;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }
}

