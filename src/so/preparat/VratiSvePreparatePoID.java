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
 * @author Megi
 */
public class VratiSvePreparatePoID extends GenerickaSistemskaOperacija {

    int preparatID;
    private Preparat p;

    public VratiSvePreparatePoID(int preparatID) {
        this.preparatID = preparatID;
        p = new Preparat();
    }

    @Override
    protected void izvrsiValidaciju() {
    }

    @Override
    protected void proveriPreduslov() throws Exception {
    }

    @Override
    protected void izvrsiKonkretnuOperaciju() throws Exception {
        p = (Preparat) broker.pretraziPoID(p, preparatID);
    }

    public Preparat vratiPreparat() {
        return p;
    }

}
