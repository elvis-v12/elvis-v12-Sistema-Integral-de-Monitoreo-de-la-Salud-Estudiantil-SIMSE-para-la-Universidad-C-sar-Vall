package com.mycompany.sistema_de_monitoreo_salud_alumno.model;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.mycompany.sistema_de_monitoreo_salud_alumno.controler.Controler.ConexionSQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class ReportService {
    private ConexionSQL conexionSQL;

    public ReportService(ConexionSQL conexionSQL) {
        this.conexionSQL = conexionSQL;
    }

    public List<Sesion> obtenerDatosReporte(int idAlumno) {
        List<Sesion> sesionList = new ArrayList<>();
        String query = "SELECT a.codigoAlumno, a.carrera, a.ciclo, "
                + "p.nombre, p.apellido, p.edad, "
                + "s.fechaInicio, s.fechaFin "
                + "FROM EstadoSalud es "
                + "INNER JOIN Alumno a ON es.idAlumno = a.idAlumno "
                + "INNER JOIN Persona p ON a.idPersona = p.idPersona "
                + "INNER JOIN Sesion s ON s.idAlumno = a.idAlumno "
                + "WHERE a.idAlumno = ?";

        try (Connection conexion = ConexionSQL.obtenerConexion();
             PreparedStatement statement = conexion.prepareStatement(query)) {
             
            statement.setInt(1, idAlumno);
            
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Persona persona = new Persona(
                        resultSet.getString("nombre"),
                        resultSet.getString("apellido"),
                        resultSet.getInt("edad")
                    );

                    Alumno alumno = new Alumno(
                        resultSet.getString("codigoAlumno"),
                        resultSet.getString("carrera"),
                        resultSet.getInt("ciclo"),
                        persona.getNombre(),
                        persona.getApellido(),
                        persona.getEdad()
                    );

                    Sesion sesion = new Sesion(
                        0,  // No tenemos idSesion en la consulta, se puede omitir o ajustar según necesidad
                        alumno,
                        resultSet.getDate("fechaInicio"),
                        resultSet.getDate("fechaFin")
                    );

                    sesionList.add(sesion);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener los datos del reporte");
            e.printStackTrace();
        }
        return sesionList;
    }

    public static void generarReporte(String filePath, List<Sesion> sesionList) {
        try {
            PdfWriter writer = new PdfWriter(filePath);
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document document = new Document(pdfDoc);
            document.add(new Paragraph("Reporte de Estado de Salud de Alumnos"));

            float[] columnWidths = {100, 100, 50, 100, 100, 50, 100, 100};
            Table table = new Table(columnWidths);
            table.addHeaderCell("Código Alumno");
            table.addHeaderCell("Carrera");
            table.addHeaderCell("Ciclo");
            table.addHeaderCell("Nombre");
            table.addHeaderCell("Apellido");
            table.addHeaderCell("Edad");
            table.addHeaderCell("Fecha Inicio Sesión");
            table.addHeaderCell("Fecha Fin Sesión");

            for (Sesion sesion : sesionList) {
                Alumno alumno = sesion.getAlumno();
                table.addCell(alumno.getCodigoAlumno());
                table.addCell(alumno.getCarrera());
                table.addCell(String.valueOf(alumno.getCiclo()));
                table.addCell(alumno.getNombre());
                table.addCell(alumno.getApellido());
                table.addCell(String.valueOf(alumno.getEdad()));
                table.addCell(sesion.getFechaInicio().toString());
                table.addCell(sesion.getFechaFin().toString());
            }

            document.add(table);
            document.close();
            System.out.println("Reporte generado en: " + filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
