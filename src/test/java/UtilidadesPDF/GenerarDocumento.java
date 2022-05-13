package UtilidadesPDF;

import Modelos.Cuenta;
import Modelos.Producto;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.IOException;

public class GenerarDocumento {

    public GenerarDocumento(Cuenta c1)  {

        try{

            PDDocument document = new PDDocument();
            PDPage page = new PDPage();
            document.addPage(page);

            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            PDFont font = PDType1Font.HELVETICA;
            contentStream.setFont(font,14);

            contentStream.beginText();
            contentStream.setLeading(50f);
            contentStream.newLineAtOffset(50, 700);
            contentStream.showText("ID Mesa: "+ c1.getId_mesa());


            contentStream.newLine();
            contentStream.showText("Camarero: "+c1.getNombreCamarero() +"  "+ c1.getPrimerApellido() +"  "+ c1.getSegundoApellido());

            contentStream.newLine();
            contentStream.showText("Código de Pedido: "+ c1.getCodigo());

            contentStream.newLine();
            contentStream.showText("CONSUMICIÓN: ");


            for (Producto p: c1.getListaProductos()){
                contentStream.newLine();
                contentStream.newLineAtOffset(200,0);
                contentStream.showText(p.getNombre() +".........     "+ p.getCantidadPedida() +".........    "+ p.getPrecioTotalProducto());
                contentStream.newLineAtOffset(-200,0);
            }

            contentStream.newLine();
            contentStream.showText("Total a Pagar: "+ c1.getPrecioTotal());

            contentStream.endText();
            contentStream.close();

            document.save("pdfBoxHelloWorld.pdf");
            document.close();

        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
