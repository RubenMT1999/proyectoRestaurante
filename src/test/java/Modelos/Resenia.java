package Modelos;

import java.util.Objects;

public class Resenia {
    private int id;
    private String nombreCliente;
    private String comentario;
    private int puntuacion;
    private String valoración;


    public Resenia() {
    }

    public Resenia(int id, String nombreCliente, String comentario, int puntuacion, String valoración) {
        this.id = id;
        this.nombreCliente = nombreCliente;
        this.comentario = comentario;
        this.puntuacion = puntuacion;
        this.valoración = valoración;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public String getValoración() {
        return valoración;
    }

    public void setValoración(String valoración) {
        this.valoración = valoración;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resenia resenia = (Resenia) o;
        return id == resenia.id && puntuacion == resenia.puntuacion && Objects.equals(nombreCliente, resenia.nombreCliente) && Objects.equals(comentario, resenia.comentario) && Objects.equals(valoración, resenia.valoración);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombreCliente, comentario, puntuacion, valoración);
    }

    @Override
    public String toString() {
        return "Resenia{" +
                "id=" + id +
                ", nombreCliente='" + nombreCliente + '\'' +
                ", comentario='" + comentario + '\'' +
                ", puntuacion=" + puntuacion +
                ", valoración='" + valoración + '\'' +
                '}';
    }
}
