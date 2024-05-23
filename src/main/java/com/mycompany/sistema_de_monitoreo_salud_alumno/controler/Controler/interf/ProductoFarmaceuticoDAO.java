package com.mycompany.sistema_de_monitoreo_salud_alumno.controler.Controler.interf;


import com.mycompany.sistema_de_monitoreo_salud_alumno.model.ProductoFarmaceutico;

public interface ProductoFarmaceuticoDAO {
    // Método para agregar un nuevo producto farmacéutico
    public void agregarProducto(ProductoFarmaceutico producto);
    
    // Método para buscar un producto farmacéutico por su código
    public ProductoFarmaceutico buscarProducto(String codigo);
    
    // Método para eliminar un producto farmacéutico por su código
    public void eliminarProducto(String codigo);
    
    // Método para actualizar la información de un producto farmacéutico
    public void actualizarProducto(ProductoFarmaceutico producto);
}
