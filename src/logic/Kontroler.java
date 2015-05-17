/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import db.DatabaseBroker;
import domen.GenerickiDomenskiObjekat;
import domen.Kompanija;
import domen.Korisnik;
import domen.Preparat;
import domen.Raspored;
import domen.Rezervacija;
import domen.Tretman;
import domen.TretmanPreparati;
import domen.Zaposleni;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import model.sesija.Sesija;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import so.GenerickaSistemskaOperacija;
import so.kompanija.DodajKompaniju;
import so.kompanija.VratiSveKompanije;
import so.kompanija.VratiSveKompanijePoID;
import so.korisnik.DodajKorisnika;
import so.korisnik.VratiSveKorisnike;
import so.preparat.DodajPreparat;
import so.preparat.VratiSvePreparate;
import so.preparat.VratiSvePreparatePoID;
import so.tretmanpreparati.VratiSvePreparateTretmana;
import so.raspored.DodajRaspored;
import so.raspored.ObrisiRasporedeTretmana;
import so.raspored.VratiSveRasporede;
import so.rezervacija.DodajRezervaciju;
import so.rezervacija.VratiSveRezervacije;
import so.tretman.DodajTretman;
import so.tretman.IzmeniTretman;
import so.tretman.ObrisiTretman;
import so.tretman.VratiSveTretmane;
import so.tretman.VratiTretmanePoID;
import so.tretmanpreparati.ObrisiPreparateTretmana;
import so.tretmanpreparati.DodajPreparateTretmana;
import so.zaposleni.DodajZaposlenog;
import so.zaposleni.VratiSveZaposlene;
import so.zaposleni.VratiZaposlenePoID;

/**
 *
 * @author student1
 */
public class Kontroler {

    DatabaseBroker dbbr;
    private static Kontroler instanca;

    public Kontroler() throws ClassNotFoundException, SQLException, IOException {
        dbbr = DatabaseBroker.getInstance();
        
        

    }

    public static Kontroler vratiInstancu() throws ClassNotFoundException, SQLException, IOException {
        if (instanca == null) {
            instanca = new Kontroler();
        }
        return instanca;
    }

    public void dodajUSesisju(Korisnik k) {
        Sesija.vratiInstancu().getMapa().put("korisnik", k);
    }

    public int sacuvajTretman(Tretman tretman) throws SQLException, Exception {
        GenerickaSistemskaOperacija so = new DodajTretman(tretman);
        so.izvrsiSO();
        return ((DodajTretman) so).vratiIDTretmana();
    }

    public List<GenerickiDomenskiObjekat> vratiSvePreparate() throws ClassNotFoundException, SQLException, IOException, Exception {
        GenerickaSistemskaOperacija so = new VratiSvePreparate();
        so.izvrsiSO();
        return ((VratiSvePreparate) so).vratiListuPreparata();
    }

    public void izmeniTretman(Tretman t) throws Exception {
        GenerickaSistemskaOperacija so = new IzmeniTretman(t);
        so.izvrsiSO();
    }

    public List<GenerickiDomenskiObjekat> vratiSveTretmane() throws ClassNotFoundException, SQLException, IOException, Exception {
        GenerickaSistemskaOperacija so = new VratiSveTretmane();
        so.izvrsiSO();
        return ((VratiSveTretmane) so).vratiListuTretmana();
    }

    public List<GenerickiDomenskiObjekat> vratiSvePreparateTretmana(int tretmanID) throws ClassNotFoundException, SQLException, IOException, Exception {
        GenerickaSistemskaOperacija so = new VratiSvePreparateTretmana(tretmanID);
        so.izvrsiSO();
        return ((VratiSvePreparateTretmana) so).vratiListuPreparataTretmana();

    }

    public void ubaciPreparateTretmana(List<TretmanPreparati> ltp) throws ClassNotFoundException, SQLException, IOException, Exception {
        GenerickaSistemskaOperacija so = new DodajPreparateTretmana(ltp);
        so.izvrsiSO();
    }

    public void obrisiPreparateTretmana(int tretmanID) throws Exception {
        GenerickaSistemskaOperacija so = new ObrisiPreparateTretmana(tretmanID);
        so.izvrsiSO();
    }

    public List<GenerickiDomenskiObjekat> vratiKorisnike() throws ClassNotFoundException, SQLException, IOException, Exception {
        GenerickaSistemskaOperacija so = new VratiSveKorisnike();
        so.izvrsiSO();
        return ((VratiSveKorisnike) so).vratiListuKorisnika();
    }

    public void unesiKorisnika(Korisnik k) throws ClassNotFoundException, SQLException, IOException, Exception {
        GenerickaSistemskaOperacija so = new DodajKorisnika(k);
        so.izvrsiSO();
    }

    public void unesiKompaniju(Kompanija pp) throws ClassNotFoundException, SQLException, IOException, Exception {
        GenerickaSistemskaOperacija so = new DodajKompaniju(pp);
        so.izvrsiSO();
    }

    public void unesiPreparat(Preparat p) throws ClassNotFoundException, SQLException, IOException, Exception {
        GenerickaSistemskaOperacija so = new DodajPreparat(p);
        so.izvrsiSO();
    }

    public List<GenerickiDomenskiObjekat> vratiSveKompanije() throws ClassNotFoundException, SQLException, IOException, Exception {
        GenerickaSistemskaOperacija so = new VratiSveKompanije();
        so.izvrsiSO();
        return ((VratiSveKompanije) so).vratiListuKompanija();
    }

    public void unesiZaposlenog(Zaposleni z) throws ClassNotFoundException, SQLException, IOException, Exception {
        GenerickaSistemskaOperacija so = new DodajZaposlenog(z);
        so.izvrsiSO();
    }

    public void obrisiTretman(int tretmanID) throws ClassNotFoundException, SQLException, IOException, Exception {
        GenerickaSistemskaOperacija so = new ObrisiTretman(tretmanID);
        so.izvrsiSO();
    }

    public List<GenerickiDomenskiObjekat> vratiSveZaposlene() throws ClassNotFoundException, SQLException, IOException, Exception {
        GenerickaSistemskaOperacija so = new VratiSveZaposlene();
        so.izvrsiSO();
        return ((VratiSveZaposlene) so).vratiListuZaposlenih();
    }

    public void sacuvajRaspored(Raspored r) throws ClassNotFoundException, SQLException, IOException, Exception {
        GenerickaSistemskaOperacija so = new DodajRaspored(r);
        so.izvrsiSO();
    }

    public List<GenerickiDomenskiObjekat> vratiRasporede() throws ClassNotFoundException, SQLException, IOException, Exception {
        GenerickaSistemskaOperacija so = new VratiSveRasporede("");
        so.izvrsiSO();
        return ((VratiSveRasporede) so).vratiListuRasporeda();
    }

    public GenerickiDomenskiObjekat vratiSvePreparatePoID(int preparatID) throws Exception {
        GenerickaSistemskaOperacija so = new VratiSvePreparatePoID(preparatID);
        so.izvrsiSO();
        return ((VratiSvePreparatePoID) so).vratiPreparat();

    }

    public GenerickiDomenskiObjekat vratiKompanijePoID(int kompanijaID) throws Exception {
        GenerickaSistemskaOperacija so = new VratiSveKompanijePoID(kompanijaID);
        so.izvrsiSO();
        return ((VratiSveKompanijePoID) so).vratiKompaniju();
    }

    public void obrisiRasporede(int tretmanID) throws Exception {
        GenerickaSistemskaOperacija so = new ObrisiRasporedeTretmana(tretmanID);
        so.izvrsiSO();
    }

    public Tretman vratiTretmanePoID(int tretmanID) throws Exception {
        GenerickaSistemskaOperacija so = new VratiTretmanePoID(tretmanID);
        so.izvrsiSO();
        return ((VratiTretmanePoID) so).vratiTretman();
    }

    public Zaposleni vratiZaposlenePoID(int zaposleniID) throws Exception {
        GenerickaSistemskaOperacija so = new VratiZaposlenePoID(zaposleniID);
        so.izvrsiSO();
        return ((VratiZaposlenePoID) so).vratiZaposlenog();
    }

    public boolean proveriRaspolozivost(int tretmanID, int zaposleniID, Date vreme) throws Exception {
        boolean raspolozivost = true;

        GenerickaSistemskaOperacija so = new VratiSveRezervacije(tretmanID, zaposleniID, vreme, " WHERE tretmanID = " + tretmanID + " AND zaposleniID = " + zaposleniID);
        so.izvrsiSO();
        List<Rezervacija> rezervacije = ((VratiSveRezervacije) so).getRezervacije();
        if (rezervacije.size()==0){
        return true;
        }
        GenerickaSistemskaOperacija vsr = new VratiSveRasporede(" WHERE tretmanID= " + tretmanID + " AND zaposleniID = " + zaposleniID);
        vsr.izvrsiSO();
        int brTermina = ((Raspored) ((VratiSveRasporede) vsr).vratiListuRasporeda().get(0)).getBrojTermina();

        if (rezervacije != null) {
            if (rezervacije.size() >= brTermina) {
                raspolozivost = false;
            }
        }
       

        GenerickaSistemskaOperacija soZ = new VratiSveRezervacije(tretmanID, zaposleniID, vreme, " WHERE zaposleniID = " + zaposleniID);
        soZ.izvrsiSO();
        List<Rezervacija> rezervacijeZaposlenog = ((VratiSveRezervacije) so).getRezervacije();

        if (rezervacijeZaposlenog != null) {
            for (Rezervacija rezervacija : rezervacijeZaposlenog) {
                //vreme zavrsetka tretmana
                Tretman t = new VratiTretmanePoID(rezervacija.getTretmanID()).vratiTretman();
                Calendar cal = new GregorianCalendar();
                cal.setTime(rezervacija.getVreme());
                cal.add(Calendar.MINUTE, t.getTrajanjeUMin());

                //trazeno vreme
                Calendar vremePocetka = new GregorianCalendar();
                vremePocetka.setTime(vreme);

                Calendar calVremeZavrsetka = new GregorianCalendar();
                calVremeZavrsetka.setTime(rezervacija.getVreme());
                calVremeZavrsetka.add(Calendar.MINUTE, t.getTrajanjeUMin());

                if (!cal.before(vremePocetka)) {
                    raspolozivost = false;
                }
            }
        }
        return raspolozivost;
    }

    public void sacuvajRezervaciju(Rezervacija r) throws Exception {
        GenerickaSistemskaOperacija so = new DodajRezervaciju(r);
        so.izvrsiSO();
    }

    public void stampajPDF(Rezervacija r) {
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

    public Korisnik vratiKorisnikaIzSesije() {
       return (Korisnik) Sesija.vratiInstancu().getMapa().get("korisnik");
    }
}
