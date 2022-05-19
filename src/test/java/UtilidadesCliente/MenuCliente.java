package UtilidadesCliente;
import Modelos.Carta;
import Modelos.Categoria;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;




    public class MenuCliente extends JFrame {
        public MenuCliente() {



            JTabbedPane pestañas = new JTabbedPane();

            // Nos traemos todos el menu y lo clasificamos por tipos de categoria
            List<Carta> carta = ObtenerProductos.obtenerProductos();
            Map<Categoria, List<Carta>> ProductoPorTipo = carta.stream().collect(Collectors.groupingBy(Carta::getCategoria));


            //Componentes del panel1 para las bebidas
            JPanel panel1 = new ImagenFondoMenu();
            //Añadimos un nombre de la pestaña y el panel

            JPanel panelProductosBebidas = new JPanel(new GridLayout(0, 1));
            panelProductosBebidas.setBorder(BorderFactory.createEmptyBorder(220, 0, 0, 0));
            panelProductosBebidas.setOpaque(false);


            //Recorremos todos los productos por categorias y enseñamos solo las que pertenecen a bebidas
            for (Categoria cat : ProductoPorTipo.keySet()) {
                for (Carta c : ProductoPorTipo.get(cat)) {
                    if (c.getCategoria() == Categoria.bebida) {
                        JLabel nombreProducto = new JLabel(c.getNombre());
                        nombreProducto.setForeground(Color.BLACK);
                        nombreProducto.setFont(new Font("Comic Sans", Font.PLAIN, 18));
                        JLabel precioProducto = new JLabel(c.getPrecio().toString());
                        precioProducto.setForeground(Color.BLACK);
                        precioProducto.setFont(new Font("Comic Sans", Font.PLAIN, 18));
                        panelProductosBebidas.add(nombreProducto);
                        panelProductosBebidas.add(precioProducto);
                    }
                }
            }


            panel1.add(panelProductosBebidas);
            pestañas.addTab("Bebidas", panel1);


            JPanel panel2 = new ImagenFondoMenu();

            JPanel panelProductosEntrantes = new JPanel(new GridLayout(0, 1));
            panelProductosEntrantes.setBorder(BorderFactory.createEmptyBorder(220, 0, 0, 0));
            panelProductosEntrantes.setOpaque(false);

            for (Categoria cat : ProductoPorTipo.keySet()) {
                for (Carta c : ProductoPorTipo.get(cat)) {
                    if (c.getCategoria() == Categoria.entrante) {
                        JLabel nombreProducto = new JLabel(c.getNombre());
                        nombreProducto.setForeground(Color.BLACK);
                        nombreProducto.setFont(new Font("Comic Sans", Font.PLAIN, 18));
                        JLabel precioProducto = new JLabel(c.getPrecio().toString());
                        precioProducto.setForeground(Color.BLACK);
                        precioProducto.setFont(new Font("Comic Sans", Font.PLAIN, 18));
                        panelProductosEntrantes.add(nombreProducto);
                        panelProductosEntrantes.add(precioProducto);
                    }
                }
            }


            panel2.add(panelProductosEntrantes);
            pestañas.addTab("Entrantes", panel2);

            JPanel panel3 = new ImagenFondoMenu();

            JPanel panelProductosPostres = new JPanel(new GridLayout(0, 1));
            panelProductosPostres.setBorder(BorderFactory.createEmptyBorder(220, 0, 0, 0));
            panelProductosPostres.setOpaque(false);

            for (Categoria cat : ProductoPorTipo.keySet()) {
                for (Carta c : ProductoPorTipo.get(cat)) {
                    if (c.getCategoria() == Categoria.postre) {
                        JLabel nombreProducto = new JLabel(c.getNombre());
                        nombreProducto.setForeground(Color.BLACK);
                        nombreProducto.setFont(new Font("Comic Sans", Font.PLAIN, 18));
                        JLabel precioProducto = new JLabel(c.getPrecio().toString());
                        precioProducto.setForeground(Color.BLACK);
                        precioProducto.setFont(new Font("Comic Sans", Font.PLAIN, 18));
                        panelProductosPostres.add(nombreProducto);
                        panelProductosPostres.add(precioProducto);
                    }
                }
            }


            panel3.add(panelProductosPostres);
            pestañas.addTab("Postres", panel3);

            JPanel panel4 = new ImagenFondoMenu();

            JPanel panelProductosCarne = new JPanel(new GridLayout(0, 1));
            panelProductosCarne.setBorder(BorderFactory.createEmptyBorder(220, 0, 0, 0));
            panelProductosCarne.setOpaque(false);

            for (Categoria cat : ProductoPorTipo.keySet()) {
                for (Carta c : ProductoPorTipo.get(cat)) {
                    if (c.getCategoria() == Categoria.carne) {
                        JLabel nombreProducto = new JLabel(c.getNombre());
                        nombreProducto.setForeground(Color.BLACK);
                        nombreProducto.setFont(new Font("Comic Sans", Font.PLAIN, 18));
                        JLabel precioProducto = new JLabel(c.getPrecio().toString());
                        precioProducto.setForeground(Color.BLACK);
                        precioProducto.setFont(new Font("Comic Sans", Font.PLAIN, 18));
                        panelProductosCarne.add(nombreProducto);
                        panelProductosCarne.add(precioProducto);
                    }
                }
            }


            panel4.add(panelProductosCarne);
            pestañas.addTab("Carnes", panel4);

            JPanel panel5 = new ImagenFondoMenu();

            JPanel panelProductosPescado = new JPanel(new GridLayout(0, 1));
            panelProductosPescado.setBorder(BorderFactory.createEmptyBorder(220, 0, 0, 0));
            panelProductosPescado.setOpaque(false);

            for (Categoria cat : ProductoPorTipo.keySet()) {
                for (Carta c : ProductoPorTipo.get(cat)) {
                    if (c.getCategoria() == Categoria.pescado) {
                        JLabel nombreProducto = new JLabel(c.getNombre());
                        nombreProducto.setForeground(Color.BLACK);
                        nombreProducto.setFont(new Font("Comic Sans", Font.PLAIN, 18));
                        JLabel precioProducto = new JLabel(c.getPrecio().toString());
                        precioProducto.setForeground(Color.BLACK);
                        precioProducto.setFont(new Font("Comic Sans", Font.PLAIN, 18));
                        panelProductosPescado.add(nombreProducto);
                        panelProductosPescado.add(precioProducto);
                    }
                }
            }


            panel5.add(panelProductosPescado);
            pestañas.addTab("Pescados", panel5);

            JPanel panel6 = new ImagenFondoMenu();

            JPanel panelProductosPasta = new JPanel(new GridLayout(0, 1));
            panelProductosPasta.setBorder(BorderFactory.createEmptyBorder(220, 0, 0, 0));
            panelProductosPasta.setOpaque(false);

            for (Categoria cat : ProductoPorTipo.keySet()) {
                for (Carta c : ProductoPorTipo.get(cat)) {
                    if (c.getCategoria() == Categoria.pasta) {
                        JLabel nombreProducto = new JLabel(c.getNombre());
                        nombreProducto.setForeground(Color.BLACK);
                        nombreProducto.setFont(new Font("Comic Sans", Font.PLAIN, 18));
                        JLabel precioProducto = new JLabel(c.getPrecio().toString());
                        precioProducto.setForeground(Color.BLACK);
                        precioProducto.setFont(new Font("Comic Sans", Font.PLAIN, 18));
                        panelProductosPasta.add(nombreProducto);
                        panelProductosPasta.add(precioProducto);
                    }
                }
            }


            panel6.add(panelProductosPasta);
            pestañas.addTab("Pastas", panel6);

            JPanel panel7 = new ImagenFondoMenu();

            JPanel panelProductosVegetariano = new JPanel(new GridLayout(0, 1));
            panelProductosVegetariano.setBorder(BorderFactory.createEmptyBorder(220, 0, 0, 0));
            panelProductosVegetariano.setOpaque(false);

            for (Categoria cat : ProductoPorTipo.keySet()) {
                for (Carta c : ProductoPorTipo.get(cat)) {
                    if (c.getCategoria() == Categoria.vegetariano) {
                        JLabel nombreProducto = new JLabel(c.getNombre());
                        nombreProducto.setForeground(Color.BLACK);
                        nombreProducto.setFont(new Font("Comic Sans", Font.PLAIN, 18));
                        JLabel precioProducto = new JLabel(c.getPrecio().toString());
                        precioProducto.setForeground(Color.BLACK);
                        precioProducto.setFont(new Font("Comic Sans", Font.PLAIN, 18));
                        panelProductosVegetariano.add(nombreProducto);
                        panelProductosVegetariano.add(precioProducto);
                    }
                }
            }


            panel7.add(panelProductosVegetariano);
            pestañas.addTab("Vegetariano", panel7);


            getContentPane().add(pestañas);
            setResizable(false);
            setLocationRelativeTo(null);
            setExtendedState(MAXIMIZED_BOTH);
            setVisible(true);
            pack();
            setBounds(500,25,510,800);


        }

        public static void main(String[] args) {
            MenuCliente ven1 = new MenuCliente();
            ven1.setVisible(true);
            ven1.setSize(510, 800);



        }
    }



    class ImagenFondoMenu extends JPanel {

        private Image imagen;

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            String ruta = new File("").getAbsolutePath();
            File miImagen = new File(ruta+"\\imagenes\\carta_menu.jpg");
            try {
                imagen = ImageIO.read(miImagen);
            } catch (IOException e) {
                System.out.println("La imagen no se encuentra");
            }
            Image imagenResultado = imagen.getScaledInstance(500, 800, Image.SCALE_DEFAULT);
            g.drawImage(imagenResultado, 0, 0, null);
        }

    }
