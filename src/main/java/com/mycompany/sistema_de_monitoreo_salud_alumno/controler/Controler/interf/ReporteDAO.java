package com.mycompany.sistema_de_monitoreo_salud_alumno.controler.Controler.interf;


import com.mycompany.sistema_de_monitoreo_salud_alumno.model.Reporte;
import java.util.Date;
import java.util.List;

public interface ReporteDAO {
    // Método para agregar un nuevo reporte
    public void agregarReporte(Reporte reporte);
    
    // Método para actualizar los datos de un reporte existente
    public void actualizarReporte(Reporte reporte);
    
    // Método para eliminar un reporte existente
    public void eliminarReporte(int idReporte);
    
    // Método para obtener un reporte por su ID
    public Reporte obtenerReporte(int idReporte);
    
    // Método para obtener todos los reportes de un alumno
    public List<Reporte> obtenerReportesDeAlumno(int idAlumno);
    
    // Método para obtener todos los reportes de una enfermera
    public List<Reporte> obtenerReportesDeEnfermera(int idEnfermera);
    
    // Método para obtener todos los reportes en una fecha específica
    public List<Reporte> obtenerReportesEnFecha(Date fecha);
    
    // Método para obtener todos los reportes
    public List<Reporte> obtenerTodosLosReportes();
}
