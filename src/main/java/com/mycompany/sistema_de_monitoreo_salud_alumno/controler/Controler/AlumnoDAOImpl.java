package com.mycompany.sistema_de_monitoreo_salud_alumno.controler.Controler;

import com.mycompany.sistema_de_monitoreo_salud_alumno.controler.Controler.interf.AlumnoDAO;
import com.mycompany.sistema_de_monitoreo_salud_alumno.model.Alumno;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlumnoDAOImpl implements AlumnoDAO {

    private ConexionSQL conexionSQL;

    public AlumnoDAOImpl(ConexionSQL conexionSQL) {
        this.conexionSQL = conexionSQL;
    }

    @Override
    public void agregarAlumno(Alumno alumno) {
        String query = "INSERT INTO Alumnos (codigoAlumno, carrera, ciclo) VALUES (?, ?, ?)";

        try (Connection conexion = conexionSQL.obtenerConexion();
             PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setString(1, alumno.getCodigoAlumno());
            statement.setString(2, alumno.getCarrera());
            statement.setString(3, alumno.getCiclo()); // Aquí se establece como cadena

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actualizarAlumno(Alumno alumno) {
        String query = "UPDATE Alumnos SET codigoAlumno = ?, carrera = ?, ciclo = ? WHERE idAlumno = ?";

        try (Connection conexion = conexionSQL.obtenerConexion();
             PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setString(1, alumno.getCodigoAlumno());
            statement.setString(2, alumno.getCarrera());
            statement.setString(3, alumno.getCiclo()); // Aquí se establece como cadena
            statement.setInt(4, alumno.getIdAlumno());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminarAlumno(int id) {
        String query = "DELETE FROM Alumnos WHERE idAlumno = ?";

        try (Connection conexion = conexionSQL.obtenerConexion();
             PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setInt(1, id);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Alumno obtenerAlumnoPorId(int id) {
        Alumno alumno = null;
        String query = "SELECT * FROM Alumnos WHERE idAlumno = ?";

        try (Connection conexion = conexionSQL.obtenerConexion();
             PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    alumno = new Alumno();
                    alumno.setIdAlumno(resultSet.getInt("idAlumno"));
                    alumno.setCodigoAlumno(resultSet.getString("codigoAlumno"));
                    alumno.setCarrera(resultSet.getString("carrera"));
                    alumno.setCiclo(resultSet.getString("ciclo")); // Se obtiene como cadena
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return alumno;
    }

    @Override
    public List<Alumno> obtenerTodosLosAlumnos() {
        List<Alumno> todosLosAlumnos = new ArrayList<>();
        String query = "SELECT * FROM Alumnos";

        try (Connection conexion = conexionSQL.obtenerConexion();
             PreparedStatement statement = conexion.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Alumno alumno = new Alumno();
                alumno.setIdAlumno(resultSet.getInt("idAlumno"));
                alumno.setCodigoAlumno(resultSet.getString("codigoAlumno"));
                alumno.setCarrera(resultSet.getString("carrera"));
                alumno.setCiclo(resultSet.getString("ciclo")); // Se obtiene como cadena

                todosLosAlumnos.add(alumno);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return todosLosAlumnos;
    }
}
