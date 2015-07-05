/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.rezervacija;

import domen.Rezervacija;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import so.GenerickaSistemskaOperacija;

/**
 *
 * @author Megi
 */
public class StampajRezervaciju extends GenerickaSistemskaOperacija {

    Rezervacija r;

    public StampajRezervaciju(Rezervacija r) {
        this.r = r;
    }

    @Override
    protected void izvrsiValidaciju() {
    }

    @Override
    protected void proveriPreduslov() throws Exception {
    }

    @Override
    protected void izvrsiKonkretnuOperaciju() throws Exception {
        PDDocument doc = null;
        PDPage page = null;

        try {
            doc = new PDDocument();
            page = new PDPage();

            doc.addPage(page);
            PDFont pdfFont = PDType1Font.HELVETICA_BOLD;
            float fontSize = 25;
            float leading = 1.5f * fontSize;

            PDPageContentStream contentStream = new PDPageContentStream(doc, page);

            PDRectangle mediabox = page.findMediaBox();
            float margin = 72;
            float width = mediabox.getWidth() - 2 * margin;
            float startX = mediabox.getLowerLeftX() + margin;
            float startY = mediabox.getUpperRightY() - margin;

            String text = "Izvrsili ste rezervaciju za tretman " + r.getTretman()
                    + ", vreme rezervacije:" + new SimpleDateFormat("YYYY-MM-dd HH:mm").format(r.getVreme()) + " zaposleni koji ce vrsiti tretman:" 
                    + r.getZaposleni().getImePrezime();
            List<String> lines = new ArrayList<String>();
            int lastSpace = -1;
            while (text.length() > 0) {
                int spaceIndex = text.indexOf(' ', lastSpace + 1);
                if (spaceIndex < 0) {
                    lines.add(text);
                    text = "";
                } else {
                    String subString = text.substring(0, spaceIndex);
                    float size = fontSize * pdfFont.getStringWidth(subString) / 1000;
                    if (size > width) {
                        if (lastSpace < 0) // So we have a word longer than the line... draw it anyways
                        {
                            lastSpace = spaceIndex;
                        }
                        subString = text.substring(0, lastSpace);
                        lines.add(subString);
                        text = text.substring(lastSpace).trim();
                        lastSpace = -1;
                    } else {
                        lastSpace = spaceIndex;
                    }
                }
            }

            contentStream.beginText();
            contentStream.setFont(pdfFont, fontSize);
            contentStream.moveTextPositionByAmount(startX, startY);
            for (String line : lines) {
                contentStream.drawString(line);
                contentStream.moveTextPositionByAmount(0, -leading);
            }
            contentStream.endText();
            contentStream.close();

         
            doc.save("PotvrdaRezervacije.pdf");

            if (Desktop.isDesktopSupported()) {
                try {
                    File myFile = new File("../ServerProjekat/PotvrdaRezervacije.pdf");
                    Desktop.getDesktop().open(myFile);
                } catch (IOException ex) {
                    // no application registered for PDFs
                }
            }

            doc.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
