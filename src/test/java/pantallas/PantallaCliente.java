package pantallas;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class PantallaCliente{


    public static void main(String[] args) {
        VentanaCliente venCl = new VentanaCliente();
    }


}
class VentanaCliente extends JFrame {
    public VentanaCliente(){


        JPanel panelExterno = new JPanel(new GridLayout(1,1,20,20));
        panelExterno.setOpaque(false);
        panelExterno.setBorder(BorderFactory.createEmptyBorder(200,0,0,0));

        JButton boton1 = new JButton();


        File menu = new File("C:\\Users\\daw20\\IdeaProjects\\proyectoRestaurante\\imagenes\\menu.bmp");
        try {
            Image imagenMenu = ImageIO.read(menu);
            Image imagenResultado = imagenMenu.getScaledInstance(175,175,Image.SCALE_SMOOTH);
            boton1.setIcon(new ImageIcon(imagenResultado));
        } catch (Exception ex) {
            System.out.println("No se pudo cargar la imagen");
        }

        panelExterno.add(boton1);



        Font fuenteBoton = new Font("Serif",Font.BOLD,15);
        Border bordeB1 = new LineBorder(Color.BLACK);

        Border esteBorde = new TitledBorder(bordeB1,"MENU",TitledBorder.CENTER,TitledBorder.DEFAULT_POSITION,
                fuenteBoton,Color.BLACK);



        boton1.setBorder(esteBorde);


        boton1.setSize(100,100);


        setBounds(500,100,600,900);

        ImagenCliente imgCl = new ImagenCliente();
        imgCl.add(panelExterno);
        add(imgCl);
        setVisible(true);
        setSize(415,640);

    }
}

class ImagenCliente extends JPanel{

    private Image imagen;

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        File miImagen = new File("C:\\Users\\daw20\\IdeaProjects\\proyectoRestaurante\\imagenes\\fondoCliente.jpg");
        try{
            imagen= ImageIO.read(miImagen);
        }catch (IOException e){
            System.out.println("La imagen no se encuentra");
        }
        Image imagenResultado = imagen.getScaledInstance(400,600,Image.SCALE_DEFAULT);
        g.drawImage(imagenResultado,0,0,null);
    }

}