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
        vC.setVisible(true);
        vC.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        vC.setSize(400,600);
    }

}



class VentanaCamarero extends JFrame {
    public VentanaCamarero(){


        JPanel panelExterno = new JPanel(new GridLayout(3,1,20,20));
        panelExterno.setOpaque(false);
        panelExterno.setBorder(BorderFactory.createEmptyBorder(160,0,0,0));

        JButton boton1 = new JButton();
        JButton boton2 = new JButton();

        JButton boton3 = new JButton();


//        File camarero = new File("C:\\Users\\daw20\\Desktop\\proyectoRestauranteApp\\imagenes\\camarero_chino.bmp");
//
//        try {
//            Image imagenCamarero = ImageIO.read(camarero);
//            Image imagenResultado = imagenCamarero.getScaledInstance(175,175,Image.SCALE_SMOOTH);
//            boton1.setIcon(new ImageIcon(imagenResultado));
//        } catch (Exception ex) {
//            System.out.println("No se pudo cargar la imagen");
//        }

        panelExterno.add(boton1);
        panelExterno.add(boton2);
        panelExterno.add(boton3);


        Font fuenteBoton = new Font("Serif",Font.BOLD,15);
        Border bordeB1 = new LineBorder(Color.BLACK);

        Border esteBorde = new TitledBorder(bordeB1,"CAMARERO",TitledBorder.CENTER,TitledBorder.DEFAULT_POSITION,
                fuenteBoton,Color.BLACK);
        Border esteBorde2 = new TitledBorder(bordeB1,"CLIENTE",TitledBorder.CENTER,TitledBorder.DEFAULT_POSITION,
                fuenteBoton,Color.BLACK);
        Border esteBorde3 = new TitledBorder(bordeB1,"ADMIN",TitledBorder.CENTER,TitledBorder.DEFAULT_POSITION,
                fuenteBoton,Color.BLACK);


        boton1.setBorder(esteBorde);
        boton2.setBorder(esteBorde2);
        boton3.setBorder(esteBorde3);

        boton1.setSize(100,100);






        setBounds(500,100,600,900);

        ImagenCamarero iC = new ImagenCamarero();
        iC.add(panelExterno);
        add(iC);

    }
}


class ImagenCamarero extends JPanel{

    private Image imagen;

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        File miImagen = new File("C:\\Users\\daw20\\Desktop\\proyectoRestauranteApp\\imagenes\\fondo_camarero.jpg");
        try{
            imagen= ImageIO.read(miImagen);
        }catch (IOException e){
            System.out.println("La imagen no se encuentra");
        }
        Image imagenResultado = imagen.getScaledInstance(400,600,Image.SCALE_DEFAULT);
        g.drawImage(imagenResultado,0,0,null);
    }

}
