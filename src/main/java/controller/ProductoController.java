package controller;

import model.Cliente.ClienteModel;
import model.Conexion;
import model.Empleado.EmpleadoModel;
import model.Producto.ProductoDAO;
import model.Producto.ProductoModel;
import view.ConsoleView;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public void actualizarProducto(int producto_id, String nombre, String descripcion, double precio, int stock, Date fecha_creacion) {
        ProductoModel datos = new ProductoModel(producto_id, nombre, descripcion, precio, stock, fecha_creacion);
        try {
            productoDAO.actualizarProducto(datos);
            viewConsole.showMessage("Actualización de producto correcta\n");
        } catch (SQLException e) {
            viewConsole.errorMessage("Error al actualizar producto: " + e.getMessage());
        }
    }
    public List<ProductoModel> obtenerTodosLosProductos(){
        try {
            return productoDAO.obtenerTodosLosProductos();
        } catch (SQLException e) {
            viewConsole.errorMessage("Error al recuperar los productos: " + e.getMessage());
            return new ArrayList<>();
        }
    }
    public ProductoModel getProductoByID(int id) {
        try {
            return productoDAO.getProductoByID(id);
        } catch (SQLException e) {
            viewConsole.errorMessage("Error al recuperar el producto: " + e.getMessage());
            return null;
        }
    }

}
