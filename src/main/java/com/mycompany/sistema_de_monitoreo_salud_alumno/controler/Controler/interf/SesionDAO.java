package com.mycompany.sistema_de_monitoreo_salud_alumno.controler.Controler.interf;


import com.mycompany.sistema_de_monitoreo_salud_alumno.model.Sesion;
import java.util.Date;
import java.util.List;

public interface SesionDAO {
    // Método para agregar una nueva sesión
    public void agregarSesion(Sesion sesion);
    
    // Método para actualizar los datos de una sesión existente
    public void actualizarSesion(Sesion sesion);
    
    // Método para eliminar una sesión existente
    public void eliminarSesion(int idSesion);
    
    // Método para obtener una sesión por su ID
    public Sesion obtenerSesion(int idSesion);
    
    // Método para obtener todas las sesiones de un usuario
    public List<Sesion>obtenerSesionesDeAlumno(int idAlumno);
    
    // Método para obtener todas las sesiones en una fecha específica
    public List<Sesion> obtenerSesionesEnFecha(Date fecha);
    
    // Método para obtener todas las sesiones
    public List<Sesion> obtenerTodasLasSesiones();
}
