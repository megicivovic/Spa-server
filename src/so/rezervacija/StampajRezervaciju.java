/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.rezervacija;

import domen.Rezervacija;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import so.GenerickaSistemskaOperacija;

/**
 *
 * @author Megi
 */
public class StampajRezervaciju extends GenerickaSistemskaOperacija{

    Rezervacija r;

    public StampajRezervaciju(Rezervacija r) {
        this.r = r;
    }
    
    
    @Override
    protected void izvrsiValidaciju() {
    }

    @Override
    protected void proveriPreduslov() throws Exception {}

    @Override
    protected void izvrsiKonkretnuOperaciju() throws Exception {
      PDDocument doc = null;
        PDPage page = null;

        try {
            doc = new PDDocument();
            page = new PDPage();

            doc.addPage(page);
            PDFont font = PDType1Font.HELVETICA_BOLD;

            PDPageContentStream content = new PDPageContentStream(doc, page);
            content.beginText();
            content.setFont(font, 12);
            content.moveTextPositionByAmount(100, 700);
            content.drawString("Zakazali ste tretman u " + r.getVreme());

            content.endText();
            content.close();
            doc.save("PotvrdaRezervacije.pdf");
            doc.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
}
