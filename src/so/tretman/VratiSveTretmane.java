/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.tretman;

import domen.GenerickiDomenskiObjekat;
import domen.Tretman;
import java.util.List;
import so.GenerickaSistemskaOperacija;

/**
 *
 * @author Megi
 */
public class VratiSveTretmane extends GenerickaSistemskaOperacija {

    List<GenerickiDomenskiObjekat> listaTretmana;

    public VratiSveTretmane() {
    }

    public VratiSveTretmane(String uslov) {

    }

    @Override
    protected void izvrsiValidaciju() {
    }

    @Override
    protected void proveriPreduslov() throws Exception {
    }

    @Override
    protected void izvrsiKonkretnuOperaciju() throws Exception {
        listaTretmana = broker.vratiSveObjekte(new Tretman(), "");
    }

    public List<GenerickiDomenskiObjekat> vratiListuTretmana() {
        return listaTretmana;
    }
}
