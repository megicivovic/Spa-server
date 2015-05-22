/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.tretmanpreparati;

import domen.TretmanPreparati;
import so.GenerickaSistemskaOperacija;

/**
 *
 * @author Megi
 */
public class ObrisiPreparateTretmana extends GenerickaSistemskaOperacija {

    int tretmanID;

    public ObrisiPreparateTretmana(int tretmanID) {
        this.tretmanID = tretmanID;
    }

    @Override
    protected void proveriPreduslov() throws Exception {

    }

    @Override
    protected void izvrsiKonkretnuOperaciju() throws Exception {
        broker.obrisi(new TretmanPreparati(), " tretmanID=" + tretmanID);
    }

    @Override
    protected void izvrsiValidaciju() {
    }

}
