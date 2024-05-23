package com.mycompany.sistema_de_monitoreo_salud_alumno.controler.Controler.interf;


import com.mycompany.sistema_de_monitoreo_salud_alumno.model.Traslado;
import java.util.Date;
import java.util.List;

public interface TrasladoDAO {
    // Método para agregar un nuevo traslado
    public void agregarTraslado(Traslado traslado);
    
    // Método para actualizar los datos de un traslado existente
    public void actualizarTraslado(Traslado traslado);
    
    // Método para eliminar un traslado existente
    public void eliminarTraslado(int idTraslado);
    
    // Método para obtener un traslado por su ID
    public Traslado obtenerTraslado(int idTraslado);
    
    // Método para obtener todos los traslados de un alumno
    public List<Traslado> obtenerTrasladosDeAlumno(int idAlumno);
    
    // Método para obtener todos los traslados de una enfermera
    public List<Traslado> obtenerTrasladosDeEnfermera(int idEnfermera);
    
    // Método para obtener todos los traslados en una fecha específica
    public List<Traslado> obtenerTrasladosEnFecha(Date fecha);
    
    // Método para obtener todos los traslados
    public List<Traslado> obtenerTodosLosTraslados();
}
