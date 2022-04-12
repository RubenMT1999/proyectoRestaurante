package pantallas;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.io.File;


public class PantallaAdmin{


    public static void main(String[] args) {
        VentanaAdmin venAd = new VentanaAdmin();

    }

}



class VentanaAdmin extends JFrame {

    private static final ImageIcon fondoPantalla = new ImageIcon(getRutaImagenFondo());

    public VentanaAdmin(){


        JPanel panelExterno = crearPanelImagenFondo();

        setContentPane(panelExterno);
        setLocationRelativeTo(null);
        setVisible(true);
        setSize(new Dimension(1200,800));
        setResizable(false);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(MAXIMIZED_BOTH);
        panelExterno.setOpaque(false);
        panelExterno.setBorder(BorderFactory.createEmptyBorder(130,0,0,0));

        JButton boton1 = new JButton();
        JButton boton2 = new JButton();
        JButton boton3 = new JButton();


        File empleado = new File(getRutaEmpleado());
        File productos = new File(getRutaProductos());
        File mesas = new File(getRutaMesas());

        try {
            Image imagenEmpleado = ImageIO.read(empleado);
            Image imagenResultado = imagenEmpleado.getScaledInstance(100,100,Image.SCALE_SMOOTH);
            boton1.setIcon(new ImageIcon(imagenResultado));
        } catch (Exception ex) {
            System.out.println("No se pudo cargar la imagen");
        }

        try {
            Image imagenProductos = ImageIO.read(productos);
            Image imagenResultado = imagenProductos.getScaledInstance(100,100,Image.SCALE_SMOOTH);
            boton2.setIcon(new ImageIcon(imagenResultado));
        } catch (Exception ex) {
            System.out.println("No se pudo cargar la imagen");
        }

        try {
            Image imagenMesas = ImageIO.read(mesas);
            Image imagenResultado = imagenMesas.getScaledInstance(100,100,Image.SCALE_SMOOTH);
            boton3.setIcon(new ImageIcon(imagenResultado));
        } catch (Exception ex) {
            System.out.println("No se pudo cargar la imagen");
        }


        panelExterno.add(boton1);
        panelExterno.add(boton2);
        panelExterno.add(boton3);


        Font fuenteBoton = new Font("Serif",Font.BOLD,15);
        Border bordeB1 = new LineBorder(Color.BLACK);

        Border esteBorde = new TitledBorder(bordeB1,"EMPLEADOS",TitledBorder.CENTER,TitledBorder.DEFAULT_POSITION,
                fuenteBoton,Color.BLACK);
        Border esteBorde2 = new TitledBorder(bordeB1,"PRODUCTOS",TitledBorder.CENTER,TitledBorder.DEFAULT_POSITION,
                fuenteBoton,Color.BLACK);
        Border esteBorde3 = new TitledBorder(bordeB1,"MESAS",TitledBorder.CENTER,TitledBorder.DEFAULT_POSITION,
                fuenteBoton,Color.BLACK);


        boton1.setBorder(esteBorde);
        boton2.setBorder(esteBorde2);
        boton3.setBorder(esteBorde3);




        setBounds(500,50,600,900);


        setVisible(true);
        setSize(500,840);

    }
    private JPanel crearPanelImagenFondo() {
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
        return ruta + "\\imagenes\\fondo_admin.jpg";
    }

    private static String getRutaEmpleado(){
        String ruta = new File("").getAbsolutePath();
        return ruta + "\\imagenes\\empleado.bmp";

    }

    private static String getRutaProductos(){
        String ruta = new File("").getAbsolutePath();
        return ruta + "\\imagenes\\pedido.bmp";

    }

    private static String getRutaMesas(){
        String ruta = new File("").getAbsolutePath();
        return ruta + "\\imagenes\\mesas.bmp";

    }

}


