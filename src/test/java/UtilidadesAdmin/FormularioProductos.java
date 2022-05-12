package UtilidadesAdmin;

import Modelos.Carta;
import Modelos.Categoria;
import Modelos.Empleado;
import Modelos.tipoEmpleado;

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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static UtilidadesBBDD.UtilidadesBBDD.cerrarConexion;
import static UtilidadesBBDD.UtilidadesBBDD.conectarConBD;


public class FormularioProductos{


    public static void main(String[] args) {
        VenFormProducto p1 = new VenFormProducto();
        p1.setVisible(true);

        p1.setSize(500,800);
    }

}


class VenFormProducto extends JFrame {
    public VenFormProducto(){

        JPanel panelExterno = new JPanel(new GridLayout(15,2,10,10));
        panelExterno.setOpaque(false);
        panelExterno.setBorder(BorderFactory.createEmptyBorder(170,0,0,0));

        JLabel labelId = new JLabel("ID:");
        JLabel labelNombre = new JLabel("Nombre:");
        JLabel labelDescripcion = new JLabel("Descripción:");
        JLabel labelCategoria = new JLabel("Categoria");
        JLabel labelPrecio = new JLabel("Precio:");
        JComboBox comboCategoria = new JComboBox<String>();
        comboCategoria.addItem("bebida");
        comboCategoria.addItem("entrante");
        comboCategoria.addItem("postre");
        comboCategoria.addItem("carne");
        comboCategoria.addItem("pescado");
        comboCategoria.addItem("pasta");
        comboCategoria.addItem("vegetariano");







        JTextField textId = new JTextField();
        JTextField textNombre = new JTextField();
        JTextField textDescripcion= new JTextField();
        JTextField textPrecio = new JTextField();

        textId.setBorder(BorderFactory.createCompoundBorder());
        textNombre.setBorder(BorderFactory.createCompoundBorder());
        textDescripcion.setBorder(BorderFactory.createCompoundBorder());
        textPrecio.setBorder(BorderFactory.createCompoundBorder());

        textId.setForeground(Color.RED);

        Font newFont = new Font("Monospaced",Font.BOLD,18);
        textId.setFont(newFont);
        textNombre.setFont(newFont);
        textDescripcion.setFont(newFont);
        textPrecio.setFont(newFont);


        labelId.setFont(newFont);
        labelNombre.setFont(newFont);
        labelDescripcion.setFont(newFont);
        labelCategoria.setFont(newFont);
        labelPrecio.setFont(newFont);


        panelExterno.add(labelId);
        panelExterno.add(textId);

        panelExterno.add(labelNombre);
        panelExterno.add(textNombre);

        panelExterno.add(labelDescripcion);
        panelExterno.add(textDescripcion);

        panelExterno.add(labelCategoria);
        panelExterno.add(comboCategoria);

        panelExterno.add(labelPrecio);
        panelExterno.add(textPrecio);

        panelExterno.add(Box.createRigidArea(new Dimension(60,0)));

        JButton botonCrear = new JButton("Crear");
        botonCrear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connection con = conectarConBD();
                try {

                    PreparedStatement query = con.prepareStatement("insert into carta (id, nombre, descripcion, categoria, precio) values (?,?,?,?,?);");
                    query.setInt(1,Integer.parseInt(textId.getText()));
                    query.setString(2, textNombre.getText());
                    query.setString(3, textDescripcion.getText());
                    query.setInt(4,comboCategoria.getSelectedIndex() + 1 );
                    query.setDouble(5, Double.parseDouble(textPrecio.getText()));

                    query.executeQuery();

                } catch (SQLException sqle) {
                    System.out.println("Error en la ejecución:"
                            + sqle.getErrorCode() + " " + sqle.getMessage());

                } finally {
                    cerrarConexion(con);
                }
                textId.setText("");
                textNombre.setText("");
                textDescripcion.setText("");
                textPrecio.setText("");
            }
        });

        JButton botonEliminar = new JButton("Eliminar");
        botonEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connection con = conectarConBD();


                try {
                    PreparedStatement query = con.prepareStatement("delete from carta where id = ?");
                    query.setInt(1, Integer.parseInt(textId.getText()));
                    ResultSet rs = query.executeQuery();

                } catch (SQLException sqle) {
                    System.out.println("Error en la ejecución:"
                            + sqle.getErrorCode() + " " + sqle.getMessage());

                } finally {
                    cerrarConexion(con);
                }
                textId.setText("");
                textNombre.setText("");
                textDescripcion.setText("");
                textPrecio.setText("");

            }
        });
        JButton botonBuscar = new JButton("Buscar");
        botonBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Carta carta = null;

                Connection con = conectarConBD();

                try {
                    PreparedStatement query = con.prepareStatement("select * from carta where id  = ? ");
                    query.setInt(1, Integer.parseInt(textId.getText()));
                    ResultSet rs = query.executeQuery();

                    while (rs.next()){
                        carta = new Carta(
                                rs.getInt("id")
                                ,rs.getString("nombre")
                                ,rs.getString("descripcion")
                                ,Categoria.values()[rs.getInt("categoria")]
                                ,rs.getDouble("precio"));
                    }



                }catch (SQLException sqle) {
                    System.out.println("Error en la ejecución:"
                            + sqle.getErrorCode() + " " + sqle.getMessage());

                } finally {
                    cerrarConexion(con);
                }
                if (carta == null ) {
                    textId.setText("");
                    textNombre.setText("");
                    textDescripcion.setText("");
                    textPrecio.setText("");
                    JOptionPane.showMessageDialog(panelExterno,
                            "No existe ningun producto con esa ID");
                }
                else
                    textNombre.setText(carta.getNombre());
                    textDescripcion.setText(carta.getDescripcion());
                if (carta.getCategoria() == Categoria.bebida){
                    comboCategoria.setSelectedIndex(0);
                }
                if (carta.getCategoria() == Categoria.entrante){
                    comboCategoria.setSelectedIndex(1);
                }
                if (carta.getCategoria() == Categoria.postre){
                    comboCategoria.setSelectedIndex(2);
                }
                if (carta.getCategoria() == Categoria.carne){
                    comboCategoria.setSelectedIndex(3);
                }
                if (carta.getCategoria() == Categoria.pescado){
                    comboCategoria.setSelectedIndex(4);
                }
                if (carta.getCategoria() == Categoria.pasta){
                    comboCategoria.setSelectedIndex(5);
                }
                if (carta.getCategoria() == Categoria.vegetariano){
                    comboCategoria.setSelectedIndex(6);
                }
                textPrecio.setText(String.valueOf(carta.getPrecio()));

            }
        });
        JButton botonModificar = new JButton("Modificar");


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
        File miImagen = new File("C:\\Users\\daw20\\IdeaProjects\\proyectoRestaurante\\imagenes\\fondo_mesas.jpg");
        try{
            imagen= ImageIO.read(miImagen);
        }catch (IOException e){
            System.out.println("La imagen no se encuentra");
        }
        Image imagenResultado = imagen.getScaledInstance(500,800,Image.SCALE_DEFAULT);
        g.drawImage(imagenResultado,0,0,null);
    }

}