package controller;

import model.Usuario.UsuarioDAO;
import model.Usuario.UsuarioModel;
import model.Conexion;
import view.ConsoleView;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class UsuarioController {
    private ConsoleView viewConsole;
    private UsuarioDAO usuarioDAO;

    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@(.+)$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    public UsuarioController(ConsoleView viewConsole) {
        this.viewConsole = viewConsole;
        Connection connection = Conexion.getConnection();
        this.usuarioDAO = new UsuarioDAO(connection);
    }

    public boolean agregarUsuario(String nombre, String apellido, String email, String contrasena, String telefono, String estado, Date fechaRegistro) {
        if (!validarDatos(nombre, apellido, email, contrasena, telefono)) {
            return false;
        }

        try {
            if (usuarioDAO.existeEmail(email)) {
                viewConsole.errorMessage("Error: El email ya está registrado en el sistema");
                return false;
            }

            UsuarioModel usuario = new UsuarioModel(nombre, apellido, email, contrasena, telefono, estado, fechaRegistro);
            usuarioDAO.agregarUsuario(usuario);
            viewConsole.showMessage("Usuario registrado correctamente");
            return true;
        } catch (SQLException e) {
            viewConsole.errorMessage("Error al registrar usuario: " + e.getMessage());
            return false;
        }
    }

    public boolean actualizarUsuario(int usuarioId, String nombre, String apellido, String email, String contrasena, String telefono, String estado, Date fechaRegistro) {
        if (!validarDatos(nombre, apellido, email, contrasena, telefono)) {
            return false;
        }

        try {
            UsuarioModel usuarioExistente = usuarioDAO.getUsuarioByID(usuarioId);
            if (usuarioExistente == null) {
                viewConsole.errorMessage("Error: Usuario no encontrado");
                return false;
            }

            if (!usuarioExistente.getEmail().equals(email) && usuarioDAO.existeEmail(email)) {
                viewConsole.errorMessage("Error: El email ya está registrado por otro usuario");
                return false;
            }

            UsuarioModel usuario = new UsuarioModel(usuarioId, nombre, apellido, email, contrasena, telefono, estado, fechaRegistro, null, null);
            usuarioDAO.actualizarUsuario(usuario);
            viewConsole.showMessage("Usuario actualizado correctamente");
            return true;
        } catch (SQLException e) {
            viewConsole.errorMessage("Error al actualizar usuario: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminarUsuario(int usuarioId) {
        try {
            UsuarioModel usuario = usuarioDAO.getUsuarioByID(usuarioId);
            if (usuario == null) {
                viewConsole.errorMessage("Error: Usuario no encontrado");
                return false;
            }

            usuarioDAO.eliminarUsuario(usuarioId);
            viewConsole.showMessage("Usuario eliminado correctamente");
            return true;
        } catch (SQLException e) {
            viewConsole.errorMessage("Error al eliminar usuario: " + e.getMessage());
            return false;
        }
    }

    public List<UsuarioModel> obtenerTodosLosUsuarios() {
        try {
            return usuarioDAO.obtenerTodosLosUsuarios();
        } catch (SQLException e) {
            viewConsole.errorMessage("Error al recuperar los usuarios: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public UsuarioModel getUsuarioByID(int id) {
        try {
            return usuarioDAO.getUsuarioByID(id);
        } catch (SQLException e) {
            viewConsole.errorMessage("Error al recuperar el usuario: " + e.getMessage());
            return null;
        }
    }

    public UsuarioModel getUsuarioByEmail(String email) {
        try {
            return usuarioDAO.getUsuarioByEmail(email);
        } catch (SQLException e) {
            viewConsole.errorMessage("Error al recuperar el usuario: " + e.getMessage());
            return null;
        }
    }

    public UsuarioModel validarLogin(String email, String contrasena) {
        try {
            if (!validarEmail(email)) {
                viewConsole.errorMessage("Error: Formato de email inválido");
                return null;
            }

            if (contrasena == null || contrasena.trim().isEmpty()) {
                viewConsole.errorMessage("Error: La contraseña es obligatoria");
                return null;
            }

            return usuarioDAO.validarLogin(email, contrasena);
        } catch (SQLException e) {
            viewConsole.errorMessage("Error al validar login: " + e.getMessage());
            return null;
        }
    }

    private boolean validarDatos(String nombre, String apellido, String email, String contrasena, String telefono) {
        if (nombre == null || nombre.trim().isEmpty()) {
            viewConsole.errorMessage("Error: El nombre es obligatorio");
            return false;
        }

        if (apellido == null || apellido.trim().isEmpty()) {
            viewConsole.errorMessage("Error: El apellido es obligatorio");
            return false;
        }

        if (!validarEmail(email)) {
            viewConsole.errorMessage("Error: Formato de email inválido");
            return false;
        }

        if (contrasena == null || contrasena.trim().isEmpty()) {
            viewConsole.errorMessage("Error: La contraseña es obligatoria");
            return false;
        }

        if (contrasena.length() < 6) {
            viewConsole.errorMessage("Error: La contraseña debe tener al menos 6 caracteres");
            return false;
        }

        if (telefono != null && !telefono.trim().isEmpty() && !validarTelefono(telefono)) {
            viewConsole.errorMessage("Error: Formato de teléfono inválido");
            return false;
        }

        return true;
    }

    private boolean validarEmail(String email) {
        return email != null && EMAIL_PATTERN.matcher(email).matches();
    }

    private boolean validarTelefono(String telefono) {
        return telefono.matches("^[0-9+\\-\\s()]{7,15}$");
    }
}