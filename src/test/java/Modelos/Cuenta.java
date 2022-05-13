package Modelos;

import java.util.List;

public class Cuenta {

    private static int contador;
    private int id_Cuenta;
    private int id_mesa;
    private String codigo;
    private String nombreCamarero;
    private String primerApellido;
    private String segundoApellido;
    private List<Producto> listaProductos;
    private double precioTotal;

    public Cuenta(){}

    public Cuenta(int id_mesa, String codigo, String nombreCamarero, String primerApellido,
                  String segundoApellido, List<Producto> listaProductos, double precioTotal) {
        this.id_Cuenta = contador++;
        this.id_mesa = id_mesa;
        this.codigo = codigo;
        this.nombreCamarero = nombreCamarero;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.listaProductos = listaProductos;
        this.precioTotal = precioTotal;
    }

    public Cuenta(Cuenta c){
        this.id_Cuenta = c.id_Cuenta;
        this.id_mesa = c.id_mesa;
        this.codigo = c.codigo;
        this.nombreCamarero = c.nombreCamarero;
        this.primerApellido = c.primerApellido;
        this.segundoApellido = c.segundoApellido;
        this.listaProductos = c.listaProductos;
        this.precioTotal = c.precioTotal;
    }


    public int getId_Cuenta() {
        return id_Cuenta;
    }

    public void setId_Cuenta(int id_Cuenta) {
        this.id_Cuenta = id_Cuenta;
    }

    public int getId_mesa() {
        return id_mesa;
    }

    public void setId_mesa(int id_mesa) {
        this.id_mesa = id_mesa;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombreCamarero() {
        return nombreCamarero;
    }

    public void setNombreCamarero(String nombreCamarero) {
        this.nombreCamarero = nombreCamarero;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public List<Producto> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(List<Producto> listaProductos) {
        this.listaProductos = listaProductos;
    }

    public double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(double precioTotal) {
        this.precioTotal = precioTotal;
    }

    @Override
    public String toString() {
        return "Cuenta{" +
                "id_Cuenta=" + id_Cuenta +
                ", id_mesa=" + id_mesa +
                ", codigo='" + codigo + '\'' +
                ", nombreCamarero='" + nombreCamarero + '\'' +
                ", primerApellido='" + primerApellido + '\'' +
                ", segundoApellido='" + segundoApellido + '\'' +
                ", listaProductos=" + listaProductos +
                ", precioTotal=" + precioTotal +
                '}';
    }
}
