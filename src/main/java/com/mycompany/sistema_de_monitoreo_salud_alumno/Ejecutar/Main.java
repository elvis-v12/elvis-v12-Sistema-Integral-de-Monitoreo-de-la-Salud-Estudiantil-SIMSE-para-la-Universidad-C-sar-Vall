package com.mycompany.sistema_de_monitoreo_salud_alumno.Ejecutar;

import com.mycompany.sistema_de_monitoreo_salud_alumno.controler.Controler.ConexionSQL;
import com.mycompany.sistema_de_monitoreo_salud_alumno.model.ReportService;
import com.mycompany.sistema_de_monitoreo_salud_alumno.model.Sesion;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        ConexionSQL conexionSQL = new ConexionSQL();
        ReportService reportService = new ReportService(conexionSQL);

        int idAlumno = 1; // Replace with the actual idAlumno
        List<Sesion> sesionList = reportService.obtenerDatosReporte(idAlumno);
        String filePath = "reporte_estado_salud_alumnos.pdf";
        reportService.generarReporte(filePath, sesionList);
    }
}
