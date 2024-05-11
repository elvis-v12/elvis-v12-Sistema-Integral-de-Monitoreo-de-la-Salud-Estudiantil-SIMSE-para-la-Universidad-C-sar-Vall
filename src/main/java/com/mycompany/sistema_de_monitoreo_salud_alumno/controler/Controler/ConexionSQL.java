package com.mycompany.sistema_de_monitoreo_salud_alumno.controler.Controler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionSQL {
private static final String URL = "jdbc:sqlserver://DESKTOP-16PJRI5\\SQLEXPRESS:1433;trustServerCertificate=true;databaseName=SIMSETopicoUCV";
private static final String USER = "elvis";
private static final String PASSWORD = "1234";

    // Método para obtener una conexión a la base de datos
    public static Connection obtenerConexion() throws SQLException {
        Connection conexion = null;
        try {
            // Cargar el driver de SQL Server
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            // Establecer la conexión
            conexion = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            // Manejar cualquier excepción que ocurra durante la conexión
            e.printStackTrace();
            throw new SQLException("Error al conectar con la base de datos");
        }
        return conexion;
    }

    // Método para cerrar la conexión
    public static void cerrarConexion(Connection conexion) {
        if (conexion != null) {
            try {
                conexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    // Método main para probar la conexión desde la misma clase
    public static void main(String[] args) {
        Connection conexion = null;
        try {
            // Obtener conexión a la base de datos
            conexion = ConexionSQL.obtenerConexion();
            System.out.println("Conexión establecida correctamente desde la clase ConexionSQL.");
            // Aquí puedes realizar operaciones con la base de datos utilizando la conexión
            // Por ejemplo, ejecutar consultas SQL
            
        } catch (SQLException e) {
            e.printStackTrace();
            // Manejar cualquier excepción que ocurra durante la conexión
        } finally {
            // Cerrar la conexión al finalizar
            ConexionSQL.cerrarConexion(conexion);
            System.out.println("Conexión cerrada correctamente desde la clase ConexionSQL.");
        }
    }
}
