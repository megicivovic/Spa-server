/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import com.mysql.jdbc.ResultSetImpl;
import com.sun.javafx.scene.control.skin.VirtualFlow;
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
public final class DatabaseBroker {

    private Connection konekcija;
    private static DatabaseBroker instance;

    public DatabaseBroker() {

    }

    public static DatabaseBroker getInstance() {
        if (instance == null) {
            instance = new DatabaseBroker();
        }
        return instance;
    }

    public void raskiniKonekciju() throws SQLException {
        konekcija.close();
    }

    public void uspostaviKonekcijuPropertiesFile() throws ClassNotFoundException, SQLException, IOException {
        Class.forName(Util.getInstance().getDriver());
        String url = Util.getInstance().getURL();
        String user = Util.getInstance().getUser();
        String password = Util.getInstance().getPassword();
        konekcija = DriverManager.getConnection(url, user, password);
        konekcija.setAutoCommit(false);
        System.out.println("Konekcija uspesna!");
    }

    public void commit() throws SQLException {
        konekcija.commit();
    }

    public void rollback() throws SQLException {
        konekcija.rollback();
    }

    public List<GenerickiDomenskiObjekat> vratiSveObjekte(GenerickiDomenskiObjekat domenskiObjekat, String uslov) throws Exception {
        try {
            String upit = "SELECT * FROM " + domenskiObjekat.dajNazivTabele() + uslov;
            System.out.println(upit);
            Statement s = konekcija.createStatement();
            ResultSet rs = s.executeQuery(upit);
           if (rs==null){
               return new ArrayList<>();
           }
            return domenskiObjekat.vratiListuIzRS(rs);
        } catch (Exception ex) {
            Logger.getLogger(DatabaseBroker.class.getName()).log(Level.SEVERE, null, ex);
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
            if (rs.next()) {
                id = rs.getInt(1);
            }

            commit();
            s.close();
        } catch (Exception ex) {
            Logger.getLogger(DatabaseBroker.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(DatabaseBroker.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception("Neuspesno azuriranje objekta.");
        }
    }

    public GenerickiDomenskiObjekat pretraziPoID(GenerickiDomenskiObjekat domenskiObjekat, int id) throws Exception {
        try {
            String upit = "SELECT * FROM " + domenskiObjekat.dajNazivTabele() + " WHERE " + domenskiObjekat.dajID() + " =" + id;
            System.out.println(upit);
            Statement s = konekcija.createStatement();
            ResultSet rs = s.executeQuery(upit);

            return domenskiObjekat.vratiListuIzRS(rs).get(0);
        } catch (Exception ex) {
            Logger.getLogger(DatabaseBroker.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(DatabaseBroker.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception("Neuspesno brisanje objekta.");
        }
    }

    public int prebroj(GenerickiDomenskiObjekat domenskiObjekat, String uslov) throws Exception {
        try {
            String upit = "SELECT COUNT(*) FROM " + domenskiObjekat.dajNazivTabele() + uslov;
            System.out.println(upit);
            Statement s = konekcija.createStatement();
            ResultSet rs = s.executeQuery(upit);

            return domenskiObjekat.vratiBrojIzRS(rs);
        } catch (Exception ex) {
            Logger.getLogger(DatabaseBroker.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception("Neuspesno ucitavanje podataka.");
        }
    }
}
