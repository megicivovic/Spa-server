/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.rezervacija;

import domen.GenerickiDomenskiObjekat;
import domen.Rezervacija;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import so.GenerickaSistemskaOperacija;

/**
 *
 * @author Megi
 */
public class VratiSveRezervacije extends GenerickaSistemskaOperacija {

    int tretmanID;
    int zaposleniID;
    Date vreme;
    String uslov;
    private List<Rezervacija> rezervacije;

    public VratiSveRezervacije(int tretmanID, int zaposleniID, Date vreme, String uslov) {
        this.tretmanID = tretmanID;
        this.zaposleniID = zaposleniID;
        this.vreme = vreme;
        this.uslov = uslov;
        rezervacije = new ArrayList<>();
    }

    @Override
    protected void izvrsiValidaciju() {
    }

    @Override
    protected void proveriPreduslov() throws Exception {
    }

    @Override
    protected void izvrsiKonkretnuOperaciju() throws Exception {
        List<GenerickiDomenskiObjekat> listaR = broker.vratiSveObjekte(new Rezervacija(), " JOIN tretman USING(tretmanID)"
                + "JOIN zaposleni USING(zaposleniID) JOIN klijent USING(klijentID) "+uslov);
        if (listaR != null && !listaR.isEmpty()) {
            for (GenerickiDomenskiObjekat r : listaR) {
                rezervacije.add((Rezervacija) r);
            }
        }
    }

    public List<Rezervacija> getRezervacije() {
        return rezervacije;
    }

    public void setRezervacije(List<Rezervacija> rezervacije) {
        this.rezervacije = rezervacije;
    }

}
