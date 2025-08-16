package view;

import com.formdev.flatlaf.FlatLightLaf;
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
    private JTable table1;
    private ProductoController productoController;
    private DefaultTableModel tableModel;

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }
        
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Gestión de Productos");
            frame.setContentPane(new ProductoForm().ProductoForm);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1200, 600);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
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
        try {
            String nombre = textNombre.getText().trim();
            String descripcion = textDescripcion.getText().trim();
            
            if (nombre.isEmpty() || descripcion.isEmpty()) {
                JOptionPane.showMessageDialog(null, "El nombre y descripción son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            Double precio = Double.valueOf(textPrecio.getText().trim());
            if (precio < 0) {
                JOptionPane.showMessageDialog(null, "El precio debe ser un valor positivo", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            int stock = Integer.parseInt(textStock.getText().trim());
            if (stock < 0) {
                JOptionPane.showMessageDialog(null, "El stock debe ser un valor positivo o cero", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            Date fechaCreacion = Date.valueOf(textFecha.getText().trim());

            productoController.agregarProducto(nombre, descripcion, precio, stock, fechaCreacion);
            JOptionPane.showMessageDialog(null, "El producto fue agregado con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Precio y stock deben ser números válidos", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, "Formato de fecha inválido. Use YYYY-MM-DD", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al agregar producto: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    public void buscarProducto() {
        try {
            String idText = textID.getText().trim();
            if (idText.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Ingrese un ID de producto", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            int id = Integer.parseInt(idText);
            if (id <= 0) {
                JOptionPane.showMessageDialog(null, "El ID debe ser un número positivo", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            ProductoModel producto = productoController.getProductoByID(id);

            if (producto == null) {
                JOptionPane.showMessageDialog(null, "Producto no encontrado", "Información", JOptionPane.INFORMATION_MESSAGE);
            } else {
                textNombre.setText(producto.getNombre());
                textDescripcion.setText(producto.getDescripcion());
                textPrecio.setText(String.valueOf(producto.getPrecio()));
                textStock.setText(String.valueOf(producto.getStock()));
                textFecha.setText(producto.getFecha_creacion().toString());
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "El ID debe ser un número válido", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al buscar producto: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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