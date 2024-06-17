package com.mycompany.sistema_de_monitoreo_salud_alumno.model;

import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.mycompany.sistema_de_monitoreo_salud_alumno.controler.Controler.ConexionSQL;
import com.mycompany.sistema_de_monitoreo_salud_alumno.model.ProductoFarmaceutico;
import com.mycompany.sistema_de_monitoreo_salud_alumno.model.Proveedor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductoFarmaceuticoService {
    private ConexionSQL conexionSQL;

    public ProductoFarmaceuticoService(ConexionSQL conexionSQL) {
        this.conexionSQL = conexionSQL;
    }

    public List<ProductoFarmaceutico> obtenerInventarioProductos() {
        List<ProductoFarmaceutico> productos = new ArrayList<>();
        String query = "SELECT ip.codigo, ip.nombre, ip.precio, ip.stock, ip.fechaVencimiento, "
                + "p.idProveedor, p.nif, p.telefono, p.tipo_producto, p.encargado " +
                       "FROM InventarioProductos ip " +
                       "INNER JOIN Proveedor p ON ip.idProveedor = p.idProveedor";
        try (Connection conexion = ConexionSQL.obtenerConexion();
             PreparedStatement statement = conexion.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Proveedor proveedor = new Proveedor(
                    resultSet.getInt("idProveedor"),
                    resultSet.getString("nif"),
                    resultSet.getString("telefono"),
                    resultSet.getString("tipo_producto"),
                    resultSet.getString("encargado")
                );
                ProductoFarmaceutico producto = new ProductoFarmaceutico(
                    resultSet.getString("codigo"),
                    resultSet.getString("nombre"),
                    resultSet.getDouble("precio"),
                    resultSet.getInt("stock"),
                    resultSet.getDate("fechaVencimiento"),
                    proveedor
                );
                productos.add(producto);
                System.out.println("Producto agregado: " + producto.getNombre() + " con proveedor " + proveedor.getEncargado());
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener el inventario de productos");
            e.printStackTrace();
        }
        return productos;
    }
    public void generarReporteInventario(String filePath) {
        List<ProductoFarmaceutico> productos = obtenerInventarioProductos();
        try {
            PdfWriter writer = new PdfWriter(filePath);
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document document = new Document(pdfDoc);
            document.add(new Paragraph("Reporte de Inventario de Productos"));
            float[] columnWidths = {50, 100, 100, 100, 100, 100, 100, 100};
            Table table = new Table(columnWidths);
            table.addHeaderCell("Código");
            table.addHeaderCell("Nombre");
            table.addHeaderCell("Precio");
            table.addHeaderCell("Stock");
            table.addHeaderCell("Fecha de Vencimiento");
            table.addHeaderCell("Proveedor");
            table.addHeaderCell("Teléfono Proveedor");
            table.addHeaderCell("Tipo Producto");
            for (ProductoFarmaceutico producto : productos) {
                table.addCell(producto.getCodigo());
                table.addCell(producto.getNombre());
                table.addCell(String.valueOf(producto.getPrecio()));
                table.addCell(String.valueOf(producto.getStock()));
                table.addCell(producto.getFechaVencimiento().toString());
                table.addCell(producto.getProveedor().getEncargado());
                table.addCell(producto.getProveedor().getTelefono());
                table.addCell(producto.getProveedor().getTipo_producto());
            }

            document.add(table);
            document.close();
            System.out.println("Reporte de inventario generado en: " + filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
