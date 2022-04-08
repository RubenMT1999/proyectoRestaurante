package UtilidadesAdmin;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.io.File;
import java.io.IOException;


public class FormularioMesas{


    public static void main(String[] args) {
        VenFormMesas m1 = new VenFormMesas();
        m1.setVisible(true);
        m1.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        m1.setSize(500,600);
    }

}



class VenFormMesas extends JFrame {
    public VenFormMesas(){

        JPanel panelExterno = new JPanel(new GridLayout(15,2,10,10));
        panelExterno.setOpaque(false);
        panelExterno.setBorder(BorderFactory.createEmptyBorder(170,0,0,0));

        JLabel labelId = new JLabel("ID:");
        JLabel labelNumeroMesa = new JLabel("Número de mesa:");
        JLabel labelNumeroComensales = new JLabel("Número de comensales:");



        JButton botonCrear = new JButton("Crear");
        JButton botonBuscar = new JButton("Buscar");
        JButton botonModificar = new JButton("Modificar");
        JButton botonEliminar = new JButton("Eliminar");




        JTextField textId = new JTextField();
        JTextField textNumeroMesa = new JTextField();
        JTextField textNumeroComensales= new JTextField();


        textId.setBorder(BorderFactory.createCompoundBorder());
        textNumeroMesa.setBorder(BorderFactory.createCompoundBorder());
        textNumeroComensales.setBorder(BorderFactory.createCompoundBorder());


        textId.setForeground(Color.RED);

        Font newFont = new Font("Monospaced",Font.BOLD,18);
        textId.setFont(newFont);
        textNumeroMesa.setFont(newFont);
        textNumeroComensales.setFont(newFont);


        labelId.setFont(newFont);
        labelNumeroMesa.setFont(newFont);
        labelNumeroComensales.setFont(newFont);


        panelExterno.add(labelId);
        panelExterno.add(textId);

        panelExterno.add(labelNumeroMesa);
        panelExterno.add(textNumeroMesa);

        panelExterno.add(labelNumeroComensales);
        panelExterno.add(textNumeroComensales);

        panelExterno.add(Box.createRigidArea(new Dimension(60,0)));

        JPanel panelBotones = new JPanel(new GridLayout(1,3,10,10));

        panelBotones.add(botonCrear);
        panelBotones.add(botonBuscar);
        panelBotones.add(botonModificar);
        panelBotones.add(botonEliminar);

        panelBotones.setOpaque(false);


        panelExterno.add(panelBotones);



        setBounds(500,50,600,900);

        ImagenFormMesas img1 = new ImagenFormMesas();
        img1.add(panelExterno);
        add(img1);

    }
}


class ImagenFormMesas extends JPanel{

    private Image imagen;

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        File miImagen = new File("C:\\Users\\dragu\\Desktop\\proyectoRestaurante\\imagenes\\fondo_mesas.jpg");
        try{
            imagen= ImageIO.read(miImagen);
        }catch (IOException e){
            System.out.println("La imagen no se encuentra");
        }
        Image imagenResultado = imagen.getScaledInstance(500,600,Image.SCALE_DEFAULT);
        g.drawImage(imagenResultado,0,0,null);
    }

}