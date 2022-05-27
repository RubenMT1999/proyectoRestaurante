package UtilidadesBBDD;

import Modelos.Pedido;
import Modelos.Resenia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static UtilidadesBBDD.UtilidadesBBDD.cerrarConexion;
import static UtilidadesBBDD.UtilidadesBBDD.conectarConBD;

public class ObtenerResenias {


    public static List<Resenia> obtenerResenias(){
        List<Resenia> lista1 = new ArrayList<>();



        Connection con = conectarConBD();

        try{
            PreparedStatement query = con.prepareStatement("Select * from resenias");
            ResultSet rs = query.executeQuery();


            while (rs.next()){
                Resenia resenia1= new Resenia(rs.getInt("id"),rs.getString("nombre_cliente"),
                        rs.getString("comentario"),rs.getInt("puntuacion"),
                        rs.getString("valoracion"));
                lista1.add(resenia1);
            }
        }catch (Exception e){
            System.out.println(e);
        }finally {
            cerrarConexion(con);
        }

        return lista1;

    }
}
