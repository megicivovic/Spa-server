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
public class VratiZaposlenePoID extends GenerickaSistemskaOperacija {

    int zaposleniID;
    private Zaposleni z;

    public VratiZaposlenePoID(int zaposleniID) {
        this.zaposleniID = zaposleniID;
        z = new Zaposleni();
    }

    @Override
    protected void izvrsiValidaciju() {
    }

    @Override
    protected void proveriPreduslov() throws Exception {
    }

    @Override
    protected void izvrsiKonkretnuOperaciju() throws Exception {
        z = (Zaposleni) broker.pretraziPoID(z, zaposleniID);
    }

    public Zaposleni vratiZaposlenog() {
        return z;
    }

}
