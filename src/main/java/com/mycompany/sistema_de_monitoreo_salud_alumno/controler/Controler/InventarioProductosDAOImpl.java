package com.mycompany.sistema_de_monitoreo_salud_alumno.controler.Controler;

import com.mycompany.sistema_de_monitoreo_salud_alumno.controler.Controler.interf.InventarioProductosDAO;
import com.mycompany.sistema_de_monitoreo_salud_alumno.model.InventarioProductos;
import com.mycompany.sistema_de_monitoreo_salud_alumno.model.ProductoFarmaceutico;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InventarioProductosDAOImpl implements InventarioProductosDAO {
    private Connection conexion;

    public InventarioProductosDAOImpl() {
        try {
            this.conexion = ConexionSQL.obtenerConexion();
            System.out.println("Conexión a la base de datos establecida correctamente.");
        } catch (SQLException e) {
            System.err.println("Error al obtener la conexión de la base de datos");
            e.printStackTrace();
        }
    }

    @Override
    public void agregarProducto(ProductoFarmaceutico producto) {
        String query = "INSERT INTO ProductosFarmaceuticos (codigo, nombre, precio, stock, fechaVencimiento) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = conexion.prepareStatement(query)) {
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
        try (PreparedStatement statement = conexion.prepareStatement(query)) {
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
        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setString(1, codigo);
            statement.executeUpdate();
            System.out.println("Producto eliminado: " + codigo);
        } catch (SQLException e) {
            System.err.println("Error al eliminar el producto: " + codigo);
            e.printStackTrace();
        }
    }

    @Override
    public void actualizarStock(String codigo, int cantidad) {
        String query = "UPDATE ProductosFarmaceuticos SET stock = ? WHERE codigo = ?";
        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setInt(1, cantidad);
            statement.setString(2, codigo);
            statement.executeUpdate();
            System.out.println("Stock actualizado para el producto: " + codigo);
        } catch (SQLException e) {
            System.err.println("Error al actualizar el stock del producto: " + codigo);
            e.printStackTrace();
        }
    }

    @Override
    public InventarioProductos obtenerInventarioProductos() {
        List<ProductoFarmaceutico> productos = new ArrayList<>();
        String query = "SELECT * FROM ProductosFarmaceuticos";
        try (PreparedStatement statement = conexion.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                ProductoFarmaceutico producto = new ProductoFarmaceutico(
                    resultSet.getString("codigo"),
                    resultSet.getString("nombre"),
                    resultSet.getDouble("precio"),
                    resultSet.getInt("stock"),
                    resultSet.getDate("fechaVencimiento")
                );
                productos.add(producto);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener el inventario de productos");
            e.printStackTrace();
        }
        return new InventarioProductos(productos);
    }
}
