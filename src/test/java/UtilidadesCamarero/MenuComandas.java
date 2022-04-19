package UtilidadesCamarero;


import Modelos.Carta;
import Modelos.Empleado;
import Modelos.Mesa;
import Modelos.tipoEmpleado;
import UtilidadesBBDD.ObtenerMesas;
import UtilidadesCliente.ObtenerProductos;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        JButton botonActualizar = new JButton("Actualizar");

        String data[][] = {};
        String col[] = {"Producto","Cantidad","Precio"};

        DefaultTableModel model = new DefaultTableModel(data,col);
        JTable tabla1 = new JTable(model);






        JScrollPane scrollPane = new JScrollPane(tabla1);
        tabla1.setFillsViewportHeight(true);


        panelCombo.add(comboMesa);
        panelCombo.add(comboCamarero);
        panelCombo.add(comboProducto);
        panelCombo.add(comboCantidad);
        panelCombo.add(botonAniadir,1,3);
        panelCombo.add(botonActualizar);


        JPanel panelTabla = new JPanel(new GridLayout(1,3));
        panelTabla.add(scrollPane);
        panelTabla.setBorder(BorderFactory.createEmptyBorder(50,50,0,50));

        panelExterno.add(panelCombo);
        panelExterno.add(panelTabla);


        List<Carta> Menu = ObtenerProductos.obtenerProductos();

        for (Carta e : Menu){
            comboProducto.addItem(e.getNombre());
        }

        List<Empleado> Empleados = ObtenerEmpleados.obtenerEmpleados();
        List<Empleado> listaCamareros = new ArrayList<>();
        List<Mesa> listaMesas = ObtenerMesas.obtenerMesas();

        for (Empleado e : Empleados){
            if (e.getTipoEmpleado().equals(tipoEmpleado.camarero)){
                listaCamareros.add(e);
            }
        }

        for (Empleado q : listaCamareros){
            comboCamarero.addItem(q.getNombre());
        }

        for (Mesa m : listaMesas){
            if (m.getLibre()==0){
                comboMesa.addItem(m.getNumeroMesa());
            }
        }

        botonAniadir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Double Precio = Menu.get(comboProducto.getSelectedIndex()).getPrecio();

                model.insertRow(0,new Object[]{comboProducto.getSelectedItem().toString(),
                        comboCantidad.getText(),Precio* Integer.parseInt(comboCantidad.getText()) });


            }
        });

        botonActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i = tabla1.getSelectedRow();
                Double Precio = Menu.get(comboProducto.getSelectedIndex()).getPrecio();
                model.setValueAt(comboProducto.getSelectedItem(), i, 0);
                model.setValueAt(comboCantidad.getText(), i, 1);
                model.setValueAt(Precio*Integer.parseInt(comboCantidad.getText()), i, 2);
            }

        });




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




