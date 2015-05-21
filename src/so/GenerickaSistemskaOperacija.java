/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import baza.BrokerBazePodataka;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ivana
 */
public abstract class GenerickaSistemskaOperacija {

    protected baza.BrokerBazePodataka broker;

    public GenerickaSistemskaOperacija() {
        try {
            broker = BrokerBazePodataka.getInstance();
            broker.uspostaviKonekcijuPropertiesFile();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GenerickaSistemskaOperacija.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(GenerickaSistemskaOperacija.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GenerickaSistemskaOperacija.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public final void izvrsiSO() throws Exception {
        try {
            izvrsiValidaciju();
            proveriPreduslov();
            izvrsiKonkretnuOperaciju();
            potvrdiTransakciju();

        } catch (Exception ex) {
            if (broker != null) {
                System.out.println(ex.getMessage());
                broker.ponistiTransakciju();
            }
            throw ex;
        } finally {
            if (broker != null) {
                // broker.raskiniKonekciju();
            }
        }

    }

    protected abstract void izvrsiValidaciju();

    protected abstract void proveriPreduslov() throws Exception;

    protected abstract void izvrsiKonkretnuOperaciju() throws Exception;

    private void potvrdiTransakciju() throws SQLException {
        BrokerBazePodataka.getInstance().potvrdiTransakciju();

    }

    private void ponistiTransakciju() throws SQLException {
        BrokerBazePodataka.getInstance().ponistiTransakciju();
    }

}
