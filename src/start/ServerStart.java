/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package start;

import domen.GenerickiDomenskiObjekat;
import domen.Kompanija;
import domen.Korisnik;
import domen.Preparat;
import domen.Raspored;
import domen.Rezervacija;
import domen.Tretman;
import domen.TretmanPreparati;
import domen.Zaposleni;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.List;
import konstante.Konstante;
import logic.Kontroler;
import transfer.KlijentTransferObjekat;
import transfer.ServerTransferObjekat;

/**
 *
 * @author student1
 */
public class ServerStart {

    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(9000);
            System.out.println("Server je pokrenut, ceka klijenta ");
            Socket s = ss.accept();
            System.out.println("Klijent se povezao sa serverom");
            new ServerStart().pokreniKomunikaciju(s);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void pokreniKomunikaciju(Socket s) throws IOException, ClassNotFoundException, SQLException, Exception {
        ObjectInputStream in = new ObjectInputStream(s.getInputStream());
        ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
        while (true) {
            //PROCITAJ ZAHTEV OD KLIJENTA
            KlijentTransferObjekat kto = (KlijentTransferObjekat) in.readObject();
            int operacija = kto.getOperacija();
            //OBRADI ZAHTEV
            ServerTransferObjekat sto = new ServerTransferObjekat();
            switch (operacija) {
                case Konstante.OPERACIJA_VRATI_SVE_KOMPANIJE: {
                    try {
                        List<GenerickiDomenskiObjekat> lk = Kontroler.vratiInstancu().vratiSveKompanije();
                        sto.setUspesnostIzvrsenjaOperacije(1);
                        sto.setPodaci(lk);
                    } catch (Exception ex) {
                        sto.setUspesnostIzvrsenjaOperacije(-1);
                        sto.setException(ex);
                        ex.printStackTrace();
                    }
                    //POSALJI ODGOVOR KLIJENTU
                    out.writeObject(sto);
                    System.out.println("Server je obradio zahtev klijenta. :)");
                    break;
                }

                case Konstante.OPERACIJA_DODAJ_REZERVACIJU: {
                    //PRoveri raspolozivost!
                    try {
                        Rezervacija r = (Rezervacija) kto.getParametar();
                        boolean raspolozivost = Kontroler.vratiInstancu().proveriRaspolozivost(r.getTretmanID(), r.getZaposleniID(), r.getVreme());
                        if (raspolozivost) {
                            Kontroler.vratiInstancu().sacuvajRezervaciju(r);
                            sto.setUspesnostIzvrsenjaOperacije(1);
                        }
                    } catch (Exception e) {
                        sto.setUspesnostIzvrsenjaOperacije(-1);
                        sto.setException(e);
                        e.printStackTrace();
                    }
                    out.writeObject(sto);
                    System.out.println("Server je obradio zahtev klijenta");
break;
                }
                case Konstante.OPERACIJA_DAJ_ID_KORISNIKA_IZ_SESIJE: {
                    try {
                        Korisnik k = Kontroler.vratiInstancu().vratiKorisnikaIzSesije();
                        sto.setUspesnostIzvrsenjaOperacije(1);
                        sto.setPodaci(k);
                    } catch (Exception ex) {
                        sto.setUspesnostIzvrsenjaOperacije(-1);
                        sto.setException(ex);
                        ex.printStackTrace();
                    }
                    //POSALJI ODGOVOR KLIJENTU
                    out.writeObject(sto);
                    System.out.println("Server je obradio zahtev klijenta. :)");
                    break;
                }
                case Konstante.OPERACIJA_DODAJ_KOMPANIJU: {
                    try {
                        Kompanija k = (Kompanija) kto.getParametar();
                        Kontroler.vratiInstancu().unesiKompaniju(k);
                        sto.setUspesnostIzvrsenjaOperacije(1);
                    } catch (Exception ex) {
                        sto.setUspesnostIzvrsenjaOperacije(-1);
                        sto.setException(ex);
                        ex.printStackTrace();
                    }
                    //POSALJI ODGOVOR KLIJENTU
                    out.writeObject(sto);
                    System.out.println("Server je obradio zahtev klijenta. :)");
                    break;
                }
                case Konstante.OPERACIJA_DODAJ_KORISNIKA: {
                    try {
                        Korisnik k = (Korisnik) kto.getParametar();
                        Kontroler.vratiInstancu().unesiKorisnika(k);
                        sto.setUspesnostIzvrsenjaOperacije(1);
                    } catch (Exception ex) {
                        sto.setUspesnostIzvrsenjaOperacije(-1);
                        sto.setException(ex);
                        ex.printStackTrace();
                    }
                    //POSALJI ODGOVOR KLIJENTU
                    out.writeObject(sto);
                    System.out.println("Server je obradio zahtev klijenta. :)");
                    break;
                }
                case Konstante.OPERACIJA_DODAJ_PREPARAT: {
                    try {
                        Preparat p = (Preparat) kto.getParametar();
                        Kontroler.vratiInstancu().unesiPreparat(p);
                        sto.setUspesnostIzvrsenjaOperacije(1);
                    } catch (Exception ex) {
                        sto.setUspesnostIzvrsenjaOperacije(-1);
                        sto.setException(ex);
                        ex.printStackTrace();
                    }
                    //POSALJI ODGOVOR KLIJENTU
                    out.writeObject(sto);
                    System.out.println("Server je obradio zahtev klijenta. :)");
                    break;
                }

                case Konstante.OPERACIJA_DODAJ_PREPARATE_TRETMANA: {
                    try {
                        List<TretmanPreparati> preparatiTretmana = (List<TretmanPreparati>) kto.getParametar();
                        Kontroler.vratiInstancu().ubaciPreparateTretmana(preparatiTretmana);
                        sto.setUspesnostIzvrsenjaOperacije(1);
                    } catch (Exception ex) {
                        sto.setUspesnostIzvrsenjaOperacije(-1);
                        sto.setException(ex);
                        ex.printStackTrace();
                    }
                    //POSALJI ODGOVOR KLIJENTU
                    out.writeObject(sto);
                    System.out.println("Server je obradio zahtev klijenta. :)");
                    break;
                }
                case Konstante.OPERACIJA_DODAJ_RASPORED: {
                    try {
                        Raspored r = (Raspored) kto.getParametar();
                        Kontroler.vratiInstancu().sacuvajRaspored(r);
                        sto.setUspesnostIzvrsenjaOperacije(1);
                    } catch (Exception ex) {
                        sto.setUspesnostIzvrsenjaOperacije(-1);
                        sto.setException(ex);
                        ex.printStackTrace();
                    }
                    //POSALJI ODGOVOR KLIJENTU
                    out.writeObject(sto);
                    System.out.println("Server je obradio zahtev klijenta. :)");
                    break;
                }

                case Konstante.OPERACIJA_DODAJ_TRETMAN: {
                    try {
                        Tretman t = (Tretman) kto.getParametar();
                        Kontroler.vratiInstancu().sacuvajTretman(t);
                        sto.setUspesnostIzvrsenjaOperacije(1);
                    } catch (Exception ex) {
                        sto.setUspesnostIzvrsenjaOperacije(-1);
                        sto.setException(ex);
                        ex.printStackTrace();
                    }
                    //POSALJI ODGOVOR KLIJENTU
                    out.writeObject(sto);
                    System.out.println("Server je obradio zahtev klijenta. :)");
                    break;
                }

                case Konstante.OPERACIJA_DODAJ_U_SESIJU: {
                    try {
                        Korisnik k = (Korisnik) kto.getParametar();
                        Kontroler.vratiInstancu().dodajUSesisju(k);
                        sto.setUspesnostIzvrsenjaOperacije(1);
                    } catch (Exception ex) {
                        sto.setUspesnostIzvrsenjaOperacije(-1);
                        sto.setException(ex);
                        ex.printStackTrace();
                    }
                    //POSALJI ODGOVOR KLIJENTU
                    out.writeObject(sto);
                    System.out.println("Server je obradio zahtev klijenta. :)");
                    break;
                }
                case Konstante.OPERACIJA_DODAJ_ZAPOSLENOG: {
                    try {
                        Zaposleni z = (Zaposleni) kto.getParametar();
                        Kontroler.vratiInstancu().unesiZaposlenog(z);
                        sto.setUspesnostIzvrsenjaOperacije(1);
                    } catch (Exception ex) {
                        sto.setUspesnostIzvrsenjaOperacije(-1);
                        sto.setException(ex);
                        ex.printStackTrace();
                    }
                    //POSALJI ODGOVOR KLIJENTU
                    out.writeObject(sto);
                    System.out.println("Server je obradio zahtev klijenta. :)");
                    break;
                }

                case Konstante.OPERACIJA_IZMENI_TRETMAN: {
                    try {
                        Tretman t = (Tretman) kto.getParametar();
                        Kontroler.vratiInstancu().izmeniTretman(t);
                        sto.setUspesnostIzvrsenjaOperacije(1);
                    } catch (Exception ex) {
                        sto.setUspesnostIzvrsenjaOperacije(-1);
                        sto.setException(ex);
                        ex.printStackTrace();
                    }
                    //POSALJI ODGOVOR KLIJENTU
                    out.writeObject(sto);
                    System.out.println("Server je obradio zahtev klijenta. :)");
                    break;
                }

                case Konstante.OPERACIJA_OBRISI_PREPARATE_TRETMANA: {
                    try {
                        int tretmanID = (int) kto.getParametar();
                        Kontroler.vratiInstancu().obrisiPreparateTretmana(tretmanID);

                        sto.setUspesnostIzvrsenjaOperacije(1);
                    } catch (Exception ex) {
                        sto.setUspesnostIzvrsenjaOperacije(-1);
                        sto.setException(ex);
                        ex.printStackTrace();
                    }
                    //POSALJI ODGOVOR KLIJENTU
                    out.writeObject(sto);
                    System.out.println("Server je obradio zahtev klijenta. :)");
                    break;
                }

                case Konstante.OPERACIJA_OBRISI_RASPOREDE_TRETMANA: {
                    try {
                        int tretmanID = (int) kto.getParametar();
                        Kontroler.vratiInstancu().obrisiRasporede(tretmanID);

                        sto.setUspesnostIzvrsenjaOperacije(1);
                    } catch (Exception ex) {
                        sto.setUspesnostIzvrsenjaOperacije(-1);
                        sto.setException(ex);
                        ex.printStackTrace();
                    }
                    //POSALJI ODGOVOR KLIJENTU
                    out.writeObject(sto);
                    System.out.println("Server je obradio zahtev klijenta. :)");
                    break;
                }

                case Konstante.OPERACIJA_OBRISI_TRETMAN: {
                    try {
                        int tretmanID = (int) kto.getParametar();
                        Kontroler.vratiInstancu().obrisiTretman(tretmanID);

                        sto.setUspesnostIzvrsenjaOperacije(1);
                    } catch (Exception ex) {
                        sto.setUspesnostIzvrsenjaOperacije(-1);
                        sto.setException(ex);
                        ex.printStackTrace();
                    }
                    //POSALJI ODGOVOR KLIJENTU
                    out.writeObject(sto);
                    System.out.println("Server je obradio zahtev klijenta. :)");
                    break;
                }
                case Konstante.OPERACIJA_STAMPAJ_PDF: {
                    try {
                        Rezervacija r = (Rezervacija) kto.getParametar();
                        Kontroler.vratiInstancu().stampajPDF(r);

                        sto.setUspesnostIzvrsenjaOperacije(1);
                    } catch (Exception ex) {
                        sto.setUspesnostIzvrsenjaOperacije(-1);
                        sto.setException(ex);
                        ex.printStackTrace();
                    }
                    //POSALJI ODGOVOR KLIJENTU
                    out.writeObject(sto);
                    System.out.println("Server je obradio zahtev klijenta. :)");
                    break;
                }
                case Konstante.OPERACIJA_VRATI_SVE_KOMPANIJE_PO_ID: {
                    try {
                        int kompanijaID = (int) kto.getParametar();
                        Kompanija k = (Kompanija) Kontroler.vratiInstancu().vratiKompanijePoID(kompanijaID);
                        sto.setPodaci(k);
                        sto.setUspesnostIzvrsenjaOperacije(1);

                    } catch (Exception ex) {
                        sto.setUspesnostIzvrsenjaOperacije(-1);
                        sto.setException(ex);
                        ex.printStackTrace();
                    }
                    //POSALJI ODGOVOR KLIJENTU
                    out.writeObject(sto);
                    System.out.println("Server je obradio zahtev klijenta. :)");
                    break;
                }

                case Konstante.OPERACIJA_VRATI_SVE_KORISNIKE: {
                    try {
                        List<GenerickiDomenskiObjekat> lk = Kontroler.vratiInstancu().vratiKorisnike();
                        sto.setPodaci(lk);
                        sto.setUspesnostIzvrsenjaOperacije(1);

                    } catch (Exception ex) {
                        sto.setUspesnostIzvrsenjaOperacije(-1);
                        sto.setException(ex);
                        ex.printStackTrace();
                    }
                    //POSALJI ODGOVOR KLIJENTU
                    out.writeObject(sto);
                    System.out.println("Server je obradio zahtev klijenta. :)");
                    break;
                }
                case Konstante.OPERACIJA_VRATI_SVE_PREPARATE: {
                    try {
                        List<GenerickiDomenskiObjekat> lp = Kontroler.vratiInstancu().vratiSvePreparate();
                        sto.setPodaci(lp);
                        sto.setUspesnostIzvrsenjaOperacije(1);

                    } catch (Exception ex) {
                        sto.setUspesnostIzvrsenjaOperacije(-1);
                        sto.setException(ex);
                        ex.printStackTrace();
                    }
                    //POSALJI ODGOVOR KLIJENTU
                    out.writeObject(sto);
                    System.out.println("Server je obradio zahtev klijenta. :)");
                    break;
                }

                case Konstante.OPERACIJA_VRATI_SVE_PREPARATE_PO_ID: {
                    try {
                        int preparatID = (int) kto.getParametar();
                        Preparat p = (Preparat) Kontroler.vratiInstancu().vratiSvePreparatePoID(preparatID);
                        sto.setPodaci(p);
                        sto.setUspesnostIzvrsenjaOperacije(1);

                    } catch (Exception ex) {
                        sto.setUspesnostIzvrsenjaOperacije(-1);
                        sto.setException(ex);
                        ex.printStackTrace();
                    }
                    //POSALJI ODGOVOR KLIJENTU
                    out.writeObject(sto);
                    System.out.println("Server je obradio zahtev klijenta. :)");
                    break;
                }
                case Konstante.OPERACIJA_VRATI_SVE_PREPARATE_TRETMANA: {
                    try {
                        int tretmanID = (int) kto.getParametar();
                        List<GenerickiDomenskiObjekat> lp = Kontroler.vratiInstancu().vratiSvePreparateTretmana(tretmanID);
                        sto.setPodaci(lp);
                        sto.setUspesnostIzvrsenjaOperacije(1);

                    } catch (Exception ex) {
                        sto.setUspesnostIzvrsenjaOperacije(-1);
                        sto.setException(ex);
                        ex.printStackTrace();
                    }
                    //POSALJI ODGOVOR KLIJENTU
                    out.writeObject(sto);
                    System.out.println("Server je obradio zahtev klijenta. :)");
                    break;
                }
                case Konstante.OPERACIJA_VRATI_SVE_RASPOREDE: {
                    try {
                        List<GenerickiDomenskiObjekat> lr = Kontroler.vratiInstancu().vratiRasporede();
                        sto.setPodaci(lr);
                        sto.setUspesnostIzvrsenjaOperacije(1);

                    } catch (Exception ex) {
                        sto.setUspesnostIzvrsenjaOperacije(-1);
                        sto.setException(ex);
                        ex.printStackTrace();
                    }
                    //POSALJI ODGOVOR KLIJENTU
                    out.writeObject(sto);
                    System.out.println("Server je obradio zahtev klijenta. :)");
                    break;
                }
                case Konstante.OPERACIJA_VRATI_SVE_TRETMANE: {
                    try {
                        List<GenerickiDomenskiObjekat> lt = Kontroler.vratiInstancu().vratiSveTretmane();
                        sto.setPodaci(lt);
                        sto.setUspesnostIzvrsenjaOperacije(1);

                    } catch (Exception ex) {
                        sto.setUspesnostIzvrsenjaOperacije(-1);
                        sto.setException(ex);
                        ex.printStackTrace();
                    }
                    //POSALJI ODGOVOR KLIJENTU
                    out.writeObject(sto);
                    System.out.println("Server je obradio zahtev klijenta. :)");
                    break;
                }
                case Konstante.OPERACIJA_VRATI_SVE_TRETMANE_PO_ID: {
                    try {
                        int tretmanID = (int) kto.getParametar();
                        Tretman t = Kontroler.vratiInstancu().vratiTretmanePoID(tretmanID);
                        sto.setPodaci(t);
                        sto.setUspesnostIzvrsenjaOperacije(1);

                    } catch (Exception ex) {
                        sto.setUspesnostIzvrsenjaOperacije(-1);
                        sto.setException(ex);
                        ex.printStackTrace();
                    }
                    //POSALJI ODGOVOR KLIJENTU
                    out.writeObject(sto);
                    System.out.println("Server je obradio zahtev klijenta. :)");
                    break;
                }

                case Konstante.OPERACIJA_VRATI_SVE_ZAPOSLENE: {
                    try {
                        List<GenerickiDomenskiObjekat> lz = Kontroler.vratiInstancu().vratiSveZaposlene();
                        sto.setPodaci(lz);
                        sto.setUspesnostIzvrsenjaOperacije(1);

                    } catch (Exception ex) {
                        sto.setUspesnostIzvrsenjaOperacije(-1);
                        sto.setException(ex);
                        ex.printStackTrace();
                    }
                    //POSALJI ODGOVOR KLIJENTU
                    out.writeObject(sto);
                    System.out.println("Server je obradio zahtev klijenta. :)");
                    break;
                }

                case Konstante.OPERACIJA_VRATI_ZAPOSLENE_PO_ID: {
                    try {
                        int zaposleniID = (int) kto.getParametar();
                        Zaposleni z = Kontroler.vratiInstancu().vratiZaposlenePoID(zaposleniID);
                        sto.setPodaci(z);
                        sto.setUspesnostIzvrsenjaOperacije(1);

                    } catch (Exception ex) {
                        sto.setUspesnostIzvrsenjaOperacije(-1);
                        sto.setException(ex);
                        ex.printStackTrace();
                    }
                    //POSALJI ODGOVOR KLIJENTU
                    out.writeObject(sto);
                    System.out.println("Server je obradio zahtev klijenta. :)");
                    break;
                }

            }

        }
    }
}
