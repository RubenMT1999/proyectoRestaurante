package UtilidadesAdmin;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.io.File;
import java.io.IOException;


public class FormularioProductos{


    public static void main(String[] args) {
        VenFormProducto p1 = new VenFormProducto();
        p1.setVisible(true);
        p1.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        p1.setSize(500,800);
    }

}



class VenFormProducto extends JFrame {
    public VenFormProducto(){

        JPanel panelExterno = new JPanel(new GridLayout(15,2,10,10));
        panelExterno.setOpaque(false);
        panelExterno.setBorder(BorderFactory.createEmptyBorder(170,0,0,0));

        JLabel labelId = new JLabel("ID:");
        JLabel labelCodigo = new JLabel("Código:");
        JLabel labelDescripcion = new JLabel("Descripción:");
        JLabel labelPrecio = new JLabel("Precio:");


        JButton botonCrear = new JButton("Crear");
        JButton botonBuscar = new JButton("Buscar");
        JButton botonModificar = new JButton("Modificar");
        JButton botonEliminar = new JButton("Eliminar");




        JTextField textId = new JTextField();
        JTextField textCodigo = new JTextField();
        JTextField textDescripcion= new JTextField();
        JTextField textPrecio = new JTextField();

        textId.setBorder(BorderFactory.createCompoundBorder());
        textCodigo.setBorder(BorderFactory.createCompoundBorder());
        textDescripcion.setBorder(BorderFactory.createCompoundBorder());
        textPrecio.setBorder(BorderFactory.createCompoundBorder());

        textId.setForeground(Color.RED);

        Font newFont = new Font("Monospaced",Font.BOLD,18);
        textId.setFont(newFont);
        textCodigo.setFont(newFont);
        textDescripcion.setFont(newFont);
        textPrecio.setFont(newFont);


        labelId.setFont(newFont);
        labelCodigo.setFont(newFont);
        labelDescripcion.setFont(newFont);
        labelPrecio.setFont(newFont);


        panelExterno.add(labelId);
        panelExterno.add(textId);

        panelExterno.add(labelCodigo);
        panelExterno.add(textCodigo);

        panelExterno.add(labelDescripcion);
        panelExterno.add(textDescripcion);

        panelExterno.add(labelPrecio);
        panelExterno.add(textPrecio);

        panelExterno.add(Box.createRigidArea(new Dimension(60,0)));

        JPanel panelBotones = new JPanel(new GridLayout(1,3,10,10));

        panelBotones.add(botonCrear);
        panelBotones.add(botonBuscar);
        panelBotones.add(botonModificar);
        panelBotones.add(botonEliminar);

        panelBotones.setOpaque(false);


        panelExterno.add(panelBotones);



        setBounds(500,50,600,900);

        ImagenFormProducto img1 = new ImagenFormProducto();
        img1.add(panelExterno);
        add(img1);

    }
}


class ImagenFormProducto extends JPanel{

    private Image imagen;

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        File miImagen = new File("C:\\Users\\NitroPC\\IdeaProjects\\proyectoRestaurante\\imagenes\\fondo_productos.jpg");
        try{
            imagen= ImageIO.read(miImagen);
        }catch (IOException e){
            System.out.println("La imagen no se encuentra");
        }
        Image imagenResultado = imagen.getScaledInstance(500,800,Image.SCALE_DEFAULT);
        g.drawImage(imagenResultado,0,0,null);
    }

}