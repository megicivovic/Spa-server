/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.tretmanpreparati;

import domen.TretmanPreparati;
import java.util.List;
import so.GenerickaSistemskaOperacija;

/**
 *
 * @author Megi
 */
public class DodajPreparateTretmana extends GenerickaSistemskaOperacija {

    List<TretmanPreparati> ltp;

    public DodajPreparateTretmana(List<TretmanPreparati> ltp) {
        this.ltp = ltp;
    }

    @Override
    protected void proveriPreduslov() throws Exception {

    }

    @Override
    protected void izvrsiKonkretnuOperaciju() throws Exception {
        for (TretmanPreparati tp : ltp) {
            broker.sacuvaj(tp);
        }

    }

    @Override
    protected void izvrsiValidaciju() {
    }
}
