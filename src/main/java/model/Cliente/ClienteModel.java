package model.Cliente;

import java.sql.Date;

public class ClienteModel {
    private int cliente_id;
        private String nombre;
        private String apellido;
        private String email;
        private String contrasena;
        private String telefono;
        private java.sql.Date fechaRegistro;

        public ClienteModel(String nombre, String apellido, String email, String telefono, java.sql.Date fechaRegistro,String contrasena) {
            this.nombre = nombre;
            this.apellido = apellido;
            this.email = email;
            this.telefono = telefono;
            this.fechaRegistro = fechaRegistro;
            this.contrasena = contrasena;

        }

    public ClienteModel(int cliente_id, String nombre, String apellido, String email, String telefono, Date fechaRegistro,String contrasena) {
        this.cliente_id = cliente_id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
        this.fechaRegistro = fechaRegistro;
        this.contrasena = contrasena;
    }

    public int getCliente_id() {
        return cliente_id;
    }

    public void setCliente_id(int cliente_id) {
        this.cliente_id = cliente_id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    @Override
    public String toString() {
        return "ClienteModel{" +
                "cliente_id=" + cliente_id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", email='" + email + '\'' +
                ", contrasena='" + contrasena + '\'' +
                ", telefono='" + telefono + '\'' +
                ", fechaRegistro=" + fechaRegistro +
                '}';
    }
}
