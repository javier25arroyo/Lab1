package model;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Conexion {
    private static final Properties dbProperties = loadDatabaseProperties();
    private static Connection instance = null;
    
    private static Properties loadDatabaseProperties() {
        Properties props = new Properties();
        
        // Intentar cargar desde archivo de propiedades
        try (InputStream input = Conexion.class.getClassLoader().getResourceAsStream("database.properties")) {
            if (input != null) {
                props.load(input);
                System.out.println("Configuración cargada desde database.properties");
                return props;
            }
        } catch (IOException e) {
            System.err.println("Error al cargar database.properties: " + e.getMessage());
        }
        
        // Fallback a variables de entorno o valores por defecto
        props.setProperty("db.url", System.getenv("DB_URL") != null ? 
            System.getenv("DB_URL") : "jdbc:mysql://45.88.196.5:3306/u484426513_diseno224");
        props.setProperty("db.user", System.getenv("DB_USER") != null ? 
            System.getenv("DB_USER") : "u484426513_diseno224");
        props.setProperty("db.password", System.getenv("DB_PASSWORD") != null ? 
            System.getenv("DB_PASSWORD") : "#7cYr646u@*Rp.P");
            
        System.out.println("Configuración cargada desde variables de entorno/valores por defecto");
        return props;
    }

    public static synchronized Connection getConnection(){
        try {
            if (instance == null || instance.isClosed()) {
                String url = dbProperties.getProperty("db.url");
                String user = dbProperties.getProperty("db.user");
                String password = dbProperties.getProperty("db.password");
                
                instance = DriverManager.getConnection(url, user, password);
                System.out.println("Conexión establecida exitosamente");
            }
        } catch (SQLException e){
            System.err.println("Fallo la conexion: " + e.getMessage());
            throw new RuntimeException("Error de conexión a la base de datos", e);
        }
        return instance;
    }
    
    public static void closeConnection() {
        try {
            if (instance != null && !instance.isClosed()) {
                instance.close();
            }
        } catch (SQLException e) {
            System.err.println("Error al cerrar la conexión: " + e.getMessage());
        }
    }
}