/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.zaposleni;

import domen.Zaposleni;
import so.GenerickaSistemskaOperacija;

/**
 *
 * @author Megi
 */
public class DodajZaposlenog extends GenerickaSistemskaOperacija {

    private Zaposleni zaposleni;

    public DodajZaposlenog(Zaposleni zaposleni) {
        this.zaposleni = zaposleni;
    }

    @Override
    protected void proveriPreduslov() throws Exception {

    }

    @Override
    protected void izvrsiKonkretnuOperaciju() throws Exception {
        broker.sacuvaj(zaposleni);
    }

    @Override
    protected void izvrsiValidaciju() {
    }
}
