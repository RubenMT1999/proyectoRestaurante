package Modelos;

import java.util.Objects;

public class Reclamacion {
    private String nombreCliente;
    private Categoria categoria;
    private String queja;

    public Reclamacion(String nombreCliente, Categoria categoria, String queja) {
        this.nombreCliente = nombreCliente;
        this.categoria = categoria;
        this.queja = queja;
    }
    public Reclamacion(Reclamacion r1) {
        this.nombreCliente = r1.getNombreCliente();
        this.categoria = r1.getCategoria();
        this.queja = r1.getQueja();
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getQueja() {
        return queja;
    }

    public void setQueja(String queja) {
        this.queja = queja;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reclamacion that = (Reclamacion) o;
        return Objects.equals(nombreCliente, that.nombreCliente) && categoria == that.categoria && Objects.equals(queja, that.queja);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombreCliente, categoria, queja);
    }

    @Override
    public String toString() {
        return "Reclamacion{" +
                "nombreCliente='" + nombreCliente + '\'' +
                ", categoria=" + categoria +
                ", queja='" + queja + '\'' +
                '}';
    }
}
