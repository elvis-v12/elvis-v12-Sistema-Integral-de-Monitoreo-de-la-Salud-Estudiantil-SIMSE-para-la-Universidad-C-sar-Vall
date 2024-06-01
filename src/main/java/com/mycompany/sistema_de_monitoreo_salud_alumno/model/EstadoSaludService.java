package com.mycompany.sistema_de_monitoreo_salud_alumno.model;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document; // Import correcto para iText
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.mycompany.sistema_de_monitoreo_salud_alumno.controler.Controler.ConexionSQL;
import com.mycompany.sistema_de_monitoreo_salud_alumno.model.Alumno;
import com.mycompany.sistema_de_monitoreo_salud_alumno.model.EstadoSalud;
import com.mycompany.sistema_de_monitoreo_salud_alumno.model.Persona;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EstadoSaludService {
    private ConexionSQL conexionSQL;

    public EstadoSaludService(ConexionSQL conexionSQL) {
        this.conexionSQL = conexionSQL;
    }

    public List<EstadoSalud> obtenerEstadoSaludAlumnos() {
        List<EstadoSalud> estados = new ArrayList<>();
        String query = "SELECT es.idEstadoSalud, es.fecha, es.descripcion, a.idAlumno, a.codigoAlumno, a.carrera, a.ciclo, p.idPersona, p.nombre, p.apellido, p.edad " +
                       "FROM EstadoSalud es " +
                       "INNER JOIN Alumno a ON es.idAlumno = a.idAlumno " +
                       "INNER JOIN Persona p ON a.idPersona = p.idPersona";
        try (Connection conexion = ConexionSQL.obtenerConexion();
             PreparedStatement statement = conexion.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Persona persona = new Persona(
                    resultSet.getInt("idPersona"),
                    resultSet.getString("nombre"),
                    resultSet.getString("apellido"),
                    resultSet.getInt("edad")
                );
                Alumno alumno = new Alumno(
                    resultSet.getInt("idAlumno"),
                    resultSet.getString("codigoAlumno"),
                    resultSet.getString("carrera"),
                    resultSet.getInt("ciclo"),
                    persona.getIdPersona(),
                    persona.getNombre(),
                    persona.getApellido(),
                    persona.getEdad()
                );
                EstadoSalud estadoSalud = new EstadoSalud();
                estadoSalud.setIdEstadoSalud(resultSet.getInt("idEstadoSalud"));
                estadoSalud.setFecha(resultSet.getDate("fecha"));
                estadoSalud.setDescripcion(resultSet.getString("descripcion"));
                estadoSalud.setAlumno(alumno);
                estados.add(estadoSalud);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener los estados de salud de los alumnos");
            e.printStackTrace();
        }
        return estados;
    }

    public static void generarReporteEstadoSalud(String filePath, List<EstadoSalud> estados) {
        try {
            PdfWriter writer = new PdfWriter(filePath);
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document document = new Document(pdfDoc);

            document.add(new Paragraph("Reporte de Estado de Salud de Alumnos"));

            float[] columnWidths = {50, 100, 100, 100, 100, 100, 200};
            Table table = new Table(columnWidths);
            table.addHeaderCell("ID Estado");
            table.addHeaderCell("Código Alumno");
            table.addHeaderCell("Nombre");
            table.addHeaderCell("Carrera");
            table.addHeaderCell("Ciclo");
            table.addHeaderCell("Fecha");
            table.addHeaderCell("Descripción");

            for (EstadoSalud estado : estados) {
                table.addCell(String.valueOf(estado.getIdEstadoSalud()));
                table.addCell(estado.getAlumno().getCodigoAlumno());
                table.addCell(estado.getAlumno().getNombre() + " " + estado.getAlumno().getApellido());
                table.addCell(estado.getAlumno().getCarrera());
                table.addCell(String.valueOf(estado.getAlumno().getCiclo()));
                table.addCell(estado.getFecha().toString());
                table.addCell(estado.getDescripcion());
            }

            document.add(table);
            document.close();
            System.out.println("Reporte de estado de salud generado en: " + filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

