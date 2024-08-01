package view;

import javax.swing.*;
import java.awt.*;

public class ProductoForm {
    private JPanel ProductoForm;
    private JTextField textNombrw;
    private JTextField textFecha;
    private JTextField textField3;
    private JButton atrásButton;
    private JTable table1;
    private JTextField textDescripcion;
    private JTextField textStock;
    private JButton guardarButton1;
    private JButton eliminarButton;
    private JButton actualizarButton;
    private JButton buscarButton;
    private JTextField textPrecio;


    public static void main(String[] args) {
        JFrame frame = new JFrame("ProductoForm");
        frame.setContentPane(new ProductoForm().ProductoForm);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public JPanel getProductoForm() {
        return ProductoForm;
    }

}
