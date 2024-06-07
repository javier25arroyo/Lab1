package model.Pedido;

import java.sql.Date;

public class PedidoModel {
    private int idCliente;
    private Date fechaPedido;
    private double total;
    private String estado;

    public PedidoModel(int idCliente, Date fechaPedido, double total, String estado) {
        this.idCliente = idCliente;
        this.fechaPedido = fechaPedido;
        this.total = total;
        this.estado = estado;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public Date getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(Date fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}


