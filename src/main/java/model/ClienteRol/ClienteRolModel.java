package model.ClienteRol;

public class ClienteRolModel {
    private int id;
    private int FK_idCliente;
    private int FK_idRol;

    public ClienteRolModel() {
    }

    public ClienteRolModel(int FK_idCliente, int FK_idRol) {
        this.FK_idCliente = FK_idCliente;
        this.FK_idRol = FK_idRol;
    }

    public ClienteRolModel(int id, int FK_idCliente, int FK_idRol) {
        this.id = id;
        this.FK_idCliente = FK_idCliente;
        this.FK_idRol = FK_idRol;
    }

    public int getId() {
        return this.id;
    }

    public int getFK_idCliente() {
        return this.FK_idCliente;
    }

    public int getFK_idRol() {
        return this.FK_idRol;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFK_idCliente(int FK_idCliente) {
        this.FK_idCliente = FK_idCliente;
    }

    public void setFK_idRol(int FK_idRol) {
        this.FK_idRol = FK_idRol;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", FK_idCliente='" + getFK_idCliente() + "'" +
            ", FK_idRol='" + getFK_idRol() + "'" +
            "}";
    }
}
