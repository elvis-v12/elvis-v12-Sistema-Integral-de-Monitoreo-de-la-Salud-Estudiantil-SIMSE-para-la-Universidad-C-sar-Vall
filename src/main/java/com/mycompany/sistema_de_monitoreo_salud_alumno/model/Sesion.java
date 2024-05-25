package com.mycompany.sistema_de_monitoreo_salud_alumno.model;

import java.util.Date;

public class Sesion {
    private int idSesion;
    private Alumno alumno;
    private Date fechaInicio;
    private Date fechaFin;

    // Constructor
    public Sesion(int idSesion, Alumno alumno, Date fechaInicio, Date fechaFin) {
        this.idSesion = idSesion;
        this.alumno = alumno;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public int getIdSesion() {
        return idSesion;
    }

    public void setIdSesion(int idSesion) {
        this.idSesion = idSesion;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
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

