package com.mycompany.sistema_de_monitoreo_salud_alumno.model;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.mycompany.sistema_de_monitoreo_salud_alumno.controler.Controler.ConexionSQL;
import com.mycompany.sistema_de_monitoreo_salud_alumno.controler.Controler.interf.AlumnoDAO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReporteGenerar implements AlumnoDAO {

    private ConexionSQL conexionSQL;

    public ReporteGenerar(ConexionSQL conexionSQL) {
        this.conexionSQL = conexionSQL;
    }

    @Override
    public List<Sesion> obtenerTodasLasSesiones() {
        List<Sesion> todasLasSesiones = new ArrayList<>();
        String query = "SELECT s.idSesion, s.idAlumno, s.fechaInicio, s.fechaFin, s.disponible, " +
                       "a.idAlumno, a.codigoAlumno, a.carrera, a.ciclo, p.idPersona, p.nombre, p.apellido, p.edad " +
                       "FROM Sesion s " +
                       "INNER JOIN Alumno a ON s.idAlumno = a.idAlumno " +
                       "INNER JOIN Persona p ON a.idPersona = p.idPersona;";

        try (Connection conexion = conexionSQL.obtenerConexion();
             PreparedStatement statement = conexion.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Alumno alumno = new Alumno(
                        resultSet.getInt("idAlumno"),
                        resultSet.getString("codigoAlumno"),
                        resultSet.getString("carrera"),
                        resultSet.getInt("ciclo"),
                        resultSet.getInt("idPersona"),
                        resultSet.getString("nombre"),
                        resultSet.getString("apellido"),
                        resultSet.getInt("edad")
                );
                Sesion sesion = new Sesion(
                        resultSet.getInt("idSesion"),
                        alumno,
                        resultSet.getDate("fechaInicio"),
                        resultSet.getDate("fechaFin"),
                        resultSet.getBoolean("disponible")
                );
                todasLasSesiones.add(sesion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return todasLasSesiones;
    }

    public void generarReporteSesionesPDF(String filePath) {
        List<Sesion> sesiones = obtenerTodasLasSesiones();

        try {
            PdfWriter writer = new PdfWriter(filePath);
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document document = new Document(pdfDoc);

            document.add(new Paragraph("Reporte de Sesiones"));

            float[] columnWidths = {50, 100, 100, 100, 100, 50, 100, 100, 50};
            Table table = new Table(columnWidths);
            table.addHeaderCell("ID Sesión");
            table.addHeaderCell("ID Alumno");
            table.addHeaderCell("Fecha Inicio");
            table.addHeaderCell("Fecha Fin");
            table.addHeaderCell("Disponible");
            table.addHeaderCell("ID Persona");
            table.addHeaderCell("Nombre");
            table.addHeaderCell("Apellido");
            table.addHeaderCell("Edad");

            for (Sesion sesion : sesiones) {
                table.addCell(String.valueOf(sesion.getIdSesion()));
                table.addCell(String.valueOf(sesion.getAlumno().getIdAlumno()));
                table.addCell(sesion.getFechaInicio().toString());
                table.addCell(sesion.getFechaFin().toString());
                table.addCell(String.valueOf(sesion.isDisponible()));
                table.addCell(String.valueOf(sesion.getAlumno().getIdPersona()));
                table.addCell(sesion.getAlumno().getNombre());
                table.addCell(sesion.getAlumno().getApellido());
                table.addCell(String.valueOf(sesion.getAlumno().getEdad()));
            }

            document.add(table);
            document.close();
            System.out.println("Reporte de sesiones generado en: " + filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void agregarAlumno(Alumno alumno) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void actualizarAlumno(Alumno alumno) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminarAlumno(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Alumno obtenerAlumnoPorId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Alumno> obtenerTodosLosAlumnos() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
