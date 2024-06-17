package com.mycompany.sistema_de_monitoreo_salud_alumno.model;

public class Alumno extends Persona {
    private int idAlumno;
    private String codigoAlumno;
    private String carrera;
    private int ciclo;

    public Alumno(int idAlumno, int idPersona) {
        super(idPersona);
        this.idAlumno = idAlumno;
    }




    public Alumno(String codigoAlumno, String carrera, int ciclo, String nombre, String apellido, int edad) {
        super(nombre, apellido, edad);
        this.codigoAlumno = codigoAlumno;
        this.carrera = carrera;
        this.ciclo = ciclo;
    }

    public Alumno(int idAlumno, String codigoAlumno, String carrera, int idPersona) {
        super(idPersona);
        this.idAlumno = idAlumno;
        this.codigoAlumno = codigoAlumno;
        this.carrera = carrera;
    }
    public Alumno() {
    }
  public Alumno(String codigoAlumno) {
       super(); 
        this.codigoAlumno = codigoAlumno;
    }
    // Constructor completo (para registros existentes)
    public Alumno(int idAlumno, String codigoAlumno, String carrera, int ciclo, int idPersona, String nombre, String apellido, int edad) {
        super(idPersona, nombre, apellido, edad);
        this.idAlumno = idAlumno;
        this.codigoAlumno = codigoAlumno;
        this.carrera = carrera;
        this.ciclo = ciclo;
    }

    // Getters y setters para los campos de Alumno
    public int getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(int idAlumno) {
        this.idAlumno = idAlumno;
    }

    public String getCodigoAlumno() {
        return codigoAlumno;
    }

    public void setCodigoAlumno(String codigoAlumno) {
        this.codigoAlumno = codigoAlumno;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public int getCiclo() {
        return ciclo;
    }

    public void setCiclo(int ciclo) {
        this.ciclo = ciclo;
    }
}
