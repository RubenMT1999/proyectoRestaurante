package UtilidadesCocinero;






import Modelos.Consumicion;


import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static UtilidadesBBDD.UtilidadesBBDD.cerrarConexion;
import static UtilidadesBBDD.UtilidadesBBDD.conectarConBD;

public class UtilidadesCocinero {
    public static void main(String[] args) {
    VentanaComanda v1 = new VentanaComanda();
        v1.setVisible(true);
        v1.setSize(new Dimension(510, 870));
    }
}
    


class VentanaComanda extends JFrame{
    private JTable tabla1 = new JTable() ;


    public VentanaComanda() {



        JPanel panelExterno = new JPanel(new GridLayout(2,1,10,10));
        panelExterno.setOpaque(false);
        panelExterno.setBorder(BorderFactory.createEmptyBorder(170,0,0,0));



        JLabel labelMesa = new JLabel("MESA");

        JComboBox numMesa = new JComboBox<String>();
        numMesa.addItem("1");
        numMesa.addItem("2");
        numMesa.addItem("3");
        numMesa.addItem("4");
        numMesa.addItem("5");
        numMesa.addItem("7");
        numMesa.addItem("8");
        numMesa.addItem("9");
        numMesa.addItem("10");


        JButton botonBuscar = new JButton("Buscar");


        Font newFont = new Font("Monospaced",Font.BOLD,18);

        labelMesa.setFont(newFont);

        JPanel panelMesa = new JPanel(new GridLayout(1,1));

        panelMesa.add(labelMesa);


        JPanel panelLabel1 = new JPanel(new GridLayout(1,1,10,10));
        panelLabel1.add(numMesa);
        panelLabel1.add(botonBuscar);



        ImagenComandas img1 = new ImagenComandas();
        img1.add(panelExterno);
        add(img1);


        consultaComandas(tabla1);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.add(tabla1);
        tabla1.setFillsViewportHeight(true);

        JButton botonSumar = new JButton("+");
        botonSumar.setSize(10,10);
        botonSumar.setVisible(true);
        botonSumar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            int row = tabla1.getSelectedRow();
            String valorIds = tabla1.getModel().getValueAt(row,0).toString();
            int valorId = Integer.parseInt(valorIds);

            Connection con = conectarConBD();
                try {
                    PreparedStatement query = con.prepareStatement("call procedure_cocinero_sumar (?,1);");
                    query.setInt(1,valorId);
                    query.executeQuery();
                    consultaComandas(tabla1);
                    tabla1.repaint();



                } catch (SQLException sqle) {
                    System.out.println("Error en la ejecución:"
                            + sqle.getErrorCode() + " " + sqle.getMessage());

                } finally {
                    cerrarConexion(con);
                }




            }
        });

        JButton botonRestar = new JButton("-");
        botonRestar.setSize(10,10);
        botonRestar.setVisible(true);
        botonRestar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = tabla1.getSelectedRow();
                String valorIds = tabla1.getModel().getValueAt(row,0).toString();
                int valorId = Integer.parseInt(valorIds);
                Connection con = conectarConBD();
                try {
                    PreparedStatement query = con.prepareStatement("call procedure_cocinero_sumar (?,0);");
                    query.setInt(1,valorId);
                    query.executeQuery();
                    consultaComandas(tabla1);
                    tabla1.repaint();




                } catch (SQLException sqle) {
                    System.out.println("Error en la ejecución:"
                            + sqle.getErrorCode() + " " + sqle.getMessage());

                } finally {
                    cerrarConexion(con);
                }

            }
        });

        int filaSeleccionada = tabla1.getSelectedRow();

        JPanel botonesFinales = new JPanel(new GridLayout(1,2));

        botonesFinales.add(botonSumar);
        botonesFinales.add(botonRestar);



        JPanel panelTabla = new JPanel(new GridLayout(1,0));

        panelTabla.add(scrollPane);



        panelExterno.add(labelMesa);
        panelExterno.add(panelLabel1);
        panelExterno.add(tabla1);
        panelExterno.add(botonesFinales);




        }

    private JTable consultaComandas(JTable tabla1) {
        List<Consumicion> comandas = ObtenerComandas.ObtenerComandas();

        String data[][] = {};
        String columnNames[] = {"Id","Producto", "Cantidad",};

        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        tabla1.setModel(model);


        for (Consumicion c1 : comandas){

            model.insertRow(0, new Object[]{c1.getId(),c1.getId_producto(), c1.getCantidad_pedida()});


        }
        return tabla1;
    }


}



class ImagenComandas extends JPanel{

    private Image imagen;

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        File miImagen = new File("C:\\Users\\daw20\\IdeaProjects\\proyectoRestaurante\\imagenes\\fondo_productos.jpg");
        try{
            imagen= ImageIO.read(miImagen);
        }catch (IOException e){
            System.out.println("La imagen no se encuentra");
        }
        Image imagenResultado = imagen.getScaledInstance(500,800,Image.SCALE_DEFAULT);
        g.drawImage(imagenResultado,0,0,null);
    }

}







