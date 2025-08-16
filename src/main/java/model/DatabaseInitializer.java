package model;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitializer {
    
    public static void initializeDatabase(Connection connection) {
        try {
            System.out.println("Iniciando verificación/creación de tablas...");
            
            createRolesTable(connection);
            createClientesTable(connection);
            createUsuariosTable(connection);
            createEmpleadosTable(connection);
            createProveedoresTable(connection);
            createProductosTable(connection);
            createPedidosTable(connection);
            createClienteRolTable(connection);
            
            System.out.println("Base de datos inicializada correctamente");
            
        } catch (SQLException e) {
            System.err.println("Error al inicializar la base de datos: " + e.getMessage());
            throw new RuntimeException("Error al crear tablas de la base de datos", e);
        }
    }
    
    private static void createRolesTable(Connection connection) throws SQLException {
        String sql = """
            CREATE TABLE IF NOT EXISTS roles (
                id INT AUTO_INCREMENT PRIMARY KEY,
                nombre VARCHAR(50) NOT NULL UNIQUE,
                descripcion TEXT,
                created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
            ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci
            """;
        
        executeSQL(connection, sql, "roles");
    }
    
    private static void createClientesTable(Connection connection) throws SQLException {
        String sql = """
            CREATE TABLE IF NOT EXISTS clientes (
                cliente_id INT AUTO_INCREMENT PRIMARY KEY,
                nombre VARCHAR(100) NOT NULL,
                apellido VARCHAR(100) NOT NULL,
                email VARCHAR(150) NOT NULL UNIQUE,
                telefono VARCHAR(20),
                fecha_registro DATE NOT NULL,
                contrasena VARCHAR(255) NOT NULL,
                created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                INDEX idx_email (email),
                INDEX idx_nombre (nombre, apellido)
            ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci
            """;
        
        executeSQL(connection, sql, "clientes");
    }
    
    private static void createUsuariosTable(Connection connection) throws SQLException {
        String sql = """
            CREATE TABLE IF NOT EXISTS usuarios (
                usuario_id INT AUTO_INCREMENT PRIMARY KEY,
                nombre VARCHAR(100) NOT NULL,
                apellido VARCHAR(100) NOT NULL,
                email VARCHAR(150) NOT NULL UNIQUE,
                contrasena VARCHAR(255) NOT NULL,
                telefono VARCHAR(20),
                estado VARCHAR(20) NOT NULL DEFAULT 'ACTIVO',
                fecha_registro DATE NOT NULL,
                created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                INDEX idx_email (email),
                INDEX idx_nombre (nombre, apellido),
                INDEX idx_estado (estado),
                CHECK (estado IN ('ACTIVO', 'INACTIVO', 'SUSPENDIDO'))
            ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci
            """;
        
        executeSQL(connection, sql, "usuarios");
    }
    
    private static void createEmpleadosTable(Connection connection) throws SQLException {
        String sql = """
            CREATE TABLE IF NOT EXISTS empleados (
                empleado_id INT AUTO_INCREMENT PRIMARY KEY,
                nombre VARCHAR(100) NOT NULL,
                apellido VARCHAR(100) NOT NULL,
                cargo VARCHAR(100) NOT NULL,
                salario DECIMAL(10,2) NOT NULL CHECK (salario >= 0),
                fecha_contratacion DATE NOT NULL,
                created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                INDEX idx_cargo (cargo),
                INDEX idx_nombre (nombre, apellido)
            ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci
            """;
        
        executeSQL(connection, sql, "empleados");
    }
    
    private static void createProveedoresTable(Connection connection) throws SQLException {
        String sql = """
            CREATE TABLE IF NOT EXISTS proveedores (
                proveedor_id INT AUTO_INCREMENT PRIMARY KEY,
                nombre VARCHAR(150) NOT NULL,
                direccion TEXT,
                telefono VARCHAR(20),
                email VARCHAR(150),
                fecha_registro DATE NOT NULL,
                created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                INDEX idx_nombre (nombre),
                INDEX idx_email (email)
            ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci
            """;
        
        executeSQL(connection, sql, "proveedores");
    }
    
    private static void createProductosTable(Connection connection) throws SQLException {
        String sql = """
            CREATE TABLE IF NOT EXISTS productos (
                producto_id INT AUTO_INCREMENT PRIMARY KEY,
                nombre VARCHAR(150) NOT NULL,
                descripcion TEXT,
                precio DECIMAL(10,2) NOT NULL CHECK (precio >= 0),
                stock INT NOT NULL DEFAULT 0 CHECK (stock >= 0),
                fecha_creacion DATE NOT NULL,
                created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                INDEX idx_nombre (nombre),
                INDEX idx_precio (precio),
                INDEX idx_stock (stock)
            ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci
            """;
        
        executeSQL(connection, sql, "productos");
    }
    
    private static void createPedidosTable(Connection connection) throws SQLException {
        String sql = """
            CREATE TABLE IF NOT EXISTS pedidos (
                pedido_id INT AUTO_INCREMENT PRIMARY KEY,
                cliente_id INT NOT NULL,
                fecha_pedido DATE NOT NULL,
                total DECIMAL(10,2) NOT NULL CHECK (total >= 0),
                estado VARCHAR(50) NOT NULL DEFAULT 'PENDIENTE',
                created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                FOREIGN KEY (cliente_id) REFERENCES clientes(cliente_id) ON DELETE CASCADE,
                INDEX idx_cliente (cliente_id),
                INDEX idx_fecha (fecha_pedido),
                INDEX idx_estado (estado)
            ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci
            """;
        
        executeSQL(connection, sql, "pedidos");
    }
    
    private static void createClienteRolTable(Connection connection) throws SQLException {
        String sql = """
            CREATE TABLE IF NOT EXISTS cliente_rol (
                id INT AUTO_INCREMENT PRIMARY KEY,
                cliente_id INT NOT NULL,
                rol_id INT NOT NULL,
                created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                FOREIGN KEY (cliente_id) REFERENCES clientes(cliente_id) ON DELETE CASCADE,
                FOREIGN KEY (rol_id) REFERENCES roles(id) ON DELETE CASCADE,
                UNIQUE KEY unique_cliente_rol (cliente_id, rol_id),
                INDEX idx_cliente (cliente_id),
                INDEX idx_rol (rol_id)
            ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci
            """;
        
        executeSQL(connection, sql, "cliente_rol");
    }
    
    private static void executeSQL(Connection connection, String sql, String tableName) throws SQLException {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(sql);
            System.out.println("✓ Tabla '" + tableName + "' verificada/creada correctamente");
        } catch (SQLException e) {
            System.err.println("✗ Error al crear tabla '" + tableName + "': " + e.getMessage());
            throw e;
        }
    }
    
    public static void insertSampleData(Connection connection) {
        try {
            System.out.println("Insertando datos de ejemplo...");
            
            // Insertar roles por defecto
            insertDefaultRoles(connection);
            
            // Insertar un cliente administrador por defecto
            insertDefaultAdmin(connection);
            
            // Insertar usuarios por defecto
            insertDefaultUsers(connection);
            
            System.out.println("Datos de ejemplo insertados correctamente");
            
        } catch (SQLException e) {
            System.err.println("Error al insertar datos de ejemplo: " + e.getMessage());
        }
    }
    
    private static void insertDefaultRoles(Connection connection) throws SQLException {
        String sql = """
            INSERT IGNORE INTO roles (nombre, descripcion) VALUES 
            ('ADMIN', 'Administrador del sistema con acceso completo'),
            ('USER', 'Usuario estándar con permisos limitados'),
            ('MANAGER', 'Gerente con permisos de gestión')
            """;
        
        try (Statement stmt = connection.createStatement()) {
            int rowsAffected = stmt.executeUpdate(sql);
            if (rowsAffected > 0) {
                System.out.println("✓ Roles por defecto insertados: " + rowsAffected + " registros");
            }
        }
    }
    
    private static void insertDefaultAdmin(Connection connection) throws SQLException {
        String sql = """
            INSERT IGNORE INTO clientes (nombre, apellido, email, telefono, fecha_registro, contrasena) 
            VALUES ('Admin', 'Sistema', 'admin@sistema.com', '1234567890', CURDATE(), 'admin123')
            """;
        
        try (Statement stmt = connection.createStatement()) {
            int rowsAffected = stmt.executeUpdate(sql);
            if (rowsAffected > 0) {
                System.out.println("✓ Usuario administrador por defecto creado (admin@sistema.com / admin123)");
            }
        }
    }
    
    private static void insertDefaultUsers(Connection connection) throws SQLException {
        String sql = """
            INSERT IGNORE INTO usuarios (nombre, apellido, email, contrasena, telefono, estado, fecha_registro) VALUES
            ('Administrador', 'Sistema', 'admin@usuarios.com', 'admin123', '1234567890', 'ACTIVO', CURDATE()),
            ('Usuario', 'Demo', 'demo@usuarios.com', 'demo123', '0987654321', 'ACTIVO', CURDATE()),
            ('Gerente', 'Prueba', 'gerente@usuarios.com', 'gerente123', '5555555555', 'ACTIVO', CURDATE())
            """;
        
        try (Statement stmt = connection.createStatement()) {
            int rowsAffected = stmt.executeUpdate(sql);
            if (rowsAffected > 0) {
                System.out.println("✓ Usuarios por defecto creados: " + rowsAffected + " registros");
                System.out.println("  - admin@usuarios.com / admin123");
                System.out.println("  - demo@usuarios.com / demo123");
                System.out.println("  - gerente@usuarios.com / gerente123");
            }
        }
    }
}