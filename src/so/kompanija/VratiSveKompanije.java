/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.kompanija;

import domen.GenerickiDomenskiObjekat;
import domen.Kompanija;
import java.util.List;
import so.GenerickaSistemskaOperacija;

/**
 *
 * @author Megi
 */
public class VratiSveKompanije extends GenerickaSistemskaOperacija {

    List<GenerickiDomenskiObjekat> listaKompanija;

    @Override
    protected void izvrsiValidaciju() {
    }

    @Override
    protected void proveriPreduslov() throws Exception {
    }

    @Override
    protected void izvrsiKonkretnuOperaciju() throws Exception {
        listaKompanija = broker.vratiSveObjekte(new Kompanija(), "");
    }

    public List<GenerickiDomenskiObjekat> vratiListuKompanija() {
        return listaKompanija;
    }
}
