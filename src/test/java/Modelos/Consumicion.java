package Modelos;

import java.util.Objects;

public class Consumicion {

    private int id;
    private String id_producto;
    private int servido;
    private int cantidad_pedida;
    private int cantidad_servida;
    private Double precio;
    private int codigo_pedido;

    public Consumicion(int id, String id_producto, int servido, int cantidad_pedida, int cantidad_servida, Double precio, int codigo_pedido) {
        this.id = id;
        this.id_producto = id_producto;
        this.servido = servido;
        this.cantidad_pedida = cantidad_pedida;
        this.cantidad_servida = cantidad_servida;
        this.precio = precio;
        this.codigo_pedido = codigo_pedido;
    }
    public Consumicion(Consumicion c1) {
        this.id = c1.getId();
        this.id_producto = c1.getId_producto();
        this.servido = c1.servido;
        this.cantidad_pedida = c1.cantidad_pedida;
        this.cantidad_servida = c1.cantidad_servida;
        this.precio = c1.getPrecio();
        this.codigo_pedido = c1.getCodigo_pedido();
    }

    public Consumicion(String id_producto, int cantidad_pedida) {
        this.id_producto = id_producto;
        this.cantidad_pedida = cantidad_pedida;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getId_producto() {
        return id_producto;
    }

    public void setId_producto(String id_producto) {
        this.id_producto = id_producto;
    }

    public int getServido() {
        return servido;
    }

    public void setServido(int servido) {
        this.servido = servido;
    }

    public int getCantidad_pedida() {
        return cantidad_pedida;
    }

    public void setCantidad_pedida(int cantidad_pedida) {
        this.cantidad_pedida = cantidad_pedida;
    }

    public int getCantidad_servida() {
        return cantidad_servida;
    }

    public void setCantidad_servida(int cantidad_servida) {
        this.cantidad_servida = cantidad_servida;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public int getCodigo_pedido() {
        return codigo_pedido;
    }

    public void setCodigo_pedido(int codigo_pedido) {
        this.codigo_pedido = codigo_pedido;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Consumicion that = (Consumicion) o;
        return id == that.id && id_producto == that.id_producto && servido == that.servido && cantidad_pedida == that.cantidad_pedida && cantidad_servida == that.cantidad_servida && codigo_pedido == that.codigo_pedido && Objects.equals(precio, that.precio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, id_producto, servido, cantidad_pedida, cantidad_servida, precio, codigo_pedido);
    }

    @Override
    public String toString() {
        return "Consumicion{" +
                "id=" + id +
                ", id_producto=" + id_producto +
                ", servido=" + servido +
                ", cantidad_pedida=" + cantidad_pedida +
                ", cantidad_servida=" + cantidad_servida +
                ", precio=" + precio +
                ", codigo_pedido=" + codigo_pedido +
                '}';
    }
}
