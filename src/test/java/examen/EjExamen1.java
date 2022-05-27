package examen;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class EjExamen1 extends JFrame{

    public EjExamen1(){

        JPanel panelExterno = new JPanel(new GridLayout(2,3,10,10));
        panelExterno.setOpaque(false);
        panelExterno.setBorder(BorderFactory.createEmptyBorder(110,40,0,40));


        // CREACION DE TABLA

        String data[][] = {};
        String col[] = {"Producto","Cantidad","Precio"};

        DefaultTableModel model = new DefaultTableModel(data,col);
        JTable tabla1 = new JTable(model);

        tabla1.setRowHeight(40);


        JScrollPane scrollPane = new JScrollPane(tabla1);
        tabla1.setFillsViewportHeight(true);

        panelExterno.add(scrollPane);

        model.insertRow(0,new Object[]{"nota", "examen", 10 });
        //int i = tabla1.getSelectedRow();
        //model.setValueAt("a", i, 1);

        add(panelExterno);



    }

    public static void main(String[] args) {
        EjExamen1 form1 = new EjExamen1();
        form1.setVisible(true);
        form1.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        form1.setSize(800,600);
        form1.setBounds(300,100,800,600);
    }
}


