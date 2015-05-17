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
 * @author Megi
 */
public class IzmeniTretman extends GenerickaSistemskaOperacija {

    private Tretman tretman;

    public IzmeniTretman(Tretman tretman) {
        this.tretman = tretman;
    }

    @Override
    protected void izvrsiValidaciju() {
    }

    @Override
    protected void proveriPreduslov() throws Exception {
    }

    @Override
    protected void izvrsiKonkretnuOperaciju() throws Exception {
        broker.azuriraj(tretman);
    }

}
