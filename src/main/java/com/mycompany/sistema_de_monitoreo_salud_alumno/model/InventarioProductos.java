package com.mycompany.sistema_de_monitoreo_salud_alumno.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ELVIS
 */
public class InventarioProductos {

    private final Map<String, ProductoFarmaceutico> inventario;

    public InventarioProductos() {
        this.inventario = new HashMap<>();
    }

    public InventarioProductos(List<ProductoFarmaceutico> productos) {
        this.inventario = new HashMap<>();
        for (ProductoFarmaceutico producto : productos) {
            this.inventario.put(producto.getCodigo(), producto);
        }
    }

    public void agregarProducto(ProductoFarmaceutico producto) {
        inventario.put(producto.getCodigo(), producto);
    }

    public ProductoFarmaceutico buscarProducto(String codigo) {
        return inventario.get(codigo);
    }

    public void eliminarProducto(String codigo) {
        inventario.remove(codigo);
    }

    public void actualizarStock(String codigo, int cantidad) {
        ProductoFarmaceutico producto = inventario.get(codigo);
        if (producto != null) {
            producto.setStock(producto.getStock() + cantidad);
        }
    }

    public List<ProductoFarmaceutico> getProductos() {
        return new ArrayList<>(inventario.values());
    }
}
