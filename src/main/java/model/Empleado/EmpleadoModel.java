package model.Empleado;

import java.sql.Date;

public class EmpleadoModel {
    private int empleado_id;
    private String nombre;
    private String apellido;
    private String cargo;
    private double salario;
    private Date fecha_contratacion;

    public EmpleadoModel(String nombre, String apellido, String cargo, double salario, Date fecha_contratacion) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.cargo = cargo;
        this.salario = salario;
        this.fecha_contratacion = fecha_contratacion;
    }

    public int getEmpleado_id() {
        return empleado_id;
    }

    public void setEmpleado_id(int empleado_id) {
        this.empleado_id = empleado_id;
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

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public Date getFecha_contratacion() {
        return fecha_contratacion;
    }

    public void setFecha_contratacion(Date fecha_contratacion) {
        this.fecha_contratacion = fecha_contratacion;
    }
}

