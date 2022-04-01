package pantallas;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
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
    public PrimeraVentana(){


        JPanel panelExterno = new JPanel(new GridLayout(2,2,20,20));
        panelExterno.setOpaque(false);
        panelExterno.setBorder(BorderFactory.createEmptyBorder(75,0,0,0));

        JButton boton1 = new JButton();
        JButton boton2 = new JButton();

        JButton boton3 = new JButton();
        JButton boton4 = new JButton();

        File camarero = new File("C:\\Users\\daw20\\Desktop\\proyectoRestauranteApp\\imagenes\\camarero_chino.bmp");
        File cliente = new File("C:\\Users\\daw20\\Desktop\\proyectoRestauranteApp\\imagenes\\cliente.bmp");
        File admin = new File("C:\\Users\\daw20\\Desktop\\proyectoRestauranteApp\\imagenes\\admin.bmp");
        File chef = new File("C:\\Users\\daw20\\Desktop\\proyectoRestauranteApp\\imagenes\\chef.bmp");

        try {
            Image imagenCamarero = ImageIO.read(camarero);
            Image imagenResultado = imagenCamarero.getScaledInstance(175,175,Image.SCALE_SMOOTH);
            boton1.setIcon(new ImageIcon(imagenResultado));
        } catch (Exception ex) {
            System.out.println("No se pudo cargar la imagen");
        }
        try {
            Image imagenCliente = ImageIO.read(cliente);
            Image imagenResultado = imagenCliente.getScaledInstance(175,175,Image.SCALE_SMOOTH);
            boton2.setIcon(new ImageIcon(imagenResultado));
        } catch (Exception ex) {
            System.out.println("No se pudo cargar la imagen");
        }
        try {
            Image imagenAdmin = ImageIO.read(admin);
            Image imagenResultado = imagenAdmin.getScaledInstance(175,175,Image.SCALE_SMOOTH);
            boton3.setIcon(new ImageIcon(imagenResultado));
        } catch (Exception ex) {
            System.out.println("No se pudo cargar la imagen");
        }
        try {
            Image imagenChef = ImageIO.read(chef);
            Image imagenResultado = imagenChef.getScaledInstance(175,175,Image.SCALE_SMOOTH);
            boton4.setIcon(new ImageIcon(imagenResultado));
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

        LaminaConImagen lamina1 = new LaminaConImagen();
        lamina1.add(panelExterno);
        add(lamina1);

    }
}


class LaminaConImagen extends JPanel{

    private Image imagen;

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        File miImagen = new File("C:\\Users\\daw20\\Desktop\\proyectoRestauranteApp\\imagenes\\foto_chino.png");
        try{
            imagen= ImageIO.read(miImagen);
        }catch (IOException e){
            System.out.println("La imagen no se encuentra");
        }
        Image imagenResultado = imagen.getScaledInstance(900,600,Image.SCALE_DEFAULT);
        g.drawImage(imagenResultado,0,0,null);
    }

}