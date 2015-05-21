/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.podesavanja;

import baza.ModelBaze;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Ivana
 */
public class KonfiguracioniModelTabele extends AbstractTableModel {

    @Override
    public int getRowCount() {
        return ModelBaze.getInstance().dajNaziveSvhPodrzanihBaza().size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int indeksReda, int indeksKolone) {
        List<String> lista = ModelBaze.getInstance().dajNaziveSvhPodrzanihBaza();
        String dbms = lista.get(indeksReda);
        switch (indeksKolone) {
            case 0:
                return dbms;
            case 1:
                return ModelBaze.getInstance().dajUrl(dbms);
            case 2:
                return ModelBaze.getInstance().dajDrajver(dbms);
            case 3:
                return ModelBaze.getInstance().dajKorisnika(dbms);
            case 4:
                return ModelBaze.getInstance().dajSifru(dbms);
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int kolona) {
        switch (kolona) {
            case 0:
                return "SUBP";
            case 1:
                return "URL";
            case 2:
                return "Drajver";
            case 3:
                return "Korisnik";
            case 4:
                return "Sifra";
            default:
                return "Greska";
        }
    }

    @Override
    public boolean isCellEditable(int indeksReda, int indeksKolone) {
        return indeksKolone != 0;
    }

    @Override
    public void setValueAt(Object vrednost, int indeksReda, int indeksKolone) {
        List<String> lista = ModelBaze.getInstance().dajNaziveSvhPodrzanihBaza();
        String dbms = lista.get(indeksReda);
        switch (indeksKolone) {
            case 1:
                ModelBaze.getInstance().postaviURL(dbms, (String) vrednost);
                break;
            case 2:
                ModelBaze.getInstance().postaviDrajver(dbms, (String) vrednost);
                break;
            case 3:
                ModelBaze.getInstance().postaviKorisnika(dbms, (String) vrednost);
                break;
            case 4:
                ModelBaze.getInstance().postaviSifru(dbms, (String) vrednost);
                break;
        }
    }

}
