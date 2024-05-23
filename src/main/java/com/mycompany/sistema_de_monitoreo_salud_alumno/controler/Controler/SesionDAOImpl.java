package com.mycompany.sistema_de_monitoreo_salud_alumno.controler.Controler;

import com.mycompany.sistema_de_monitoreo_salud_alumno.controler.Controler.interf.SesionDAO;
import com.mycompany.sistema_de_monitoreo_salud_alumno.controler.Controler.interf.UsuarioDAO;
import com.mycompany.sistema_de_monitoreo_salud_alumno.model.Sesion;
import com.mycompany.sistema_de_monitoreo_salud_alumno.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SesionDAOImpl implements SesionDAO {

    private ConexionSQL conexionSQL;
    private UsuarioDAO usuarioDAO;

    public SesionDAOImpl(ConexionSQL conexionSQL, UsuarioDAO usuarioDAO) {
        this.conexionSQL = conexionSQL;
        this.usuarioDAO = usuarioDAO;
    }

    @Override
    public void agregarSesion(Sesion sesion) {
        String query = "INSERT INTO Sesiones (idSesion, idUsuario, fechaInicio, fechaFin) VALUES (?, ?, ?, ?)";
        try (Connection conexion = conexionSQL.obtenerConexion();
             PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setInt(1, sesion.getIdSesion());
            statement.setInt(2, sesion.getUsuario().getIdUsuario());
            statement.setDate(3, new java.sql.Date(sesion.getFechaInicio().getTime()));
            statement.setDate(4, new java.sql.Date(sesion.getFechaFin().getTime()));
            statement.executeUpdate();
            System.out.println("Sesión agregada: " + sesion.getIdSesion());
        } catch (SQLException e) {
            System.err.println("Error al agregar la sesión: " + sesion.getIdSesion());
            e.printStackTrace();
        }
    }

    @Override
    public void actualizarSesion(Sesion sesion) {
        String query = "UPDATE Sesiones SET idUsuario = ?, fechaInicio = ?, fechaFin = ? WHERE idSesion = ?";
        try (Connection conexion = conexionSQL.obtenerConexion();
             PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setInt(1, sesion.getUsuario().getIdUsuario());
            statement.setDate(2, new java.sql.Date(sesion.getFechaInicio().getTime()));
            statement.setDate(3, new java.sql.Date(sesion.getFechaFin().getTime()));
            statement.setInt(4, sesion.getIdSesion());
            statement.executeUpdate();
            System.out.println("Sesión actualizada: " + sesion.getIdSesion());
        } catch (SQLException e) {
            System.err.println("Error al actualizar la sesión: " + sesion.getIdSesion());
            e.printStackTrace();
        }
    }

    @Override
    public void eliminarSesion(int idSesion) {
        String query = "DELETE FROM Sesiones WHERE idSesion = ?";
        try (Connection conexion = conexionSQL.obtenerConexion();
             PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setInt(1, idSesion);
            statement.executeUpdate();
            System.out.println("Sesión eliminada: " + idSesion);
        } catch (SQLException e) {
            System.err.println("Error al eliminar la sesión: " + idSesion);
            e.printStackTrace();
        }
    }

    @Override
    public Sesion obtenerSesion(int idSesion) {
        Sesion sesion = null;
        String query = "SELECT * FROM Sesiones WHERE idSesion = ?";
        try (Connection conexion = conexionSQL.obtenerConexion();
             PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setInt(1, idSesion);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Usuario usuario = usuarioDAO.obtenerUsuarioPorId(resultSet.getInt("idUsuario"));
                    sesion = new Sesion(
                            resultSet.getInt("idSesion"),
                            usuario,
                            resultSet.getDate("fechaInicio"),
                            resultSet.getDate("fechaFin")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener la sesión: " + idSesion);
            e.printStackTrace();
        }
        return sesion;
    }

    @Override
    public List<Sesion> obtenerSesionesDeUsuario(int idUsuario) {
        List<Sesion> sesionesDeUsuario = new ArrayList<>();
        String query = "SELECT * FROM Sesiones WHERE idUsuario = ?";
        try (Connection conexion = conexionSQL.obtenerConexion();
             PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setInt(1, idUsuario);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Usuario usuario = usuarioDAO.obtenerUsuarioPorId(resultSet.getInt("idUsuario"));
                    Sesion sesion = new Sesion(
                            resultSet.getInt("idSesion"),
                            usuario,
                            resultSet.getDate("fechaInicio"),
                            resultSet.getDate("fechaFin")
                    );
                    sesionesDeUsuario.add(sesion);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener las sesiones del usuario: " + idUsuario);
            e.printStackTrace();
        }
        return sesionesDeUsuario;
    }

    @Override
    public List<Sesion> obtenerSesionesEnFecha(Date fecha) {
        List<Sesion> sesionesEnFecha = new ArrayList<>();
        String query = "SELECT * FROM Sesiones WHERE fechaInicio = ? OR fechaFin = ?";
        try (Connection conexion = conexionSQL.obtenerConexion();
             PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setDate(1, new java.sql.Date(fecha.getTime()));
            statement.setDate(2, new java.sql.Date(fecha.getTime()));
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Usuario usuario = usuarioDAO.obtenerUsuarioPorId(resultSet.getInt("idUsuario"));
                    Sesion sesion = new Sesion(
                            resultSet.getInt("idSesion"),
                            usuario,
                            resultSet.getDate("fechaInicio"),
                            resultSet.getDate("fechaFin")
                    );
                    sesionesEnFecha.add(sesion);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener las sesiones en la fecha: " + fecha);
            e.printStackTrace();
        }
        return sesionesEnFecha;
    }

    @Override
    public List<Sesion> obtenerTodasLasSesiones() {
        List<Sesion> todasLasSesiones = new ArrayList<>();
        String query = "SELECT * FROM Sesiones";
        try (Connection conexion = conexionSQL.obtenerConexion();
             PreparedStatement statement = conexion.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Usuario usuario = usuarioDAO.obtenerUsuarioPorId(resultSet.getInt("idUsuario"));
                Sesion sesion = new Sesion(
                        resultSet.getInt("idSesion"),
                        usuario,
                        resultSet.getDate("fechaInicio"),
                        resultSet.getDate("fechaFin")
                );
                todasLasSesiones.add(sesion);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener todas las sesiones");
            e.printStackTrace();
        }
        return todasLasSesiones;
    }
}
