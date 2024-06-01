package com.mycompany.sistema_de_monitoreo_salud_alumno.controler.Controler.interf;


import com.mycompany.sistema_de_monitoreo_salud_alumno.model.InventarioProductos;
import com.mycompany.sistema_de_monitoreo_salud_alumno.model.ProductoFarmaceutico;
import java.util.List;

public interface InventarioProductosDAO {
    // Método para agregar un nuevo producto al inventario
    public void agregarProducto(ProductoFarmaceutico producto);
    
    // Método para buscar un producto en el inventario por su código
    public ProductoFarmaceutico buscarProducto(String codigo);
    
    // Método para eliminar un producto del inventario por su código
    public void eliminarProducto(String codigo);
    
    // Método para actualizar el stock de un producto en el inventario por su código
    public void actualizarStock(String codigo, int cantidad);
    
    // Método para obtener el objeto de modelo de InventarioProductos
    public List<ProductoFarmaceutico> obtenerInventarioProductos();
    public List<ProductoFarmaceutico> ObtenerInventarioProductos();

}
