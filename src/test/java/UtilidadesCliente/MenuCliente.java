package UtilidadesCliente;






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

public class MenuCliente {
    public static void main(String[] args) {
        VentanaMenu ven1 = new VentanaMenu();
        ven1.setVisible(true);
        ven1.setSize(new Dimension(510, 1284));

    } }

    class VentanaMenu extends JFrame {
        public VentanaMenu() {

            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setExtendedState(MAXIMIZED_BOTH);
            setVisible(true);

            JTabbedPane pestañas = new JTabbedPane();


            //Componentes del panel1
            JPanel panel1 = new ImagenFondoMenu();
            //Añadimos un nombre de la pestaña y el panel
            pestañas.addTab("Bebidas", panel1);


            JPanel panel2 = new ImagenFondoMenu();
            pestañas.addTab("Tapas", panel2);

            JPanel panel3 = new ImagenFondoMenu();
            pestañas.addTab("Postres", panel3);


            getContentPane().add(pestañas);


        }

    }


    class ImagenFondoMenu extends JPanel {

        private Image imagen;

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            File miImagen = new File("C:\\Users\\daw20\\IdeaProjects\\proyectoRestaurante\\imagenes\\carta_menu.jpg");
            try {
                imagen = ImageIO.read(miImagen);
            } catch (IOException e) {
                System.out.println("La imagen no se encuentra");
            }
            Image imagenResultado = imagen.getScaledInstance(500, 800, Image.SCALE_DEFAULT);
            g.drawImage(imagenResultado, 0, 0, null);
        }

    }

