package controller;

import model.Conexion;
import view.ConsoleView;

import java.sql.Connection;
import java.sql.SQLException;

public class ConexionController {

    private ConsoleView viewConsole;

    public ConexionController(ConsoleView viewConsole){
        this.viewConsole = viewConsole;
    }

    public void openConnection(){
        Connection connection = Conexion.getConnection();
        if (connection!=null){
            try{
                connection.close();
                viewConsole.showMessage("Conexion Establecida\n");
            }catch (SQLException e){
                viewConsole.errorMessage("Error al conectar"+e.getMessage());
            }
        }
    }

}
