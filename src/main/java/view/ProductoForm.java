package view;

import controller.ProductoController;
import model.Producto.ProductoModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Date;
import java.util.List;

public class ProductoForm {
    private JPanel ProductoForm;
    private JTextField textNombrw;
    private JTextField textFecha;
    private JTextField textField3;
    private JButton atrásButton;
    private JTextField textStock;
    private JButton guardarButton1;
    private JTextField textPrecio;
    private JTextField textNombre;
    private JTextField textDescripcion;
    private JButton guardarButton;
    private JButton eliminarButton;
    private JButton actualizarButton;
    private JButton buscarButton;
    private JTextField textID;
    private JButton atrasButton;
    private JTable table1;
    private ProductoController productoController;
    private DefaultTableModel tableModel;

    public static void main(String[] args) {
        JFrame frame = new JFrame("ProductoForm");
        frame.setContentPane(new ProductoForm().ProductoForm);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200,600);
        frame.setVisible(true);
    }
    public ProductoForm() {
        productoController = new ProductoController(new ConsoleView());
        tableModel = new DefaultTableModel(new Object[]{"producto_id", "nombre", "descripcion", "precio", "stock", "fecha_creacion"}, 0);
        table1.setModel(tableModel);

        guardarButton.addActionListener(e -> {
            agregarProducto();
            cargarProductos();
            limpiarCampos();
        });

        eliminarButton.addActionListener(e -> {
            eliminarProducto();
            cargarProductos();
            limpiarCampos();
        });

        buscarButton.addActionListener(e -> buscarProducto());

        actualizarButton.addActionListener(e -> {
            actualizarProducto();
            cargarProductos();
            limpiarCampos();
        });

        cargarProductos();

    }

    public JPanel getProductoForm() {
        return ProductoForm;
    }

    public void agregarProducto() {
        String nombre = textNombre.getText();
        String descripcion = textDescripcion.getText();
        Double precio = Double.valueOf(textPrecio.getText());
        int stock = Integer.parseInt(textStock.getText());
        Date fechaCreacion = Date.valueOf(textFecha.getText());


        productoController.agregarProducto(nombre, descripcion,precio,stock, fechaCreacion);
        JOptionPane.showMessageDialog(null, "El producto fue agregado con éxito");
    }
    public void buscarProducto() {
        int id = Integer.parseInt(textID.getText());
        ProductoModel producto = productoController.getProductoByID(id);

        if (producto == null) {
            JOptionPane.showMessageDialog(null, "Producto no encontrado");
        } else {
            textNombre.setText(producto.getNombre());
            textDescripcion.setText(producto.getDescripcion());
            textPrecio.setText(String.valueOf(producto.getPrecio()));
            textStock.setText(String.valueOf(producto.getStock()));
            textFecha.setText(producto.getFecha_creacion().toString());
        }
    }
    public void actualizarProducto() {
        int id = Integer.parseInt(textID.getText());
        String nombre = textNombre.getText();
        String descripcion = textDescripcion.getText();
        double precio = Double.parseDouble(textPrecio.getText());
        int stock = Integer.parseInt(textStock.getText());
        Date fechaCreacion = Date.valueOf(textFecha.getText());

        productoController.actualizarProducto(id, nombre, descripcion, precio, stock, fechaCreacion);
        JOptionPane.showMessageDialog(null, "El producto fue actualizado con éxito");
    }
    public void cargarProductos() {
        tableModel.setRowCount(0);
        try {
            List<ProductoModel> productos = productoController.obtenerTodosLosProductos();
            for (ProductoModel producto : productos) {
                tableModel.addRow(new Object[]{
                        producto.getProducto_id(),
                        producto.getNombre(),
                        producto.getDescripcion(),
                        producto.getPrecio(),
                        producto.getStock(),
                        producto.getFecha_creacion()
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar productos: " + e.getMessage());
        }
    }

    public void eliminarProducto() {
        int id = Integer.parseInt(textID.getText());
        productoController.eliminarProducto(id);
        JOptionPane.showMessageDialog(null, "El producto fue eliminado con éxito");
    }
    public void limpiarCampos() {
        textNombre.setText("");
        textDescripcion.setText("");
        textPrecio.setText("");
        textStock.setText("");
        textFecha.setText("");
    }

}