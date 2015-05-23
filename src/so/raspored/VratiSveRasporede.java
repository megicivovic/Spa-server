/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.raspored;

import domen.GenerickiDomenskiObjekat;
import domen.Raspored;
import domen.Tretman;
import domen.Zaposleni;
import java.util.List;
import so.GenerickaSistemskaOperacija;

/**
 *
 * @author Megi
 */
public class VratiSveRasporede extends GenerickaSistemskaOperacija {

    List<GenerickiDomenskiObjekat> listaRasporeda;
    String uslov;

    public VratiSveRasporede(String uslov) {
        this.uslov = uslov;
    }

    @Override
    protected void izvrsiValidaciju() {
    }

    @Override
    protected void proveriPreduslov() throws Exception {
    }

    @Override
    protected void izvrsiKonkretnuOperaciju() throws Exception {
        listaRasporeda = broker.vratiSveObjekte(new Raspored(), " JOIN zaposleni USING(zaposleniID) JOIN tretman USING(tretmanID) "+uslov);        
    }

    public List<GenerickiDomenskiObjekat> vratiListuRasporeda() {
        return listaRasporeda;
    }
}
