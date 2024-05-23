package com.mycompany.sistema_de_monitoreo_salud_alumno.controler.Controler.interf;


import com.mycompany.sistema_de_monitoreo_salud_alumno.model.EstadoSalud;
import java.util.Date;
import java.util.List;

public interface EstadoSaludDAO {
    // Método para agregar un nuevo estado de salud
    public void agregarEstadoSalud(EstadoSalud estadoSalud);
    
    // Método para actualizar los datos de un estado de salud existente
    public void actualizarEstadoSalud(EstadoSalud estadoSalud);
    
    // Método para eliminar un estado de salud existente
    public void eliminarEstadoSalud(int idEstadoSalud);
    
    // Método para obtener un estado de salud por su ID
    public EstadoSalud obtenerEstadoSalud(int idEstadoSalud);
    
    // Método para obtener todos los estados de salud de un alumno
    public List<EstadoSalud> obtenerEstadosSaludDeAlumno(int idAlumno);
    
    // Método para obtener todos los estados de salud en una fecha específica
    public List<EstadoSalud> obtenerEstadosSaludEnFecha(Date fecha);
    
    // Método para obtener todos los estados de salud de todos los alumnos
    public List<EstadoSalud> obtenerTodosLosEstadosSalud();
}
