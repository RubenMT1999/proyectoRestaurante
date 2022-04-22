package Modelos;

public class Mesa {
    private int id;
    private int numeroMesa;
    private int maxPersonas;
    private int libre;


    public Mesa(int id, int numeroMesa, int maxPersonas, int libre) {
        this.id = id;
        this.numeroMesa = numeroMesa;
        this.maxPersonas = maxPersonas;
        this.libre = libre;
    }

    public Mesa() {
    }


    public Mesa(Mesa m){
        this.id = m.id;
        this.numeroMesa = m.numeroMesa;
        this.maxPersonas = m.maxPersonas;
        this.libre = m.libre;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumeroMesa() {
        return numeroMesa;
    }

    public void setNumeroMesa(int numeroMesa) {
        this.numeroMesa = numeroMesa;
    }

    public int getMaxPersonas() {
        return maxPersonas;
    }

    public void setMaxPersonas(int maxPersonas) {
        this.maxPersonas = maxPersonas;
    }

    public int getLibre() {
        return libre;
    }

    public void setLibre(int libre) {
        this.libre = libre;
    }


    @Override
    public String toString() {
        return "Mesa{" +
                "id=" + id +
                ", numeroMesa=" + numeroMesa +
                ", maxPersonas=" + maxPersonas +
                ", libre=" + libre +
                '}';
    }
}
