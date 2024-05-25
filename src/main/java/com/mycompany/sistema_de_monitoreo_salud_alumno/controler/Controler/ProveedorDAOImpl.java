package com.mycompany.sistema_de_monitoreo_salud_alumno.controler.Controler;

import com.mycompany.sistema_de_monitoreo_salud_alumno.model.Proveedor;
import com.mycompany.sistema_de_monitoreo_salud_alumno.controler.Controler.ConexionSQL;
import com.mycompany.sistema_de_monitoreo_salud_alumno.controler.Controler.interf.ProveedorDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProveedorDAOImpl implements ProveedorDAO {
        private ConexionSQL conexionSQL;

    public ProveedorDAOImpl(ConexionSQL conexionSQL) {
        this.conexionSQL = conexionSQL;
    }

 @Override
public void crearProveedor(Proveedor proveedor) {
    Connection conexion = null;
    PreparedStatement stmt = null;
    try {
        // Obtener conexión a la base de datos
        conexion = ConexionSQL.obtenerConexion();
        // Preparar la consulta SQL para insertar el proveedor
        String sql = "INSERT INTO Proveedor (nif, telefono, tipo_producto, encargado) VALUES (?, ?, ?, ?)";
        stmt = conexion.prepareStatement(sql);
        stmt.setString(1, proveedor.getNif());
        stmt.setString(2, proveedor.getTelefono());
        stmt.setString(3, proveedor.getTipo_producto());
        stmt.setString(4, proveedor.getEncargado());
        // Ejecutar la consulta
        stmt.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        // Cerrar recursos
        cerrarRecursos(conexion, stmt, null);
    }
}


    // Método para actualizar un proveedor existente por su ID
@Override
public void actualizarProveedor(Proveedor proveedor) {
    Connection conexion = null;
    PreparedStatement stmt = null;
    try {
        // Obtener conexión a la base de datos
        conexion = ConexionSQL.obtenerConexion();
        // Preparar la consulta SQL para actualizar el proveedor por su ID
        String sql = "UPDATE Proveedor SET nif = ?, telefono = ?, tipo_producto = ?, encargado = ? WHERE idProveedor = ?";
        stmt = conexion.prepareStatement(sql);
        stmt.setString(1, proveedor.getNif());
        stmt.setString(2, proveedor.getTelefono());
        stmt.setString(3, proveedor.getTipo_producto());
        stmt.setString(4, proveedor.getEncargado());
        stmt.setInt(5, proveedor.getIdProveedor());
        // Ejecutar la consulta
        stmt.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        // Cerrar recursos
        cerrarRecursos(conexion, stmt, null);
    }
}

    
    // Método para eliminar un proveedor por su ID
    @Override
    public void eliminarProveedor(int idProveedor) {
        Connection conexion = null;
        PreparedStatement stmt = null;
        try {
            // Obtener conexión a la base de datos
            conexion = ConexionSQL.obtenerConexion();
            // Preparar la consulta SQL para eliminar el proveedor
            String sql = "DELETE FROM Proveedor WHERE idProveedor = ?";
            stmt = conexion.prepareStatement(sql);
            stmt.setInt(1, idProveedor);
            // Ejecutar la consulta
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Cerrar recursos
            cerrarRecursos(conexion, stmt, null);
        }
    }
    
    // Método para obtener todos los proveedores
    @Override
    public ArrayList<Proveedor> obtenerTodosProveedores() {
        ArrayList<Proveedor> listaProveedores = new ArrayList<>();
        Connection conexion = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            // Obtener conexión a la base de datos
            conexion = ConexionSQL.obtenerConexion();
            // Preparar la consulta SQL para seleccionar todos los proveedores
            String sql = "SELECT * FROM Proveedor";
            stmt = conexion.prepareStatement(sql);
            // Ejecutar la consulta
            rs = stmt.executeQuery();
            // Procesar el resultado
            while (rs.next()) {
                Proveedor proveedor = new Proveedor(
                    rs.getInt("idProveedor"),
                    rs.getString("nif"),
                    rs.getString("telefono"),
                    rs.getString("tipo_producto"),
                    rs.getString("encargado")
                );
                listaProveedores.add(proveedor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Cerrar recursos
            cerrarRecursos(conexion, stmt, rs);
        }
        return listaProveedores;
    }
    
    // Método auxiliar para cerrar recursos
    private void cerrarRecursos(Connection conexion, PreparedStatement stmt, ResultSet rs) {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conexion != null) conexion.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

// Método para leer un proveedor por su ID y devolver solo los datos como un arreglo de objetos
@Override
public Object[] leerProveedor(int idProveedor) {
    Connection conexion = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    Object[] datosProveedor = null;
    try {
        // Obtener conexión a la base de datos
        conexion = ConexionSQL.obtenerConexion();
        // Preparar la consulta SQL para seleccionar el proveedor por su ID
        String sql = "SELECT * FROM Proveedor WHERE idProveedor = ?";
        stmt = conexion.prepareStatement(sql);
        stmt.setInt(1, idProveedor);
        // Ejecutar la consulta
        rs = stmt.executeQuery();
        // Procesar el resultado
        if (rs.next()) {
            // Obtener los datos del proveedor
            datosProveedor = new Object[]{
                rs.getInt("idProveedor"),
                rs.getString("nif"),
                rs.getString("telefono"),
                rs.getString("tipo_producto"),
                rs.getString("encargado")
            };
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        // Cerrar recursos
        cerrarRecursos(conexion, stmt, rs);
    }
    return datosProveedor;
}
}
