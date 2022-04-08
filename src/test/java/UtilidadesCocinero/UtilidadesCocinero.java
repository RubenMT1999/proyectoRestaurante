package UtilidadesCocinero;





import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class UtilidadesCocinero {
    public static void main(String[] args) {
    VentanaComanda v1 = new VentanaComanda();
        v1.setVisible(true);
        v1.setSize(new Dimension(510, 870));
    }
}
    


class VentanaComanda extends JFrame{
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
        numMesa.addItem("6");

        JButton botonBuscar = new JButton("Buscar");


        Font newFont = new Font("Monospaced",Font.BOLD,18);

        labelMesa.setFont(newFont);



        panelExterno.add(labelMesa);

        JPanel panelLabel1 = new JPanel(new GridLayout(1,2,10,10));
        panelLabel1.add(numMesa);
        panelLabel1.add(botonBuscar);
        panelExterno.add(panelLabel1);


        ImagenComandas img1 = new ImagenComandas();
        img1.add(panelExterno);
        add(img1);






    }

}

class ImagenComandas extends JPanel{

    private Image imagen;

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        File miImagen = new File("C:\\Users\\dragu\\Desktop\\proyectoRestaurante\\imagenes\\fondo_productos.jpg");
        try{
            imagen= ImageIO.read(miImagen);
        }catch (IOException e){
            System.out.println("La imagen no se encuentra");
        }
        Image imagenResultado = imagen.getScaledInstance(500,800,Image.SCALE_DEFAULT);
        g.drawImage(imagenResultado,0,0,null);
    }

}







