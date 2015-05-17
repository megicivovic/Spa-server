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
import logic.Kontroler;
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
        listaRasporeda = broker.vratiSveObjekte(new Raspored(), uslov);
        for (GenerickiDomenskiObjekat raspored : listaRasporeda) {
            Tretman t = Kontroler.vratiInstancu().vratiTretmanePoID(((Raspored) raspored).getTretman().getTretmanID());
            Zaposleni z = Kontroler.vratiInstancu().vratiZaposlenePoID(((Raspored) raspored).getZaposleni().getZaposleniID());
            ((Raspored) raspored).setTretman(t);
            ((Raspored) raspored).setZaposleni(z);
        }
    }

    public List<GenerickiDomenskiObjekat> vratiListuRasporeda() {
        return listaRasporeda;
    }
}
