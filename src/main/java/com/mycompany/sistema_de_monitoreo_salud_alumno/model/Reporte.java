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
public class Reporte {
    private int idReporte;
    private Alumno alumno;
    private Enfermera enfermera;
    private Date fecha;
    private String descripcion;

    // Constructor
    public Reporte(int idReporte, Alumno alumno, Enfermera enfermera, Date fecha, String descripcion) {
        this.idReporte = idReporte;
        this.alumno = alumno;
        this.enfermera = enfermera;
        this.fecha = fecha;
        this.descripcion = descripcion;
    }

    // Getters y Setters
    public int getIdReporte() {
        return idReporte;
    }

    public void setIdReporte(int idReporte) {
        this.idReporte = idReporte;
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

