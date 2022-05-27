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


    


public class UtilidadesCocinero extends JFrame{
    //Aquí esta creado el jtable para que se quede como una ruta fija y se pueda utilizar la tabla modificando los datos con los botones y el fondo se quede fijo con una ruta fija
    private JTable tabla1 = new JTable() ;
    private static final ImageIcon fondoPantalla = new ImageIcon(getRutaImagenFondo());




    public UtilidadesCocinero() {


        //LLamada al metodo del panel
        JPanel panelExterno = crearPanelImagenFondo(null);

        panelExterno.setOpaque(false);
        panelExterno.setBorder(BorderFactory.createEmptyBorder(170,170,170,170));




        JLabel labelMesa = new JLabel("MESA");
        //Obtencion de datos con un metodo creado con llamada a la base de datos
        List<Mesa> listaMesas = ObtenerMesas.obtenerMesas();

        JComboBox numMesa = new JComboBox<String>();

        //Recorremos el numero de mesas y se crea una opcion por cada numero de mesa
        for (Mesa m1 : listaMesas){
            numMesa.addItem(m1.getNumeroMesa());

        }



        JButton botonBuscar = new JButton("Buscar");
        botonBuscar.setSize(10,10);
        botonBuscar.setVisible(true);
        botonBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //declaramos el numero de mesa como un entero para pasarle a la función el numero de mesa seleccionado
                int num_mesa = (int) numMesa.getSelectedItem();
                consultaComandas(tabla1,num_mesa);
                tabla1.repaint();


            }
        });



        Font newFont = new Font("Monospaced",Font.BOLD,18);

        labelMesa.setFont(newFont);
        //Panel para el label mesa
        JPanel panelMesa = new JPanel(new GridLayout(1,1));

        panelMesa.add(labelMesa);

        //Panel para el combobox y el boton de buscar
        JPanel panelLabel1 = new JPanel(new GridLayout(1,1,10,10));
        panelLabel1.add(numMesa);

        panelLabel1.add(botonBuscar);












        JButton botonRestar = new JButton("+");
        botonRestar.setSize(10,10);
        botonRestar.setVisible(true);
        botonRestar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = tabla1.getSelectedRow();
                //Seleccion en la tabla del producto para indicar el id que le vamos a pasar a la query de base de datos
                String valorIds = tabla1.getModel().getValueAt(row,0).toString();
                //Parseamos de string a entero ya que la query recibe un entero y no un String
                int valorId = Integer.parseInt(valorIds);
                Connection con = conectarConBD();
                try {
                    PreparedStatement query = con.prepareStatement("call procedure_cocinero_sumar (?,0);");
                    query.setInt(1,valorId);
                    query.executeQuery();
                    int num_mesa = (int) numMesa.getSelectedItem();
                    int sel = row - 1 ;
                    //se vuelve a llamar a la funcion de consultar comandas para mostrar los datos actualizados
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
                // Filtramos las mesas
                List<Mesa> mesa1 = listaMesas.stream().filter(mesa -> mesa.getNumeroMesa() == num_mesa).collect(Collectors.toList());
                //Filtramos los pedidos por mesas
                List<Pedido> l1 = listaPedidos.stream().filter(m -> m.getId_mesa()== mesa1.get(0).getId()).filter(p->p.getPagado()==0).collect(Collectors.toList());

                int filas = tabla1.getRowCount();
                boolean existe = true;

                for (int i = 0; i<filas; i++){
                    String valor1 = tabla1.getValueAt(i, 2).toString();
                    if (!valor1.equals(tabla1.getValueAt(i,3).toString())){
                        existe = false;
                    }
                }

                if (existe == true){Connection con = conectarConBD();
                    try {
                        CallableStatement stmt2 = con.prepareCall("{call estado_pedido(?)}");

                        stmt2.setString(1, listaPedidos.get(0).getCodigo());
                        stmt2.executeQuery();
                        JOptionPane.showMessageDialog(panelExterno,
                                "Cuenta completada correctamente");
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    } finally {
                        cerrarConexion(con);
                    }


                }
             else {
                 JOptionPane.showMessageDialog(panelExterno,
                         "Hasta que no esten los servidos igual a los pedidos no se puede completar");
             }

            }
        });

        int filaSeleccionada = tabla1.getSelectedRow();

        JPanel botonesFinales = new JPanel(new GridLayout(1,2));


        botonesFinales.add(botonRestar);
        botonesFinales.add(botonCuenta);



        JPanel panelTabla = new JPanel(new GridLayout(0,1));

        panelTabla.add(tabla1);
        panelTabla.add(tabla1.getTableHeader(),BorderLayout.CENTER);
        panelTabla.add(tabla1,BorderLayout.SOUTH);




        panelExterno.add(labelMesa);
        panelExterno.add(panelLabel1);
        panelExterno.add(new JLabel("", SwingConstants.CENTER));
        panelExterno.add(panelTabla);
        panelExterno.add(new JLabel("", SwingConstants.CENTER));
        panelExterno.add(botonesFinales);

        setContentPane(panelExterno);
        setResizable(false);
        setLocationRelativeTo(null);
        setExtendedState(MAXIMIZED_BOTH);
        setVisible(true);
        setResizable(false);
        setBounds(450,50,600,800);

        }

    private JTable consultaComandas(JTable tabla1,int nummesa) {
        //Traemos las comandas de la base de datos pasandole el numero de mesa
        List<Consumicion> comandas = ObtenerComandas.ObtenerComandas(nummesa);
        //Le damos las propiedades a la mesa con los nombres de las columnas, donde vamos a mostrar los datos
        String data[][] = {};
        String columnNames[] = {"Id","Producto", "Servida","Pedida"};

        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        tabla1.setModel(model);

        //Insercion de filas recorriendo todas las comandas, y se crea una por cada comanda
        for (Consumicion c1 : comandas){

            model.insertRow(0, new Object[]{c1.getId(),c1.getId_producto(), c1.getCantidad_pedida(), c1.getCantidad_servida()});


        }
        return tabla1;
    }



    private JPanel crearPanelImagenFondo(GridLayout gridLayout) {
        JPanel panel = null;
        if (gridLayout == null){
            panel = new JPanel(){  public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(fondoPantalla.getImage(), 0, 0, null);


            }};
        }else{
            panel = new JPanel(gridLayout){  public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(fondoPantalla.getImage(), 0, 0, null);


            }};
        }
        return panel;
    }

    private static  String getRutaImagenFondo(){
        String ruta = new File("").getAbsolutePath();
        return ruta + "\\imagenes\\fondo_cocinero_2.jpg";
    }
    public static void main(String[] args) {
        UtilidadesCocinero v1 = new UtilidadesCocinero();
        v1.setVisible(true);
        v1.setSize(new Dimension(600, 800));
    }

}











