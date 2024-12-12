# Employee and Client Role Management System

This project is a Java-based application for managing employees and client roles. It allows users to add, update, search, and delete employees and their associated roles.

## Project Structure

### Models

- **EmpleadoModel**: Represents an employee with attributes such as `empleado_id`, `nombre`, `apellido`, `cargo`, `salario`, and `fecha_contratacion`.
- **ClienteRolModel**: Represents the relationship between a client and a role.

### Controllers

- **EmpleadoController**: Manages CRUD (Create, Read, Update, Delete) operations for employees.
- **ClienteRolController**: Manages CRUD operations for client roles.

### Views

- **EmpleadoForm**: GUI for managing employees. Allows adding, updating, searching, and deleting employees.
- **ClienteRolForm**: GUI for managing client roles. Allows adding, updating, searching, and deleting client roles.

### Database Connection

- **Conexion**: Manages the database connection using environment variables `DB_URL`, `DB_USER`, and `DB_PASSWORD`.

## Requirements

- Java 8 or higher
- Maven
- MySQL Database

## Configuration

1. Clone the repository:
    ```bash
    git clone https://github.com/javier25arroyo/Lab1.git
    cd Lab1
    ```

2. Set up the environment variables for the database connection:
    ```bash
    export DB_URL=jdbc:mysql://localhost:3306/your_database
    export DB_USER=your_username
    export DB_PASSWORD=your_password
    ```

3. Build the project with Maven:
    ```bash
    mvn clean install
    ```

## Execution

To run the application, execute the main class of the desired form:

For `EmpleadoForm`:
```bash
mvn exec:java -Dexec.mainClass="view.EmpleadoForm"
```

# Sistema de Gestión de Empleados y Roles de Clientes

Este proyecto es una aplicación basada en Java para gestionar empleados y roles de clientes. Permite a los usuarios agregar, actualizar, buscar y eliminar empleados y sus roles asociados.

## Estructura del Proyecto

### Modelos

- **EmpleadoModel**: Representa un empleado con atributos como `empleado_id`, `nombre`, `apellido`, `cargo`, `salario` y `fecha_contratacion`.
- **ClienteRolModel**: Representa la relación entre un cliente y un rol.

### Controladores

- **EmpleadoController**: Gestiona las operaciones CRUD (Crear, Leer, Actualizar, Eliminar) para empleados.
- **ClienteRolController**: Gestiona las operaciones CRUD para roles de clientes.

### Vistas

- **EmpleadoForm**: Interfaz gráfica para gestionar empleados. Permite agregar, actualizar, buscar y eliminar empleados.
- **ClienteRolForm**: Interfaz gráfica para gestionar roles de clientes. Permite agregar, actualizar, buscar y eliminar roles de clientes.

### Conexión a la Base de Datos

- **Conexion**: Gestiona la conexión a la base de datos utilizando las variables de entorno `DB_URL`, `DB_USER` y `DB_PASSWORD`.

## Requisitos

- Java 8 o superior
- Maven
- Base de datos MySQL

## Configuración

1. Clona el repositorio:
    ```bash
    git clone https://github.com/javier25arroyo/Lab1.git
    cd Lab1
    ```

2. Configura las variables de entorno para la conexión a la base de datos:
    ```bash
    export DB_URL=jdbc:mysql://localhost:3306/tu_base_de_datos
    export DB_USER=tu_usuario
    export DB_PASSWORD=tu_contraseña
    ```

3. Compila el proyecto con Maven:
    ```bash
    mvn clean install
    ```

## Ejecución

Para ejecutar la aplicación, ejecuta la clase principal del formulario deseado:

Para `EmpleadoForm`:
```bash
mvn exec:java -Dexec.mainClass="view.EmpleadoForm"