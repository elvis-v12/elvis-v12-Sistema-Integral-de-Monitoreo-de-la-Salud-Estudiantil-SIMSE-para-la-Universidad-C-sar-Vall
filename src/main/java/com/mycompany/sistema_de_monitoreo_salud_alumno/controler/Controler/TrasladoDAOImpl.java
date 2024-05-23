package com.mycompany.sistema_de_monitoreo_salud_alumno.controler.Controler;

import com.mycompany.sistema_de_monitoreo_salud_alumno.controler.Controler.interf.AlumnoDAO;
import com.mycompany.sistema_de_monitoreo_salud_alumno.controler.Controler.interf.EnfermeraDAO;
import com.mycompany.sistema_de_monitoreo_salud_alumno.controler.Controler.interf.TrasladoDAO;
import com.mycompany.sistema_de_monitoreo_salud_alumno.model.Alumno;
import com.mycompany.sistema_de_monitoreo_salud_alumno.model.Enfermera;
import com.mycompany.sistema_de_monitoreo_salud_alumno.model.Traslado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TrasladoDAOImpl implements TrasladoDAO {
    private final ConexionSQL conexionSQL;

    public TrasladoDAOImpl(ConexionSQL conexionSQL) {
        this.conexionSQL = conexionSQL;
    }

   @Override
public void agregarTraslado(Traslado traslado) {
    String query = "INSERT INTO Traslados (idTraslado, idAlumno, idEnfermera, fechaInicio, fechaFin) VALUES (?, ?, ?, ?, ?)";
    try (Connection conexion = conexionSQL.obtenerConexion();
         PreparedStatement statement = conexion.prepareStatement(query)) {
        statement.setInt(1, traslado.getIdTraslado());
        statement.setInt(2, traslado.getAlumno().getIdAlumno());
        statement.setInt(3, traslado.getEnfermera().getIdEnfermera());
        statement.setDate(4, new java.sql.Date(traslado.getFechaInicio().getTime()));
        statement.setDate(5, new java.sql.Date(traslado.getFechaFin().getTime()));
        statement.executeUpdate();
        System.out.println("Traslado agregado: " + traslado.getIdTraslado());
    } catch (SQLException e) {
        System.err.println("Error al agregar el traslado: " + traslado.getIdTraslado());
        e.printStackTrace();
    }
}

@Override
public void actualizarTraslado(Traslado traslado) {
    String query = "UPDATE Traslados SET idAlumno = ?, idEnfermera = ?, fechaInicio = ?, fechaFin = ? WHERE idTraslado = ?";
    try (Connection conexion = conexionSQL.obtenerConexion();
         PreparedStatement statement = conexion.prepareStatement(query)) {
        statement.setInt(1, traslado.getAlumno().getIdAlumno());
        statement.setInt(2, traslado.getEnfermera().getIdEnfermera());
        statement.setDate(3, new java.sql.Date(traslado.getFechaInicio().getTime()));
        statement.setDate(4, new java.sql.Date(traslado.getFechaFin().getTime()));
        statement.setInt(5, traslado.getIdTraslado());
        statement.executeUpdate();
        System.out.println("Traslado actualizado: " + traslado.getIdTraslado());
    } catch (SQLException e) {
        System.err.println("Error al actualizar el traslado: " + traslado.getIdTraslado());
        e.printStackTrace();
    }
}

@Override
public Traslado obtenerTraslado(int idTraslado) {
    Traslado traslado = null;
    String query = "SELECT * FROM Traslados WHERE idTraslado = ?";
    try (Connection conexion = conexionSQL.obtenerConexion();
         PreparedStatement statement = conexion.prepareStatement(query)) {
        statement.setInt(1, idTraslado);
        try (ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                // Obtener el alumno y la enfermera desde las clases DAO correspondientes
                AlumnoDAO alumnoDAO = new AlumnoDAOImpl(conexionSQL);
                EnfermeraDAO enfermeraDAO = new EnfermeraDAOImpl(conexionSQL);

                Alumno alumno = alumnoDAO.obtenerAlumnoPorId(resultSet.getInt("idAlumno"));
                Enfermera enfermera = enfermeraDAO.obtenerEnfermeraPorId(resultSet.getInt("idEnfermera"));

                traslado = new Traslado(
                        resultSet.getInt("idTraslado"),
                        alumno,
                        enfermera,
                        resultSet.getDate("fechaInicio"),
                        resultSet.getDate("fechaFin")
                );
            }
        }
    } catch (SQLException e) {
        System.err.println("Error al obtener el traslado: " + idTraslado);
        e.printStackTrace();
    }
    return traslado;
}

@Override
public List<Traslado> obtenerTrasladosDeAlumno(int idAlumno) {
    List<Traslado> trasladosDeAlumno = new ArrayList<>();
    String query = "SELECT * FROM Traslados WHERE idAlumno = ?";
    try (Connection conexion = conexionSQL.obtenerConexion();
         PreparedStatement statement = conexion.prepareStatement(query)) {
        statement.setInt(1, idAlumno);
        try (ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                // Obtener el alumno y la enfermera desde las clases DAO correspondientes
                AlumnoDAO alumnoDAO = new AlumnoDAOImpl(conexionSQL);
                EnfermeraDAO enfermeraDAO = new EnfermeraDAOImpl(conexionSQL);

                Alumno alumno = alumnoDAO.obtenerAlumnoPorId(resultSet.getInt("idAlumno"));
                Enfermera enfermera = enfermeraDAO.obtenerEnfermeraPorId(resultSet.getInt("idEnfermera"));

                Traslado traslado = new Traslado(
                        resultSet.getInt("idTraslado"),
                        alumno,
                        enfermera,
                        resultSet.getDate("fechaInicio"),
                        resultSet.getDate("fechaFin")
                );
                trasladosDeAlumno.add(traslado);
            }
        }
    } catch (SQLException e) {
        System.err.println("Error al obtener los traslados del alumno: " + idAlumno);
        e.printStackTrace();
    }
    return trasladosDeAlumno;
}

@Override
public List<Traslado> obtenerTrasladosDeEnfermera(int idEnfermera) {
    List<Traslado> trasladosDeEnfermera = new ArrayList<>();
    String query = "SELECT * FROM Traslados WHERE idEnfermera = ?";
    try (Connection conexion = conexionSQL.obtenerConexion();
         PreparedStatement statement = conexion.prepareStatement(query)) {
        statement.setInt(1, idEnfermera);
        try (ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                // Obtener el alumno y la enfermera desde las clases DAO correspondientes
                AlumnoDAO alumnoDAO = new AlumnoDAOImpl(conexionSQL);
                EnfermeraDAO enfermeraDAO = new EnfermeraDAOImpl(conexionSQL);

                Alumno alumno = alumnoDAO.obtenerAlumnoPorId(resultSet.getInt("idAlumno"));
                Enfermera enfermera = enfermeraDAO.obtenerEnfermeraPorId(resultSet.getInt("idEnfermera"));

                Traslado traslado = new Traslado(
                        resultSet.getInt("idTraslado"),
                        alumno,
                        enfermera,
                        resultSet.getDate("fechaInicio"),
                        resultSet.getDate("fechaFin")
                );
                trasladosDeEnfermera.add(traslado);
            }
        }
    } catch (SQLException e) {
        System.err.println("Error al obtener los traslados de la enfermera: " + idEnfermera);
        e.printStackTrace();
    }
    return trasladosDeEnfermera;
}

@Override
public List<Traslado> obtenerTrasladosEnFecha(Date fecha) {
    List<Traslado> trasladosEnFecha = new ArrayList<>();
    String query = "SELECT * FROM Traslados WHERE fechaInicio = ? OR fechaFin = ?";
    try (Connection conexion = conexionSQL.obtenerConexion();
         PreparedStatement statement = conexion.prepareStatement(query)) {
        statement.setDate(1, new java.sql.Date(fecha.getTime()));
        statement.setDate(2, new java.sql.Date(fecha.getTime()));
        try (ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                // Obtener el alumno y la enfermera desde las clases DAO correspondientes
                AlumnoDAO alumnoDAO = new AlumnoDAOImpl(conexionSQL);
                EnfermeraDAO enfermeraDAO = new EnfermeraDAOImpl(conexionSQL);

                Alumno alumno = alumnoDAO.obtenerAlumnoPorId(resultSet.getInt("idAlumno"));
                Enfermera enfermera = enfermeraDAO.obtenerEnfermeraPorId(resultSet.getInt("idEnfermera"));

                Traslado traslado = new Traslado(
                        resultSet.getInt("idTraslado"),
                        alumno,
                        enfermera,
                        resultSet.getDate("fechaInicio"),
                        resultSet.getDate("fechaFin")
                );
                trasladosEnFecha.add(traslado);
            }
        }
    } catch (SQLException e) {
        System.err.println("Error al obtener los traslados en la fecha: " + fecha);
        e.printStackTrace();
    }
    return trasladosEnFecha;
}

@Override
public List<Traslado> obtenerTodosLosTraslados() {
    List<Traslado> todosLosTraslados = new ArrayList<>();
    String query = "SELECT * FROM Traslados";
    try (Connection conexion = conexionSQL.obtenerConexion();
         PreparedStatement statement = conexion.prepareStatement(query);
         ResultSet resultSet = statement.executeQuery()) {
        while (resultSet.next()) {
            // Obtener el alumno y la enfermera desde las clases DAO correspondientes
            AlumnoDAO alumnoDAO = new AlumnoDAOImpl(conexionSQL);
            EnfermeraDAO enfermeraDAO = new EnfermeraDAOImpl(conexionSQL);

            Alumno alumno = alumnoDAO.obtenerAlumnoPorId(resultSet.getInt("idAlumno"));
            Enfermera enfermera = enfermeraDAO.obtenerEnfermeraPorId(resultSet.getInt("idEnfermera"));

            Traslado traslado = new Traslado(
                    resultSet.getInt("idTraslado"),
                    alumno,
                    enfermera,
                    resultSet.getDate("fechaInicio"),
                    resultSet.getDate("fechaFin")
            );
            todosLosTraslados.add(traslado);
        }
    } catch (SQLException e) {
        System.err.println("Error al obtener todos los traslados");
        e.printStackTrace();
    }
    return todosLosTraslados;
}

    @Override
public void eliminarTraslado(int idTraslado) {
    String query = "DELETE FROM Traslados WHERE idTraslado = ?";
    try (Connection conexion = conexionSQL.obtenerConexion();
         PreparedStatement statement = conexion.prepareStatement(query)) {
        statement.setInt(1, idTraslado);
        int filasEliminadas = statement.executeUpdate();
        if (filasEliminadas > 0) {
            System.out.println("Traslado eliminado correctamente: " + idTraslado);
        } else {
            System.out.println("No se encontró ningún traslado con el ID especificado: " + idTraslado);
        }
    } catch (SQLException e) {
        System.err.println("Error al eliminar el traslado: " + idTraslado);
        e.printStackTrace();
    }
}
}

