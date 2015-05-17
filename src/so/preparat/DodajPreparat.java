/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.preparat;

import domen.Preparat;
import so.GenerickaSistemskaOperacija;

/**
 *
 * @author Ivana
 */
public class DodajPreparat extends GenerickaSistemskaOperacija {

    private Preparat preparat;

    public DodajPreparat(Preparat preparat) {
        this.preparat = preparat;
    }

    @Override
    protected void proveriPreduslov() throws Exception {

    }

    @Override
    protected void izvrsiKonkretnuOperaciju() throws Exception {
        broker.sacuvaj(preparat);
    }

    @Override
    protected void izvrsiValidaciju() {
    }

}
