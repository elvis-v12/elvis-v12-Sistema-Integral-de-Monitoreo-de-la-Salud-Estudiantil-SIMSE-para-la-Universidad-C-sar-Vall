package com.mycompany.sistema_de_monitoreo_salud_alumno.controler.Controler.interf;
import com.mycompany.sistema_de_monitoreo_salud_alumno.model.Alumno;
import com.mycompany.sistema_de_monitoreo_salud_alumno.model.Sesion;
import java.util.List;

public interface AlumnoDAO {
    // Operaciones CRUD para la clase Alumno
    void agregarAlumno(Alumno alumno);
    void actualizarAlumno(Alumno alumno);
    void eliminarAlumno(int id);
    Alumno obtenerAlumnoPorId(String codigoAlumno);
    List<Alumno> obtenerTodosLosAlumnos();
    List<Sesion> obtenerTodasLasSesiones();
}
