package pantallas;


import UtilidadesAdmin.FormularioEmpleado;
import UtilidadesAdmin.FormularioMesas;
import UtilidadesAdmin.FormularioProductos;
import examen.EjExamen1;


import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;


public class PantallaAdmin{


    public static void main(String[] args) {
        VentanaAdmin venAd = new VentanaAdmin();
        venAd.setVisible(true);
        venAd.setSize(new Dimension(400, 500));

    }

}



class VentanaAdmin extends JFrame {

    private static final ImageIcon fondoPantalla = new ImageIcon(getRutaImagenFondo());

    public VentanaAdmin(){


        JPanel panelExterno = crearPanelImagenFondo();

        setContentPane(panelExterno);
        setLocationRelativeTo(null);

        setResizable(false);
        setExtendedState(MAXIMIZED_BOTH);
        panelExterno.setOpaque(false);
        panelExterno.setBorder(BorderFactory.createEmptyBorder(130,0,0,0));

        JButton boton1 = new JButton();
        JButton boton2 = new JButton();
        JButton boton3 = new JButton();
        JButton boton4 = new JButton("RESEÑAS");




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

        boton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FormularioEmpleado fme = new FormularioEmpleado();
                fme.setVisible(true);
                fme.setSize(500, 800);
            }
        });

        boton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                FormularioProductos fme = new FormularioProductos();
                fme.setVisible(true);
                fme.setSize(500, 800);
            }
        });

        boton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FormularioMesas fme = new FormularioMesas();
                fme.setVisible(true);
                fme.setSize(500, 600);

            }
        });

        boton4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EjExamen1 ex1 = new EjExamen1();
                ex1.setVisible(true);
                ex1.setSize(800,600);
            }
        });


        panelExterno.add(boton1);
        panelExterno.add(boton2);
        panelExterno.add(boton3);
        panelExterno.add(boton4);


        Font fuenteBoton = new Font("Serif",Font.BOLD,15);
        Border bordeB1 = new LineBorder(Color.BLACK);

        Border esteBorde = new TitledBorder(bordeB1,"EMPLEADOS",TitledBorder.CENTER,TitledBorder.DEFAULT_POSITION,
                fuenteBoton,Color.BLACK);
        Border esteBorde2 = new TitledBorder(bordeB1,"PRODUCTOS",TitledBorder.CENTER,TitledBorder.DEFAULT_POSITION,
                fuenteBoton,Color.BLACK);
        Border esteBorde3 = new TitledBorder(bordeB1,"MESAS",TitledBorder.CENTER,TitledBorder.DEFAULT_POSITION,
                fuenteBoton,Color.BLACK);
        Border esteBorde4 = new TitledBorder(bordeB1,"RESEÑAS",TitledBorder.CENTER,TitledBorder.DEFAULT_POSITION,
                fuenteBoton,Color.BLACK);


        boton1.setBorder(esteBorde);
        boton2.setBorder(esteBorde2);
        boton3.setBorder(esteBorde3);
        boton4.setBorder(esteBorde4);




        setBounds(500,50,400,500);


        setSize(new Dimension(400,500));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);


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


