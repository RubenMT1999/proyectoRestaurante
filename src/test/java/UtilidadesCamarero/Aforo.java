package UtilidadesCamarero;



import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Aforo {

    public static void main(String[] args) {
        VenFormAforo m1 = new VenFormAforo();
        m1.setVisible(true);
        m1.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        m1.setSize(500,300);
    }
}

class VenFormAforo extends JFrame {
    public VenFormAforo(){


        String[] columnNames = {"Mesa","Estado"};

        Object[][] data = {
                { "aaa", "dato2" },
                { "dato3", "dato4" },
                { "dato1", "dato2" },
                { "dato1", "dato2" },
                { "dato1", "dato2" },
                { "dato1", "dato2" },
                { "dato1", "dato2" },
                { "dato1", "dato2" },
                { "dato1", "dato2" },
                { "dato1", "dato2" },
                { "dato1", "dato2" },
                { "dato1", "dato2" },
                { "dato1", "dato2" },
                { "dato1", "dato2" },
                { "dato1", "dato2" },
                { "dato1", "dato2" },
                { "dato1", "dato2" },
                { "dato1", "dato2" },
                { "dato7", "dato2" },
                { "dato1", "dato2" },
                { "dato1", "dato2" },
                { "dato1", "dato2" },
        };

        JTable miTabla = new JTable(data,columnNames);

        JScrollPane scroll = new JScrollPane(miTabla);


        int A = this.getWidth();
        int B = this.getHeight();

        miTabla.setSize(A,B);
        JPanel panelExterno = new JPanel(new GridLayout());
        panelExterno.add(scroll);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        miTabla.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        miTabla.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);

        scroll.setVisible(true);

        setBounds(500,50,600,900);

        add(panelExterno);


    }
}



