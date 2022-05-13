package UtilidadesBBDD;

import Modelos.Cuenta;
import Modelos.Pedido;
import Modelos.Producto;
import UtilidadesPDF.GenerarDocumento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static UtilidadesBBDD.UtilidadesBBDD.conectarConBD;


public class obtenerCuenta {

    public obtenerCuenta(int id_mesa){


        Connection conn = conectarConBD();

        try {
            PreparedStatement stmt = conn.prepareStatement("select * from cuentaview c where id_mesa = ?");

            stmt.setInt(1,id_mesa);

            ResultSet rs = stmt.executeQuery();


            List<Producto> listaPedidos = new ArrayList<>();
            int mesa = 0;
            String codigo = "";
            String nombreCamarero = "";
            String apellido1 = "";
            String apellido2 = "";

            while (rs.next()){

                Producto p1 = new Producto(rs.getString("nombre"),rs.getInt("cantidad_pedida"),
                        rs.getInt("precio"));
                listaPedidos.add(p1);
                mesa = rs.getInt("id_mesa");
                codigo = rs.getString("codigo");
                nombreCamarero = rs.getString("nombreCamarero");
                apellido1 = rs.getString("apellido1");
                apellido2 = rs.getString("apellido2");

            }

            double precioCuenta = listaPedidos.stream().mapToDouble(p-> p.getPrecioTotalProducto()).sum();


            Cuenta c1 = new Cuenta(mesa,codigo,nombreCamarero,apellido1,apellido2,listaPedidos,precioCuenta);

            new GenerarDocumento(c1);


        } catch (SQLException ex) {
            ex.printStackTrace();
        }


    }


}

