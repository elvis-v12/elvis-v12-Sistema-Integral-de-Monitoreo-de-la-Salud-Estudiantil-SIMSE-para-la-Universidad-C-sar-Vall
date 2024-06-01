package com.mycompany.sistema_de_monitoreo_salud_alumno.Ejecutar;

import com.mycompany.sistema_de_monitoreo_salud_alumno.controler.Controler.ConexionSQL;
import com.mycompany.sistema_de_monitoreo_salud_alumno.model.EstadoSalud;
import com.mycompany.sistema_de_monitoreo_salud_alumno.model.EstadoSaludService;
import java.util.List;


public class Main {
    public static void main(String[] args) {
       ConexionSQL conexionSQL = new ConexionSQL();
        EstadoSaludService estadoSaludService = new EstadoSaludService(conexionSQL);

        // Obtener la lista de estados de salud de los alumnos
        List<EstadoSalud> estados = estadoSaludService.obtenerEstadoSaludAlumnos();

        // Generar el reporte de estado de salud en formato PDF
        EstadoSaludService.generarReporteEstadoSalud("reporte_estado_salud.pdf", estados);
}
}