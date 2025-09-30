package edu.unizg.foi.nwtis.imilanovi20.vjezba_08_dz_3.jf;


import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import edu.unizg.foi.nwtis.imilanovi20.vjezba_08_dz_3.jpa.entiteti.Partneri;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

// TODO: Auto-generated Javadoc
/**
 * The Class PartnerKorisnik.
 */
@ApplicationScoped
@Named("partnerKorisnik")
public class PartnerKorisnik implements Serializable {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = -1289210636045127109L;

  /** The lista partnera korisnika. */
  private Map<String, Partneri> listaPartneraKorisnika = new HashMap<String, Partneri>();


  /**
   * Dodaj partnera za korisnik.
   *
   * @param korisnik the korisnik
   * @param partner the partner
   */
  public void dodajPartneraZaKorisnik(String korisnik, Partneri partner) {
    listaPartneraKorisnika.put(korisnik, partner);
  }

  /**
   * Ukloni partner za korisnika.
   *
   * @param korisnik the korisnik
   */
  public void ukloniPartnerZaKorisnika(String korisnik) {
    listaPartneraKorisnika.remove(korisnik);
  }

  /**
   * Dohvati partnera zakorisnika.
   *
   * @param korisnik the korisnik
   * @return the partneri
   */
  public Partneri dohvatiPartneraZakorisnika(String korisnik) {
    return listaPartneraKorisnika.get(korisnik);
  }


}
