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


        private JPanel topPanel;
        private JTable table;
        private JScrollPane scrollPane;
        private String[] columns = new String[3];
        private String[][] data = new String[3][3];
        JButton button = new JButton();
  public Aforo()
        {
            setTitle("JButton in JTable");
            setSize(300,150);
            topPanel = new JPanel();
            topPanel.setLayout(new BorderLayout());
            getContentPane().add(topPanel);
            columns = new String[] {"Id",  "Action"};
            data = new String[][]{
            };
            DefaultTableModel model = new DefaultTableModel(data,columns);
            table = new JTable();
            table.setModel(model);
            //table.getColumn("Action").setCellRenderer((TableCellRenderer) new ButtonRenderer());
            JComboBox combo = new JComboBox();
            combo.addItem("Libre");
            combo.addItem("Ocupada");

            table.getColumn("Action").setCellEditor(new DefaultCellEditor(combo));
            scrollPane = new JScrollPane(table);
            topPanel.add(scrollPane,BorderLayout.CENTER);

            List<Mesa> listaMesas = ObtenerMesas.obtenerMesas();

            for (Mesa m : listaMesas){
                model.addRow(new Object[]{m.getNumeroMesa()});
            }

        }




        public static void main(String args[])
        {
            Aforo f = new Aforo();
            f.setVisible(true);
        }


}




