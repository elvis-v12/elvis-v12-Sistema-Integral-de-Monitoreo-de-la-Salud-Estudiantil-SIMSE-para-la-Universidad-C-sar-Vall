
package com.mycompany.sistema_de_monitoreo_salud_alumno.model;
    import java.util.HashMap;
import java.util.Map;
/**
 *
 * @author ELVIS
 */
public class InventarioProductos {

    private Map<String, ProductoFarmaceutico> inventario;

    public InventarioProductos() {
        this.inventario = new HashMap<>();
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

}
