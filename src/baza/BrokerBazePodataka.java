/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baza;

import domen.GenerickiDomenskiObjekat;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author student1
 */
public final class BrokerBazePodataka {

    private Connection konekcija;
    private static BrokerBazePodataka instance;

    public BrokerBazePodataka() {

    }

    public static BrokerBazePodataka getInstance() {
        if (instance == null) {
            instance = new BrokerBazePodataka();
        }
        return instance;
    }

    public void ucitajDrajver() throws ClassNotFoundException {

        try {
            Class.forName(ModelBaze.getInstance().dajDrajver());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BrokerBazePodataka.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }

    public void uspostaviKonekcijuPropertiesFile() throws ClassNotFoundException, SQLException, IOException {
        try {
            konekcija = DriverManager.getConnection(ModelBaze.getInstance().dajURL(), ModelBaze.getInstance().dajKorisnika(), ModelBaze.getInstance().dajSifru());
            konekcija.setAutoCommit(false);
        } catch (SQLException ex) {
            Logger.getLogger(BrokerBazePodataka.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void zatvoriKonekciju() {

        try {
            konekcija.close();
        } catch (SQLException ex) {
            Logger.getLogger(BrokerBazePodataka.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void potvrdiTransakciju() throws SQLException {
        konekcija.commit();
    }

    public void ponistiTransakciju() throws SQLException {
        konekcija.rollback();
    }

    public List<GenerickiDomenskiObjekat> vratiSveObjekte(GenerickiDomenskiObjekat domenskiObjekat, String uslov) throws Exception {
        try {
            String upit = "SELECT * FROM " + domenskiObjekat.dajNazivTabele() + uslov;
            System.out.println(upit);
            Statement s = konekcija.createStatement();
            ResultSet rs = s.executeQuery(upit);
            if (rs == null) {
                return new ArrayList<>();
            }
            return domenskiObjekat.vratiListuIzRS(rs);
        } catch (Exception ex) {
            Logger.getLogger(BrokerBazePodataka.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception("Neuspesno ucitavanje podataka.");
        }
    }

    public int sacuvaj(GenerickiDomenskiObjekat domenskiObjekat) throws Exception {
        int id = 0;
        try {
            String upit = "INSERT INTO " + domenskiObjekat.dajNazivTabele()
                    + " (" + domenskiObjekat.dajImenaSvihAtributa() + ")"
                    + " VALUES (" + domenskiObjekat.dajInsertVrednosti() + ")";
            System.out.println(upit);
            Statement s = konekcija.createStatement();
            s.executeUpdate(upit, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = s.getGeneratedKeys();
            if (rs == null) {
                return -1;
            }
            if (rs.next()) {
                id = rs.getInt(1);
            }
            potvrdiTransakciju();
            s.close();
        } catch (Exception ex) {
            Logger.getLogger(BrokerBazePodataka.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception("Neuspesno cuvanje objekta.");
        }
        return id;
    }

    public void azuriraj(GenerickiDomenskiObjekat domenskiObjekat) throws Exception {

        try {
            String upit = "UPDATE " + domenskiObjekat.dajNazivTabele() + " SET " + domenskiObjekat.dajApdejtVrednosti();
            System.out.println(upit);
            Statement s = konekcija.createStatement();
            s.executeUpdate(upit);
            s.close();
        } catch (Exception ex) {
            Logger.getLogger(BrokerBazePodataka.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception("Neuspesno azuriranje objekta.");
        }
    }

    public GenerickiDomenskiObjekat pretraziPoID(GenerickiDomenskiObjekat domenskiObjekat, int id) throws Exception {
        try {
            String upit = "SELECT * FROM " + domenskiObjekat.dajNazivTabele() + " WHERE " + domenskiObjekat.dajID() + " =" + id;
            System.out.println(upit);
            Statement s = konekcija.createStatement();
            ResultSet rs = s.executeQuery(upit);
            if (rs == null) {
                return (GenerickiDomenskiObjekat) new Object();
            }
            return domenskiObjekat.vratiListuIzRS(rs).get(0);
        } catch (Exception ex) {
            Logger.getLogger(BrokerBazePodataka.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception("Neuspesno ucitavanje podataka.");
        }
    }

    public void obrisi(GenerickiDomenskiObjekat domenskiObjekat, String uslov) throws Exception {
        try {
            String upit = "DELETE FROM " + domenskiObjekat.dajNazivTabele() + " WHERE " + uslov;
            System.out.println(upit);
            Statement s = konekcija.createStatement();
            s.executeUpdate(upit);
            s.close();
        } catch (Exception ex) {
            Logger.getLogger(BrokerBazePodataka.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception("Neuspesno brisanje objekta.");
        }
    }

//    public int prebroj(GenerickiDomenskiObjekat domenskiObjekat, String uslov) throws Exception {
//        try {
//            String upit = "SELECT COUNT(*) FROM " + domenskiObjekat.dajNazivTabele() + uslov;
//            System.out.println(upit);
//            Statement s = konekcija.createStatement();
//            ResultSet rs = s.executeQuery(upit);
//
//            return domenskiObjekat.vratiBrojIzRS(rs);
//        } catch (Exception ex) {
//            Logger.getLogger(BrokerBazePodataka.class.getName()).log(Level.SEVERE, null, ex);
//            throw new Exception("Neuspesno ucitavanje podataka.");
//        }
//    }
}
