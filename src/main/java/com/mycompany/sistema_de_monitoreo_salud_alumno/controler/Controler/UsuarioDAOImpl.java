
package com.mycompany.sistema_de_monitoreo_salud_alumno.controler.Controler;

import com.mycompany.sistema_de_monitoreo_salud_alumno.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAOImpl implements UsuarioDAO {

    @Override
    public Usuario autenticarUsuario(String codigo, String contrasena) {
        Usuario usuario = null;

        try (Connection conexion = ConexionSQL.obtenerConexion();
             PreparedStatement statement = conexion.prepareStatement("SELECT * FROM Usuario WHERE codigo = ? AND contrasena = ?")) {

            statement.setString(1, codigo);
            statement.setString(2, contrasena);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    usuario = new Usuario(resultSet.getString("codigo"), resultSet.getString("contrasena"));
                }
            }
        } catch (SQLException e) {
            // Manejar cualquier excepción que ocurra durante la operación
            e.printStackTrace();
        }

        return usuario;
    }
}
