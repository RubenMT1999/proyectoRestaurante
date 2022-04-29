package UtilidadesCocinero;






import Modelos.Consumicion;
import Modelos.Mesa;
import Modelos.Pedido;
import UtilidadesBBDD.ObtenerMesas;
import UtilidadesBBDD.ObtenerPedido;
import UtilidadesBBDD.numeroMesas;


import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static UtilidadesBBDD.UtilidadesBBDD.cerrarConexion;
import static UtilidadesBBDD.UtilidadesBBDD.conectarConBD;

public class UtilidadesCocinero {
    public static void main(String[] args) {
    VentanaComanda v1 = new VentanaComanda();
        v1.setVisible(true);
        v1.setSize(new Dimension(1200, 800));
    }
}
    


class VentanaComanda extends JFrame{
    private JTable tabla1 = new JTable() ;
    private static final ImageIcon fondoPantalla = new ImageIcon(getRutaImagenFondo());




    public VentanaComanda() {



        JPanel panelExterno = crearPanelImagenFondo(new GridLayout(2,1,10,10));
        setContentPane(panelExterno);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(MAXIMIZED_BOTH);
        setVisible(true);
        setResizable(false);
        panelExterno.setOpaque(false);
        panelExterno.setBorder(BorderFactory.createEmptyBorder(170,0,0,0));




        JLabel labelMesa = new JLabel("MESA");

        List<Mesa> listaMesas = ObtenerMesas.obtenerMesas();

        JComboBox numMesa = new JComboBox<String>();

        for (Mesa m1 : listaMesas){
            numMesa.addItem(m1.getNumeroMesa());

        }



        JButton botonBuscar = new JButton("Buscar");
        botonBuscar.setSize(10,10);
        botonBuscar.setVisible(true);
        botonBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int num_mesa = (int) numMesa.getSelectedItem();
                consultaComandas(tabla1,num_mesa);
                tabla1.repaint();


            }
        });



        Font newFont = new Font("Monospaced",Font.BOLD,18);

        labelMesa.setFont(newFont);

        JPanel panelMesa = new JPanel(new GridLayout(1,1));

        panelMesa.add(labelMesa);


        JPanel panelLabel1 = new JPanel(new GridLayout(1,1,10,10));
        panelLabel1.add(numMesa);
        panelLabel1.add(botonBuscar);





        //consultaComandas(tabla1,0);

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
                    int num_mesa = (int) numMesa.getSelectedItem();
                    consultaComandas(tabla1,num_mesa);
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
                    int num_mesa = (int) numMesa.getSelectedItem();
                    consultaComandas(tabla1,num_mesa);
                    tabla1.repaint();




                } catch (SQLException sqle) {
                    System.out.println("Error en la ejecución:"
                            + sqle.getErrorCode() + " " + sqle.getMessage());

                } finally {
                    cerrarConexion(con);
                }

            }
        });

        JButton botonCuenta = new JButton("Cuenta Completada");
        botonCuenta.setSize(10,10);
        botonCuenta.setVisible(true);
        botonCuenta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int num_mesa = (int) numMesa.getSelectedItem();
                List<Pedido> listaPedidos = ObtenerPedido.obtenerPedidos();
                List<Mesa> mesa1 = listaMesas.stream().filter(mesa -> mesa.getNumeroMesa() == num_mesa).collect(Collectors.toList());
                List<Pedido> l1 = listaPedidos.stream().filter(m -> m.getId_mesa()== mesa1.get(0).getId()).filter(p->p.getPagado()==0).collect(Collectors.toList());

                if (tabla1.getRowCount() != 0 ) {
                    JOptionPane.showMessageDialog(panelExterno,
                            "Aún quedan productos, no puede poner la cuenta completada");
                }
                else {


                    Connection con = conectarConBD();
                    try {
                        CallableStatement stmt2 = con.prepareCall("{call estado_pedido(?)}");

                        stmt2.setString(1, l1.get(0).getCodigo());
                        stmt2.executeQuery();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    } finally {
                        cerrarConexion(con);
                    }
                }
            }
        });

        int filaSeleccionada = tabla1.getSelectedRow();

        JPanel botonesFinales = new JPanel(new GridLayout(1,2));

        botonesFinales.add(botonSumar);
        botonesFinales.add(botonRestar);
        botonesFinales.add(botonCuenta);



        JPanel panelTabla = new JPanel(new GridLayout(1,0));

        panelTabla.add(tabla1);




        panelExterno.add(labelMesa);
        panelExterno.add(panelLabel1);
        panelExterno.add(panelTabla);
        panelExterno.add(botonesFinales);




        }

    private JTable consultaComandas(JTable tabla1,int nummesa) {
        List<Consumicion> comandas = ObtenerComandas.ObtenerComandas(nummesa);

        String data[][] = {};
        String columnNames[] = {"Id","Producto", "Cantidad",};

        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        tabla1.setModel(model);


        for (Consumicion c1 : comandas){

            model.insertRow(0, new Object[]{c1.getId(),c1.getId_producto(), c1.getCantidad_pedida()});


        }
        return tabla1;
    }

    private JPanel crearPanelImagenFondo(GridLayout gridLayout) {
        JPanel panel = new JPanel() {
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(fondoPantalla.getImage(), 0, 0, null);


            }
        };
        return panel;
    }

    private static  String getRutaImagenFondo(){
        String ruta = new File("").getAbsolutePath();
        return ruta + "\\imagenes\\fondo_cocinero_2.jpg";
    }

}











