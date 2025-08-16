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
    private static boolean databaseInitialized = false;
    
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
                
                // Inicializar base de datos si es la primera conexión
                if (!databaseInitialized) {
                    initializeDatabaseSchema();
                    databaseInitialized = true;
                }
            }
        } catch (SQLException e){
            System.err.println("❌ FALLO EN LA CONEXIÓN A LA BASE DE DATOS");
            System.err.println("Error: " + e.getMessage());
            
            // Análisis específico del error
            String message = e.getMessage().toLowerCase();
            if (message.contains("access denied")) {
                System.err.println("\n🔐 PROBLEMA: Credenciales incorrectas o acceso denegado");
                System.err.println("💡 SOLUCIONES:");
                System.err.println("   1. Verificar usuario y contraseña en database.properties");
                System.err.println("   2. Configurar MySQL local (recomendado)");
                System.err.println("   3. Contactar administrador del servidor para acceso desde su IP");
                System.err.println("\n📖 Ver: setup-local-mysql.md para configuración local");
                System.err.println("🔧 Ejecutar: java model.DatabaseDiagnostic para diagnóstico");
                
            } else if (message.contains("unknown host") || message.contains("connection refused")) {
                System.err.println("\n🌐 PROBLEMA: Servidor no disponible");
                System.err.println("💡 SOLUCIÓN: Configurar MySQL local (ver setup-local-mysql.md)");
                
            } else if (message.contains("unknown database")) {
                System.err.println("\n🗄️ PROBLEMA: Base de datos no existe");
                System.err.println("💡 SOLUCIÓN: Crear base de datos 'gestion_db' en MySQL");
            }
            
            throw new RuntimeException("Error de conexión a la base de datos. Ver mensajes anteriores para soluciones.", e);
        }
        return instance;
    }
    
    private static void initializeDatabaseSchema() {
        try {
            System.out.println("Inicializando esquema de base de datos...");
            DatabaseInitializer.initializeDatabase(instance);
            
            // Insertar datos de ejemplo si la configuración lo permite
            String insertSampleData = dbProperties.getProperty("db.insert.sample.data", "true");
            if ("true".equalsIgnoreCase(insertSampleData)) {
                DatabaseInitializer.insertSampleData(instance);
            }
            
        } catch (Exception e) {
            System.err.println("Error al inicializar esquema de base de datos: " + e.getMessage());
            // No lanzamos excepción aquí para permitir que la aplicación continúe
        }
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