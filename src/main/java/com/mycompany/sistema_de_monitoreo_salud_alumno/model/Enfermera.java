package com.mycompany.sistema_de_monitoreo_salud_alumno.model;

public class Enfermera extends Persona {
    private int idEnfermera;
    private String especialidad;
    private Alumno alumno; // Utilizar la clase Alumno para almacenar la referencia al alumno

    public Enfermera(int idEnfermera, String especialidad, Alumno alumno, int idPersona) {
        super(idPersona);
        this.idEnfermera = idEnfermera;
        this.especialidad = especialidad;
        this.alumno = alumno;
    }

    public Enfermera(int idEnfermera, String especialidad, Alumno alumno, String nombre, String apellido, int edad) {
        super(nombre, apellido, edad);
        this.idEnfermera = idEnfermera;
        this.especialidad = especialidad;
        this.alumno = alumno;
    }

    public Enfermera(String especialidad, Alumno alumno, String nombre, String apellido, int edad) {
        super(nombre, apellido, edad);
        this.especialidad = especialidad;
        this.alumno = alumno;
    }

    public int getIdEnfermera() {
        return idEnfermera;
    }

    public void setIdEnfermera(int idEnfermera) {
        this.idEnfermera = idEnfermera;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }
    
    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }
}
