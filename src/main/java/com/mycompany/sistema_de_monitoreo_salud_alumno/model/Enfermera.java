/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistema_de_monitoreo_salud_alumno.model;

/**
 *
 * @author ELVIS
 */
public class Enfermera extends Persona {
    private int idEnfermera;
    private String trabajo;
    private String especialidad;

    public Enfermera(int idEnfermera, String trabajo, String especialidad, String nombre, String apellido, int edad) {
        super(nombre, apellido, edad);
        this.idEnfermera = idEnfermera;
        this.trabajo = trabajo;
        this.especialidad = especialidad;
    }

    public Enfermera(String trabajo, String especialidad, String nombre, String apellido, int edad) {
        super(nombre, apellido, edad);
        this.trabajo = trabajo;
        this.especialidad = especialidad;
    }

    public int getIdEnfermera() {
        return idEnfermera;
    }

    public void setIdEnfermera(int idEnfermera) {
        this.idEnfermera = idEnfermera;
    }

    public String getTrabajo() {
        return trabajo;
    }

    public void setTrabajo(String trabajo) {
        this.trabajo = trabajo;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }
}
