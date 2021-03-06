package UtilidadesPDF;

import Modelos.Cuenta;
import Modelos.Producto;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static UtilidadesBBDD.UtilidadesBBDD.cerrarConexion;
import static UtilidadesBBDD.UtilidadesBBDD.conectarConBD;

public class GenerarDocumento {

    public GenerarDocumento(Cuenta c1)  {

        Connection conn = conectarConBD();

        try{

            PreparedStatement stmt = conn.prepareStatement("select numero_mesa from mesa where id = ?");

            stmt.setInt(1,c1.getId_mesa());
            ResultSet rs = stmt.executeQuery();

            int numero_mesa = 0;

            if (rs.next()){
                numero_mesa = rs.getInt("numero_mesa");
            }




            PDDocument document = new PDDocument();
            PDPage page = new PDPage();
            document.addPage(page);

            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            String imgUrl = new File("").getAbsolutePath()+"\\imagenes\\fotoCuenta.JPG";

            PDImageXObject pdImage = PDImageXObject.createFromFile(imgUrl,document);

            contentStream.drawImage(pdImage,400,600);

            PDFont font = PDType1Font.TIMES_BOLD;
            contentStream.setFont(font,17);

            contentStream.beginText();
            contentStream.setLeading(50f);
            contentStream.newLineAtOffset(50, 700);
            contentStream.showText("Numero de Mesa: "+ numero_mesa);


            contentStream.newLine();
            contentStream.showText("Camarero: "+c1.getNombreCamarero() +"  "+ c1.getPrimerApellido() +"  "+ c1.getSegundoApellido());

            contentStream.newLine();
            contentStream.showText("C??digo de Pedido: "+ c1.getCodigo());
            contentStream.endText();

            contentStream.moveTo(50,550); //Start point coordinates
            contentStream.lineTo(550, 550); //End point coordinates
            contentStream.setLineWidth(1f);   //Line thickness
            contentStream.stroke();

            contentStream.beginText();
            contentStream.newLineAtOffset(250,500);
            contentStream.showText("CONSUMICI??N: ");
            contentStream.newLineAtOffset(-250,0);

            contentStream.newLine();
            contentStream.newLineAtOffset(75,0);
            contentStream.showText("Producto");
            contentStream.newLineAtOffset(200,0);
            contentStream.showText("Cantidad");
            contentStream.newLineAtOffset(175,0);
            contentStream.showText("Precio");

            contentStream.newLineAtOffset(-450,0);

            PDFont font2 = PDType1Font.COURIER;
            contentStream.setFont(font2,14);

            for (Producto p: c1.getListaProductos()){
                contentStream.newLine();
                contentStream.newLineAtOffset(75,0);
                contentStream.showText(p.getNombre());
                contentStream.newLineAtOffset(225,0);
                contentStream.showText(Integer.toString(p.getCantidadPedida()));
                contentStream.newLineAtOffset(160,0);
                contentStream.showText(Double.toString(p.getPrecioTotalProducto()));
                contentStream.newLineAtOffset(-460,0);

            }

            contentStream.setFont(font,17);
            contentStream.newLine();
            contentStream.newLine();
            contentStream.newLine();
            contentStream.newLine();
            contentStream.newLine();
            contentStream.newLineAtOffset(375,0);
            contentStream.showText("Total a Pagar:   ");

            contentStream.setFont(font2,14);
            contentStream.showText(Double.toString(c1.getPrecioTotal()));

            contentStream.endText();
            contentStream.close();

            document.save("pdfBoxHelloWorld.pdf");
            document.close();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            cerrarConexion(conn);
        }

    }

}
