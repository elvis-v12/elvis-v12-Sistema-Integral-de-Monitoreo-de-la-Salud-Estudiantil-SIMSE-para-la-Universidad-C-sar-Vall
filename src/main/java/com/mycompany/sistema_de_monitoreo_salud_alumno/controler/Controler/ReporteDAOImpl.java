package com.mycompany.sistema_de_monitoreo_salud_alumno.controler.Controler;

import com.mycompany.sistema_de_monitoreo_salud_alumno.controler.Controler.interf.AlumnoDAO;
import com.mycompany.sistema_de_monitoreo_salud_alumno.controler.Controler.interf.EnfermeraDAO;
import com.mycompany.sistema_de_monitoreo_salud_alumno.controler.Controler.interf.ReporteDAO;
import com.mycompany.sistema_de_monitoreo_salud_alumno.model.Alumno;
import com.mycompany.sistema_de_monitoreo_salud_alumno.model.Enfermera;
import com.mycompany.sistema_de_monitoreo_salud_alumno.model.Reporte;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReporteDAOImpl implements ReporteDAO {

    private ConexionSQL conexionSQL;

    public ReporteDAOImpl(ConexionSQL conexionSQL) {
        this.conexionSQL = conexionSQL;
    }

    @Override
    public void agregarReporte(Reporte reporte) {
        String query = "INSERT INTO Reportes (idReporte, idAlumno, idEnfermera, fecha, descripcion) VALUES (?, ?, ?, ?, ?)";
        try (Connection conexion = conexionSQL.obtenerConexion();
             PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setInt(1, reporte.getIdReporte());
            statement.setInt(2, reporte.getAlumno().getIdAlumno());
            statement.setInt(3, reporte.getEnfermera().getIdEnfermera());
            statement.setDate(4, new java.sql.Date(reporte.getFecha().getTime()));
            statement.setString(5, reporte.getDescripcion());
            statement.executeUpdate();
            System.out.println("Reporte agregado: " + reporte.getIdReporte());
        } catch (SQLException e) {
            System.err.println("Error al agregar el reporte: " + reporte.getIdReporte());
            e.printStackTrace();
        }
    }

    @Override
    public void actualizarReporte(Reporte reporte) {
        String query = "UPDATE Reportes SET idAlumno = ?, idEnfermera = ?, fecha = ?, descripcion = ? WHERE idReporte = ?";
        try (Connection conexion = conexionSQL.obtenerConexion();
             PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setInt(1, reporte.getAlumno().getIdAlumno());
            statement.setInt(2, reporte.getEnfermera().getIdEnfermera());
            statement.setDate(3, new java.sql.Date(reporte.getFecha().getTime()));
            statement.setString(4, reporte.getDescripcion());
            statement.setInt(5, reporte.getIdReporte());
            statement.executeUpdate();
            System.out.println("Reporte actualizado: " + reporte.getIdReporte());
        } catch (SQLException e) {
            System.err.println("Error al actualizar el reporte: " + reporte.getIdReporte());
            e.printStackTrace();
        }
    }

    @Override
    public void eliminarReporte(int idReporte) {
        String query = "DELETE FROM Reportes WHERE idReporte = ?";
        try (Connection conexion = conexionSQL.obtenerConexion();
             PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setInt(1, idReporte);
            statement.executeUpdate();
            System.out.println("Reporte eliminado: " + idReporte);
        } catch (SQLException e) {
            System.err.println("Error al eliminar el reporte: " + idReporte);
            e.printStackTrace();
        }
    }

    @Override
    public Reporte obtenerReporte(int idReporte) {
        Reporte reporte = null;
        String query = "SELECT * FROM Reportes WHERE idReporte = ?";
        try (Connection conexion = conexionSQL.obtenerConexion();
             PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setInt(1, idReporte);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    // Aquí asumo que tienes métodos para obtener Alumno y Enfermera por ID
                    // Puedes ajustar esto según la estructura de tu base de datos y código existente
                    AlumnoDAO alumnoDAO = new AlumnoDAOImpl(conexionSQL);
                    Alumno alumno = alumnoDAO.obtenerAlumnoPorId(resultSet.getInt("idAlumno"));
                    EnfermeraDAO enfermeraDAO = new EnfermeraDAOImpl(conexionSQL); Enfermera enfermera = enfermeraDAO.obtenerEnfermeraPorId(resultSet.getInt("idEnfermera"));
                    reporte = new Reporte(
                            resultSet.getInt("idReporte"),
                            alumno,
                            enfermera,
                            resultSet.getDate("fecha"),
                            resultSet.getString("descripcion")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener el reporte: " + idReporte);
            e.printStackTrace();
        }
        return reporte;
    }

    @Override
    public List<Reporte> obtenerReportesDeAlumno(int idAlumno) {
        List<Reporte> reportesDeAlumno = new ArrayList<>();
        String query = "SELECT * FROM Reportes WHERE idAlumno = ?";
        try (Connection conexion = conexionSQL.obtenerConexion();
             PreparedStatement statement =
conexion.prepareStatement(query)) {
            statement.setInt(1, idAlumno);
            try (ResultSet resultSet = statement.executeQuery()) {
                AlumnoDAO alumnoDAO = new AlumnoDAOImpl(conexionSQL);
                EnfermeraDAO enfermeraDAO = new EnfermeraDAOImpl(conexionSQL);
                while (resultSet.next()) {
                    Alumno alumno = alumnoDAO.obtenerAlumnoPorId(resultSet.getInt("idAlumno"));
                    Enfermera enfermera = enfermeraDAO.obtenerEnfermeraPorId(resultSet.getInt("idEnfermera"));
                    Reporte reporte = new Reporte(
                            resultSet.getInt("idReporte"),
                            alumno,
                            enfermera,
                            resultSet.getDate("fecha"),
                            resultSet.getString("descripcion")
                    );
                    reportesDeAlumno.add(reporte);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener los reportes del alumno: " + idAlumno);
            e.printStackTrace();
        }
        return reportesDeAlumno;
    }

    @Override
    public List<Reporte> obtenerReportesDeEnfermera(int idEnfermera) {
        List<Reporte> reportesDeEnfermera = new ArrayList<>();
        String query = "SELECT * FROM Reportes WHERE idEnfermera = ?";
        try (Connection conexion = conexionSQL.obtenerConexion();
             PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setInt(1, idEnfermera);
            try (ResultSet resultSet = statement.executeQuery()) {
                AlumnoDAO alumnoDAO = new AlumnoDAOImpl(conexionSQL);
                EnfermeraDAO enfermeraDAO = new EnfermeraDAOImpl(conexionSQL);
                while (resultSet.next()) {
                    Alumno alumno = alumnoDAO.obtenerAlumnoPorId(resultSet.getInt("idAlumno"));
                    Enfermera enfermera = enfermeraDAO.obtenerEnfermeraPorId(resultSet.getInt("idEnfermera"));
                    Reporte reporte = new Reporte(
                            resultSet.getInt("idReporte"),
                            alumno,
                            enfermera,
                            resultSet.getDate("fecha"),
                            resultSet.getString("descripcion")
                    );
                    reportesDeEnfermera.add(reporte);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener los reportes de la enfermera: " + idEnfermera);
            e.printStackTrace();
        }
        return reportesDeEnfermera;
    }

    @Override
    public List<Reporte> obtenerReportesEnFecha(Date fecha) {
        List<Reporte> reportesEnFecha = new ArrayList<>();
        String query = "SELECT * FROM Reportes WHERE fecha = ?";
        try (Connection conexion = conexionSQL.obtenerConexion();
             PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setDate(1, new java.sql.Date(fecha.getTime()));
            try (ResultSet resultSet = statement.executeQuery()) {
                AlumnoDAO alumnoDAO = new AlumnoDAOImpl(conexionSQL);
                EnfermeraDAO enfermeraDAO = new EnfermeraDAOImpl(conexionSQL);
                while (resultSet.next()) {
                    Alumno alumno = alumnoDAO.obtenerAlumnoPorId(resultSet.getInt("idAlumno"));
                    Enfermera enfermera = enfermeraDAO.obtenerEnfermeraPorId(resultSet.getInt("idEnfermera"));
                    Reporte reporte = new Reporte(
                            resultSet.getInt("idReporte"),
                            alumno,
                            enfermera,
                            resultSet.getDate("fecha"),
                            resultSet.getString("descripcion")
                    );
                    reportesEnFecha.add(reporte);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener los reportes en la fecha: " + fecha);
            e.printStackTrace();
        }
        return reportesEnFecha;
    }

    @Override
    public List<Reporte> obtenerTodosLosReportes() {
        List<Reporte> todosLosReportes = new ArrayList<>();
        String query = "SELECT * FROM Reportes";
        try (Connection conexion = conexionSQL.obtenerConexion();
             PreparedStatement statement = conexion.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            AlumnoDAO alumnoDAO = new AlumnoDAOImpl(conexionSQL);
            EnfermeraDAO enfermeraDAO = new EnfermeraDAOImpl(conexionSQL);
            while (resultSet.next()) {
                Alumno alumno = alumnoDAO.obtenerAlumnoPorId(resultSet.getInt("idAlumno"));
                Enfermera enfermera = enfermeraDAO.obtenerEnfermeraPorId(resultSet.getInt("idEnfermera"));
                Reporte reporte = new Reporte(
                        resultSet.getInt("idReporte"),
                        alumno,
                        enfermera,
                        resultSet.getDate("fecha"),
                        resultSet.getString("descripcion")
                );
                todosLosReportes.add(reporte);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener todos los reportes");
            e.printStackTrace();
        }
        return todosLosReportes;
    }
}
