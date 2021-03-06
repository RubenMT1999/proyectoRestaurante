package UtilidadesCamarero;

import Modelos.Mesa;
import UtilidadesBBDD.ObtenerMesas;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import static UtilidadesBBDD.UtilidadesBBDD.cerrarConexion;
import static UtilidadesBBDD.UtilidadesBBDD.conectarConBD;


public class Aforo extends JFrame {

    public static  int contador;


  public Aforo()
        {
            setTitle("Aforo");

            JPanel panelExterno = new JPanel(new GridLayout(0,2,50,30));
            panelExterno.setBorder(BorderFactory.createEmptyBorder(10,10,10,30));

            //Obtenemos todas la mesas
            List<Mesa> listaMesas = ObtenerMesas.obtenerMesas();

            JLabel labelMesas = new JLabel("      "+"Nº Mesa");
            JLabel labelEstado = new JLabel("      "+"Estado");
            panelExterno.add(labelMesas);
            panelExterno.add(labelEstado);

            JButton boton1 = new JButton("Aceptar");
            JLabel labelVacio = new JLabel("");

            contador = listaMesas.size();

            //Para cada mesa que hay creamos un label con el número de mesa y un comboBox.
            for (Mesa m : listaMesas){
                JLabel label1 = new JLabel("           "+String.valueOf(m.getNumeroMesa()));
                JComboBox combo1 = new JComboBox();
                combo1.addItem("Ocupado");
                combo1.addItem("Libre");


                //De cada mesa, si la mesa está libre, el comboBox mostrará libre.
                if (m.getLibre()==1){
                    combo1.setSelectedItem("Libre");
                }else {
                    combo1.setSelectedItem("Ocupado");
                }

                panelExterno.add(label1);
                panelExterno.add(combo1);


                boton1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        //Guardamos el valor del comboBox. Todas las mesas tienen el mismo comboBox.
                        int eleccion = combo1.getSelectedIndex();

                        Connection con = conectarConBD();

                        //Hacemos el update de todas las mesas recorriéndolas desde la última a la primera
                        //mediante el contador
                        try{
                            PreparedStatement query = con.prepareStatement("UPDATE mesa set libre = "
                                    +String.valueOf(eleccion)+
                                    " where numero_mesa ="+String.valueOf(listaMesas.get(contador-1).getNumeroMesa())
                            );
                            contador--;
                            ResultSet rs = query.executeQuery();

                            //Cuando el contador sea 0 tenemos que resetearlo, sino cuando volvamos a querer
                            //usar la ventana el contador estará a 0 y no funcionaria nada.
                            if (contador == 0){
                                contador= listaMesas.size();
                                JOptionPane.showMessageDialog(panelExterno,
                                        "Mesas actualizadas correctamente");
                            }

                        }catch (Exception u){
                            System.out.println(u);
                        }finally {
                            cerrarConexion(con);
                        }

                    }
                });

            }


            panelExterno.add(labelVacio);
            panelExterno.add(boton1);

            add(panelExterno);
            setBounds(500,50,300,400);

        }




        public static void main(String args[])
        {
            Aforo f = new Aforo();
            f.setVisible(true);
            f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        }


}




