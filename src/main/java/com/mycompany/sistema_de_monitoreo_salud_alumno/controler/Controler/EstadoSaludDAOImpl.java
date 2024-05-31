package com.mycompany.sistema_de_monitoreo_salud_alumno.controler.Controler;

import com.mycompany.sistema_de_monitoreo_salud_alumno.controler.Controler.interf.AlumnoDAO;
import com.mycompany.sistema_de_monitoreo_salud_alumno.controler.Controler.interf.EstadoSaludDAO;
import com.mycompany.sistema_de_monitoreo_salud_alumno.model.EstadoSalud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public  class EstadoSaludDAOImpl implements EstadoSaludDAO {
    private ConexionSQL conexionSQL;

    public EstadoSaludDAOImpl(ConexionSQL conexionSQL) {
        this.conexionSQL = conexionSQL;
    }

    @Override
    public void agregarEstadoSalud(EstadoSalud estadoSalud) {
        String query = "INSERT INTO EstadoSalud (idAlumno, fecha, descripcion) VALUES (?, ?, ?)";
        try (Connection conexion = conexionSQL.obtenerConexion();
             PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setInt(1, estadoSalud.getAlumno().getIdAlumno());
            statement.setDate(2, new java.sql.Date(estadoSalud.getFecha().getTime()));
            statement.setString(3, estadoSalud.getDescripcion());
            statement.executeUpdate();
            System.out.println("Estado de salud agregado: " + estadoSalud.getIdEstadoSalud());
        } catch (SQLException e) {
            System.err.println("Error al agregar el estado de salud: " + estadoSalud.getIdEstadoSalud());
            e.printStackTrace();
        }
    }

    @Override
    public void actualizarEstadoSalud(EstadoSalud estadoSalud) {
        String query = "UPDATE EstadoSalud SET idAlumno = ?, fecha = ?, descripcion = ? WHERE idEstadoSalud = ?";
        try (Connection conexion = conexionSQL.obtenerConexion();
             PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setInt(1, estadoSalud.getAlumno().getIdAlumno());
            statement.setDate(2, new java.sql.Date(estadoSalud.getFecha().getTime()));
            statement.setString(3, estadoSalud.getDescripcion());
            statement.setInt(4, estadoSalud.getIdEstadoSalud());
            statement.executeUpdate();
            System.out.println("Estado de salud actualizado: " + estadoSalud.getIdEstadoSalud());
        } catch (SQLException e) {
            System.err.println("Error al actualizar el estado de salud: " + estadoSalud.getIdEstadoSalud());
            e.printStackTrace();
        }
    }

    @Override
    public void eliminarEstadoSalud(int idEstadoSalud) {
        String query = "DELETE FROM EstadoSalud WHERE idEstadoSalud = ?";
        try (Connection conexion = conexionSQL.obtenerConexion();
             PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setInt(1, idEstadoSalud);
            statement.executeUpdate();
            System.out.println("Estado de salud eliminado: " + idEstadoSalud);
        } catch (SQLException e) {
            System.err.println("Error al eliminar el estado de salud: " + idEstadoSalud);
            e.printStackTrace();
        }
    }

    @Override
    public EstadoSalud obtenerEstadoSalud(int idEstadoSalud) {
        EstadoSalud estadoSalud = null;
        String query = "SELECT * FROM EstadoSalud WHERE idEstadoSalud = ?";
        try (Connection conexion = conexionSQL.obtenerConexion();
             PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setInt(1, idEstadoSalud);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    estadoSalud = new EstadoSalud();
                    estadoSalud.setIdEstadoSalud(resultSet.getInt("idEstadoSalud"));
                    AlumnoDAO alumnoDAO = new AlumnoDAOImpl(conexionSQL);
                    estadoSalud.setAlumno(alumnoDAO.obtenerAlumnoPorId(resultSet.getInt("idAlumno")));
                    estadoSalud.setFecha(resultSet.getDate("fecha"));
                    estadoSalud.setDescripcion(resultSet.getString("descripcion"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener el estado de salud: " + idEstadoSalud);
            e.printStackTrace();
        }
        return estadoSalud;
    }

    @Override
    public List<EstadoSalud> obtenerEstadosSaludDeAlumno(int idAlumno) {
        List<EstadoSalud> estadosSaludDeAlumno = new ArrayList<>();
        String query = "SELECT * FROM EstadoSalud WHERE idAlumno = ?";
        try (Connection conexion = conexionSQL.obtenerConexion();
             PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setInt(1, idAlumno);
            try (ResultSet resultSet = statement.executeQuery()) {
                AlumnoDAO alumnoDAO = new AlumnoDAOImpl(conexionSQL);
                while (resultSet.next()) {
                    EstadoSalud estadoSalud = new EstadoSalud();
                    estadoSalud.setIdEstadoSalud(resultSet.getInt("idEstadoSalud"));
                    estadoSalud.setAlumno(alumnoDAO.obtenerAlumnoPorId(resultSet.getInt("idAlumno")));
                    estadoSalud.setFecha(resultSet.getDate("fecha"));
                    estadoSalud.setDescripcion(resultSet.getString("descripcion"));
                    estadosSaludDeAlumno.add(estadoSalud);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener los estados de salud del alumno: " + idAlumno);
            e.printStackTrace();
        }
        return estadosSaludDeAlumno;
    }

    @Override
    public List<EstadoSalud> obtenerEstadosSaludEnFecha(Date fecha) {
        List<EstadoSalud> estadosSaludEnFecha = new ArrayList<>();
        String query = "SELECT * FROM EstadoSalud WHERE fecha = ?";
        try (Connection conexion = conexionSQL.obtenerConexion();
             PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setDate(1, new java.sql.Date(fecha.getTime()));
            try (ResultSet resultSet = statement.executeQuery()) {
                AlumnoDAO alumnoDAO = new AlumnoDAOImpl(conexionSQL);
                while (resultSet.next()) {
                    EstadoSalud estadoSalud = new EstadoSalud();
                    estadoSalud.setIdEstadoSalud(resultSet.getInt("idEstadoSalud"));
                    estadoSalud.setAlumno(alumnoDAO.obtenerAlumnoPorId(resultSet.getInt("idAlumno")));
                    estadoSalud.setFecha(resultSet.getDate("fecha"));
                    estadoSalud.setDescripcion(resultSet.getString("descripcion"));
                    estadosSaludEnFecha.add(estadoSalud);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener los estados de salud en la fecha: " + fecha);
            e.printStackTrace();
        }
        return estadosSaludEnFecha;
    }
@Override
public List<EstadoSalud> obtenerTodosLosEstadosSalud() {
    List<EstadoSalud> todosLosEstadosSalud = new ArrayList<>();
    String query = "SELECT * FROM EstadoSalud";
    try (Connection conexion = conexionSQL.obtenerConexion();
         PreparedStatement statement = conexion.prepareStatement(query);
         ResultSet resultSet = statement.executeQuery()) {
        AlumnoDAO alumnoDAO = new AlumnoDAOImpl(conexionSQL);
        while (resultSet.next()) {
            EstadoSalud estadoSalud = new EstadoSalud();
            estadoSalud.setIdEstadoSalud(resultSet.getInt("idEstadoSalud"));
            estadoSalud.setAlumno(alumnoDAO.obtenerAlumnoPorId(resultSet.getInt("idAlumno")));
            estadoSalud.setFecha(resultSet.getDate("fecha"));
            estadoSalud.setDescripcion(resultSet.getString("descripcion"));
            todosLosEstadosSalud.add(estadoSalud);
        }
    } catch (SQLException e) {
        System.err.println("Error al obtener todos los estados de salud");
        e.printStackTrace();
    }
    return todosLosEstadosSalud;
}
}