/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.kompanija;

import domen.Kompanija;
import so.GenerickaSistemskaOperacija;

/**
 *
 * @author Megi
 */
public class DodajKompaniju extends GenerickaSistemskaOperacija {

    private Kompanija kompanija;

    public DodajKompaniju(Kompanija kompanija) {
        this.kompanija = kompanija;
    }

    @Override
    protected void proveriPreduslov() throws Exception {

    }

    @Override
    protected void izvrsiKonkretnuOperaciju() throws Exception {
        broker.sacuvaj(kompanija);
    }

    @Override
    protected void izvrsiValidaciju() {
    }

}
