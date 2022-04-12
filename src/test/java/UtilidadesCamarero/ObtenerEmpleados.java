package UtilidadesCamarero;

import Modelos.Empleado;
import Modelos.tipoEmpleado;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static UtilidadesBBDD.UtilidadesBBDD.cerrarConexion;
import static UtilidadesBBDD.UtilidadesBBDD.conectarConBD;


public class ObtenerEmpleados {

    public List<Empleado> obtenerEmpleados(){

        List<Empleado> listaEmpleados = new ArrayList<>();

        Connection con = conectarConBD();

        try{
            PreparedStatement query = con.prepareStatement("Select * from empleado");
            ResultSet rs = query.executeQuery();


            while (rs.next()){
                Empleado empleado = new Empleado(rs.getInt("id"),rs.getString("codigo"),
                        tipoEmpleado.values()[rs.getInt("tipo")],rs.getString("nombre"),
                        rs.getString("apellido1"),rs.getString("apellido2"),
                        rs.getString("dni"));
                listaEmpleados.add(empleado);
            }
        }catch (Exception e){
            System.out.println(e);
        }finally {
            cerrarConexion(con);
        }

        return listaEmpleados;

    }
}
