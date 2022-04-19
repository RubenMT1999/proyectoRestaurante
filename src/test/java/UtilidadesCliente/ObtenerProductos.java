package UtilidadesCliente;

import Modelos.Carta;
import Modelos.Categoria;
import UtilidadesBBDD.UtilidadesBBDD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ObtenerProductos extends UtilidadesBBDD {
    public static List<Carta> obtenerProductos() {
        List<Carta> Menu = new ArrayList<>();

        Connection con = conectarConBD();


        try {
            PreparedStatement query = con.prepareStatement("SELECT id, nombre, descripcion, categoria, precio  FROM carta");
            ResultSet rs = query.executeQuery();

            //Recorremos los datos
            while (rs.next()) {
             Carta carta = new Carta(rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("descripcion"),
                        Categoria.values()[rs.getInt("categoria")],
                        rs.getDouble("precio"));
             Menu.add(carta);

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
