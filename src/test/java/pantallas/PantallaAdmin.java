package pantallas;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.io.File;
import java.io.IOException;


public class PantallaAdmin{


    public static void main(String[] args) {
        VentanaAdmin venAd = new VentanaAdmin();

    }

}



class VentanaAdmin extends JFrame {
    public VentanaAdmin(){


        JPanel panelExterno = new JPanel(new GridLayout(3,1,20,20));
        panelExterno.setOpaque(false);
        panelExterno.setBorder(BorderFactory.createEmptyBorder(130,0,0,0));

        JButton boton1 = new JButton();
        JButton boton2 = new JButton();
        JButton boton3 = new JButton();


        File empleado = new File("C:\\Users\\daw20\\IdeaProjects\\proyectoRestaurante\\imagenes\\empleado.bmp");
        File productos = new File("C:\\Users\\daw20\\IdeaProjects\\proyectoRestaurante\\imagenes\\pedido.bmp");
        File mesas = new File("C:\\Users\\daw20\\IdeaProjects\\proyectoRestaurante\\imagenes\\mesas.bmp");

        try {
            Image imagenEmpleado = ImageIO.read(empleado);
            Image imagenResultado = imagenEmpleado.getScaledInstance(150,150,Image.SCALE_SMOOTH);
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

        ImagenAdmin imgAd = new ImagenAdmin();
        imgAd.add(panelExterno);
        add(imgAd);

        setVisible(true);
        setSize(500,840);

    }
}


class ImagenAdmin extends JPanel{

    private Image imagen;

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        File miImagen = new File("C:\\Users\\daw20\\IdeaProjects\\proyectoRestaurante\\imagenes\\fondo_admin.jpg");
        try{
            imagen= ImageIO.read(miImagen);
        }catch (IOException e){
            System.out.println("La imagen no se encuentra");
        }
        Image imagenResultado = imagen.getScaledInstance(500,800,Image.SCALE_DEFAULT);
        g.drawImage(imagenResultado,0,0,null);
    }

}
