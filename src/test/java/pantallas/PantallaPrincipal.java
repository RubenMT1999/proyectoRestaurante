package pantallas;

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

public class PantallaPrincipal {



    public static void main(String[] args) {
        PrimeraVentana ven1 = new PrimeraVentana();
        ven1.setVisible(true);
        ven1.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        ven1.setSize(900,600);
    }

}



class PrimeraVentana extends JFrame {

    private static final ImageIcon fondoPantalla = new ImageIcon(getRutaImagenFondo());

    public PrimeraVentana(){



        JPanel panelExterno = crearPanelImagenFondo((new GridLayout(2,2,20,20)));

        setContentPane(panelExterno);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(MAXIMIZED_BOTH);
        setVisible(true);
        panelExterno.setOpaque(false);
        panelExterno.setBorder(BorderFactory.createEmptyBorder(75,0,0,0));

        JButton boton1 = new JButton();
        JButton boton2 = new JButton();
        JButton boton3 = new JButton();
        JButton boton4 = new JButton();

        File camarero = new File(getRutaCamarero());
        File cliente = new File(getRutaCliente());
        File admin = new File(getRutaAdmin());
        File chef = new File(getRutaChef());

        try {
            Image imagenCamarero = ImageIO.read(camarero);
            Image imagenResultado = imagenCamarero.getScaledInstance(175,175,Image.SCALE_SMOOTH);
            boton1.setIcon(new ImageIcon(imagenResultado));
            boton1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new VentanaCamarero();
                }
            });

            Image imagenCliente = ImageIO.read(cliente);
            Image imagenResultado2 = imagenCliente.getScaledInstance(175,175,Image.SCALE_SMOOTH);
            boton2.setIcon(new ImageIcon(imagenResultado2));
            boton2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new VentanaCliente();
                }
            });


            Image imagenAdmin = ImageIO.read(admin);
            Image imagenResultado3 = imagenAdmin.getScaledInstance(175,175,Image.SCALE_SMOOTH);
            boton3.setIcon(new ImageIcon(imagenResultado3));
            boton3.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new VentanaAdmin();
                }
            });


            Image imagenChef = ImageIO.read(chef);
            Image imagenResultado4 = imagenChef.getScaledInstance(175,175,Image.SCALE_SMOOTH);
            boton4.setIcon(new ImageIcon(imagenResultado4));
            boton4.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new VentanaCocinero();
                }
            });
        } catch (Exception ex) {
            System.out.println("No se pudo cargar la imagen");
        }




        panelExterno.add(boton1);
        panelExterno.add(boton2);
        panelExterno.add(boton3);
        panelExterno.add(boton4);

        Font fuenteBoton = new Font("Serif",Font.BOLD,15);
        Border bordeB1 = new LineBorder(Color.BLACK);

        Border esteBorde = new TitledBorder(bordeB1,"CAMARERO",TitledBorder.CENTER,TitledBorder.DEFAULT_POSITION,
                fuenteBoton,Color.BLACK);
        Border esteBorde2 = new TitledBorder(bordeB1,"CLIENTE",TitledBorder.CENTER,TitledBorder.DEFAULT_POSITION,
                fuenteBoton,Color.BLACK);
        Border esteBorde3 = new TitledBorder(bordeB1,"ADMIN",TitledBorder.CENTER,TitledBorder.DEFAULT_POSITION,
                fuenteBoton,Color.BLACK);
        Border esteBorde4 = new TitledBorder(bordeB1,"CHEF",TitledBorder.CENTER,TitledBorder.DEFAULT_POSITION,
                fuenteBoton,Color.BLACK);

        boton1.setBorder(esteBorde);
        boton2.setBorder(esteBorde2);
        boton3.setBorder(esteBorde3);
        boton4.setBorder(esteBorde4);



        setBounds(200,300,600,400);



    }

    private JPanel crearPanelImagenFondo(GridLayout gridLayout) {
        JPanel panel = new JPanel() {
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(fondoPantalla.getImage(), 0, 0, null);
            }
        };
        return panel;
    }

    private static  String getRutaImagenFondo(){
        String ruta = new File("").getAbsolutePath();
        return ruta + "\\imagenes\\foto_chino.png";
    }

    private static String getRutaCamarero(){
        String ruta = new File("").getAbsolutePath();
        return ruta + "\\imagenes\\camarero_chino.bmp";

    }
    private static String getRutaCliente(){
        String ruta = new File("").getAbsolutePath();
        return ruta + "\\imagenes\\cliente.bmp";

    }

    private static String getRutaAdmin(){
        String ruta = new File("").getAbsolutePath();
        return ruta + "\\imagenes\\admin.bmp";

    }

    private static String getRutaChef(){
        String ruta = new File("").getAbsolutePath();
        return ruta + "\\imagenes\\chef.bmp";

    }
}





