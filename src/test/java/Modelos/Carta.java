package Modelos;

import java.util.Objects;

public class Carta {
    private int id;
    private String nombre;
    private String descripcion;
    private Categoria categoria;
    private Double precio;

    public Carta(int id, String nombre, String descripcion, Categoria categoria, Double precio) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.precio = precio;
    }

    public Carta(Carta c1) {
        this.id = c1.getId();
        this.nombre = c1.getNombre();
        this.descripcion = c1.getDescripcion();
        this.categoria = c1.getCategoria();
        this.precio = c1.getPrecio();
    }

    public Carta(int id, String nombre, String descripcion, double precio){
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Carta carta = (Carta) o;
        return id == carta.id && Objects.equals(nombre, carta.nombre) && Objects.equals(descripcion, carta.descripcion) && categoria == carta.categoria && Objects.equals(precio, carta.precio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, descripcion, categoria, precio);
    }

    @Override
    public String toString() {
        return "Carta{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", categoria=" + categoria +
                ", precio=" + precio +
                '}';
    }
}
