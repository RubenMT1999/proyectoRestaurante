package examen;

import javax.swing.*;
import java.awt.*;

public class EjExamen1 extends JFrame{

    public EjExamen1(){

        JPanel panelExterno = new JPanel(new GridLayout(16,1,10,10));
        panelExterno.setOpaque(false);
        panelExterno.setBorder(BorderFactory.createEmptyBorder(110,40,0,0));
    }

    public static void main(String[] args) {
        EjExamen1 form1 = new EjExamen1();
        form1.setVisible(true);
        form1.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        form1.setSize(500,800);
    }
}


