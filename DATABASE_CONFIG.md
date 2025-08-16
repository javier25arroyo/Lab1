# Configuración de Base de Datos

Este proyecto soporta múltiples formas de configurar la conexión a la base de datos para mayor flexibilidad y seguridad.

## 🚀 Inicialización Automática

**¡NUEVA FUNCIONALIDAD!** La aplicación ahora **crea automáticamente todas las tablas** necesarias al ejecutarse por primera vez. Solo necesita:

1. Configurar la conexión a MySQL
2. Ejecutar la aplicación 
3. ¡Las tablas se crean automáticamente!

### Características:
- ✅ Creación automática de todas las tablas
- ✅ Datos de ejemplo insertados automáticamente 
- ✅ Usuario administrador por defecto: `admin@sistema.com` / `admin123`
- ✅ Roles del sistema preconfigurados

## 🔧 Métodos de Configuración (en orden de prioridad)

### 1. Archivo de Propiedades (Recomendado)
Copie el archivo de ejemplo y configure sus credenciales:

```bash
cp src/main/resources/database.properties.example src/main/resources/database.properties
```

Edite `src/main/resources/database.properties` con sus credenciales:

```properties
db.url=jdbc:mysql://su-servidor:3306/su_base_de_datos
db.user=su_usuario
db.password=su_contraseña
db.driver=com.mysql.cj.jdbc.Driver
db.connection.timeout=30000
db.max.pool.size=10

# Configuración de inicialización automática
db.auto.create.tables=true
db.insert.sample.data=true
```

⚠️ **IMPORTANTE**: Este archivo está en `.gitignore` y no se subirá al repositorio.

**Opciones de inicialización:**
- `db.auto.create.tables=true`: Crea las tablas automáticamente si no existen
- `db.insert.sample.data=true`: Inserta datos de ejemplo (roles y usuario admin)

### 2. Variables de Entorno
Configure las siguientes variables de entorno:

```bash
export DB_URL="jdbc:mysql://localhost:3306/gestion_db"
export DB_USER="root"
export DB_PASSWORD="su_contraseña"
```

### 3. Configuración en pom.xml (Desarrollo)
Para desarrollo, puede modificar las propiedades en `pom.xml`:

```xml
<properties>
    <db.url>jdbc:mysql://localhost:3306/gestion_db</db.url>
    <db.user>root</db.user>
    <db.password>su_contraseña</db.password>
</properties>
```

⚠️ **CUIDADO**: No haga commit de credenciales reales en pom.xml.

## 🚀 Ejecución

```bash
# Compilar el proyecto
mvn clean compile

# Ejecutar la aplicación
java -cp "target/classes:dependencias/*" view.MenuForm
```

## 🔒 Seguridad

- **Archivo de propiedades**: Excluido del control de versiones
- **Variables de entorno**: Seguras para producción
- **Validación**: La aplicación valida la conexión antes de usar
- **Fallback**: Sistema de respaldo en caso de configuración faltante

## 📋 Resolución de Problemas

1. **Error de conexión**: Verifique que el servidor MySQL esté ejecutándose
2. **Credenciales inválidas**: Confirme usuario y contraseña
3. **Base de datos no encontrada**: Verifique que la base de datos exista
4. **Puerto bloqueado**: Confirme que el puerto 3306 esté abierto

## 📁 Estructura de Archivos

```
src/main/resources/
├── database.properties          # Sus credenciales (NO en git)
└── database.properties.example  # Plantilla (SÍ en git)
```