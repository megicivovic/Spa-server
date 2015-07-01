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
public class VratiTretmanePoID extends GenerickaSistemskaOperacija {

    int tretmanID;
    private Tretman t;

    public VratiTretmanePoID(int tretmanID) {
        this.tretmanID = tretmanID;
        t = new Tretman();
    }

    @Override
    protected void izvrsiValidaciju() {
    }

    @Override
    protected void proveriPreduslov() throws Exception {
    }

    @Override
    protected void izvrsiKonkretnuOperaciju() throws Exception {
        t = (Tretman) broker.pretraziPoID(t, tretmanID,"");
    }

    public Tretman vratiTretman() {
        return t;
    }

}
