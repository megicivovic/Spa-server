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
public class ObrisiTretman extends GenerickaSistemskaOperacija {

    int tretmanID;

    public ObrisiTretman(int tretmanID) {
        this.tretmanID = tretmanID;
    }

    @Override
    protected void proveriPreduslov() throws Exception {

    }

    @Override
    protected void izvrsiKonkretnuOperaciju() throws Exception {
        broker.obrisi(new Tretman(), " tretmanID=" + tretmanID);
        
    }

    @Override
    protected void izvrsiValidaciju() {
    }

}
