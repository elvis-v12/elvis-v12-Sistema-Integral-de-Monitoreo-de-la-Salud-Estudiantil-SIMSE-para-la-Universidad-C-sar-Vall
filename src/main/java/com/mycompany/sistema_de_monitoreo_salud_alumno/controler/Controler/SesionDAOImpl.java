package com.mycompany.sistema_de_monitoreo_salud_alumno.controler.Controler;

import com.mycompany.sistema_de_monitoreo_salud_alumno.controler.Controler.interf.SesionDAO;
import com.mycompany.sistema_de_monitoreo_salud_alumno.model.Sesion;
import com.mycompany.sistema_de_monitoreo_salud_alumno.model.Alumno;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SesionDAOImpl implements SesionDAO {

    private ConexionSQL conexionSQL;

    public SesionDAOImpl(ConexionSQL conexionSQL) {
        this.conexionSQL = conexionSQL;
    }

@Override
public void agregarSesion(Sesion sesion) {
    String query = "INSERT INTO Sesion (idAlumno, fechaInicio, fechaFin, disponible) VALUES (?, ?, ?, ?)";
    try (Connection conexion = conexionSQL.obtenerConexion();
         PreparedStatement statement = conexion.prepareStatement(query)) {
        statement.setInt(1, sesion.getAlumno().getIdAlumno());
        statement.setTimestamp(2, new java.sql.Timestamp(sesion.getFechaInicio().getTime()));
        statement.setTimestamp(3, new java.sql.Timestamp(sesion.getFechaFin().getTime()));
        statement.setBoolean(4, sesion.isDisponible());
        statement.executeUpdate();
        System.out.println("Sesión agregada.");
    } catch (SQLException e) {
        System.err.println("Error al agregar la sesión.");
        e.printStackTrace();
    }
}


@Override
public void actualizarSesion(Sesion sesion) {
    String query = "UPDATE Sesion SET idAlumno = ?, fechaInicio = ?, fechaFin = ?, disponible = ? WHERE idSesion = ?";
    try (Connection conexion = conexionSQL.obtenerConexion();
         PreparedStatement statement = conexion.prepareStatement(query)) {
        statement.setInt(1, sesion.getAlumno().getIdAlumno());
        statement.setDate(2, new java.sql.Date(sesion.getFechaInicio().getTime()));
        statement.setDate(3, new java.sql.Date(sesion.getFechaFin().getTime()));
        statement.setBoolean(4, sesion.isDisponible());
        statement.setInt(5, sesion.getIdSesion());
        statement.executeUpdate();
        System.out.println("Sesión actualizada.");
    } catch (SQLException e) {
        System.err.println("Error al actualizar la sesión.");
        e.printStackTrace();
    }
}
@Override
public Sesion obtenerUltimaSesionAgregada() {
    Sesion sesion = null;
    String query = "SELECT TOP 1 * FROM Sesion ORDER BY idSesion DESC";
    try (Connection conexion = conexionSQL.obtenerConexion();
         PreparedStatement statement = conexion.prepareStatement(query);
         ResultSet resultSet = statement.executeQuery()) {
        if (resultSet.next()) {
            Alumno alumno = obtenerAlumnoPorId(resultSet.getInt("idAlumno"));
            sesion = new Sesion(
                    resultSet.getInt("idSesion"),
                    alumno,
                    resultSet.getDate("fechaInicio"),
                    resultSet.getDate("fechaFin"),
                    resultSet.getBoolean("disponible")
            );
        }
    } catch (SQLException e) {
        System.err.println("Error al obtener la última sesión agregada.");
        e.printStackTrace();
    }
    return sesion;
}


    @Override
    public void eliminarSesion(int idSesion) {
        String query = "DELETE FROM Sesion WHERE idSesion = ?";
        try (Connection conexion = conexionSQL.obtenerConexion();
             PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setInt(1, idSesion);
            statement.executeUpdate();
            System.out.println("Sesión eliminada.");
        } catch (SQLException e) {
            System.err.println("Error al eliminar la sesión.");
            e.printStackTrace();
        }
    }

    @Override
    public Sesion obtenerSesion(int idSesion) {
        Sesion sesion = null;
        String query = "SELECT * FROM Sesion WHERE idSesion = ?";
        try (Connection conexion = conexionSQL.obtenerConexion();
             PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setInt(1, idSesion);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Alumno alumno = obtenerAlumnoPorId(resultSet.getInt("idAlumno"));
                    sesion = new Sesion(
                            resultSet.getInt("idSesion"),
                            alumno,
                            resultSet.getDate("fechaInicio"),
                            resultSet.getDate("fechaFin"),
                            resultSet.getBoolean("disponible")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener la sesión.");
            e.printStackTrace();
        }
        return sesion;
    }

    @Override
    public List<Sesion> obtenerSesionesDeAlumno(int idAlumno) {
        List<Sesion> sesionesDeAlumno = new ArrayList<>();
        String query = "SELECT * FROM Sesion WHERE idAlumno = ?";
        try (Connection conexion = conexionSQL.obtenerConexion();
             PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setInt(1, idAlumno);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Alumno alumno = obtenerAlumnoPorId(resultSet.getInt("idAlumno"));
                    Sesion sesion = new Sesion(
                            resultSet.getInt("idSesion"),
                            alumno,
                            resultSet.getDate("fechaInicio"),
                            resultSet.getDate("fechaFin"),
                            resultSet.getBoolean("disponible")
                    );
                    sesionesDeAlumno.add(sesion);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener las sesiones del alumno.");
            e.printStackTrace();
        }
        return sesionesDeAlumno;
    }

    @Override
public List<Sesion> obtenerSesionesEnFecha(Date fecha) {
    List<Sesion> sesionesEnFecha = new ArrayList<>();
    String query = "SELECT * FROM Sesion WHERE fechaInicio = ? OR fechaFin = ?";
    try (Connection conexion = conexionSQL.obtenerConexion();
         PreparedStatement statement = conexion.prepareStatement(query)) {
        statement.setDate(1, new java.sql.Date(fecha.getTime()));
        statement.setDate(2, new java.sql.Date(fecha.getTime()));
        try (ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Alumno alumno = obtenerAlumnoPorId(resultSet.getInt("idAlumno"));
                Sesion sesion = new Sesion(
                        resultSet.getInt("idSesion"),
                        alumno,
                        resultSet.getDate("fechaInicio"),
                        resultSet.getDate("fechaFin"),
                        resultSet.getBoolean("disponible")
                );
                sesionesEnFecha.add(sesion);
            }
        }
    } catch (SQLException e) {
        System.err.println("Error al obtener las sesiones en la fecha.");
        e.printStackTrace();
    }
    return sesionesEnFecha;
}


    @Override
public List<Sesion> obtenerTodasLasSesiones() {
    List<Sesion> todasLasSesiones = new ArrayList<>();
    String query = "SELECT * FROM Sesion";
    try (Connection conexion = conexionSQL.obtenerConexion();
         PreparedStatement statement = conexion.prepareStatement(query);
         ResultSet resultSet = statement.executeQuery()) {
        while (resultSet.next()) {
            Alumno alumno = obtenerAlumnoPorId(resultSet.getInt("idAlumno"));
            Sesion sesion = new Sesion(
                    resultSet.getInt("idSesion"),
                    alumno,
                    resultSet.getTimestamp("fechaInicio"),
                    resultSet.getTimestamp("fechaFin"),
                    resultSet.getBoolean("disponible")
            );
            todasLasSesiones.add(sesion);
        }
    } catch (SQLException e) {
        System.err.println("Error al obtener todas las sesiones.");
        e.printStackTrace();
    }
    return todasLasSesiones;
}

    public Alumno obtenerAlumnoPorId(int idAlumno) {
        Alumno alumno = null;
        String query = "SELECT * FROM Alumno WHERE idAlumno = ?";
        try (Connection conexion = conexionSQL.obtenerConexion();
             PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setInt(1, idAlumno);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    alumno = new Alumno(
                            resultSet.getInt("idAlumno"),
                            resultSet.getString("codigoAlumno"),
                            resultSet.getString("carrera"),
                            resultSet.getInt("idPersona")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener el alumno por ID.");
            e.printStackTrace();
        }
        return alumno;
    }

    // Método para obtener sesiones disponibles para un alumno específico
    @Override
    public List<Sesion> obtenerFechasDisponiblesDeAlumno(int idAlumno) {
        List<Sesion> fechasDisponibles = new ArrayList<>();
        String query = "SELECT * FROM Sesion WHERE idAlumno = ? AND disponible = 1";
        try (Connection conexion = conexionSQL.obtenerConexion();
             PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setInt(1, idAlumno);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Alumno alumno = obtenerAlumnoPorId(resultSet.getInt("idAlumno"));
                    Sesion sesion = new Sesion(
                            resultSet.getInt("idSesion"),
                            alumno,
                            resultSet.getDate("fechaInicio"),
                            resultSet.getDate("fechaFin"),
                            resultSet.getBoolean("disponible")
                    );
                    fechasDisponibles.add(sesion);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener las fechas disponibles del alumno.");
            e.printStackTrace();
        }
        return fechasDisponibles;
    }

    // Método para obtener sesiones no disponibles para un alumno específico
    @Override
    public List<Sesion> obtenerFechasNoDisponiblesDeAlumno(int idAlumno) {
        List<Sesion> fechasNoDisponibles = new ArrayList<>();
        String query = "SELECT * FROM Sesion WHERE idAlumno = ? AND disponible = 0";
        try (Connection conexion = conexionSQL.obtenerConexion();
             PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setInt(1, idAlumno);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Alumno alumno = obtenerAlumnoPorId(resultSet.getInt("idAlumno"));
                    Sesion sesion = new Sesion(
                            resultSet.getInt("idSesion"),
                            alumno,
                            resultSet.getDate("fechaInicio"),
                            resultSet.getDate("fechaFin"),
                            resultSet.getBoolean("disponible")
                    );
                    fechasNoDisponibles.add(sesion);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener las fechas no disponibles del alumno.");
            e.printStackTrace();
        }
        return fechasNoDisponibles;
    }
}
