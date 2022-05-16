package UtilidadesAdmin;

import Modelos.Mesa;
import UtilidadesBBDD.UtilidadesBBDD;

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
import java.sql.*;

import static UtilidadesBBDD.UtilidadesBBDD.cerrarConexion;
import static UtilidadesBBDD.UtilidadesBBDD.conectarConBD;






public class FormularioMesas extends JFrame {
    public FormularioMesas(){

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


        botonCrear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connection conn = conectarConBD();

                try{
                    PreparedStatement stmt = conn.prepareStatement("insert into mesa(numero_mesa,max_personas,libre)" +
                            " values (?,?,?)");

                    stmt.setInt(1,Integer.parseInt(textNumeroMesa.getText()));
                    stmt.setInt(2,Integer.parseInt(textNumeroComensales.getText()));
                    stmt.setInt(3,1);

                    ResultSet rs = stmt.executeQuery();

                    JOptionPane.showMessageDialog(panelExterno,
                            "Mesa insertada con éxito");

                }catch (Exception o){
                    o.printStackTrace();
                    System.out.println("Error"+  o.getMessage());
                    if (o instanceof SQLIntegrityConstraintViolationException){
                        JOptionPane.showMessageDialog(panelExterno,
                                "Ya hay una mesa con ese Número");
                    }else {
                    JOptionPane.showMessageDialog(panelExterno,
                            "No se ha podido insertar la mesa." +
                                    "Por favor, introduce los datos correctamente.");
                    }

                }finally {
                    cerrarConexion(conn);
                }
            }
        });

        botonBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connection conn = conectarConBD();
                Mesa m1 = null;

                try{
                    PreparedStatement stmt = conn.prepareStatement("select * from mesa where id = ?");

                    stmt.setInt(1,Integer.parseInt(textId.getText()));
                    ResultSet rs = stmt.executeQuery();

                    while (rs.next()){
                         m1 = new Mesa(rs.getInt("id"),
                                rs.getInt("numero_mesa"),
                                rs.getInt("max_personas"),
                                rs.getInt("libre"));
                    }


                    textId.setText(Integer.toString(m1.getId()));
                    textNumeroMesa.setText(Integer.toString(m1.getNumeroMesa()));
                    textNumeroComensales.setText(Integer.toString(m1.getMaxPersonas()));

                }catch (Exception i){
                    System.out.println("error");
                    i.printStackTrace();
                    if (i instanceof NullPointerException){
                        JOptionPane.showMessageDialog(panelExterno,
                                "No existe una mesa con ese ID");
                    }else {
                        JOptionPane.showMessageDialog(panelExterno,
                                "Introduzca los datos correctamente");
                    }
                }finally {
                    cerrarConexion(conn);
                }
            }
        });



        botonEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connection conn = conectarConBD();

                try {
                    PreparedStatement stmt2 = conn.prepareStatement("select id from mesa where id = ?");
                    stmt2.setInt(1,Integer.parseInt(textId.getText()));
                    ResultSet rs2 = stmt2.executeQuery();

                    int id = 0;

                    if (rs2.next()){
                        id = rs2.getInt("id");
                    }

                    if (id >0){
                        JOptionPane.showMessageDialog(panelExterno,
                                "Mesa borrada correctamente");
                    }


                    PreparedStatement stmt = conn.prepareStatement("delete from mesa where id = ?");
                    stmt.setInt(1,Integer.parseInt(textId.getText()));
                    ResultSet rs = stmt.executeQuery();

                    textId.setText("");
                    textNumeroComensales.setText("");
                    textNumeroMesa.setText("");


                }catch (Exception i){
                    System.out.println("error");
                    i.printStackTrace();
                    if (i instanceof NullPointerException){
                        JOptionPane.showMessageDialog(panelExterno,
                                "No existe una mesa con ese ID");
                    }else {
                        JOptionPane.showMessageDialog(panelExterno,
                                "Introduzca los datos correctamente");
                    }
                }finally {
                    cerrarConexion(conn);
                }
            }
        });

        botonModificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connection conn = conectarConBD();

                try{

                    PreparedStatement stmt = conn.prepareStatement("update mesa set numero_mesa = ? ," +
                            " max_personas = ? where id = ?");
                    stmt.setInt(1,Integer.parseInt(textNumeroMesa.getText()));
                    stmt.setInt(2,Integer.parseInt(textNumeroComensales.getText()));
                    stmt.setInt(3,Integer.parseInt(textId.getText()));

                    ResultSet rs = stmt.executeQuery();

                    JOptionPane.showMessageDialog(panelExterno,
                            "Mesa modificada con éxito");

                }catch (Exception i){
                    i.printStackTrace();

                    if (i instanceof SQLIntegrityConstraintViolationException){
                        JOptionPane.showMessageDialog(panelExterno,
                                "Ya existe ese Número de Mesa, elija otro");
                    }

                    if (i instanceof NumberFormatException){
                        JOptionPane.showMessageDialog(panelExterno,
                                "Rellena los datos correctamente");
                    }

                }finally {
                    cerrarConexion(conn);
                }
            }
        });


        panelBotones.setOpaque(false);


        panelExterno.add(panelBotones);



        setBounds(500,50,600,900);
        setResizable(false);
        setVisible(true);

        ImagenFormMesas img1 = new ImagenFormMesas();
        img1.add(panelExterno);
        add(img1);

    }
    public static void main(String[] args) {
        FormularioMesas m1 = new FormularioMesas();
        m1.setVisible(true);
        m1.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        m1.setSize(500,600);
    }
}


class ImagenFormMesas extends JPanel{

    private Image imagen;

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        String ruta = new File("").getAbsolutePath();
        File miImagen = new File(ruta+"\\imagenes\\fondo_mesas.jpg");
        try{
            imagen= ImageIO.read(miImagen);
        }catch (IOException e){
            System.out.println("La imagen no se encuentra");
        }
        Image imagenResultado = imagen.getScaledInstance(500,600,Image.SCALE_DEFAULT);
        g.drawImage(imagenResultado,0,0,null);
    }

}