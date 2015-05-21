/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import protokol.Protokol;
import protokol.Status;
import protokol.objekti.KlijentZahtev;
import protokol.objekti.ServerOdgovor;

/**
 *
 * @author Ivana
 */
public class ServerskaNit extends Thread {

    Socket socket;

    public ServerskaNit(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            zapocniKomunikaciju();
        } catch (IOException ex) {
            Logger.getLogger(ServerskaNit.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServerskaNit.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void zapocniKomunikaciju() throws IOException, ClassNotFoundException {
        while (true) {
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            KlijentZahtev klijentZahtev = (KlijentZahtev) inputStream.readObject();
            obradiZahtev(klijentZahtev);
        }
    }

    private synchronized void obradiZahtev(KlijentZahtev klijentZahtev) throws IOException {
        Protokol protokol = klijentZahtev.getProtokol();
        ServerOdgovor serverOdgovor = null;
        System.out.println(protokol);
        switch (protokol) {
            case OPERACIJA_ULOGUJ_SE:
                serverOdgovor = ServerKontroler.ulogujSe(klijentZahtev);
                break;
            case OPERACIJA_VRATI_SVE_KOMPANIJE:
                serverOdgovor = ServerKontroler.vratiSveKompanije(klijentZahtev);
                break;
            case OPERACIJA_DODAJ_REZERVACIJU:
                serverOdgovor = ServerKontroler.dodajRezervaciju(klijentZahtev);
                break;
            case OPERACIJA_DAJ_ID_KORISNIKA_IZ_SESIJE:
                // serverOdgovor = ServerKontroler.pretraziSerije(klijentZahtev);
                break;
            case OPERACIJA_DODAJ_KOMPANIJU:
                serverOdgovor = ServerKontroler.dodajKompaniju(klijentZahtev);
                break;
            case OPERACIJA_DODAJ_KORISNIKA:
                serverOdgovor = ServerKontroler.dodajKorisnika(klijentZahtev);
                break;
            case OPERACIJA_DODAJ_PREPARAT:
                serverOdgovor = ServerKontroler.dodajPreparat(klijentZahtev);
                break;
            case OPERACIJA_DODAJ_PREPARATE_TRETMANA:
                serverOdgovor = ServerKontroler.dodajPreparateTretmana(klijentZahtev);
                break;
            case OPERACIJA_DODAJ_RASPORED:
                serverOdgovor = ServerKontroler.dodajRaspored(klijentZahtev);
                break;
            case OPERACIJA_DODAJ_TRETMAN:
                serverOdgovor = ServerKontroler.dodajTretman(klijentZahtev);
                break;
            case OPERACIJA_DODAJ_U_SESIJU:
                // serverOdgovor = ServerKontroler.dodajSeriju(klijentZahtev);
                break;
            case OPERACIJA_DODAJ_ZAPOSLENOG:
                serverOdgovor = ServerKontroler.dodajZaposlenog(klijentZahtev);
                break;
            case OPERACIJA_IZMENI_TRETMAN:
                serverOdgovor = ServerKontroler.izmeniTretman(klijentZahtev);
                break;
            case OPERACIJA_OBRISI_PREPARATE_TRETMANA:
                serverOdgovor = ServerKontroler.obrisiPreparateTretmana(klijentZahtev);
                break;
            case OPERACIJA_OBRISI_RASPOREDE_TRETMANA:
                serverOdgovor = ServerKontroler.obrisiRasporedeTretmana(klijentZahtev);
                break;
            case OPERACIJA_OBRISI_TRETMAN:
                serverOdgovor = ServerKontroler.obrisiTretman(klijentZahtev);
                break;
            case OPERACIJA_STAMPAJ_PDF:
                serverOdgovor = ServerKontroler.stampajPDF(klijentZahtev);
                break;
            case OPERACIJA_VRATI_SVE_KOMPANIJE_PO_ID:
                serverOdgovor = ServerKontroler.vratiSveKompanijePoID(klijentZahtev);
                break;
            case OPERACIJA_VRATI_SVE_KORISNIKE:
                serverOdgovor = ServerKontroler.vratiSveKorisnike(klijentZahtev);
                break;
            case OPERACIJA_VRATI_SVE_PREPARATE:
                serverOdgovor = ServerKontroler.vratiSvePreparate(klijentZahtev);
                break;
            case OPERACIJA_VRATI_SVE_PREPARATE_PO_ID:
                serverOdgovor = ServerKontroler.vratiSvePreparatePoID(klijentZahtev);
                break;
            case OPERACIJA_VRATI_SVE_PREPARATE_TRETMANA:
                serverOdgovor = ServerKontroler.vratiSvePreparateTretmana(klijentZahtev);
                break;
            case OPERACIJA_VRATI_SVE_RASPOREDE:
                serverOdgovor = ServerKontroler.vratiSveRasporede(klijentZahtev);
                break;
            case OPERACIJA_VRATI_SVE_TRETMANE:
                serverOdgovor = ServerKontroler.vratiSveTretmane(klijentZahtev);
                break;
            case OPERACIJA_VRATI_SVE_TRETMANE_PO_ID:
                serverOdgovor = ServerKontroler.vratiSveTretmanePoID(klijentZahtev);
                break;
            case OPERACIJA_VRATI_SVE_ZAPOSLENE:
                serverOdgovor = ServerKontroler.vratiSveZaposlene(klijentZahtev);
                break;
            case OPERACIJA_VRATI_ZAPOSLENE_PO_ID:
                serverOdgovor = ServerKontroler.vratiSveZaposlenePoID(klijentZahtev);
                break;

            default:
                serverOdgovor = new ServerOdgovor();
                serverOdgovor.setStatus(Status.PROTOKOL_NEPOZNAT);
        }
        ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
        outputStream.writeObject(serverOdgovor);
        System.out.println(serverOdgovor.getStatus());
    }

}
