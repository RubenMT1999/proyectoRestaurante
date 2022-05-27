package examen;

import Modelos.Resenia;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import static UtilidadesBBDD.ObtenerResenias.obtenerResenias;
import static UtilidadesBBDD.UtilidadesBBDD.cerrarConexion;
import static UtilidadesBBDD.UtilidadesBBDD.conectarConBD;

public class EjExamen1 extends JFrame{

    public EjExamen1(){

        JPanel panelExterno = new JPanel(new GridLayout(2,4,10,10));
        panelExterno.setOpaque(false);
        panelExterno.setBorder(BorderFactory.createEmptyBorder(0,40,50,40));

        JPanel panelBotones = new JPanel(new GridLayout(2,2,20,-30));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(20,40,70,40));


        //Creacion de label , comboBox y botón

        JLabel label1 = new JLabel("Valoración");
        JLabel labelVacio = new JLabel("");
        JComboBox combo1 = new JComboBox();
        combo1.addItem("Muy Bueno");
        combo1.addItem("Bueno");
        combo1.addItem("Mejorable");
        combo1.addItem("Malo");
        combo1.addItem("Pesimo");

        JButton boton1 = new JButton("Buscar");

        panelBotones.add(label1);
        panelBotones.add(labelVacio);
        panelBotones.add(combo1);
        panelBotones.add(boton1);

        panelExterno.add(panelBotones);

        // CREACION DE TABLA

        String data[][] = {};
        String col[] = {"Cliente","Comentario","Puntuación","Valoración"};

        DefaultTableModel model = new DefaultTableModel(data,col);
        JTable tabla1 = new JTable(model);

        tabla1.setRowHeight(40);


        JScrollPane scrollPane = new JScrollPane(tabla1);
        tabla1.setFillsViewportHeight(true);

        panelExterno.add(scrollPane);

        //model.insertRow(0,new Object[]{"nota", "examen", 10 });
        //int i = tabla1.getSelectedRow();
        //model.setValueAt("a", i, 1);

        add(panelExterno);

        //Obtenemos todas las resenias:
        List<Resenia> listaResenia = obtenerResenias();

        int contadorFilas = 0;

        for (Resenia r : listaResenia){
            model.insertRow(contadorFilas,new Object[]{r.getNombreCliente(),r.getComentario(),r.getPuntuacion(),
                    r.getValoración()});
            contadorFilas++;
        }



        boton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String valoracionCombo = combo1.getSelectedItem().toString();

                Connection con = conectarConBD();

                try{
                    PreparedStatement stmt = con.prepareStatement("select * from resenias" +
                            " where valoracion = ?");

                    stmt.setString(1,valoracionCombo);
                    ResultSet rs = stmt.executeQuery();


                    model.setRowCount(0);
                    int contador = 0;
                    while (rs.next()){
                        model.insertRow(contador,new Object[]{rs.getString("nombre_cliente"),
                                rs.getString("comentario"),
                                rs.getInt("puntuacion"), rs.getString("valoracion") });

                        contador++;
                    }

                }catch (Exception q){
                    q.printStackTrace();
                }finally {
                    cerrarConexion(con);
                }
            }
        });


}


    public static void main(String[] args) {
        EjExamen1 form1 = new EjExamen1();
        form1.setVisible(true);
        form1.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        form1.setSize(800,600);
        form1.setBounds(300,100,600,600);
    }
}


