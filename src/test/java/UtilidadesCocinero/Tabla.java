package UtilidadesCocinero;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;

//https://www.youtube.com/watch?v=jPfKFm2Yfow&ab_channel=DavidPachecoJimenez
public class Tabla {
    public void ver_tabla(JTable tabla){

        DefaultTableModel tab = new DefaultTableModel(

                new Object[][]{{"1", "Pedro"}, {"2","Juan"}},
                new Object[]{"Producto","Cantidad"}


        );

        tabla.setModel(tab);
    }
}
