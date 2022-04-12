package UtilidadesCamarero;


import Modelos.Carta;
import Modelos.Empleado;
import UtilidadesCliente.ObtenerProductos;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


class MenuComandas extends JFrame {
    public MenuComandas(){

        JPanel panelExterno = new JPanel(new GridLayout(2,1,80,10));
        JPanel panelCombo = new JPanel(new GridLayout(3,3,80,10));
        panelCombo.setOpaque(false);
        panelCombo.setBorder(BorderFactory.createEmptyBorder(50,50,0,50));

        JComboBox comboMesa = new JComboBox<>();
        JComboBox comboProducto = new JComboBox<>();
        JComboBox comboCamarero = new JComboBox<>();
        JTextField comboCantidad = new JTextField();


        JButton botonAniadir = new JButton("+");

        String data[][] = {};
        String col[] = {"Producto","Cantidad","Precio"};

        DefaultTableModel model = new DefaultTableModel(data,col);
        JTable tabla1 = new JTable(model);


        model.insertRow(1,new Object[]{"Calamares",4,2.5});



        JScrollPane scrollPane = new JScrollPane(tabla1);
        tabla1.setFillsViewportHeight(true);


        panelCombo.add(comboMesa);
        panelCombo.add(comboProducto);
        panelCombo.add(comboCamarero);
        panelCombo.add(comboCantidad);
        panelCombo.add(botonAniadir,1,3);


        JPanel panelTabla = new JPanel(new GridLayout(1,3));
        panelTabla.add(scrollPane);
        panelTabla.setBorder(BorderFactory.createEmptyBorder(50,50,0,50));

        panelExterno.add(panelCombo);
        panelExterno.add(panelTabla);


        List<Carta> Menu = ObtenerProductos.obtenerProductos();

        for (Carta e : Menu){
            comboProducto.addItem(e.getNombre());
        }





        System.out.println(comboProducto.getSelectedItem().toString());

        add(panelExterno);


        setBounds(500,50,600,900);


    }





    public static void main(String[] args) {
        MenuComandas p1 = new MenuComandas();
        p1.setVisible(true);
        p1.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        p1.setSize(600,600);
    }
}




