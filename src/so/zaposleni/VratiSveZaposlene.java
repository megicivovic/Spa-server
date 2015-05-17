/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.zaposleni;

import domen.GenerickiDomenskiObjekat;
import domen.Zaposleni;
import java.util.List;
import so.GenerickaSistemskaOperacija;

/**
 *
 * @author Megi
 */
public class VratiSveZaposlene extends GenerickaSistemskaOperacija {

    List<GenerickiDomenskiObjekat> listaZaposlenih;

    @Override
    protected void izvrsiValidaciju() {
    }

    @Override
    protected void proveriPreduslov() throws Exception {
    }

    @Override
    protected void izvrsiKonkretnuOperaciju() throws Exception {
        listaZaposlenih = broker.vratiSveObjekte(new Zaposleni(), "");
    }

    public List<GenerickiDomenskiObjekat> vratiListuZaposlenih() {
        return listaZaposlenih;
    }
}
