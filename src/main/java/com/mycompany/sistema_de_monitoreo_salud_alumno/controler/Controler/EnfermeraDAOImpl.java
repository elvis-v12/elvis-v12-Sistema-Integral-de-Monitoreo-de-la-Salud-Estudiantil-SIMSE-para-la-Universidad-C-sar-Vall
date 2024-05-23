package com.mycompany.sistema_de_monitoreo_salud_alumno.controler.Controler;


import com.mycompany.sistema_de_monitoreo_salud_alumno.controler.Controler.interf.EnfermeraDAO;
import com.mycompany.sistema_de_monitoreo_salud_alumno.model.Enfermera;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EnfermeraDAOImpl implements EnfermeraDAO {
    private ConexionSQL conexionSQL;

    public EnfermeraDAOImpl(ConexionSQL conexionSQL) {
        this.conexionSQL = conexionSQL;
    }

    @Override
    public void agregarEnfermera(Enfermera enfermera) {
        String query = "INSERT INTO Enfermeras (idEnfermera, trabajo, especialidad) VALUES (?, ?, ?)";
        try (Connection conexion = conexionSQL.obtenerConexion();
             PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setInt(1, enfermera.getIdEnfermera());
            statement.setString(2, enfermera.getTrabajo());
            statement.setString(3, enfermera.getEspecialidad());
            statement.executeUpdate();
            System.out.println("Enfermera agregada: " + enfermera.getIdEnfermera());
        } catch (SQLException e) {
            System.err.println("Error al agregar la enfermera: " + enfermera.getIdEnfermera());
            e.printStackTrace();
        }
    }

    @Override
    public void actualizarEnfermera(Enfermera enfermera) {
        String query = "UPDATE Enfermeras SET trabajo = ?, especialidad = ? WHERE idEnfermera = ?";
        try (Connection conexion = conexionSQL.obtenerConexion();
             PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setString(1, enfermera.getTrabajo());
            statement.setString(2, enfermera.getEspecialidad());
            statement.setInt(3, enfermera.getIdEnfermera());
            statement.executeUpdate();
            System.out.println("Enfermera actualizada: " + enfermera.getIdEnfermera());
        } catch (SQLException e) {
            System.err.println("Error al actualizar la enfermera: " + enfermera.getIdEnfermera());
            e.printStackTrace();
        }
    }

    @Override
    public void eliminarEnfermera(int idEnfermera) {
        String query = "DELETE FROM Enfermeras WHERE idEnfermera = ?";
        try (Connection conexion = conexionSQL.obtenerConexion();
             PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setInt(1, idEnfermera);
            statement.executeUpdate();
            System.out.println("Enfermera eliminada: " + idEnfermera);
        } catch (SQLException e) {
            System.err.println("Error al eliminar la enfermera: " + idEnfermera);
            e.printStackTrace();
        }
    }

    @Override
    public Enfermera obtenerEnfermeraPorId(int idEnfermera) {
        Enfermera enfermera = null;
        String query = "SELECT * FROM Enfermeras WHERE idEnfermera = ?";
        try (Connection conexion = conexionSQL.obtenerConexion();
             PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setInt(1, idEnfermera);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    enfermera = new Enfermera();
                    enfermera.setIdEnfermera(resultSet.getInt("idEnfermera"));
                    enfermera.setTrabajo(resultSet.getString("trabajo"));
                    enfermera.setEspecialidad(resultSet.getString("especialidad"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener la enfermera: " + idEnfermera);
            e.printStackTrace();
        }
        return enfermera;
    }

    @Override
    public List<Enfermera> obtenerTodasLasEnfermeras() {
        List<Enfermera> todasLasEnfermeras = new ArrayList<>();
        String query = "SELECT * FROM Enfermeras";
        try (Connection conexion = conexionSQL.obtenerConexion();
             PreparedStatement statement = conexion.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Enfermera enfermera = new Enfermera();
                enfermera.setIdEnfermera(resultSet.getInt("idEnfermera"));
                enfermera.setTrabajo(resultSet.getString("trabajo"));
                enfermera.setEspecialidad(resultSet.getString("especialidad"));
                todasLasEnfermeras.add(enfermera);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener todas las enfermeras");
            e.printStackTrace();
        }
        return todasLasEnfermeras;
    }
}
