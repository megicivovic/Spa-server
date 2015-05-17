/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.tretmanpreparati;

import domen.GenerickiDomenskiObjekat;
import domen.TretmanPreparati;
import java.util.List;
import so.GenerickaSistemskaOperacija;

/**
 *
 * @author Megi
 */
public class VratiSvePreparateTretmana extends GenerickaSistemskaOperacija {

    List<GenerickiDomenskiObjekat> listaPreparataTretmana;
    int tretmanID;

    public VratiSvePreparateTretmana(int tretmanID) {
        this.tretmanID = tretmanID;
    }

    @Override
    protected void izvrsiValidaciju() {
    }

    @Override
    protected void proveriPreduslov() throws Exception {
    }

    @Override
    protected void izvrsiKonkretnuOperaciju() throws Exception {
        listaPreparataTretmana = broker.vratiSveObjekte(new TretmanPreparati(), " WHERE tretmanID=" + tretmanID);
    }

    public List<GenerickiDomenskiObjekat> vratiListuPreparataTretmana() {
        return listaPreparataTretmana;
    }
}
