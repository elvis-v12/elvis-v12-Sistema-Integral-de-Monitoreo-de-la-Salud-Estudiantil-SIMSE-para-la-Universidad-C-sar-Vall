package com.mycompany.sistema_de_monitoreo_salud_alumno.model;

/**
 * Clase que representa a una Persona.
 */
public class Persona {
    private int idPersona;
    String nombre;
    String apellido;
    private int edad; 

    // Constructor sin idPersona
    public Persona(String nombre, String apellido, int edad) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
    }
    public Persona() {
        // Inicialización por defecto, si es necesario
    }

    public Persona(int idPersona) {
        this.idPersona = idPersona;
    }

    // Constructor con idPersona
    public Persona(int idPersona, String nombre, String apellido, int edad) {
        this.idPersona = idPersona;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
    }

    // Getters y setters
    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getEdad() { // Cambiado a int
        return edad;
    }

    public void setEdad(int edad) { // Cambiado a int
        this.edad = edad;
    }
}
