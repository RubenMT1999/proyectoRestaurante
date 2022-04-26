package UtilidadesBBDD;

import Modelos.Mesa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static UtilidadesBBDD.UtilidadesBBDD.cerrarConexion;
import static UtilidadesBBDD.UtilidadesBBDD.conectarConBD;

public class numeroMesas {
    public static List<Mesa> obtenernumMesas(){
        List<Mesa> numMesa = new ArrayList<>();

        Connection con = conectarConBD();


            try{
            PreparedStatement query = con.prepareStatement("select numero_mesa from mesa");
            ResultSet rs = query.executeQuery();

            while (rs.next()) {
                Mesa mesa = new Mesa(rs.getInt("numero_mesa"));
                numMesa.add(mesa);
            }
        } catch (
        SQLException sqle) {
            System.out.println("Error en la ejecución:"
                    + sqle.getErrorCode() + " " + sqle.getMessage());

        } finally {
            cerrarConexion(con);
        }


            return numMesa;
    }
}





