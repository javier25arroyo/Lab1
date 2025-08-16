# 🏢 Sistema de Gestión Empresarial

Sistema completo de gestión empresarial desarrollado en Java con interfaz gráfica Swing, que permite administrar clientes, empleados, productos, proveedores, pedidos y roles de usuario.

## 🚀 Características Principales

- ✅ **Inicialización automática de base de datos** - Crea todas las tablas automáticamente
- ✅ **Gestión completa de entidades** - Clientes, empleados, productos, proveedores, pedidos y roles
- ✅ **Interfaz gráfica moderna** - FlatLaf Look & Feel para mejor experiencia de usuario
- ✅ **Sistema de autenticación** - Login con roles de usuario
- ✅ **Validaciones robustas** - Validación de datos en formularios
- ✅ **Múltiples opciones de configuración** - Archivos de propiedades, variables de entorno

## 📁 Estructura del Proyecto

### 🎯 Modelos (Models)
- **ClienteModel**: Gestión de información de clientes
- **EmpleadoModel**: Datos de empleados (ID, nombre, apellido, cargo, salario, fecha contratación)
- **ProductoModel**: Catálogo de productos con precios y stock
- **ProveedorModel**: Información de proveedores
- **PedidoModel**: Gestión de pedidos de clientes
- **RolModel**: Roles del sistema (ADMIN, USER, MANAGER)
- **ClienteRolModel**: Relación entre clientes y roles

### 🎮 Controladores (Controllers)
- **ClienteController**: CRUD de clientes
- **EmpleadoController**: CRUD de empleados
- **ProductoController**: CRUD de productos
- **ProveedorController**: CRUD de proveedores
- **PedidoController**: CRUD de pedidos
- **RolController**: CRUD de roles
- **ClienteRolController**: Gestión de roles de clientes

### 🖥️ Vistas (Views)
- **MenuForm**: Menú principal de navegación
- **LoginForm**: Formulario de autenticación
- **ClienteForm**: Gestión de clientes
- **EmpleadoForm**: Gestión de empleados
- **ProductoForm**: Gestión de productos
- **ProveedorForm**: Gestión de proveedores
- **PedidoForm**: Gestión de pedidos
- **RolForm**: Gestión de roles
- **ClienteRolForm**: Asignación de roles a clientes

### 🔧 Utilidades
- **Conexion**: Gestión de conexiones a base de datos
- **DatabaseInitializer**: Inicialización automática de tablas
- **DatabaseDiagnostic**: Herramienta de diagnóstico de conexión

## ⚙️ Requisitos

- **Java 21** o superior
- **Maven 3.6+**
- **MySQL 8.0+** (o XAMPP para desarrollo local)
- **Git** para control de versiones

## 🛠️ Instalación y Configuración

### 1. Clonar el repositorio
```bash
git clone https://github.com/javier25arroyo/Lab1.git
cd Lab1
```

### 2. Configurar Base de Datos

#### Opción A: XAMPP (Recomendado para desarrollo)
```bash
# 1. Instalar XAMPP desde https://www.apachefriends.org/
# 2. Iniciar MySQL en XAMPP Control Panel
# 3. Crear base de datos 'gestion_db' en phpMyAdmin (localhost/phpmyadmin)
```

#### Opción B: MySQL Standalone
```bash
# Crear base de datos
CREATE DATABASE gestion_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

### 3. Configurar Conexión

Copiar y editar el archivo de configuración:
```bash
cp src/main/resources/database.properties.example src/main/resources/database.properties
```

Editar `database.properties`:
```properties
# Para XAMPP
db.url=jdbc:mysql://localhost:3306/gestion_db
db.user=root
db.password=

# Para MySQL standalone
db.url=jdbc:mysql://localhost:3306/gestion_db
db.user=tu_usuario
db.password=tu_contraseña

# Configuración de inicialización
db.auto.create.tables=true
db.insert.sample.data=true
```

### 4. Compilar el proyecto
```bash
mvn clean compile
```

## 🚀 Ejecución

### Aplicación Principal
```bash
java -cp "target/classes:dependencias/*" view.MenuForm
```

### Diagnóstico de Conexión
```bash
java -cp "target/classes:dependencias/*" model.DatabaseDiagnostic
```

### Usuario por Defecto
- **Email**: `admin@sistema.com`
- **Contraseña**: `admin123`

## 🧪 Testing

Verificar la conexión antes de usar la aplicación:
```bash
java -cp "target/classes:dependencias/*" model.DatabaseDiagnostic
```

## 📚 Documentación Adicional

- 📊 [**Esquema de Base de Datos**](DATABASE_SCHEMA.md) - Estructura completa de tablas y relaciones
- ⚙️ [**Configuración de BD**](DATABASE_CONFIG.md) - Guía detallada de configuración
- 🔧 [**Solución de Conexión**](SOLUCION_CONEXION.md) - Resolución de problemas de conectividad
- 🚀 [**Setup MySQL Local**](setup-local-mysql.md) - Configuración rápida con XAMPP
- 🔒 [**Política de Seguridad**](SECURITY.md) - Información de seguridad del proyecto

## 🆘 Resolución de Problemas

### Error de Conexión
Si experimenta problemas de conexión, ejecute el diagnóstico:
```bash
java -cp "target/classes:dependencias/*" model.DatabaseDiagnostic
```

### Problemas Comunes
- **"Driver not found"**: Verificar dependencias Maven con `mvn clean compile`
- **"Unknown database"**: Crear la base de datos `gestion_db` manualmente
- **"Access denied"**: Verificar credenciales en `database.properties`

## 📄 Licencia

Este proyecto está bajo la Licencia MIT. Ver el archivo `LICENSE` para más detalles.

## 👨‍💻 Autores

- **Javier Arroyo** - *Desarrollo inicial* - [javier25arroyo](https://github.com/javier25arroyo)

---

⭐ **¡Dale una estrella al proyecto si te fue útil!**
