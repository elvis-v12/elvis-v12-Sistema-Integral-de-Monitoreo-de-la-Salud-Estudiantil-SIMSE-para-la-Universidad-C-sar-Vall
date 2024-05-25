package com.mycompany.sistema_de_monitoreo_salud_alumno.controler.Controler.interf;
import com.mycompany.sistema_de_monitoreo_salud_alumno.model.Proveedor;
import java.util.ArrayList;

public interface ProveedorDAO {
    // Método para crear un proveedor
    void crearProveedor(Proveedor proveedor);
    
    Object[] leerProveedor(int idProveedor);
    
    // Método para actualizar un proveedor existente
    void actualizarProveedor(Proveedor proveedor);
    
    // Método para eliminar un proveedor por su ID
    void eliminarProveedor(int idProveedor);
    
    // Método para obtener todos los proveedores
    ArrayList<Proveedor> obtenerTodosProveedores();
}
