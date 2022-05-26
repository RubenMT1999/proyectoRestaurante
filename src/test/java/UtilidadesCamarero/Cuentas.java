package UtilidadesCamarero;

import Modelos.Mesa;
import Modelos.Pedido;
import UtilidadesBBDD.ObtenerMesas;
import UtilidadesBBDD.ObtenerPedido;
import UtilidadesBBDD.obtenerCuenta;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static UtilidadesBBDD.UtilidadesBBDD.cerrarConexion;
import static UtilidadesBBDD.UtilidadesBBDD.conectarConBD;

public class Cuentas extends JFrame{

    public Cuentas(){
        JPanel panelTabla = new JPanel(new GridLayout(1,2,0,0));
        panelTabla.setBorder(BorderFactory.createEmptyBorder(25,25,25,25));
        String data[][] = {};
        String col[] = {"Numero Mesa","Pedido Finalizado"};

        DefaultTableModel model = new DefaultTableModel(data,col);
        JTable tabla1 = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(tabla1);
        tabla1.setFillsViewportHeight(true);

        //Obtenemos todas las mesas
        List<Mesa> listaMesas = ObtenerMesas.obtenerMesas();
        Collections.reverse(listaMesas);

        List<Pedido> listaPedidos = ObtenerPedido.obtenerPedidos();
        Collections.reverse(listaPedidos);


        for (Mesa m : listaMesas){

            int contador = 0;
            //Vemos los pedidos que tengan el id de esta mesa y que el estado pagado sea 0, es decir, aun no han pagado
            List<Pedido> p1 = listaPedidos.stream().filter(p->p.getId_mesa() == m.getId()).filter(p->p.getPagado() ==0)
                    .collect(Collectors.toList());

            if (!p1.isEmpty()){

                String estado = "";
                int prueba = p1.get(0).getEstado();

                //Si el pedido está pagado es SÍ del contrario es NO
                if (prueba == 0){
                    estado = "NO";
                }else
                    estado = "SÍ";

                model.insertRow(contador,new Object[]{"                                   "+m.getNumeroMesa(),"                                  "+
                  estado});
                contador++;
            }else {
                //Si no hay pedido con este id mesa y que no esté pagado por defecto saldrá NO
                model.insertRow(contador,new Object[]{"                                   "+m.getNumeroMesa(),"                                 "+
                       " NO" });
                contador++;
            }
        }

        JPanel panelBoton = new JPanel(new GridLayout(1,2,0,0));
        panelBoton.setBorder(BorderFactory.createEmptyBorder(25,0,50,150));
        panelBoton.setSize(new Dimension(300,100));
        JButton boton1 = new JButton("Cuenta");
        JLabel labelVacio = new JLabel("");

        panelBoton.add(labelVacio);
        panelBoton.add(boton1);

        panelTabla.add(scrollPane);

        JPanel panelExterno = new JPanel(new GridLayout(2,1,0,0));



        boton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //Obtenemos el numero de fila y luego vemos el valor que hay en la fila x y columna 1. Dará SI o NO.
                int i = tabla1.getSelectedRow();
                Object estado = model.getValueAt(i,1);

                //Comprobamos si obtenemos un Sí o un NO
                String estadoStr = String.valueOf(estado);
                char letra = estadoStr.charAt(estadoStr.length()-1);
                char si = 'Í';


                if (letra == si){


                    Connection con = conectarConBD();

                    try{
                        CallableStatement stmt = con.prepareCall("{ call procedure_cuenta(?)}");

                        //Obtenemos el número de mesa para pasárselo al procedure.
                        String obtenerMesa = String.valueOf(model.getValueAt(i,0));
                        char obtenerMesaStr= obtenerMesa.charAt(obtenerMesa.length()-1);
                        int obtenerMesaInt = Character.getNumericValue(obtenerMesaStr);

                        stmt.setInt(1,obtenerMesaInt);

                        ResultSet rs = stmt.executeQuery();

                    //--------------------------------------------------
                        //Esto es para generar el pdf. Primero para obtener la cuenta le tenemos que pasar el idMesa.
                        PreparedStatement stmt2 = con.prepareStatement("select id from mesa where numero_mesa  = ?");

                        stmt2.setInt(1,obtenerMesaInt);
                        ResultSet rs2 = stmt2.executeQuery();

                        int id_mesa = 0;
                        if (rs2.next()){
                            id_mesa = rs2.getInt("id");
                        }

                        new obtenerCuenta(id_mesa);

                        JOptionPane.showMessageDialog(panelExterno,
                                "La cuenta ha sido creada");


                    }catch (Exception z){
                        System.out.println(z);

                    }finally {
                        cerrarConexion(con);
                    }

                }else
                    JOptionPane.showMessageDialog(panelExterno,
                            "Esta mesa no ha finalizado");

            }
        });


        panelExterno.add(panelTabla);
        panelExterno.add(panelBoton);

        add(panelExterno);

        setBounds(600,300,500,400);

    }



    public static void main(String[] args) {
        Cuentas c1 = new Cuentas();
        c1.setVisible(true);
        c1.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }
}
