package UtilidadesBBDD;

import Modelos.Empleado;
import Modelos.Mesa;
import Modelos.tipoEmpleado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static UtilidadesBBDD.UtilidadesBBDD.cerrarConexion;
import static UtilidadesBBDD.UtilidadesBBDD.conectarConBD;

public class ObtenerMesas {

    public static List<Mesa> obtenerMesas(){

        List<Mesa> listaMesas = new ArrayList<>();

        Connection con = conectarConBD();

        try{
            PreparedStatement query = con.prepareStatement("Select * from mesa");
            ResultSet rs = query.executeQuery();


            while (rs.next()){
                Mesa mesa1 = new Mesa(rs.getInt("id"), rs.getInt("numero_mesa")
                        ,rs.getInt("max_personas"), rs.getInt("libre"));
                listaMesas.add(mesa1);
            }
        }catch (Exception e){
            System.out.println(e);
        }finally {
            cerrarConexion(con);
        }

        return listaMesas;

    }
}
