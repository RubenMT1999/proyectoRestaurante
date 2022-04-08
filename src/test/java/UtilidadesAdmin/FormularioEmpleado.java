package UtilidadesAdmin;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.io.File;
import java.io.IOException;


public class FormularioEmpleado{


    public static void main(String[] args) {
        VenFormEmpleado form1 = new VenFormEmpleado();
        form1.setVisible(true);
        form1.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        form1.setSize(500,800);
    }

}



class VenFormEmpleado extends JFrame {
    public VenFormEmpleado(){




        JPanel panelExterno = new JPanel(new GridLayout(16,1,10,10));
        panelExterno.setOpaque(false);
        panelExterno.setBorder(BorderFactory.createEmptyBorder(75,0,0,0));

        JLabel labelId = new JLabel("ID:");
        JLabel labelNombre = new JLabel("Nombre:");
        JLabel labelCodigo = new JLabel("Código:");
        JLabel labelApellido1 = new JLabel("Primer Apellido:");
        JLabel labelApellido2 = new JLabel("Segundo Apellido:");
        JLabel labelTipo = new JLabel("Tipo Empleado");

        JComboBox comboTipo = new JComboBox<String>();
        comboTipo.addItem("camarero");
        comboTipo.addItem("cocinero");
        comboTipo.addItem("admin");

        JButton botonBuscar = new JButton("Buscar");
        JButton botonGuardar = new JButton("Guardar");
        JButton botonEliminar = new JButton("Eliminar");




        JTextField textId = new JTextField(20);
        JTextField textNombre = new JTextField();
        JTextField textCodigo = new JTextField();
        JTextField textApellido1 = new JTextField();
        JTextField textApellido2 = new JTextField();

        textId.setBorder(BorderFactory.createCompoundBorder());
        textNombre.setBorder(BorderFactory.createCompoundBorder());
        textCodigo.setBorder(BorderFactory.createCompoundBorder());
        textApellido1.setBorder(BorderFactory.createCompoundBorder());
        textApellido2.setBorder(BorderFactory.createCompoundBorder());

        textId.setForeground(Color.RED);

        Font newFont = new Font("Monospaced",Font.BOLD,18);
        textId.setFont(newFont);
        textNombre.setFont(newFont);
        textCodigo.setFont(newFont);
        textApellido1.setFont(newFont);
        textApellido2.setFont(newFont);


        labelId.setFont(newFont);
        labelNombre.setFont(newFont);
        labelCodigo.setFont(newFont);
        labelApellido1.setFont(newFont);
        labelApellido2.setFont(newFont);
        labelTipo.setFont(newFont);

        panelExterno.add(labelId);
        panelExterno.add(textId);

        panelExterno.add(labelNombre);
        panelExterno.add(textNombre);

        panelExterno.add(labelCodigo);
        panelExterno.add(textCodigo);

        panelExterno.add(labelTipo);
        panelExterno.add(comboTipo);

        panelExterno.add(labelApellido1);
        panelExterno.add(textApellido1);

        panelExterno.add(labelApellido2);
        panelExterno.add(textApellido2);

        panelExterno.add(Box.createRigidArea(new Dimension(60,0)));

        JPanel panelBotones = new JPanel(new GridLayout(1,3,10,10));

        panelBotones.add(botonBuscar);
        panelBotones.add(botonGuardar);
        panelBotones.add(botonEliminar);

        panelBotones.setOpaque(false);


        panelExterno.add(panelBotones);



        setBounds(500,50,600,900);

        ImagenFormEmpleado img1 = new ImagenFormEmpleado();
        img1.add(panelExterno);
        add(img1);

    }
}


class ImagenFormEmpleado extends JPanel{

    private Image imagen;

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        File miImagen = new File("C:\\Users\\daw20\\Desktop\\proyectoRestauranteApp\\imagenes\\formularioEmpleado.jpg");
        try{
            imagen= ImageIO.read(miImagen);
        }catch (IOException e){
            System.out.println("La imagen no se encuentra");
        }
        Image imagenResultado = imagen.getScaledInstance(500,800,Image.SCALE_DEFAULT);
        g.drawImage(imagenResultado,0,0,null);
    }

}