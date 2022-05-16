package UtilidadesAdmin;

import Modelos.Carta;
import Modelos.Categoria;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.*;

import static UtilidadesBBDD.UtilidadesBBDD.cerrarConexion;
import static UtilidadesBBDD.UtilidadesBBDD.conectarConBD;





public class FormularioProductos extends JFrame {
    public FormularioProductos(){

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



        JComboBox combo1 = new JComboBox<>();
        combo1.addItem(Categoria.bebida);
        combo1.addItem(Categoria.entrante);
        combo1.addItem(Categoria.postre);
        combo1.addItem(Categoria.carne);
        combo1.addItem(Categoria.pescado);
        combo1.addItem(Categoria.pasta);
        combo1.addItem(Categoria.vegetariano);






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
        labelCategoria.setFont(newFont);


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
                    query.setInt(4,combo1.getSelectedIndex() + 1 );
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
                    JOptionPane.showMessageDialog(panelExterno,
                            "Producto eliminado correctamente");

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
        botonModificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connection conn = conectarConBD();

                try{

                    PreparedStatement stmt = conn.prepareStatement("update carta set nombre = ?, descripcion = ? , categoria = ? , precio = ? where id = ? ");
                    stmt.setString(1,textNombre.getText());
                    stmt.setString(2, textDescripcion.getText());
                    stmt.setInt(3,comboCategoria.getSelectedIndex());
                    stmt.setDouble(4, Double.parseDouble(textPrecio.getText()));
                    stmt.setInt(5, Integer.parseInt(textId.getText()));

                    ResultSet rs = stmt.executeQuery();

                    JOptionPane.showMessageDialog(panelExterno,
                            "Producto modificado correctamente");

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


        JPanel panelBotones = new JPanel(new GridLayout(1,3,10,10));

        panelBotones.add(botonCrear);
        panelBotones.add(botonBuscar);
        panelBotones.add(botonModificar);
        panelBotones.add(botonEliminar);

        panelBotones.setOpaque(false);


        panelExterno.add(panelBotones);


        botonCrear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Connection con = conectarConBD();

                try{
                    PreparedStatement query = con.prepareStatement("insert into carta (nombre,descripcion," +
                            "categoria,precio) values(?,?,?,?)");

                    query.setString(1,textNombre.getText());
                    query.setString(2,textDescripcion.getText());
                    query.setInt(3,comboCategoria.getSelectedIndex());
                    query.setDouble(4,Double.parseDouble(textPrecio.getText()));

                    ResultSet rs = query.executeQuery();
                    JOptionPane.showMessageDialog(panelExterno,
                            "Producto creado correctamente");

                }catch (Exception o){
                    System.out.println(o);
                }finally {
                    cerrarConexion(con);
                }
            }
        });





        setBounds(500,50,600,900);
        setResizable(false);
        setVisible(true);

        ImagenFormProducto img1 = new ImagenFormProducto();
        img1.add(panelExterno);
        add(img1);

    }
    public static void main(String[] args) {
        FormularioProductos p1 = new FormularioProductos();
        p1.setVisible(true);
        p1.setSize(500,800);
    }
}


class ImagenFormProducto extends JPanel{

    private Image imagen;

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        String ruta = new File("").getAbsolutePath();
        File miImagen = new File(ruta + "\\imagenes\\fondo_productos.jpg");
        try{
            imagen= ImageIO.read(miImagen);
        }catch (IOException e){
            System.out.println("La imagen no se encuentra");
        }
        Image imagenResultado = imagen.getScaledInstance(500,800,Image.SCALE_DEFAULT);
        g.drawImage(imagenResultado,0,0,null);
    }

}