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
import server.ServerKontroler;
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
        listaPreparata = broker.vratiSveObjekte(new Preparat(), " JOIN kompanija ON (preparat.proizvodjac=kompanija.kompanijaID) ");
    }

    public List<GenerickiDomenskiObjekat> vratiListuPreparata() {
        return listaPreparata;
    }
}
