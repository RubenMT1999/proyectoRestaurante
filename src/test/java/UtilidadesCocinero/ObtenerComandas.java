package UtilidadesCocinero;

import Modelos.Carta;
import Modelos.Categoria;
import Modelos.Consumicion;
import UtilidadesBBDD.UtilidadesBBDD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ObtenerComandas extends UtilidadesBBDD {
      {
        public static List<Carta> obtenerProductos) {
            List<Consumicion> Menu = new ArrayList<>();

            Connection con = conectarConBD();


            try {
                PreparedStatement query = con.prepareStatement("SELECT id, nombre, descripcion, categoria, precio  FROM carta");
                ResultSet rs = query.executeQuery();

                //Recorremos los datos
                while (rs.next()) {
                    Consumicion consumicion = new Consumicion(
                            rs.getInt("id_producto"),
                            rs.getInt("cantidad_pedida"));

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
