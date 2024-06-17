package com.mycompany.sistema_de_monitoreo_salud_alumno.controler.Controler;

import com.mycompany.sistema_de_monitoreo_salud_alumno.controler.Controler.interf.AlumnoDAO;
import com.mycompany.sistema_de_monitoreo_salud_alumno.model.Alumno;
import com.mycompany.sistema_de_monitoreo_salud_alumno.model.Persona;
import com.mycompany.sistema_de_monitoreo_salud_alumno.model.Sesion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AlumnoDAOImpl implements AlumnoDAO {

    private ConexionSQL conexionSQL;

    public AlumnoDAOImpl(ConexionSQL conexionSQL) {
        this.conexionSQL = conexionSQL;
    }

    @Override
    public void agregarAlumno(Alumno alumno) {
        String queryPersona = "INSERT INTO Persona (nombre, apellido, edad) VALUES (?, ?, ?);";
        String queryAlumno = "INSERT INTO Alumno (codigoAlumno, carrera, ciclo, idPersona) VALUES (?, ?, ?, ?);";

        try (Connection conexion = conexionSQL.obtenerConexion();
             PreparedStatement statementPersona = conexion.prepareStatement(queryPersona, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement statementAlumno = conexion.prepareStatement(queryAlumno)) {

            // Insertar datos en la tabla Personas
            statementPersona.setString(1, alumno.getNombre());
            statementPersona.setString(2, alumno.getApellido());
            statementPersona.setInt(3, alumno.getEdad());
            statementPersona.executeUpdate();

            // Obtener el idPersona generado
            ResultSet rs = statementPersona.getGeneratedKeys();
            int idPersona = -1;
            if (rs.next()) {
                idPersona = rs.getInt(1);
            }

            // Insertar datos en la tabla Alumnos
            statementAlumno.setString(1, alumno.getCodigoAlumno());
            statementAlumno.setString(2, alumno.getCarrera());
            statementAlumno.setInt(3, alumno.getCiclo());
            statementAlumno.setInt(4, idPersona); // Aquí se usa el idPersona generado como clave foránea
            statementAlumno.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

   @Override
public void eliminarAlumno(int id) {
    String queryEliminarSesiones = "DELETE FROM Sesion WHERE idAlumno = ?;";
    String queryAlumno = "DELETE FROM Alumno WHERE idAlumno = ?;";
    String queryPersona = "DELETE FROM Persona WHERE idPersona = ?;";

    try (Connection conexion = conexionSQL.obtenerConexion();
         PreparedStatement statementEliminarSesiones = conexion.prepareStatement(queryEliminarSesiones);
         PreparedStatement statementAlumno = conexion.prepareStatement(queryAlumno);
         PreparedStatement statementPersona = conexion.prepareStatement(queryPersona)) {

        // Eliminar las sesiones asociadas al alumno
        statementEliminarSesiones.setInt(1, id);
        statementEliminarSesiones.executeUpdate();

        // Eliminar el alumno de la tabla Alumnos
        statementAlumno.setInt(1, id);
        statementAlumno.executeUpdate();

        // Eliminar la persona asociada del registro en la tabla Personas
        statementPersona.setInt(1, id);
        statementPersona.executeUpdate();

    } catch (SQLException e) {
        e.printStackTrace();
    }
}


  @Override
public Alumno obtenerAlumnoPorId(String codigoAlumno) {
    Alumno alumno = null;
    String query = "SELECT a.idAlumno, a.codigoAlumno, a.carrera, a.ciclo, p.idPersona, p.nombre, p.apellido, p.edad " +
                   "FROM Alumno a " +
                   "INNER JOIN Persona p ON a.idPersona = p.idPersona " +
                   "WHERE a.codigoAlumno = ?;";

    try (Connection conexion = conexionSQL.obtenerConexion();
         PreparedStatement statement = conexion.prepareStatement(query)) {
        statement.setString(1, codigoAlumno);

        try (ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                alumno = new Alumno(
                        resultSet.getInt("idAlumno"),
                        resultSet.getString("codigoAlumno"),
                        resultSet.getString("carrera"),
                        resultSet.getInt("ciclo"),
                        resultSet.getInt("idPersona"),
                        resultSet.getString("nombre"),
                        resultSet.getString("apellido"),
                        resultSet.getInt("edad")
                );
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return alumno;
}

    public List<Alumno> obtenerTodosLosAlumnos() {
        List<Alumno> todosLosAlumnos = new ArrayList<>();
        String query = "SELECT a.idAlumno, a.codigoAlumno, a.carrera, a.ciclo, p.idPersona, p.nombre, p.apellido, p.edad " +
                       "FROM Alumno a " +
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
                todosLosAlumnos.add(alumno);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return todosLosAlumnos;
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

    
@Override
    public void actualizarAlumno(Alumno alumno) {
        String queryPersona = "UPDATE Persona SET nombre = ?, apellido = ?, edad = ? WHERE idPersona = ?;";
        String queryAlumno = "UPDATE Alumno SET codigoAlumno = ?, carrera = ?, ciclo = ? WHERE idAlumno = ?;";

        try (Connection conexion = conexionSQL.obtenerConexion();
             PreparedStatement statementPersona = conexion.prepareStatement(queryPersona);
             PreparedStatement statementAlumno = conexion.prepareStatement(queryAlumno)) {

            // Actualizar datos en la tabla Personas
            statementPersona.setString(1, alumno.getNombre());
            statementPersona.setString(2, alumno.getApellido());
            statementPersona.setInt(3, alumno.getEdad());
            statementPersona.setInt(4, alumno.getIdPersona());
            statementPersona.executeUpdate();

            // Actualizar datos en la tabla Alumnos
            statementAlumno.setString(1, alumno.getCodigoAlumno());
            statementAlumno.setString(2, alumno.getCarrera());
            statementAlumno.setInt(3, alumno.getCiclo());
            statementAlumno.setInt(4, alumno.getIdAlumno());
            statementAlumno.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
