/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.raspored;

import domen.Raspored;
import domen.Tretman;
import domen.TretmanPreparati;
import so.GenerickaSistemskaOperacija;

/**
 *
 * @author Megi
 */
public class ObrisiRasporedeTretmana extends GenerickaSistemskaOperacija {

    int tretmanID;

    public ObrisiRasporedeTretmana(int tretmanID) {
        this.tretmanID = tretmanID;
    }

    @Override
    protected void proveriPreduslov() throws Exception {

    }

    @Override
    protected void izvrsiKonkretnuOperaciju() throws Exception {
        broker.obrisi(new Raspored(), " tretmanID=" + tretmanID);
    }

    @Override
    protected void izvrsiValidaciju() {
    }

}
