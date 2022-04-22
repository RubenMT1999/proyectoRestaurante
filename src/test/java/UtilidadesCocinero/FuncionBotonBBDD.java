//package UtilidadesCocinero;
//
//import Modelos.Consumicion;
//import UtilidadesBBDD.UtilidadesBBDD;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class FuncionBotonBBDD extends UtilidadesBBDD {
//    Connection con = conectarConBD();
//
//            try {
//        PreparedStatement query = con.prepareStatement("SELECT c.id, c2.nombre, c.cantidad_pedida  FROM consumicion c join carta c2 on c2.id = c.id_producto");
//        ResultSet rs = query.executeQuery();
//
//
//    } catch (SQLException sqle) {
//        System.out.println("Error en la ejecución:"
//                + sqle.getErrorCode() + " " + sqle.getMessage());
//
//    } finally {
//        cerrarConexion(con);
//    }
//
//
//
//}






