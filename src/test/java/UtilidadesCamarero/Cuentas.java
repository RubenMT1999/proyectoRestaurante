package UtilidadesCamarero;

import Modelos.Mesa;
import Modelos.Pedido;
import UtilidadesBBDD.ObtenerMesas;
import UtilidadesBBDD.ObtenerPedido;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Cuentas extends JFrame{

    public Cuentas(){
        JPanel panelTabla = new JPanel(new GridLayout(1,2,0,0));
        String data[][] = {};
        String col[] = {"Numero Mesa","Pedido Finalizado"};

        DefaultTableModel model = new DefaultTableModel(data,col);
        JTable tabla1 = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(tabla1);
        tabla1.setFillsViewportHeight(true);


        List<Mesa> listaMesas = ObtenerMesas.obtenerMesas();
        Collections.reverse(listaMesas);

        List<Pedido> listaPedidos = ObtenerPedido.obtenerPedidos();
        Collections.reverse(listaPedidos);


        for (Mesa m : listaMesas){

            int contador = 0;
            List<Pedido> p1 = listaPedidos.stream().filter(p->p.getId_mesa() == m.getId()).filter(p->p.getPagado() ==0)
                    .collect(Collectors.toList());

            if (!p1.isEmpty()){

                String estado = "";
                int prueba = p1.get(0).getEstado();

                if (prueba == 0){
                    estado = "NO";
                }else
                    estado = "SÍ";

                model.insertRow(contador,new Object[]{"                     "+m.getNumeroMesa(),"                       "+
                        estado});
                contador++;
            }else {
                model.insertRow(contador,new Object[]{"                     "+m.getNumeroMesa(),"                       "+
                       "NO" });
                contador++;
            }

        }

        JPanel panelBoton = new JPanel(new GridLayout(1,2,0,0));
        panelBoton.setSize(new Dimension(300,100));
        JButton boton1 = new JButton("Cuenta");
        JLabel labelVacio = new JLabel("");

        panelBoton.add(labelVacio);
        panelBoton.add(boton1);

        panelTabla.add(scrollPane);

        JPanel panelExterno = new JPanel(new GridLayout(2,1,0,0));


        panelExterno.add(panelTabla);
        panelExterno.add(panelBoton);

        add(panelExterno);

        setBounds(600,300,300,300);

    }



    public static void main(String[] args) {
        Cuentas c1 = new Cuentas();
        c1.setVisible(true);
        c1.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }
}
