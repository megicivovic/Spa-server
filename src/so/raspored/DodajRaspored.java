/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.raspored;

import domen.Raspored;
import so.GenerickaSistemskaOperacija;

/**
 *
 * @author Megi
 */
public class DodajRaspored extends GenerickaSistemskaOperacija {

    private Raspored raspored;

    public DodajRaspored(Raspored raspored) {
        this.raspored = raspored;
    }

    @Override
    protected void proveriPreduslov() throws Exception {

    }

    @Override
    protected void izvrsiKonkretnuOperaciju() throws Exception {
        broker.sacuvaj(raspored);
    }

    @Override
    protected void izvrsiValidaciju() {
    }
}
