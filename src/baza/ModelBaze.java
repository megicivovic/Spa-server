/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baza;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ivana
 */
public class ModelBaze {

    private final Properties podesavanja;

    private ModelBaze() {

        podesavanja = new Properties();
        try {
            podesavanja.load(new FileInputStream("baza.properties"));
        } catch (IOException ex) {
            Logger.getLogger(ModelBaze.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static ModelBaze getInstance() {
        return ModelBazeHolder.INSTANCE;
    }

    private static class ModelBazeHolder {

        private static final ModelBaze INSTANCE = new ModelBaze();

    }

    public List<String> dajNaziveSvhPodrzanihBaza() {
        Set<String> set = podesavanja.stringPropertyNames();
        List<String> lista = new ArrayList<>();
        String db = null;
        for (String string : set) {
            if (string.startsWith("URL")) {
                lista.add(string.substring(3));
            }
        }
        return lista;
    }

    public String dajUrl(String dbms) {
        return podesavanja.getProperty("URL" + dbms);
    }

    public String dajDrajver(String dbms) {
        return podesavanja.getProperty("drajver" + dbms);
    }

    public String dajKorisnika(String dbms) {
        return podesavanja.getProperty("korisnik" + dbms);
    }

    public String dajSifru(String dbms) {
        return podesavanja.getProperty("sifra" + dbms);
    }

    public String dajNazivTrenutneBaze() {
        return podesavanja.getProperty("trenutnabaza");
    }

    public String dajURL() {
        return podesavanja.getProperty("URL" + podesavanja.getProperty("trenutnabaza"));
    }

    public String dajDrajver() {
        return podesavanja.getProperty("drajver" + podesavanja.getProperty("trenutnabaza"));
    }

    public String dajKorisnika() {
        return podesavanja.getProperty("korisnik" + podesavanja.getProperty("trenutnabaza"));
    }

    public String dajSifru() {
        return podesavanja.getProperty("sifra" + podesavanja.getProperty("trenutnabaza"));
    }

    public void postaviURL(String dbms, String url) {
        podesavanja.setProperty("URL" + dbms, url);
    }

    public void postaviDrajver(String dbms, String drajver) {
        podesavanja.setProperty("drajver" + dbms, drajver);
    }

    public void postaviKorisnika(String dbms, String korisnik) {
        podesavanja.setProperty("korisnik" + dbms, korisnik);
    }

    public void postaviSifru(String dbms, String sifra) {
        podesavanja.setProperty("sifra" + dbms, sifra);
    }

    public void postaviTrenutnuBazu(String dbms) {
        podesavanja.setProperty("trenutnabaza", dbms);
    }

    public void sacuvajPodesavanja() {
        OutputStream out = null;
        try {
            out = new FileOutputStream(new File("baza.properties"));
            podesavanja.store(out, "Promenjeno od strane korisnika");
        } catch (Exception ex) {
            Logger.getLogger(ModelBaze.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(ModelBaze.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
}
