# 🚀 Configuración Rápida MySQL Local

## El Problema que Tienes

El servidor remoto `45.88.196.5` está **denegando el acceso** desde tu IP (`201.202.14.126`). Esto es muy común en servidores de hosting compartido.

## ✅ Solución Recomendada: MySQL Local

### Opción 1: XAMPP (Más Fácil)

1. **Descargar XAMPP:**
   ```
   https://www.apachefriends.org/download.html
   ```

2. **Instalar y ejecutar:**
   - Instalar XAMPP
   - Abrir XAMPP Control Panel
   - Hacer clic en "Start" para MySQL

3. **Configurar base de datos:**
   - Abrir navegador: `http://localhost/phpmyadmin`
   - Crear nueva base de datos: `gestion_db`
   - Usuario: `root`, Contraseña: (vacía por defecto)

### Opción 2: MySQL Standalone

#### Windows:
```bash
# Descargar MySQL Community Server
https://dev.mysql.com/downloads/mysql/

# Durante instalación:
# - Usuario: root
# - Contraseña: tu_password
# - Puerto: 3306
```

#### Linux (Ubuntu/Debian):
```bash
sudo apt update
sudo apt install mysql-server
sudo mysql_secure_installation

# Crear base de datos
sudo mysql -u root -p
CREATE DATABASE gestion_db;
CREATE USER 'app_user'@'localhost' IDENTIFIED BY 'tu_password';
GRANT ALL PRIVILEGES ON gestion_db.* TO 'app_user'@'localhost';
FLUSH PRIVILEGES;
EXIT;
```

#### macOS:
```bash
# Con Homebrew
brew install mysql
brew services start mysql

# Configurar
mysql_secure_installation
mysql -u root -p
CREATE DATABASE gestion_db;
```

## 🔧 Configurar la Aplicación

1. **Copiar configuración local:**
   ```bash
   cp src/main/resources/database-local.properties.example src/main/resources/database.properties
   ```

2. **Editar `database.properties`:**
   ```properties
   # Para XAMPP
   db.url=jdbc:mysql://localhost:3306/gestion_db
   db.user=root
   db.password=
   
   # Para MySQL standalone
   db.url=jdbc:mysql://localhost:3306/gestion_db
   db.user=root
   db.password=tu_password_mysql
   ```

## 🧪 Probar la Conexión

```bash
# Ejecutar herramienta de diagnóstico
java -cp "target/classes:dependencias/*" model.DatabaseDiagnostic
```

## 🚀 Ejecutar la Aplicación

```bash
# Una vez configurado MySQL local
java -cp "target/classes:dependencias/*" view.MenuForm
```

## ✨ Ventajas de MySQL Local

- ✅ **Control total**: Sin restricciones de IP
- ✅ **Velocidad**: Conexión local súper rápida  
- ✅ **Desarrollo**: Ideal para pruebas y desarrollo
- ✅ **Sin dependencias**: No depende de servidor externo
- ✅ **Privacidad**: Tus datos están en tu máquina

## 🔄 Migración de Datos (Opcional)

Si necesitas datos del servidor remoto más adelante:

```bash
# Exportar desde servidor remoto (si tienes acceso)
mysqldump -h 45.88.196.5 -u usuario -p base_datos > backup.sql

# Importar a local
mysql -u root -p gestion_db < backup.sql
```

## 🆘 Solución de Problemas

### Error "Driver not found":
```bash
# Verificar que mysql-connector-java esté en el classpath
mvn dependency:build-classpath
```

### Error "Unknown database":
```sql
-- Crear la base de datos manualmente
CREATE DATABASE gestion_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

### Puerto ocupado:
```bash
# Verificar qué usa el puerto 3306
netstat -an | grep 3306

# Cambiar puerto en my.cnf si es necesario
[mysqld]
port=3307
```

## 📞 Si Necesitas el Servidor Remoto

Para usar el servidor original necesitarías:

1. **Contactar al administrador** del hosting
2. **Solicitar acceso** desde tu IP: `201.202.14.126`
3. **Verificar credenciales** del usuario
4. **Confirmar permisos** de conexión remota

¡Pero MySQL local es mucho mejor para desarrollo! 🎉