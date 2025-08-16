# 🔧 Solución al Error de Conexión

## ❌ El Error que Tienes

```
Access denied for user 'u484426513_diseno224'@'201.202.14.126' (using password: YES)
```

**Traducción**: El servidor remoto está **bloqueando tu IP** y/o las credenciales no son válidas.

## ✅ Solución Rápida (5 minutos)

### 1️⃣ Instalar XAMPP

```bash
# Descargar desde: https://www.apachefriends.org/
# Instalar y abrir XAMPP Control Panel
# Hacer clic en "Start" para MySQL
```

### 2️⃣ Crear Base de Datos

```bash
# Abrir en navegador: http://localhost/phpmyadmin
# Crear nueva base de datos llamada: gestion_db
```

### 3️⃣ Configurar Aplicación

```bash
# Copiar configuración local
cp src/main/resources/database-local.properties.example src/main/resources/database.properties
```

### 4️⃣ Editar Configuración

En `src/main/resources/database.properties`:

```properties
db.url=jdbc:mysql://localhost:3306/gestion_db
db.user=root
db.password=
```

### 5️⃣ ¡Ejecutar!

```bash
java -cp "target/classes:dependencias/*" view.MenuForm
```

## 🧪 Diagnóstico

Para verificar la conexión:

```bash
java -cp "target/classes:dependencias/*" model.DatabaseDiagnostic
```

## 🎯 Resultado Esperado

- ✅ Conexión exitosa a MySQL local
- ✅ Tablas creadas automáticamente 
- ✅ Usuario admin: `admin@sistema.com` / `admin123`
- ✅ Aplicación funcionando completamente

## 📋 Configuraciones Alternativas

### MySQL Standalone
```properties
db.url=jdbc:mysql://localhost:3306/gestion_db
db.user=root
db.password=tu_password_mysql
```

### Base de Datos Remota (Si tienes acceso)
```properties
db.url=jdbc:mysql://tu-servidor:3306/tu_base_de_datos
db.user=tu_usuario
db.password=tu_contraseña
```

## 🆘 Problemas Comunes

### "Driver not found"
```bash
# Verificar dependencias Maven
mvn clean compile
```

### "Unknown database 'gestion_db'"
```sql
-- En phpMyAdmin o MySQL:
CREATE DATABASE gestion_db;
```

### Puerto 3306 ocupado
```bash
# Verificar servicios en puerto 3306
netstat -an | grep 3306

# O usar puerto diferente
db.url=jdbc:mysql://localhost:3307/gestion_db
```

## 💡 ¿Por Qué MySQL Local?

- ✅ **Sin restricciones de IP**
- ✅ **Velocidad máxima** (conexión local)
- ✅ **Control total** sobre la base de datos
- ✅ **Ideal para desarrollo** y pruebas
- ✅ **Sin dependencias externas**

## 📞 ¿Necesitas el Servidor Original?

El servidor `45.88.196.5` requiere:
1. Autorización desde tu IP: `201.202.14.126`
2. Verificación de credenciales del hosting
3. Contacto con el administrador del servidor

**Pero MySQL local es mucho mejor para desarrollo! 🚀**