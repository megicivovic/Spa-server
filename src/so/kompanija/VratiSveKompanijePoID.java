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
public class VratiSveKompanijePoID extends GenerickaSistemskaOperacija {

    int kompanijaID;
    private Kompanija k;

    public VratiSveKompanijePoID(int kompanijaID) {
        this.kompanijaID = kompanijaID;
        k = new Kompanija();
    }

    @Override
    protected void izvrsiValidaciju() {
    }

    @Override
    protected void proveriPreduslov() throws Exception {
    }

    @Override
    protected void izvrsiKonkretnuOperaciju() throws Exception {
        k = (Kompanija) broker.pretraziPoID(k, kompanijaID,"");
    }

    public Kompanija vratiKompaniju() {
        return k;
    }

}
