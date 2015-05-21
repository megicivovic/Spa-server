/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import domen.Kompanija;
import domen.Korisnik;
import domen.Preparat;
import domen.Raspored;
import domen.Rezervacija;
import domen.Tretman;
import domen.TretmanPreparati;
import domen.Zaposleni;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import protokol.Status;
import protokol.objekti.KlijentZahtev;
import protokol.objekti.ServerOdgovor;
import so.GenerickaSistemskaOperacija;
import so.kompanija.DodajKompaniju;
import so.kompanija.VratiSveKompanije;
import so.kompanija.VratiSveKompanijePoID;
import so.korisnik.DodajKorisnika;
import so.korisnik.UlogujSe;
import so.korisnik.ValidirajKorisnickoIme;
import so.korisnik.VratiSveKorisnike;
import so.preparat.DodajPreparat;
import so.preparat.VratiSvePreparate;
import so.preparat.VratiSvePreparatePoID;
import so.raspored.DodajRaspored;
import so.raspored.ObrisiRasporedeTretmana;
import so.raspored.VratiSveRasporede;
import so.rezervacija.DodajRezervaciju;
import so.rezervacija.StampajRezervaciju;
import so.rezervacija.VratiSveRezervacije;
import so.tretman.DodajTretman;
import so.tretman.IzmeniTretman;
import so.tretman.ObrisiTretman;
import so.tretman.VratiSveTretmane;
import so.tretman.VratiTretmanePoID;
import so.tretmanpreparati.DodajPreparateTretmana;
import so.tretmanpreparati.ObrisiPreparateTretmana;
import so.tretmanpreparati.VratiSvePreparateTretmana;
import so.zaposleni.DodajZaposlenog;
import so.zaposleni.VratiSveZaposlene;
import so.zaposleni.VratiZaposlenePoID;

/**
 *
 * @author Ivana
 */
public class ServerKontroler {

    public static ServerOdgovor ulogujSe(KlijentZahtev zahtev) {
        Korisnik klijent = (Korisnik) zahtev.getObjekat();
        GenerickaSistemskaOperacija so = new UlogujSe(klijent);
        ServerOdgovor serverOdgovor = new ServerOdgovor();

        try {
            so.izvrsiSO();
            serverOdgovor.setObjekat(((UlogujSe) so).getKlijent());
            serverOdgovor.setStatus(Status.ZAVRSENO);
        } catch (Exception ex) {
            serverOdgovor.setObjekat(ex);
            serverOdgovor.setStatus(Status.IZUZETAK);
        }
        return serverOdgovor;
    }

    public static ServerOdgovor registrujSe(KlijentZahtev zahtev) {
        Korisnik klijent = (Korisnik) zahtev.getObjekat();
        GenerickaSistemskaOperacija so = new DodajKorisnika(klijent);
        ServerOdgovor serverOdgovor = new ServerOdgovor();

        try {
            so.izvrsiSO();
            serverOdgovor.setObjekat(((DodajKorisnika) so).getKorisnik());
            serverOdgovor.setStatus(Status.ZAVRSENO);
        } catch (Exception ex) {
            serverOdgovor.setObjekat(ex);
            serverOdgovor.setStatus(Status.IZUZETAK);
        }
        return serverOdgovor;
    }

    public static ServerOdgovor validirajIme(KlijentZahtev zahtev) {
        Korisnik korisnik = (Korisnik) zahtev.getObjekat();
        GenerickaSistemskaOperacija so = new ValidirajKorisnickoIme(korisnik);
        ServerOdgovor serverOdgovor = new ServerOdgovor();
        try {
            so.izvrsiSO();
            serverOdgovor.setObjekat(((ValidirajKorisnickoIme) so).isMoze());
            serverOdgovor.setStatus(Status.ZAVRSENO);
        } catch (Exception ex) {
            serverOdgovor.setObjekat(ex);
            serverOdgovor.setStatus(Status.IZUZETAK);
        }
        return serverOdgovor;
    }

    public static ServerOdgovor vratiSveKompanije(KlijentZahtev klijentZahtev) {
        GenerickaSistemskaOperacija so = new VratiSveKompanije();
        ServerOdgovor serverOdgovor = new ServerOdgovor();

        try {
            so.izvrsiSO();
            serverOdgovor.setObjekat(((VratiSveKompanije) so).vratiListuKompanija());
            serverOdgovor.setStatus(Status.ZAVRSENO);
        } catch (Exception ex) {
            serverOdgovor.setObjekat(ex);
            serverOdgovor.setStatus(Status.IZUZETAK);
        }

        return serverOdgovor;
    }

    static ServerOdgovor dodajRezervaciju(KlijentZahtev klijentZahtev) {
        Rezervacija r = (Rezervacija) klijentZahtev.getObjekat();
        ServerOdgovor serverOdgovor = new ServerOdgovor();
        try {
            boolean raspolozivost = proveriRaspolozivost(r.getTretmanID(), r.getZaposleniID(), r.getVreme());
            if (raspolozivost) {
                GenerickaSistemskaOperacija so = new DodajRezervaciju(r);
                so.izvrsiSO();
                serverOdgovor.setStatus(Status.ZAVRSENO);
            }

        } catch (Exception ex) {
            serverOdgovor.setObjekat(ex);
            serverOdgovor.setStatus(Status.IZUZETAK);
        }
        return serverOdgovor;

    }

    static boolean proveriRaspolozivost(int tretmanID, int zaposleniID, Date vreme) throws Exception {
        boolean raspolozivost = true;

        GenerickaSistemskaOperacija so = new VratiSveRezervacije(tretmanID, zaposleniID, vreme, " WHERE tretmanID = " + tretmanID + " AND zaposleniID = " + zaposleniID);
        so.izvrsiSO();
        try {
            List<Rezervacija> rezervacije = ((VratiSveRezervacije) so).getRezervacije();

            GenerickaSistemskaOperacija vsr = new VratiSveRasporede(" WHERE tretmanID= " + tretmanID + " AND zaposleniID = " + zaposleniID);
            vsr.izvrsiSO();
            int brTermina = ((Raspored) ((VratiSveRasporede) vsr).vratiListuRasporeda().get(0)).getBrojTermina();

            if (rezervacije != null) {
                if (rezervacije.size() >= brTermina) {
                    raspolozivost = false;
                }
            }

            GenerickaSistemskaOperacija soZ = new VratiSveRezervacije(tretmanID, zaposleniID, vreme, " WHERE zaposleniID = " + zaposleniID);
            soZ.izvrsiSO();
            List<Rezervacija> rezervacijeZaposlenog = ((VratiSveRezervacije) so).getRezervacije();

            if (rezervacijeZaposlenog != null) {
                for (Rezervacija rezervacija : rezervacijeZaposlenog) {
                    //vreme zavrsetka tretmana
                    Tretman t = new VratiTretmanePoID(rezervacija.getTretmanID()).vratiTretman();
                    Calendar cal = new GregorianCalendar();
                    cal.setTime(rezervacija.getVreme());
                    cal.add(Calendar.MINUTE, t.getTrajanjeUMin());

                    //trazeno vreme
                    Calendar vremePocetka = new GregorianCalendar();
                    vremePocetka.setTime(vreme);

                    Calendar calVremeZavrsetka = new GregorianCalendar();
                    calVremeZavrsetka.setTime(rezervacija.getVreme());
                    calVremeZavrsetka.add(Calendar.MINUTE, t.getTrajanjeUMin());

                    if (!cal.before(vremePocetka)) {
                        raspolozivost = false;
                    }
                }
            }

        } catch (NullPointerException ne) {
            return true;
        }
        return raspolozivost;

    }

    static ServerOdgovor dodajKompaniju(KlijentZahtev klijentZahtev) {

        Kompanija k = (Kompanija) klijentZahtev.getObjekat();
        GenerickaSistemskaOperacija so = new DodajKompaniju(k);
        ServerOdgovor serverOdgovor = new ServerOdgovor();
        try {
            so.izvrsiSO();
            serverOdgovor.setStatus(Status.ZAVRSENO);
        } catch (Exception ex) {
            serverOdgovor.setObjekat(ex);
            serverOdgovor.setStatus(Status.IZUZETAK);
        }
        return serverOdgovor;

    }

    static ServerOdgovor dodajKorisnika(KlijentZahtev klijentZahtev) {
        Korisnik k = (Korisnik) klijentZahtev.getObjekat();
        GenerickaSistemskaOperacija so = new DodajKorisnika(k);
        ServerOdgovor serverOdgovor = new ServerOdgovor();
        try {
            so.izvrsiSO();
            serverOdgovor.setStatus(Status.ZAVRSENO);
        } catch (Exception ex) {
            serverOdgovor.setObjekat(ex);
            serverOdgovor.setStatus(Status.IZUZETAK);
        }
        return serverOdgovor;
    }

    static ServerOdgovor dodajPreparat(KlijentZahtev klijentZahtev) {

        Preparat p = (Preparat) klijentZahtev.getObjekat();
        GenerickaSistemskaOperacija so = new DodajPreparat(p);
        ServerOdgovor serverOdgovor = new ServerOdgovor();
        try {
            so.izvrsiSO();
            serverOdgovor.setStatus(Status.ZAVRSENO);
        } catch (Exception ex) {
            serverOdgovor.setObjekat(ex);
            serverOdgovor.setStatus(Status.IZUZETAK);
        }
        return serverOdgovor;
    }

    static ServerOdgovor dodajPreparateTretmana(KlijentZahtev klijentZahtev) {

        List<TretmanPreparati> ltp = (List<TretmanPreparati>) klijentZahtev.getObjekat();
        GenerickaSistemskaOperacija so = new DodajPreparateTretmana(ltp);
        ServerOdgovor serverOdgovor = new ServerOdgovor();
        try {
            so.izvrsiSO();
            serverOdgovor.setStatus(Status.ZAVRSENO);
        } catch (Exception ex) {
            serverOdgovor.setObjekat(ex);
            serverOdgovor.setStatus(Status.IZUZETAK);
        }
        return serverOdgovor;
    }

    static ServerOdgovor dodajRaspored(KlijentZahtev klijentZahtev) {
        Raspored r = (Raspored) klijentZahtev.getObjekat();
        GenerickaSistemskaOperacija so = new DodajRaspored(r);
        ServerOdgovor serverOdgovor = new ServerOdgovor();
        try {
            so.izvrsiSO();
            serverOdgovor.setStatus(Status.ZAVRSENO);
        } catch (Exception ex) {
            serverOdgovor.setObjekat(ex);
            serverOdgovor.setStatus(Status.IZUZETAK);
        }
        return serverOdgovor;
    }

    static ServerOdgovor dodajTretman(KlijentZahtev klijentZahtev) {
        Tretman t = (Tretman) klijentZahtev.getObjekat();
        GenerickaSistemskaOperacija so = new DodajTretman(t);
        ServerOdgovor serverOdgovor = new ServerOdgovor();
        try {
            so.izvrsiSO();
            serverOdgovor.setObjekat(((DodajTretman) so).vratiIDTretmana());
            serverOdgovor.setStatus(Status.ZAVRSENO);
        } catch (Exception ex) {
            serverOdgovor.setObjekat(ex);
            serverOdgovor.setStatus(Status.IZUZETAK);
        }
        return serverOdgovor;
    }

    static ServerOdgovor dodajZaposlenog(KlijentZahtev klijentZahtev) {
        Zaposleni z = (Zaposleni) klijentZahtev.getObjekat();
        GenerickaSistemskaOperacija so = new DodajZaposlenog(z);
        ServerOdgovor serverOdgovor = new ServerOdgovor();
        try {
            so.izvrsiSO();
            serverOdgovor.setStatus(Status.ZAVRSENO);
        } catch (Exception ex) {
            serverOdgovor.setObjekat(ex);
            serverOdgovor.setStatus(Status.IZUZETAK);
        }
        return serverOdgovor;
    }

    static ServerOdgovor izmeniTretman(KlijentZahtev klijentZahtev) {
        Tretman tretman = (Tretman) klijentZahtev.getObjekat();
        GenerickaSistemskaOperacija so = new IzmeniTretman(tretman);
        ServerOdgovor serverOdgovor = new ServerOdgovor();

        try {
            so.izvrsiSO();
            serverOdgovor.setStatus(Status.ZAVRSENO);
        } catch (Exception ex) {
            serverOdgovor.setObjekat(ex);
            serverOdgovor.setStatus(Status.IZUZETAK);
        }
        return serverOdgovor;
    }

    static ServerOdgovor obrisiPreparateTretmana(KlijentZahtev klijentZahtev) {
        Tretman tretman = (Tretman) klijentZahtev.getObjekat();
        GenerickaSistemskaOperacija so = new ObrisiPreparateTretmana(tretman.getTretmanID());
        ServerOdgovor serverOdgovor = new ServerOdgovor();

        try {
            so.izvrsiSO();
            serverOdgovor.setStatus(Status.ZAVRSENO);
        } catch (Exception ex) {
            serverOdgovor.setObjekat(ex);
            serverOdgovor.setStatus(Status.IZUZETAK);
        }
        return serverOdgovor;
    }

    static ServerOdgovor obrisiRasporedeTretmana(KlijentZahtev klijentZahtev) {
        Tretman tretman = (Tretman) klijentZahtev.getObjekat();
        GenerickaSistemskaOperacija so = new ObrisiRasporedeTretmana(tretman.getTretmanID());
        ServerOdgovor serverOdgovor = new ServerOdgovor();

        try {
            so.izvrsiSO();
            serverOdgovor.setStatus(Status.ZAVRSENO);
        } catch (Exception ex) {
            serverOdgovor.setObjekat(ex);
            serverOdgovor.setStatus(Status.IZUZETAK);
        }
        return serverOdgovor;
    }

    static ServerOdgovor obrisiTretman(KlijentZahtev klijentZahtev) {
        Tretman tretman = (Tretman) klijentZahtev.getObjekat();
        GenerickaSistemskaOperacija so = new ObrisiTretman(tretman.getTretmanID());
        ServerOdgovor serverOdgovor = new ServerOdgovor();

        try {
            so.izvrsiSO();
            serverOdgovor.setStatus(Status.ZAVRSENO);
        } catch (Exception ex) {
            serverOdgovor.setObjekat(ex);
            serverOdgovor.setStatus(Status.IZUZETAK);
        }
        return serverOdgovor;
    }

    static ServerOdgovor stampajPDF(KlijentZahtev klijentZahtev) {
        Rezervacija r = (Rezervacija) klijentZahtev.getObjekat();
        GenerickaSistemskaOperacija so = new StampajRezervaciju(r);
        ServerOdgovor serverOdgovor = new ServerOdgovor();

        try {
            so.izvrsiSO();
            serverOdgovor.setStatus(Status.ZAVRSENO);
        } catch (Exception ex) {
            serverOdgovor.setObjekat(ex);
            serverOdgovor.setStatus(Status.IZUZETAK);
        }
        return serverOdgovor;
    }

    static ServerOdgovor vratiSveKompanijePoID(KlijentZahtev klijentZahtev) {
        Kompanija kompanija = (Kompanija) klijentZahtev.getObjekat();
        GenerickaSistemskaOperacija so = new VratiSveKompanijePoID(kompanija.getKompanijaID());
        ServerOdgovor serverOdgovor = new ServerOdgovor();

        try {
            so.izvrsiSO();
            serverOdgovor.setObjekat(((VratiSveKompanijePoID) so).vratiKompaniju());
            serverOdgovor.setStatus(Status.ZAVRSENO);
        } catch (Exception ex) {
            serverOdgovor.setObjekat(ex);
            serverOdgovor.setStatus(Status.IZUZETAK);
        }
        return serverOdgovor;
    }

    static ServerOdgovor vratiSveKorisnike(KlijentZahtev klijentZahtev) {
        Korisnik korisnik = (Korisnik) klijentZahtev.getObjekat();
        GenerickaSistemskaOperacija so = new VratiSveKorisnike();
        ServerOdgovor serverOdgovor = new ServerOdgovor();

        try {
            so.izvrsiSO();
            serverOdgovor.setObjekat(((VratiSveKorisnike) so).vratiListuKorisnika());
            serverOdgovor.setStatus(Status.ZAVRSENO);
        } catch (Exception ex) {
            serverOdgovor.setObjekat(ex);
            serverOdgovor.setStatus(Status.IZUZETAK);
        }
        return serverOdgovor;
    }

    static ServerOdgovor vratiSvePreparate(KlijentZahtev klijentZahtev) {
        Preparat preparat = (Preparat) klijentZahtev.getObjekat();
        GenerickaSistemskaOperacija so = new VratiSvePreparate();
        ServerOdgovor serverOdgovor = new ServerOdgovor();

        try {
            so.izvrsiSO();
            serverOdgovor.setObjekat(((VratiSvePreparate) so).vratiListuPreparata());
            serverOdgovor.setStatus(Status.ZAVRSENO);
        } catch (Exception ex) {
            serverOdgovor.setObjekat(ex);
            serverOdgovor.setStatus(Status.IZUZETAK);
        }
        return serverOdgovor;
    }

    static ServerOdgovor vratiSvePreparatePoID(KlijentZahtev klijentZahtev) {
        Preparat preparat = (Preparat) klijentZahtev.getObjekat();
        GenerickaSistemskaOperacija so = new VratiSvePreparatePoID(preparat.getPreparatID());
        ServerOdgovor serverOdgovor = new ServerOdgovor();

        try {
            so.izvrsiSO();
            serverOdgovor.setObjekat(((VratiSvePreparatePoID) so).vratiPreparat());
            serverOdgovor.setStatus(Status.ZAVRSENO);
        } catch (Exception ex) {
            serverOdgovor.setObjekat(ex);
            serverOdgovor.setStatus(Status.IZUZETAK);
        }
        return serverOdgovor;
    }

    static ServerOdgovor vratiSveRasporede(KlijentZahtev klijentZahtev) {
        Raspored raspored = (Raspored) klijentZahtev.getObjekat();
        GenerickaSistemskaOperacija so = new VratiSveRasporede("");
        ServerOdgovor serverOdgovor = new ServerOdgovor();

        try {
            so.izvrsiSO();
            serverOdgovor.setObjekat(((VratiSveRasporede) so).vratiListuRasporeda());
            serverOdgovor.setStatus(Status.ZAVRSENO);
        } catch (Exception ex) {
            serverOdgovor.setObjekat(ex);
            serverOdgovor.setStatus(Status.IZUZETAK);
        }
        return serverOdgovor;
    }

    static ServerOdgovor vratiSvePreparateTretmana(KlijentZahtev klijentZahtev) {
        Tretman tretman = (Tretman) klijentZahtev.getObjekat();
        GenerickaSistemskaOperacija so = new VratiSvePreparateTretmana(tretman.getTretmanID());
        ServerOdgovor serverOdgovor = new ServerOdgovor();

        try {
            so.izvrsiSO();
            serverOdgovor.setObjekat(((VratiSvePreparateTretmana) so).vratiListuPreparataTretmana());
            serverOdgovor.setStatus(Status.ZAVRSENO);
        } catch (Exception ex) {
            serverOdgovor.setObjekat(ex);
            serverOdgovor.setStatus(Status.IZUZETAK);
        }
        return serverOdgovor;
    }

    static ServerOdgovor vratiSveTretmane(KlijentZahtev klijentZahtev) {
        Tretman tretman = (Tretman) klijentZahtev.getObjekat();
        GenerickaSistemskaOperacija so = new VratiSveTretmane();
        ServerOdgovor serverOdgovor = new ServerOdgovor();

        try {
            so.izvrsiSO();
            serverOdgovor.setObjekat(((VratiSveTretmane) so).vratiListuTretmana());
            serverOdgovor.setStatus(Status.ZAVRSENO);
        } catch (Exception ex) {
            serverOdgovor.setObjekat(ex);
            serverOdgovor.setStatus(Status.IZUZETAK);
        }
        return serverOdgovor;
    }

    static ServerOdgovor vratiSveTretmanePoID(KlijentZahtev klijentZahtev) {
        Tretman tretman = (Tretman) klijentZahtev.getObjekat();
        GenerickaSistemskaOperacija so = new VratiTretmanePoID(tretman.getTretmanID());
        ServerOdgovor serverOdgovor = new ServerOdgovor();

        try {
            so.izvrsiSO();
            serverOdgovor.setObjekat(((VratiTretmanePoID) so).vratiTretman());
            serverOdgovor.setStatus(Status.ZAVRSENO);
        } catch (Exception ex) {
            serverOdgovor.setObjekat(ex);
            serverOdgovor.setStatus(Status.IZUZETAK);
        }
        return serverOdgovor;
    }

    static ServerOdgovor vratiSveZaposlene(KlijentZahtev klijentZahtev) {
        //    Zaposleni tretman = (Tretman) klijentZahtev.getObjekat();
        GenerickaSistemskaOperacija so = new VratiSveZaposlene();
        ServerOdgovor serverOdgovor = new ServerOdgovor();

        try {
            so.izvrsiSO();
            serverOdgovor.setObjekat(((VratiSveZaposlene) so).vratiListuZaposlenih());
            serverOdgovor.setStatus(Status.ZAVRSENO);
        } catch (Exception ex) {
            serverOdgovor.setObjekat(ex);
            serverOdgovor.setStatus(Status.IZUZETAK);
        }
        return serverOdgovor;
    }

    static ServerOdgovor vratiSveZaposlenePoID(KlijentZahtev klijentZahtev) {
        Zaposleni zaposleni = (Zaposleni) klijentZahtev.getObjekat();
        GenerickaSistemskaOperacija so = new VratiZaposlenePoID(zaposleni.getZaposleniID());
        ServerOdgovor serverOdgovor = new ServerOdgovor();

        try {
            so.izvrsiSO();
            serverOdgovor.setObjekat(((VratiZaposlenePoID) so).vratiZaposlenog());
            serverOdgovor.setStatus(Status.ZAVRSENO);
        } catch (Exception ex) {
            serverOdgovor.setObjekat(ex);
            serverOdgovor.setStatus(Status.IZUZETAK);
        }
        return serverOdgovor;
    }

}
