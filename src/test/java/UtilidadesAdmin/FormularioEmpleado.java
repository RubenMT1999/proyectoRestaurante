package UtilidadesAdmin;

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
import java.util.ArrayList;
import java.util.List;

import static UtilidadesBBDD.UtilidadesBBDD.cerrarConexion;
import static UtilidadesBBDD.UtilidadesBBDD.conectarConBD;


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
        JLabel labelDni = new JLabel("DNI");

        JComboBox comboTipo = new JComboBox<String>();
        comboTipo.addItem("camarero");
        comboTipo.addItem("cocinero");
        comboTipo.addItem("admin");








        JButton botonEliminar = new JButton("Eliminar");





        JTextField textId = new JTextField(20);
        JTextField textNombre = new JTextField();
        JTextField textCodigo = new JTextField();
        JTextField textApellido1 = new JTextField();
        JTextField textApellido2 = new JTextField();
        JTextField textDni = new JTextField();

        textId.setBorder(BorderFactory.createCompoundBorder());
        textNombre.setBorder(BorderFactory.createCompoundBorder());
        textCodigo.setBorder(BorderFactory.createCompoundBorder());
        textApellido1.setBorder(BorderFactory.createCompoundBorder());
        textApellido2.setBorder(BorderFactory.createCompoundBorder());
        textDni.setBorder(BorderFactory.createCompoundBorder());

        textId.setForeground(Color.RED);

        Font newFont = new Font("Monospaced",Font.BOLD,18);
        textId.setFont(newFont);
        textNombre.setFont(newFont);
        textCodigo.setFont(newFont);
        textApellido1.setFont(newFont);
        textApellido2.setFont(newFont);
        textDni.setFont(newFont);


        labelId.setFont(newFont);
        labelNombre.setFont(newFont);
        labelCodigo.setFont(newFont);
        labelApellido1.setFont(newFont);
        labelApellido2.setFont(newFont);
        labelTipo.setFont(newFont);
        labelDni.setFont(newFont);

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

        panelExterno.add(labelDni);
        panelExterno.add(textDni);

        panelExterno.add(Box.createRigidArea(new Dimension(60,0)));

        JButton botonBuscar = new JButton("Buscar");
        botonBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Empleado empleado = null;
                Connection con = conectarConBD();



                try {
                    PreparedStatement query = con.prepareStatement("select * from empleado where id  = ? ");
                    query.setInt(1, Integer.parseInt(textId.getText()));
                    ResultSet rs = query.executeQuery();

                    while (rs.next()){
                        empleado = new Empleado(
                             rs.getInt("id")
                            ,rs.getString("nombre")
                            ,rs.getString("codigo")
                            ,tipoEmpleado.valueOf(rs.getString("tipo"))
                            ,rs.getString("apellido1")
                            ,rs.getString("apellido2")
                            ,rs.getString("dni") );


                    }



                }catch (SQLException sqle) {
                    System.out.println("Error en la ejecución:"
                            + sqle.getErrorCode() + " " + sqle.getMessage());

                } finally {
                    cerrarConexion(con);
                }
                if (empleado == null ) {
                    JOptionPane.showMessageDialog(panelExterno,
                            "No existe ningun empleado con esa ID");
                }
                else

                textNombre.setText(empleado.getNombre());
                textCodigo.setText(empleado.getCodigo());
                if (empleado.getTipoEmpleado() == tipoEmpleado.camarero){
                    comboTipo.setSelectedIndex(0);
                }
                if (empleado.getTipoEmpleado() == tipoEmpleado.cocinero){
                    comboTipo.setSelectedIndex(1);
                }
                if (empleado.getTipoEmpleado() == tipoEmpleado.admin){
                    comboTipo.setSelectedIndex(2);
                }
                textApellido1.setText(empleado.getApellido1());
                textApellido2.setText(empleado.getApellido2());
                textDni.setText(empleado.getDni());



            }
        });

        JButton botonGuardar = new JButton("Guardar");
        botonGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                Connection con = conectarConBD();
                try {




                    PreparedStatement query = con.prepareStatement("insert into empleado (codigo, tipo, nombre, apellido1, apellido2, dni) values (?,?,?,?,?,?);");
                    query.setString(1, textCodigo.getText());
                    query.setInt(2, comboTipo.getSelectedIndex() + 1) ;
                    query.setString(3, textNombre.getText());
                    query.setString(4, textApellido1.getText());
                    query.setString(5, textApellido2.getText());
                    query.setString(6, textDni.getText());



                    query.executeQuery();




                } catch (SQLException sqle) {
                    System.out.println("Error en la ejecución:"
                            + sqle.getErrorCode() + " " + sqle.getMessage());

                } finally {
                    cerrarConexion(con);
                }
            }
        });


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
        File miImagen = new File("C:\\Users\\dragu\\Desktop\\proyectoRestaurante\\imagenes\\formularioEmpleado.jpg");
        try{
            imagen= ImageIO.read(miImagen);
        }catch (IOException e){
            System.out.println("La imagen no se encuentra");
        }
        Image imagenResultado = imagen.getScaledInstance(500,800,Image.SCALE_DEFAULT);
        g.drawImage(imagenResultado,0,0,null);
    }

}