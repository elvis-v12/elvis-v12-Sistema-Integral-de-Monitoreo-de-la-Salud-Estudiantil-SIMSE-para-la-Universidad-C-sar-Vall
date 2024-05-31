package com.mycompany.sistema_de_monitoreo_salud_alumno.controler.Controler;

import com.mycompany.sistema_de_monitoreo_salud_alumno.controler.Controler.interf.EnfermeraDAO;
import com.mycompany.sistema_de_monitoreo_salud_alumno.model.Alumno;
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
    if (!existePersona(enfermera.getIdPersona())) {
        System.err.println("La persona con id " + enfermera.getIdPersona() + " no existe.");
        return;
    }

    Alumno alumno = enfermera.getAlumno();
    if (alumno != null && !existeAlumno(alumno.getIdAlumno())) {
        System.err.println("El alumno con id " + alumno.getIdAlumno() + " no existe.");
        return;
    }

    String query = "INSERT INTO Enfermera (especialidad, idPersona, idAlumno) VALUES (?, ?, ?)";
    try (Connection conexion = conexionSQL.obtenerConexion();
         PreparedStatement statement = conexion.prepareStatement(query)) {
        statement.setString(1, enfermera.getEspecialidad());
        statement.setInt(2, enfermera.getIdPersona());
        
        if (alumno != null) {
            statement.setInt(3, alumno.getIdAlumno());
        } else {
            statement.setNull(3, java.sql.Types.INTEGER); // o lanza una excepción si alumno no puede ser null
        }

        statement.executeUpdate();
        System.out.println("Enfermera agregada: " + enfermera.getIdEnfermera());
    } catch (SQLException e) {
        System.err.println("Error al agregar la enfermera: " + enfermera.getIdEnfermera());
        e.printStackTrace();
    }
}

public boolean existePersona(int idPersona) {
    String query = "SELECT COUNT(*) FROM Persona WHERE idPersona = ?";
    try (Connection conexion = conexionSQL.obtenerConexion();
         PreparedStatement statement = conexion.prepareStatement(query)) {
        statement.setInt(1, idPersona);
        try (ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                return resultSet.getInt(1) > 0;
            }
        }
    } catch (SQLException e) {
        System.err.println("Error al verificar la existencia de la persona: " + idPersona);
        e.printStackTrace();
    }
    return false;
}

public boolean existeAlumno(int idAlumno) {
    String query = "SELECT COUNT(*) FROM Alumno WHERE idAlumno = ?";
    try (Connection conexion = conexionSQL.obtenerConexion();
         PreparedStatement statement = conexion.prepareStatement(query)) {
        statement.setInt(1, idAlumno);
        try (ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                return resultSet.getInt(1) > 0;
            }
        }
    } catch (SQLException e) {
        System.err.println("Error al verificar la existencia del alumno: " + idAlumno);
        e.printStackTrace();
    }
    return false;
}


    @Override
    public void actualizarEnfermera(Enfermera enfermera) {
        String query = "UPDATE Enfermera SET especialidad = ?, idPersona = ?, idAlumno = ? WHERE idEnfermera = ?";
        try (Connection conexion = conexionSQL.obtenerConexion();
             PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setString(1, enfermera.getEspecialidad());
            statement.setInt(2, enfermera.getIdPersona());
            statement.setInt(3, enfermera.getAlumno().getIdAlumno()); // Acceder al idAlumno del objeto Alumno
            statement.setInt(4, enfermera.getIdEnfermera());
            statement.executeUpdate();
            System.out.println("Enfermera actualizada: " + enfermera.getIdEnfermera());
        } catch (SQLException e) {
            System.err.println("Error al actualizar la enfermera: " + enfermera.getIdEnfermera());
            e.printStackTrace();
        }
    }

    @Override
    public void eliminarEnfermera(int idEnfermera) {
        String query = "DELETE FROM Enfermera WHERE idEnfermera = ?";
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
    String query = "SELECT * FROM Enfermera WHERE idEnfermera = ?";
    try (Connection conexion = conexionSQL.obtenerConexion();
         PreparedStatement statement = conexion.prepareStatement(query)) {
        statement.setInt(1, idEnfermera);
        try (ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                int idPersona = resultSet.getInt("idPersona");
                int idAlumno = resultSet.getInt("idAlumno");
                
                // Crear el objeto Alumno solo si idAlumno no es NULL
                Alumno alumno = (idAlumno != 0) ? obtenerAlumnoPorId(idAlumno) : null;
                
                enfermera = new Enfermera(
                        resultSet.getInt("idEnfermera"),
                        resultSet.getString("especialidad"),
                        alumno, // Pasa el objeto Alumno creado o null
                        idPersona
                );
            }
        }
    } catch (SQLException e) {
        System.err.println("Error al obtener la enfermera: " + idEnfermera);
        e.printStackTrace();
    }
    return enfermera;
}
public Alumno obtenerAlumnoPorId(int idAlumno) {
    Alumno alumno = null;
    String query = "SELECT idAlumno, idPersona FROM Alumno WHERE idAlumno = ?";
    try (Connection conexion = conexionSQL.obtenerConexion();
         PreparedStatement statement = conexion.prepareStatement(query)) {
        statement.setInt(1, idAlumno);
        try (ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                alumno = new Alumno(
                        resultSet.getInt("idAlumno"),
                        resultSet.getInt("idPersona")
                );
            }
        }
    } catch (SQLException e) {
        System.err.println("Error al obtener el alumno: " + idAlumno);
        e.printStackTrace();
    }
    return alumno;
}


 @Override
public List<Enfermera> obtenerTodasLasEnfermeras() {
    List<Enfermera> todasLasEnfermeras = new ArrayList<>();
    String query = "SELECT * FROM Enfermera";
    try (Connection conexion = conexionSQL.obtenerConexion();
         PreparedStatement statement = conexion.prepareStatement(query);
         ResultSet resultSet = statement.executeQuery()) {
        while (resultSet.next()) {
            int idPersona = resultSet.getInt("idPersona");
            Integer idAlumno = (resultSet.getObject("idAlumno") != null) ? resultSet.getInt("idAlumno") : null;
            
            // Crear el objeto Alumno solo si idAlumno no es NULL
            Alumno alumno = (idAlumno != null) ? obtenerAlumnoPorId(idAlumno) : null;
            
            Enfermera enfermera = new Enfermera(
                    resultSet.getInt("idEnfermera"),
                    resultSet.getString("especialidad"),
                    alumno, // Pasa el objeto Alumno creado o null
                    idPersona
            );
            todasLasEnfermeras.add(enfermera);
        }
    } catch (SQLException e) {
        System.err.println("Error al obtener todas las enfermeras");
        e.printStackTrace();
    }
    return todasLasEnfermeras;
}
}