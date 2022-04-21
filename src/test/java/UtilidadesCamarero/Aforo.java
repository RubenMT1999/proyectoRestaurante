package UtilidadesCamarero;

import Modelos.Mesa;
import UtilidadesBBDD.ObtenerMesas;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.List;


class Aforo extends JFrame {



  public Aforo()
        {
            setTitle("JButton in JTable");
            setSize(100,150);
            JPanel panelExterno = new JPanel(new GridLayout(0,2,10,10));


            List<Mesa> listaMesas = ObtenerMesas.obtenerMesas();

            for (Mesa m : listaMesas){
                JLabel label1 = new JLabel(String.valueOf(m.getNumeroMesa()));
                Checkbox check1 = new Checkbox();
                panelExterno.add(label1);
                panelExterno.add(check1);

            }
            add(panelExterno);

        }




        public static void main(String args[])
        {
            Aforo f = new Aforo();
            f.setVisible(true);
        }


}




