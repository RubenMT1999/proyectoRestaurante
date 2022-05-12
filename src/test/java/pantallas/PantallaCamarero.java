package pantallas;

import Modelos.Pedido;
import UtilidadesCamarero.Aforo;
import UtilidadesCamarero.Cuentas;
import UtilidadesCamarero.MenuComandas;

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


public class PantallaCamarero{


    public static void main(String[] args) {
        VentanaCamarero vC = new VentanaCamarero();
    }

}



class VentanaCamarero extends JFrame {

    private static final ImageIcon fondoPantalla = new ImageIcon(getRutaImagenFondo());

    public VentanaCamarero(){




        JPanel panelExterno = crearPanelImagenFondo((new GridLayout(3,1,20,20)));
        setContentPane(panelExterno);
        setVisible(true);
        setSize(new Dimension(500,900));
        setResizable(false);
        setLocationRelativeTo(null);
        setExtendedState(MAXIMIZED_BOTH);
        panelExterno.setOpaque(false);
        panelExterno.setBorder(BorderFactory.createEmptyBorder(130,0,0,0));

        JButton boton1 = new JButton();
        boton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Aforo a1 = new Aforo();
                a1.setVisible(true);
            }
        });



        JButton boton2 = new JButton();
        boton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuComandas m1 = new MenuComandas();
                m1.setVisible(true);
            }
        });

        JButton boton3 = new JButton();
        boton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Cuentas c1 = new Cuentas();
                c1.setVisible(true);
            }
        });


        File aforo = new File(getRutaAforo());
        File pedidos = new File(getRutaPedidos());
        File cuentas = new File(getRutaCuentas());

        try {
            Image imagenAforo = ImageIO.read(aforo);
            Image imagenResultado = imagenAforo.getScaledInstance(100,100,Image.SCALE_SMOOTH);
            boton1.setIcon(new ImageIcon(imagenResultado));
        } catch (Exception ex) {
            System.out.println("No se pudo cargar la imagen");
        }

        try {
            Image imagenPedidos = ImageIO.read(pedidos);
            Image imagenResultado = imagenPedidos.getScaledInstance(100,100,Image.SCALE_SMOOTH);
            boton2.setIcon(new ImageIcon(imagenResultado));
        } catch (Exception ex) {
            System.out.println("No se pudo cargar la imagen");
        }

        try {
            Image imagenCuentas = ImageIO.read(cuentas);
            Image imagenResultado = imagenCuentas.getScaledInstance(100,100,Image.SCALE_SMOOTH);
            boton3.setIcon(new ImageIcon(imagenResultado));
        } catch (Exception ex) {
            System.out.println("No se pudo cargar la imagen");
        }


        panelExterno.add(boton1);
        panelExterno.add(boton2);
        panelExterno.add(boton3);


        Font fuenteBoton = new Font("Serif",Font.BOLD,15);
        Border bordeB1 = new LineBorder(Color.BLACK);

        Border esteBorde = new TitledBorder(bordeB1,"AFORO",TitledBorder.CENTER,TitledBorder.DEFAULT_POSITION,
                fuenteBoton,Color.BLACK);
        Border esteBorde2 = new TitledBorder(bordeB1,"PEDIDOS",TitledBorder.CENTER,TitledBorder.DEFAULT_POSITION,
                fuenteBoton,Color.BLACK);
        Border esteBorde3 = new TitledBorder(bordeB1,"CUENTAS",TitledBorder.CENTER,TitledBorder.DEFAULT_POSITION,
                fuenteBoton,Color.BLACK);


        boton1.setBorder(esteBorde);
        boton2.setBorder(esteBorde2);
        boton3.setBorder(esteBorde3);




        setBounds(500,50,600,900);




    }

    private JPanel crearPanelImagenFondo(GridLayout gridLayout){
        JPanel panel = new JPanel(){
            public void paintComponent (Graphics g ){
                super.paintComponent(g);
                g.drawImage(fondoPantalla.getImage(),0,0,null);
            }
        };
        return panel;
    }


    private static  String getRutaImagenFondo(){
        String ruta = new File("").getAbsolutePath();
        return ruta + "\\imagenes\\fondo_camarero.jpg";
    }



    private static String getRutaAforo(){
        String ruta = new File("").getAbsolutePath();
        return ruta + "\\imagenes\\aforo.ico";

    }


    private static String getRutaPedidos(){
        String ruta = new File("").getAbsolutePath();
        return ruta + "\\imagenes\\pedidos.ico";

    }

    private static String getRutaCuentas(){
        String ruta = new File("").getAbsolutePath();
        return ruta + "\\imagenes\\cuentas.ico";

    }
}



