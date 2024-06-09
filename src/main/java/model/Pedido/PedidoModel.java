package model.Pedido;

import java.sql.Date;

public class PedidoModel {
    private int pedido_id;
    private int clienteId;
    private Date fechaPedido;
    private double total;
    private String estado;

    public PedidoModel(int clienteId, Date fechaPedido, double total, String estado) {
        this.clienteId = clienteId;
        this.fechaPedido = fechaPedido;
        this.total = total;
        this.estado = estado;
    }

    public int getPedido_id() {
        return pedido_id;
    }

    public void setPedido_id(int pedido_id) {
        this.pedido_id = pedido_id;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setIdCliente(int clienteId) {
        this.clienteId = clienteId;
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


