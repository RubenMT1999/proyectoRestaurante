package Modelos;

import java.util.Objects;

public class Empleado {

    private int id;
    private String codigo;
    private tipoEmpleado tipoEmpleado;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private String dni;

    public Empleado(int id, String codigo, Modelos.tipoEmpleado tipoEmpleado, String nombre, String apellido1,
                    String apellido2, String dni) {
        this.id = id;
        this.codigo = codigo;
        this.tipoEmpleado = tipoEmpleado;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.dni = dni;
    }

    public Empleado(int id, String nombre, String codigo, String tipoEmpleado, String apellido1, String apellido2, String dni) {
    }

    public Empleado(Empleado e){
        this.id = e.id;
        this.codigo = e.codigo;
        this.tipoEmpleado = e.tipoEmpleado;
        this.nombre = e.nombre;
        this.apellido1 = e.apellido1;
        this.apellido2 = e.apellido2;
        this.dni = e.dni;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Modelos.tipoEmpleado getTipoEmpleado() {
        return tipoEmpleado;
    }

    public void setTipoEmpleado(Modelos.tipoEmpleado tipoEmpleado) {
        this.tipoEmpleado = tipoEmpleado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Empleado empleado = (Empleado) o;
        return id == empleado.id && Objects.equals(codigo, empleado.codigo) && tipoEmpleado == empleado.tipoEmpleado && Objects.equals(nombre, empleado.nombre) && Objects.equals(apellido1, empleado.apellido1) && Objects.equals(apellido2, empleado.apellido2) && Objects.equals(dni, empleado.dni);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, codigo, tipoEmpleado, nombre, apellido1, apellido2, dni);
    }


    @Override
    public String toString() {
        return "Empleado{" +
                "id=" + id +
                ", codigo='" + codigo + '\'' +
                ", tipoEmpleado=" + tipoEmpleado +
                ", nombre='" + nombre + '\'' +
                ", apellido1='" + apellido1 + '\'' +
                ", apellido2='" + apellido2 + '\'' +
                ", dni='" + dni + '\'' +
                '}';
    }
}
