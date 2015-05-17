/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.korisnik;

import domen.GenerickiDomenskiObjekat;
import domen.Korisnik;
import java.util.List;
import so.GenerickaSistemskaOperacija;

/**
 *
 * @author Megi
 */
public class VratiSveKorisnike extends GenerickaSistemskaOperacija {

    List<GenerickiDomenskiObjekat> listaKorisnika;

    @Override
    protected void izvrsiValidaciju() {
    }

    @Override
    protected void proveriPreduslov() throws Exception {
    }

    @Override
    protected void izvrsiKonkretnuOperaciju() throws Exception {
        listaKorisnika = broker.vratiSveObjekte(new Korisnik(), "");
    }

    public List<GenerickiDomenskiObjekat> vratiListuKorisnika() {
        return listaKorisnika;
    }
}
