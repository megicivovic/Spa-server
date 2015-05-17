/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.korisnik;

import domen.Korisnik;
import so.GenerickaSistemskaOperacija;

/**
 *
 * @author Ivana
 */
public class DodajKorisnika extends GenerickaSistemskaOperacija {

    private Korisnik korisnik;

    public DodajKorisnika(Korisnik korisnik) {
        this.korisnik = korisnik;
    }

    @Override
    protected void proveriPreduslov() throws Exception {

    }

    @Override
    protected void izvrsiKonkretnuOperaciju() throws Exception {
        broker.sacuvaj(korisnik);
    }

    @Override
    protected void izvrsiValidaciju() {
    }

}
