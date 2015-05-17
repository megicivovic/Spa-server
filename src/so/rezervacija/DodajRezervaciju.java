/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.rezervacija;

import domen.Rezervacija;
import so.GenerickaSistemskaOperacija;

/**
 *
 * @author Megi
 */
public class DodajRezervaciju extends GenerickaSistemskaOperacija {

    Rezervacija rezervacija;

    public DodajRezervaciju(Rezervacija rezervacija) {
        this.rezervacija = rezervacija;
    }

    @Override
    protected void izvrsiValidaciju() {
    }

    @Override
    protected void proveriPreduslov() throws Exception {
    }

    @Override
    protected void izvrsiKonkretnuOperaciju() throws Exception {
        broker.sacuvaj(rezervacija);
    }

}
