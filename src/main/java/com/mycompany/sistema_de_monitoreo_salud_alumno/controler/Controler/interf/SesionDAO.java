package com.mycompany.sistema_de_monitoreo_salud_alumno.controler.Controler.interf;


import com.mycompany.sistema_de_monitoreo_salud_alumno.model.Sesion;
import java.util.Date;
import java.util.List;

public interface SesionDAO {
    void agregarSesion(Sesion sesion);
    void actualizarSesion(Sesion sesion);
    void eliminarSesion(int idSesion);
    Sesion obtenerSesion(int idSesion);
    List<Sesion> obtenerSesionesDeAlumno(int idAlumno);
    List<Sesion> obtenerSesionesEnFecha(Date fecha);
    List<Sesion> obtenerTodasLasSesiones();
    List<Sesion> obtenerFechasDisponiblesDeAlumno(int idAlumno);
    List<Sesion> obtenerFechasNoDisponiblesDeAlumno(int idAlumno);
    Sesion obtenerUltimaSesionAgregada(); 
}
