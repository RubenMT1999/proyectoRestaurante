package UtilidadesCliente;

import Modelos.Categoria;
import Modelos.Empleado;
import Modelos.Reclamacion;
import Modelos.tipoEmpleado;
import UtilidadesAdmin.FormularioProductos;


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

public class Cliente extends JFrame {
    public Cliente() {

        JPanel panelExterno = new JPanel(new GridLayout(7, 1, 10, 10));
        panelExterno.setOpaque(false);
        panelExterno.setBorder(BorderFactory.createEmptyBorder(110, 40, 0, 0));

        JLabel labelnomcli = new JLabel("Nombre cliente");
        JLabel labelProducto = new JLabel("Producto");
        JLabel labelQueja = new JLabel("Queja");

        JTextField textnomcli = new JTextField();
        JTextField textqueja = new JTextField();


        JComboBox combo1 = new JComboBox<>();
        combo1.addItem(Categoria.bebida);
        combo1.addItem(Categoria.entrante);
        combo1.addItem(Categoria.postre);
        combo1.addItem(Categoria.carne);
        combo1.addItem(Categoria.pescado);
        combo1.addItem(Categoria.pasta);
        combo1.addItem(Categoria.vegetariano);


        textnomcli.setBorder(BorderFactory.createCompoundBorder());
        textqueja.setBorder(BorderFactory.createCompoundBorder());

        Font newFont = new Font("Monospaced", Font.BOLD, 18);

        labelnomcli.setFont(newFont);
        labelProducto.setFont(newFont);
        labelQueja.setFont(newFont);

        panelExterno.add(labelnomcli);
        panelExterno.add(textnomcli);

        panelExterno.add(labelProducto);
        panelExterno.add(combo1);

        panelExterno.add(labelQueja);
        panelExterno.add(textqueja);

        JButton botonCrear = new JButton("Crear");
        botonCrear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connection con = conectarConBD();
                try {

                    PreparedStatement query = con.prepareStatement("insert into reclamacion (nombre_cliente, id_producto, queja) values (?,?,?);");
                    query.setString(1, textnomcli.getText());
                    query.setInt(2, combo1.getSelectedIndex() + 1);
                    query.setString(3, textqueja.getText());


                    query.executeQuery();

                } catch (SQLException sqle) {
                    System.out.println("Error en la ejecución:"
                            + sqle.getErrorCode() + " " + sqle.getMessage());

                } finally {
                    cerrarConexion(con);
                }
                textnomcli.setText("");
                textqueja.setText("");


            }
        });

        JButton botonBuscar = new JButton("Buscar");
        botonBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Reclamacion reclamacion = null;
                Connection con = conectarConBD();


                try {
                    PreparedStatement query = con.prepareStatement("select * from reclamacion where nombre_cliente  = ? ");
                    query.setString(1, textnomcli.getText());
                    ResultSet rs = query.executeQuery();

                    while (rs.next()) {
                        reclamacion = new Reclamacion(

                                rs.getString("nombre_cliente")
                                , Categoria.values()[rs.getInt("id_producto")]
                                , rs.getString("queja"));

                    }


                } catch (SQLException sqle) {
                    System.out.println("Error en la ejecución:"
                            + sqle.getErrorCode() + " " + sqle.getMessage());

                } finally {
                    cerrarConexion(con);
                }
                if (reclamacion == null) {
                    textnomcli.setText("");
                    textqueja.setText("");

                    JOptionPane.showMessageDialog(panelExterno,
                            "No existe ningun empleado con esa ID");
                } else

                    textnomcli.setText(reclamacion.getNombreCliente());
                if (reclamacion.getCategoria() == Categoria.bebida) {
                    combo1.setSelectedIndex(0);
                }
                if (reclamacion.getCategoria() == Categoria.entrante) {
                    combo1.setSelectedIndex(1);
                }
                if (reclamacion.getCategoria() == Categoria.postre) {
                    combo1.setSelectedIndex(2);
                }
                if (reclamacion.getCategoria() == Categoria.carne) {
                    combo1.setSelectedIndex(3);
                }
                if (reclamacion.getCategoria() == Categoria.pescado) {
                    combo1.setSelectedIndex(4);
                }
                if (reclamacion.getCategoria() == Categoria.pasta) {
                    combo1.setSelectedIndex(5);
                }
                if (reclamacion.getCategoria() == Categoria.vegetariano) {
                    combo1.setSelectedIndex(6);
                }
                textqueja.setText(reclamacion.getQueja());


            }
        });

        JButton botonModificar = new JButton("Modificar");
        botonModificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connection conn = conectarConBD();

                try {

                    PreparedStatement stmt = conn.prepareStatement("update reclamacion set  id_producto = ? , queja = ?   where nombre_cliente = ? ");
                    stmt.setInt(1, combo1.getSelectedIndex());
                    stmt.setString(2, textqueja.getText());
                    stmt.setString(3, textnomcli.getText());


                    ResultSet rs = stmt.executeQuery();

                    JOptionPane.showMessageDialog(panelExterno,
                            "Reclamacion modificada correctamente");

                } catch (Exception i) {
                    i.printStackTrace();
                    if (i instanceof SQLIntegrityConstraintViolationException){
                        JOptionPane.showMessageDialog(panelExterno,
                                "Ya existe ese empleado, elija otro");
                    }

                    if (i instanceof NumberFormatException){
                        JOptionPane.showMessageDialog(panelExterno,
                                "Rellena los datos correctamente");
                    }


                } finally {
                    cerrarConexion(conn);
                }
            }
        });
        JButton botonEliminar = new JButton("Eliminar");
        botonEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connection con = conectarConBD();


                try {
                    PreparedStatement query = con.prepareStatement("delete from reclamacion where nombre_cliente = ?");
                    query.setString(1, textnomcli.getText());
                    ResultSet rs = query.executeQuery();
                    JOptionPane.showMessageDialog(panelExterno,
                            "Reclamacion eliminada correctamente");

                } catch (SQLException sqle) {
                    System.out.println("Error en la ejecución:"
                            + sqle.getErrorCode() + " " + sqle.getMessage());

                } finally {
                    cerrarConexion(con);
                }
                labelnomcli.setText("");
                textqueja.setText("");


            }
        });

        JPanel panelBotones = new JPanel(new GridLayout(1, 3, 10, 10));

        panelBotones.add(botonCrear);
        panelBotones.add(botonBuscar);
        panelBotones.add(botonModificar);
        panelBotones.add(botonEliminar);

        panelBotones.setOpaque(false);

        panelExterno.add(panelBotones);


        setBounds(500, 50, 600, 900);
        setResizable(false);
        setVisible(true);

        ImagenFormCliente img1 = new ImagenFormCliente();
        img1.add(panelExterno);
        add(img1);

    }

    public static void main(String[] args) {
        Cliente p1 = new Cliente();
        p1.setVisible(true);
        p1.setSize(500, 800);
    }

    class ImagenFormCliente extends JPanel {

        private Image imagen;

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            String ruta = new File("").getAbsolutePath();
            File miImagen = new File(ruta + "\\imagenes\\fondo_productos.jpg");
            try {
                imagen = ImageIO.read(miImagen);
            } catch (IOException e) {
                System.out.println("La imagen no se encuentra");
            }
            Image imagenResultado = imagen.getScaledInstance(500, 800, Image.SCALE_DEFAULT);
            g.drawImage(imagenResultado, 0, 0, null);
        }

    }
}
