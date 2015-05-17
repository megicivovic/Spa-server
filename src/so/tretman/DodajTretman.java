/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.tretman;

import domen.Tretman;
import so.GenerickaSistemskaOperacija;

/**
 *
 * @author Ivana
 */
public class DodajTretman extends GenerickaSistemskaOperacija {

    private Tretman tretman;
    int tretmanID;

    public DodajTretman(Tretman tretman) {
        this.tretman = tretman;
    }

    @Override
    protected void proveriPreduslov() throws Exception {

    }

    @Override
    protected void izvrsiKonkretnuOperaciju() throws Exception {
        tretmanID = broker.sacuvaj(tretman);

    }

    @Override
    protected void izvrsiValidaciju() {
    }

    public int vratiIDTretmana() {
        return tretmanID;
    }

}
