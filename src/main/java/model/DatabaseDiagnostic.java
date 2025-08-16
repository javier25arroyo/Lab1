package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.InputStream;
import java.io.IOException;

public class DatabaseDiagnostic {
    
    public static void main(String[] args) {
        System.out.println("=== DIAGNÓSTICO DE CONEXIÓN A BASE DE DATOS ===\n");
        
        Properties props = loadProperties();
        testConnection(props);
        suggestSolutions();
    }
    
    private static Properties loadProperties() {
        Properties props = new Properties();
        
        try (InputStream input = DatabaseDiagnostic.class.getClassLoader().getResourceAsStream("database.properties")) {
            if (input != null) {
                props.load(input);
                System.out.println("✓ Archivo database.properties encontrado");
            } else {
                System.out.println("⚠ Archivo database.properties NO encontrado, usando valores por defecto");
                setDefaultProperties(props);
            }
        } catch (IOException e) {
            System.out.println("⚠ Error al leer database.properties: " + e.getMessage());
            setDefaultProperties(props);
        }
        
        return props;
    }
    
    private static void setDefaultProperties(Properties props) {
        props.setProperty("db.url", "jdbc:mysql://45.88.196.5:3306/u484426513_diseno224");
        props.setProperty("db.user", "u484426513_diseno224");
        props.setProperty("db.password", "#7cYr646u@*Rp.P");
    }
    
    private static void testConnection(Properties props) {
        String url = props.getProperty("db.url");
        String user = props.getProperty("db.user");
        String password = props.getProperty("db.password");
        
        System.out.println("📋 CONFIGURACIÓN ACTUAL:");
        System.out.println("URL: " + url);
        System.out.println("Usuario: " + user);
        System.out.println("Contraseña: " + (password != null ? "***" + password.substring(Math.max(0, password.length()-3)) : "null"));
        System.out.println();
        
        System.out.println("🔍 PROBANDO CONEXIÓN...");
        
        try {
            // Verificar driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("✓ Driver MySQL encontrado");
            
            // Probar conexión
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("✅ CONEXIÓN EXITOSA!");
            System.out.println("   - Servidor: " + conn.getMetaData().getDatabaseProductName());
            System.out.println("   - Versión: " + conn.getMetaData().getDatabaseProductVersion());
            conn.close();
            
        } catch (ClassNotFoundException e) {
            System.out.println("❌ ERROR: Driver MySQL no encontrado");
            System.out.println("   Solución: Verificar que mysql-connector-java esté en el classpath");
            
        } catch (SQLException e) {
            System.out.println("❌ ERROR DE CONEXIÓN:");
            analyzeConnectionError(e, url, user);
        }
    }
    
    private static void analyzeConnectionError(SQLException e, String url, String user) {
        String message = e.getMessage().toLowerCase();
        
        if (message.contains("access denied")) {
            System.out.println("   🔐 PROBLEMA: Credenciales incorrectas o acceso denegado");
            System.out.println("   📍 Tu IP: Conectando desde IP externa");
            System.out.println("   \n   POSIBLES CAUSAS:");
            System.out.println("   1. Usuario/contraseña incorrectos");
            System.out.println("   2. Base de datos no permite conexiones desde tu IP");
            System.out.println("   3. Privilegios de usuario insuficientes");
            
        } else if (message.contains("unknown host") || message.contains("connection refused")) {
            System.out.println("   🌐 PROBLEMA: No se puede conectar al servidor");
            System.out.println("   \n   POSIBLES CAUSAS:");
            System.out.println("   1. Servidor no disponible");
            System.out.println("   2. Firewall bloqueando conexión");
            System.out.println("   3. URL de conexión incorrecta");
            
        } else if (message.contains("unknown database")) {
            System.out.println("   🗄️ PROBLEMA: Base de datos no existe");
            System.out.println("   \n   SOLUCIÓN:");
            System.out.println("   1. Crear la base de datos en el servidor");
            System.out.println("   2. Verificar el nombre en la URL");
            
        } else {
            System.out.println("   ❓ ERROR DESCONOCIDO: " + e.getMessage());
        }
    }
    
    private static void suggestSolutions() {
        System.out.println("\n💡 SOLUCIONES RECOMENDADAS:");
        System.out.println("\n1️⃣ USAR BASE DE DATOS LOCAL (Recomendado):");
        System.out.println("   - Instalar MySQL localmente");
        System.out.println("   - Configurar usuario y base de datos local");
        System.out.println("   - La aplicación creará las tablas automáticamente");
        
        System.out.println("\n2️⃣ CONFIGURACIÓN LOCAL MYSQL:");
        System.out.println("   URL: jdbc:mysql://localhost:3306/gestion_db");
        System.out.println("   Usuario: root");
        System.out.println("   Contraseña: tu_password_local");
        
        System.out.println("\n3️⃣ VERIFICAR SERVIDOR REMOTO:");
        System.out.println("   - Contactar administrador del servidor");
        System.out.println("   - Solicitar acceso desde tu IP");
        System.out.println("   - Verificar credenciales");
        
        System.out.println("\n4️⃣ USAR XAMPP/WAMP (Para desarrollo):");
        System.out.println("   - Instalar XAMPP o WAMP");
        System.out.println("   - Iniciar servidor MySQL");
        System.out.println("   - Crear base de datos 'gestion_db'");
        
        System.out.println("\n📝 Para cambiar configuración:");
        System.out.println("   Editar: src/main/resources/database.properties");
    }
}