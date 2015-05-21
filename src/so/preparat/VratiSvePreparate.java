/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.preparat;

import domen.GenerickiDomenskiObjekat;
import domen.Kompanija;
import domen.Preparat;
import java.util.List;
import server.Kontroler;
import so.GenerickaSistemskaOperacija;

/**
 *
 * @author Megi
 */
public class VratiSvePreparate extends GenerickaSistemskaOperacija {

    List<GenerickiDomenskiObjekat> listaPreparata;

    @Override
    protected void izvrsiValidaciju() {
    }

    @Override
    protected void proveriPreduslov() throws Exception {
    }

    @Override
    protected void izvrsiKonkretnuOperaciju() throws Exception {
        listaPreparata = broker.vratiSveObjekte(new Preparat(), "");
        for (GenerickiDomenskiObjekat p : listaPreparata) {
            Kompanija k = (Kompanija) Kontroler.vratiInstancu().vratiKompanijePoID(((Preparat) p).getProizvodjac().getKompanijaID());
            ((Preparat) p).setProizvodjac(k);
        }
    }

    public List<GenerickiDomenskiObjekat> vratiListuPreparata() {
        return listaPreparata;
    }
}
