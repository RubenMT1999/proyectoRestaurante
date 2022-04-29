package UtilidadesBBDD;

import Modelos.Mesa;
import Modelos.Pedido;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static UtilidadesBBDD.UtilidadesBBDD.cerrarConexion;
import static UtilidadesBBDD.UtilidadesBBDD.conectarConBD;

public class ObtenerPedido {

    public static List<Pedido>obtenerPedidos(){

        List<Pedido> listaPedidos = new ArrayList<>();

        Connection con = conectarConBD();

        try{
            PreparedStatement query = con.prepareStatement("Select * from pedido");
            ResultSet rs = query.executeQuery();


            while (rs.next()){
                Pedido pedido1= new Pedido(rs.getInt("id"),rs.getString("codigo"),
                        rs.getDate("fecha"),rs.getInt("estado"),rs.getInt("precio_total"),
                        rs.getInt("pagado"),rs.getInt("id_mesa"),rs.getInt("id_empleado"));
                listaPedidos.add(pedido1);
            }
        }catch (Exception e){
            System.out.println(e);
        }finally {
            cerrarConexion(con);
        }

        return listaPedidos;

    }
}
