package UtilidadesCliente;
import Modelos.Carta;
import UtilidadesBBDD.UtilidadesBBDD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ObtenerProductos extends UtilidadesBBDD {
    public static List<Carta> obtenerPorId(Integer id) {
        List<Carta> Menu = new ArrayList<>();

        Connection con = conectarConBD();
        Carta carta = null;

        try {
            PreparedStatement query = con.prepareStatement("SELECT * FROM carta where id = ?  ");
            query.setInt(1, id);
            ResultSet rs = query.executeQuery();

            //Recorremos los datos
            while (rs.next()) {
                carta = new Carta(rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("apellidos"),
                        TipoEmpleado.values()[rs.getInt("tipo_empleado")]);
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
