package Modelos;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

public class Pedido {
    private int id;
    private String codigo;
    private Date fecha;
    private int estado;
    private int precio_total;
    private int pagado;
    private int id_mesa;
    private int id_empleado;


    public Pedido() {
    }

    public Pedido(int id, String codigo,Date fecha, int estado, int precio_total, int pagado,
                  int id_mesa, int id_empleado) {
        this.id = id;
        this.codigo = codigo;
        this.fecha = fecha;
        this.estado = estado;
        this.precio_total = precio_total;
        this.pagado = pagado;
        this.id_mesa = id_mesa;
        this.id_empleado = id_empleado;
    }


    public Pedido(Pedido p){
        this.id = p.id;
        this.codigo = p.codigo;
        this.fecha = p.fecha;
        this.estado = p.estado;
        this.precio_total = p.precio_total;
        this.pagado = p.pagado;
        this.id_mesa = p.id_mesa;
        this.id_empleado = p.id_empleado;
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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getPrecio_total() {
        return precio_total;
    }

    public void setPrecio_total(int precio_total) {
        this.precio_total = precio_total;
    }

    public int getPagado() {
        return pagado;
    }

    public void setPagado(int pagado) {
        this.pagado = pagado;
    }

    public int getId_mesa() {
        return id_mesa;
    }

    public void setId_mesa(int id_mesa) {
        this.id_mesa = id_mesa;
    }

    public int getId_empleado() {
        return id_empleado;
    }

    public void setId_empleado(int id_empleado) {
        this.id_empleado = id_empleado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pedido pedido = (Pedido) o;
        return id == pedido.id && estado == pedido.estado && precio_total == pedido.precio_total && pagado == pedido.pagado && id_mesa == pedido.id_mesa && id_empleado == pedido.id_empleado && Objects.equals(codigo, pedido.codigo) && Objects.equals(fecha, pedido.fecha);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, codigo, fecha, estado, precio_total, pagado, id_mesa, id_empleado);
    }


    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", codigo='" + codigo + '\'' +
                ", fecha=" + fecha +
                ", estado=" + estado +
                ", precio_total=" + precio_total +
                ", pagado=" + pagado +
                ", id_mesa=" + id_mesa +
                ", id_empleado=" + id_empleado +
                '}';
    }
}
