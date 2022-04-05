package UtilidadesCamarero;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;


class Aforo extends JFrame {
    public Aforo(){

        JPanel panelExterno = new JPanel(new GridLayout(10,10,10,10));
        panelExterno.setOpaque(false);
        panelExterno.setBorder(BorderFactory.createEmptyBorder(20,0,0,0));

        JLabel labelMesa1 = new JLabel("Mesa");
        JTextField textoMesa1 = new JTextField();
        JButton botonLibre = new JButton("Liberar");
        JButton botonOcupado = new JButton("Ocupar");

        botonLibre.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textoMesa1.setBackground(Color.green);
            }
        } );

        botonOcupado.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textoMesa1.setBackground(Color.RED);
            }
        } );


        setBounds(500,50,600,900);

        panelExterno.add(labelMesa1);
        panelExterno.add(textoMesa1);
        panelExterno.add(botonLibre);
        panelExterno.add(botonOcupado);
        add(panelExterno);

    }

    public static void main(String[] args) {
        Aforo a1 = new Aforo();
        a1.setVisible(true);
        a1.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        a1.setSize(400,400);
    }


}




