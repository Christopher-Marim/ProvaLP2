/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.ArrayList;
import model.Venda;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

/**
 *
 * @author chris
 */
public class ManipularArquivoControl {

    public static void arquivoSalvarTxt(String conteudo) throws FileNotFoundException {

        PrintWriter pw = new PrintWriter(new FileOutputStream("carrinho.txt"));
        pw.write(conteudo);
        pw.flush();
        pw.close();

    }

    public static void arquivoPDF(ArrayList<Venda> vendas, double qtdTotal) throws IOException {
        try (PDDocument doc = new PDDocument()) {

            PDPage myPage = new PDPage();
            doc.addPage(myPage);

            try (PDPageContentStream cont = new PDPageContentStream(doc, myPage)) {

                cont.beginText();

                cont.setFont(PDType1Font.TIMES_ROMAN, 12);
                cont.setLeading(14.5f);

                cont.newLineAtOffset(25, 700);

                String lineTitle = "Carrinho Mercado";
                cont.showText(lineTitle);
                cont.newLine();
                cont.newLine();

                String lineTabela = "ProdutoNome  preço   quantidade ";
                cont.showText(lineTabela);
                cont.newLine();
                for (Venda venda : vendas) {
                    String line1 = "" + venda.getNome() + "                  " + venda.getPreco() + "           " + venda.getQtd() + "";
                    cont.showText(line1);
                    cont.newLine();
                }
                cont.newLine();
                String line1 = "Total:" + qtdTotal + " ";
                cont.showText(line1);
                cont.newLine();

                cont.endText();
            }

            doc.save("carrinho.pdf");
        }
    }
}
