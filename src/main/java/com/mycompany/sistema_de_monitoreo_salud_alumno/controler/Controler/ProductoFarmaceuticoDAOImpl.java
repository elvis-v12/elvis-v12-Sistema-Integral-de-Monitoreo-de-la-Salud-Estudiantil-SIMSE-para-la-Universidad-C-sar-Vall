package com.mycompany.sistema_de_monitoreo_salud_alumno.controler.Controler;

import com.mycompany.sistema_de_monitoreo_salud_alumno.controler.Controler.interf.ProductoFarmaceuticoDAO;
import com.mycompany.sistema_de_monitoreo_salud_alumno.model.ProductoFarmaceutico;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductoFarmaceuticoDAOImpl implements ProductoFarmaceuticoDAO {

    private ConexionSQL conexionSQL;

    public ProductoFarmaceuticoDAOImpl(ConexionSQL conexionSQL) {
        this.conexionSQL = conexionSQL;
    }

    @Override
    public void agregarProducto(ProductoFarmaceutico producto) {
        String query = "INSERT INTO ProductosFarmaceuticos (codigo, nombre, precio, stock, fechaVencimiento) VALUES (?, ?, ?, ?, ?)";
        try (Connection conexion = conexionSQL.obtenerConexion();
             PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setString(1, producto.getCodigo());
            statement.setString(2, producto.getNombre());
            statement.setDouble(3, producto.getPrecio());
            statement.setInt(4, producto.getStock());
            statement.setDate(5, new java.sql.Date(producto.getFechaVencimiento().getTime()));
            statement.executeUpdate();
            System.out.println("Producto agregado: " + producto.getCodigo());
        } catch (SQLException e) {
            System.err.println("Error al agregar el producto: " + producto.getCodigo());
            e.printStackTrace();
        }
    }

    @Override
    public ProductoFarmaceutico buscarProducto(String codigo) {
        ProductoFarmaceutico producto = null;
        String query = "SELECT * FROM ProductosFarmaceuticos WHERE codigo = ?";
        try (Connection conexion = conexionSQL.obtenerConexion();
             PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setString(1, codigo);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    producto = new ProductoFarmaceutico(
                            resultSet.getString("codigo"),
                            resultSet.getString("nombre"),
                            resultSet.getDouble("precio"),
                            resultSet.getInt("stock"),
                            resultSet.getDate("fechaVencimiento")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar el producto: " + codigo);
            e.printStackTrace();
        }
        return producto;
    }

    @Override
    public void eliminarProducto(String codigo) {
        String query = "DELETE FROM ProductosFarmaceuticos WHERE codigo = ?";
        try (Connection conexion = conexionSQL.obtenerConexion();
             PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setString(1, codigo);
            statement.executeUpdate();
            System.out.println("Producto eliminado: " + codigo);
        } catch (SQLException e) {
            System.err.println("Error al eliminar el producto: " + codigo);
            e.printStackTrace();
        }
    }

    @Override
    public void actualizarProducto(ProductoFarmaceutico producto) {
        String query = "UPDATE ProductosFarmaceuticos SET nombre = ?, precio = ?, stock = ?, fechaVencimiento = ? WHERE codigo = ?";
        try (Connection conexion = conexionSQL.obtenerConexion();
             PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setString(1, producto.getNombre());
            statement.setDouble(2, producto.getPrecio());
            statement.setInt(3, producto.getStock());
            statement.setDate(4, new java.sql.Date(producto.getFechaVencimiento().getTime()));
            statement.setString(5, producto.getCodigo());
            statement.executeUpdate();
            System.out.println("Producto actualizado: " + producto.getCodigo());
        } catch (SQLException e) {
            System.err.println("Error al actualizar el producto: " + producto.getCodigo());
            e.printStackTrace();
        }
    }
}
