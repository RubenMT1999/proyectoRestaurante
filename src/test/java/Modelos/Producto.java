package Modelos;

import java.util.Objects;

public class Producto {

    private String nombre;
    private int cantidadPedida;
    private double precioTotalProducto;


    public Producto() {
    }

    public Producto(String nombre, int cantidadPedida, double precioTotalProducto) {
        this.nombre = nombre;
        this.cantidadPedida = cantidadPedida;
        this.precioTotalProducto = precioTotalProducto;
    }



    public Producto(Producto p){
        this.nombre = p.nombre;
        this.cantidadPedida = p.cantidadPedida;
        this.precioTotalProducto = p.precioTotalProducto;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidadPedida() {
        return cantidadPedida;
    }

    public void setCantidadPedida(int cantidadPedida) {
        this.cantidadPedida = cantidadPedida;
    }

    public double getPrecioTotalProducto() {
        return precioTotalProducto;
    }

    public void setPrecioTotalProducto(double precioTotalProducto) {
        this.precioTotalProducto = precioTotalProducto;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Producto producto = (Producto) o;
        return cantidadPedida == producto.cantidadPedida && Double.compare(producto.precioTotalProducto, precioTotalProducto) == 0 && Objects.equals(nombre, producto.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, cantidadPedida, precioTotalProducto);
    }


    @Override
    public String toString() {
        return "Producto{" +
                "nombre='" + nombre + '\'' +
                ", cantidadPedida=" + cantidadPedida +
                ", precioTotalProducto=" + precioTotalProducto +
                '}';
    }
}
