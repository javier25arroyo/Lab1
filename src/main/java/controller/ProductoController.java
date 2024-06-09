package controller;

import model.Conexion;
import model.Producto.ProductoDAO;
import model.Producto.ProductoModel;
import view.ConsoleView;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

public class ProductoController {
    private ConsoleView viewConsole;

    private ProductoDAO productoDAO;

    public ProductoController(ConsoleView viewConsole){
        this.viewConsole = viewConsole;
        Connection connection = Conexion.getConnection();
        this.productoDAO = new ProductoDAO(connection);
    }

    public void agregarProducto(String nombre, String descripcion, double precio, int stock, Date fecha_creacion){
        ProductoModel datos = new ProductoModel(nombre, descripcion, precio, stock, fecha_creacion);

        try{
            productoDAO.agregarProducto(datos);
            viewConsole.showMessage("Inserccion de datos correcta\n");
        }catch (SQLException e){
            viewConsole.errorMessage("Error al insertar datos" + e.getMessage());
        }
    }
    public void eliminarProducto(int producto_id) {
        try {
            productoDAO.eliminarProducto(producto_id);
            viewConsole.showMessage("Eliminación de producto correcta\n");
        } catch (SQLException e) {
            viewConsole.errorMessage("Error al eliminar cliente: " + e.getMessage());
        }
    }

}
