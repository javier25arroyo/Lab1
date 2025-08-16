# 🔒 Política de Seguridad

## 📋 Versiones Soportadas

Las siguientes versiones del Sistema de Gestión Empresarial están siendo mantenidas con actualizaciones de seguridad:

| Versión | Soportada          | Estado |
| ------- | ------------------ | ------ |
| 1.0.x   | ✅ | Versión actual, soporte completo |
| < 1.0   | ❌ | Versiones de desarrollo, no recomendadas para producción |

## 🛡️ Características de Seguridad

### Autenticación y Autorización
- ✅ **Sistema de login** con validación de credenciales
- ✅ **Roles de usuario** (ADMIN, USER, MANAGER)
- ✅ **Contraseñas encriptadas** en base de datos
- ✅ **Validación de sesiones** en la aplicación

### Protección de Datos
- ✅ **Configuración segura** - Credenciales en archivos no versionados
- ✅ **Validación de entrada** - Sanitización de datos en formularios
- ✅ **Prepared Statements** - Protección contra SQL injection
- ✅ **Manejo de errores** - Sin exposición de información sensible

### Configuración Segura
- ✅ **Variables de entorno** para credenciales de producción
- ✅ **Archivos .gitignore** que excluyen configuraciones sensibles
- ✅ **Conexiones SSL** disponibles para base de datos remota
- ✅ **Timeouts de conexión** configurables

## 🚨 Reportar una Vulnerabilidad

### Dónde Reportar
Si descubre una vulnerabilidad de seguridad, por favor:

1. **NO** cree un issue público en GitHub
2. **SÍ** envíe un email a: [javier25arroyo@gmail.com]
3. **SÍ** incluya los detalles en el reporte

### Qué Incluir en el Reporte
- **Descripción detallada** de la vulnerabilidad
- **Pasos para reproducir** el problema
- **Impacto potencial** y severidad
- **Versión afectada** del sistema
- **Entorno** donde se detectó (OS, Java version, etc.)

### Proceso de Respuesta

| Tiempo | Acción |
|--------|--------|
| **24 horas** | Confirmación de recepción del reporte |
| **7 días** | Evaluación inicial y clasificación de severidad |
| **30 días** | Parche de seguridad y notificación de resolución |
| **30+ días** | Si requiere más tiempo, actualizaciones semanales |

### Clasificación de Severidad

- 🔴 **CRÍTICA**: Acceso no autorizado a datos sensibles, ejecución de código remoto
- 🟠 **ALTA**: Escalación de privilegios, bypass de autenticación
- 🟡 **MEDIA**: Exposición de información, DoS limitado
- 🟢 **BAJA**: Problemas menores de configuración, información no crítica

## 🔧 Recomendaciones de Seguridad

### Para Desarrolladores
- Siempre usar **prepared statements** para consultas SQL
- **Validar** y **sanitizar** todas las entradas de usuario
- **No hardcodear** credenciales en el código fuente
- Mantener **dependencias actualizadas** (Maven dependencies)
- Realizar **revisiones de código** antes de merge

### Para Administradores
- Usar **contraseñas fuertes** para base de datos
- Configurar **firewalls** adecuados para MySQL
- Habilitar **logs de auditoría** en base de datos
- Realizar **backups regulares** y cifrados
- Mantener **Java y sistema operativo** actualizados

### Para Usuarios
- Cambiar **contraseña por defecto** inmediatamente
- Usar **contraseñas únicas** y complejas
- **Cerrar sesión** al terminar de usar la aplicación
- **No compartir** credenciales con otros usuarios

## 📞 Contacto de Seguridad

- **Email de Seguridad**: [javier25arroyo@gmail.com]
- **Tiempo de Respuesta**: 24-48 horas
- **Idiomas**: Español, Inglés

## 🏆 Reconocimientos

Agradecemos a todos los investigadores de seguridad que ayudan a mantener nuestro proyecto seguro. Los reportes responsables de vulnerabilidades serán reconocidos públicamente (si el reportador lo desea) después de la resolución.

---

**Última actualización**: Agosto 2025  
**Próxima revisión**: Febrero 2026
