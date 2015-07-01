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
   
    String uslov;
    private List<Rezervacija> rezervacije=new ArrayList<>();

    public VratiSveRezervacije() {
        uslov="";
    }

    public VratiSveRezervacije(String uslov) {
        this.uslov = uslov;
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

    public List<Rezervacija> vratiListuRezervacija() {
        return rezervacije;
    }

    public void setRezervacije(List<Rezervacija> rezervacije) {
        this.rezervacije = rezervacije;
    }

}
