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
public class UlogujSe extends GenerickaSistemskaOperacija  {

    private Korisnik klijent;
    
     public UlogujSe(Korisnik korisnik) {
        this.klijent = korisnik;
    }


    @Override
    protected void proveriPreduslov() throws Exception {

    }

    @Override
    protected void izvrsiKonkretnuOperaciju() throws Exception {     
        List<GenerickiDomenskiObjekat> klijenti = broker.vratiSveObjekte(new Korisnik(), "");
        for (GenerickiDomenskiObjekat gdo : klijenti) {
            Korisnik k = (Korisnik) gdo;
            if (k.equals(klijent)) {
                klijent = k;
                break;
            }
        }

    }

    public Korisnik getKlijent() {
        return klijent;
    }

    public void setKlijent(Korisnik klijent) {
        this.klijent = klijent;
    }

    @Override
    protected void izvrsiValidaciju() {     
    }

}
 