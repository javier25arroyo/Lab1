package model.Proveedor;

import java.sql.Date;

public class ProveedorModel {
    private int proveedor_id;
    private String nombre;
    private String direccion;
    private String telefono;
    private String email;
    private Date fecha_registro;

    public ProveedorModel(String nombre, String direccion, String telefono, String email, Date fecha_registro) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
        this.fecha_registro = fecha_registro;
    }

    public int getProveedor_id() {
        return proveedor_id;
    }

    public void setProveedor_id(int proveedor_id) {
        this.proveedor_id = proveedor_id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getFecha_registro() {
        return fecha_registro;
    }

    public void setFecha_registro(Date fecha_registro) {
        this.fecha_registro = fecha_registro;
    }
}



