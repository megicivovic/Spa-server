/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.korisnik;

import domen.GenerickiDomenskiObjekat;
import domen.Korisnik;
import java.util.List;
import so.GenerickaSistemskaOperacija;

/**
 *
 * @author Ivana
 */
public class ValidirajKorisnickoIme extends GenerickaSistemskaOperacija {

    private boolean moze;
    private Korisnik klijent;

    public ValidirajKorisnickoIme(Korisnik korisnik) {
        this.klijent = korisnik;
    }

    @Override
    protected void proveriPreduslov() throws Exception {
    }

    @Override
    protected void izvrsiKonkretnuOperaciju() throws Exception {
        List<GenerickiDomenskiObjekat> klijenti = broker.vratiSveObjekte(new Korisnik(), "");
        for (GenerickiDomenskiObjekat generickiDomenskiObjekat : klijenti) {
            Korisnik k = (Korisnik) generickiDomenskiObjekat;
            if (klijent.getKorisnickoIme().equals(k.getKorisnickoIme())) {
                moze = false;
                return;
            }
        }
        moze = true;

    }

    public boolean isMoze() {
        return moze;
    }

    public void setMoze(boolean moze) {
        this.moze = moze;
    }

    @Override
    protected void izvrsiValidaciju() {
    }

}
