package pantallas;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
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
        panelExterno.setOpaque(false);
        panelExterno.setBorder(BorderFactory.createEmptyBorder(130,0,0,0));

        JButton boton1 = new JButton();
        JButton boton2 = new JButton();

        JButton boton3 = new JButton();



        File aforo = new File("C:\\Users\\dragu\\IdeaProjects\\proyectoRestaurante\\imagenes\\aforo.ico");
        File pedidos = new File("C:\\Users\\dragu\\IdeaProjects\\proyectoRestaurante\\imagenes\\pedidos.ico");
        File cuentas = new File("C:\\Users\\dragu\\IdeaProjects\\proyectoRestaurante\\imagenes\\cuentas.ico");

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

}



