package UtilidadesCocinero;

import Modelos.Consumicion;
import Modelos.Mesa;
import UtilidadesBBDD.UtilidadesBBDD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ObtenerComandas extends UtilidadesBBDD {
    public static List<Consumicion> ObtenerComandas() {
        List<Consumicion> Menu = new ArrayList<>();

        Connection con = conectarConBD();


        try {
            PreparedStatement query = con.prepareStatement("SELECT c.id, c2.nombre, c.cantidad_pedida  FROM consumicion c join carta c2 on c2.id = c.id_producto");
            ResultSet rs = query.executeQuery();

            //Recorremos los datos
            while (rs.next()) {
                Consumicion consumicion = new Consumicion(
                        rs.getInt("c.id"),
                        rs.getString("c2.nombre"),
                        rs.getInt("c.cantidad_pedida"));

                Menu.add(consumicion);


            }
        } catch (SQLException sqle) {
            System.out.println("Error en la ejecución:"
                    + sqle.getErrorCode() + " " + sqle.getMessage());

        } finally {
            cerrarConexion(con);
        }


        return Menu;
    }
}


