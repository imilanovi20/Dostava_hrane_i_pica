package edu.unizg.foi.nwtis.imilanovi20.vjezba_08_dz_3.jf;

import java.io.Serializable;
import java.util.List;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import edu.unizg.foi.nwtis.imilanovi20.vjezba_08_dz_3.ServisPartnerKlijent;
import edu.unizg.foi.nwtis.imilanovi20.vjezba_08_dz_3.jpa.entiteti.Korisnici;
import edu.unizg.foi.nwtis.imilanovi20.vjezba_08_dz_3.jpa.pomocnici.GrupeFacade;
import edu.unizg.foi.nwtis.imilanovi20.vjezba_08_dz_3.jpa.pomocnici.KorisniciFacade;
import edu.unizg.foi.nwtis.podaci.Korisnik;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.Response;

// TODO: Auto-generated Javadoc
/**
 * The Class UpravljanjeKorisnicima.
 */
@SessionScoped
@Named("upravljanjeKorisnicima")
public class UpravljanjeKorisnicima implements Serializable {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 484545790534292711L;

  /** The servis partner. */
  @Inject
  @RestClient
  private ServisPartnerKlijent servisPartner;

  @Inject
  private GrupeFacade grupeFacade;

  /** The korisnici facade. */
  @Inject
  private KorisniciFacade korisniciFacade;

  /** The novi korisnik. */
  private String noviKorisnik;

  /** The novo ime. */
  private String novoIme;

  /** The novo prezime. */
  private String novoPrezime;

  /** The nova lozinka. */
  private String novaLozinka;

  /** The novi email. */
  private String noviEmail;

  /** The korisnk dodan. */
  private boolean korisnkDodan;

  /** The poruka dodan. */
  private String porukaDodan;

  /** The ime pretrazivanje. */
  private String imePretrazivanje;

  /** The prezime pretrazivanje. */
  private String prezimePretrazivanje;

  /** The korisnici pretrazivanje. */
  private List<Korisnici> korisniciPretrazivanje;


  /** The svi korisnici. */
  private Korisnik[] sviKorisnici;

  /** The status dohvaceni korisnici. */
  private Integer statusDohvaceniKorisnici;

  /** The id trazenog korisnika. */
  private String idTrazenogKorisnika;

  /** The trazeni korisnik. */
  private Korisnik trazeniKorisnik;

  /** The status dohvacen korisnik. */
  private Integer statusDohvacenKorisnik;



  /**
   * Inits the.
   */
  @PostConstruct
  public void init() {
    dohvatiSveKorisnike();
  }

  /**
   * Dodaj korisnika.
   *
   * @return the string
   */
  public String dodajKorisnika() {
    try {
      Korisnik dodaniKorisnik =
          new Korisnik(noviKorisnik, novaLozinka, novoPrezime, novoIme, noviEmail);
      Response odgovor = servisPartner.postKorisnik(dodaniKorisnik);

      if (odgovor.getStatus() == 201) {
        korisnkDodan = true;
        Korisnici korisnikUBazu = korisniciFacade.find(noviKorisnik, novaLozinka);
        grupeFacade.pridruziKorisnikaGrupi(korisnikUBazu, "nwtis");
        porukaDodan = "Korisnik uspiješno dodan";
      } else if (odgovor.getStatus() == 409) {
        korisnkDodan = false;
        porukaDodan = "Korisnik s korisničim imenom već postoji";
      } else {
        korisnkDodan = false;
        porukaDodan = "Korisnik nije dodan: " + odgovor.getStatus();
      }
    } catch (Exception e) {
      korisnkDodan = false;
      porukaDodan = "Greška s poslužiteljem: " + e.getMessage();
    }

    ocistiPolja();
    return null;
  }

  /**
   * Dohvati sve korisnike.
   */
  public void dohvatiSveKorisnike() {
    try {
      Response odgovor = servisPartner.getKorisnici();
      this.statusDohvaceniKorisnici = odgovor.getStatus();
      if (odgovor.getStatus() == 200) {
        this.sviKorisnici = odgovor.readEntity(new GenericType<Korisnik[]>() {});
      }
    } catch (WebApplicationException e) {
      this.statusDohvaceniKorisnici = e.getResponse().getStatus();
    } catch (Exception e) {
      this.statusDohvaceniKorisnici = 500;
    }
  }

  /**
   * Dohvati odabranog korisnika.
   *
   * @return the string
   */
  public String dohvatiOdabranogKorisnika() {
    try {
      Response odgovor = servisPartner.getKorisnik(idTrazenogKorisnika);
      this.statusDohvacenKorisnik = odgovor.getStatus();
      if (odgovor.getStatus() == 200) {
        this.trazeniKorisnik = odgovor.readEntity(new GenericType<Korisnik>() {});
      }
      return "/admin/korisnik.xhtml?faces-redirect=true";
    } catch (WebApplicationException e) {
      this.statusDohvacenKorisnik = e.getResponse().getStatus();
      return "";
    } catch (Exception e) {
      this.statusDohvacenKorisnik = 500;
      return "";
    }
  }

  /**
   * Pronadi korisnike.
   */
  public void pronadiKorisnike() {
    try {
      this.korisniciPretrazivanje =
          this.korisniciFacade.findByPrezimeIme(this.imePretrazivanje, this.prezimePretrazivanje);
    } catch (Exception e) {
    }
  }

  /**
   * Ocisti polja.
   */
  private void ocistiPolja() {
    noviKorisnik = "";
    novaLozinka = "";
    novoIme = "";
    novoPrezime = "";
    noviEmail = "";
  }

  /**
   * Gets the novi korisnik.
   *
   * @return the novi korisnik
   */
  public String getNoviKorisnik() {
    return noviKorisnik;
  }

  /**
   * Sets the novi korisnik.
   *
   * @param noviKorisnik the new novi korisnik
   */
  public void setNoviKorisnik(String noviKorisnik) {
    this.noviKorisnik = noviKorisnik;
  }

  /**
   * Gets the novo ime.
   *
   * @return the novo ime
   */
  public String getNovoIme() {
    return novoIme;
  }

  /**
   * Sets the novo ime.
   *
   * @param novoIme the new novo ime
   */
  public void setNovoIme(String novoIme) {
    this.novoIme = novoIme;
  }

  /**
   * Gets the novo prezime.
   *
   * @return the novo prezime
   */
  public String getNovoPrezime() {
    return novoPrezime;
  }

  /**
   * Sets the novo prezime.
   *
   * @param novoPrezime the new novo prezime
   */
  public void setNovoPrezime(String novoPrezime) {
    this.novoPrezime = novoPrezime;
  }

  /**
   * Gets the nova lozinka.
   *
   * @return the nova lozinka
   */
  public String getNovaLozinka() {
    return novaLozinka;
  }

  /**
   * Sets the nova lozinka.
   *
   * @param novaLozinka the new nova lozinka
   */
  public void setNovaLozinka(String novaLozinka) {
    this.novaLozinka = novaLozinka;
  }

  /**
   * Gets the novi email.
   *
   * @return the novi email
   */
  public String getNoviEmail() {
    return noviEmail;
  }

  /**
   * Sets the novi email.
   *
   * @param noviEmail the new novi email
   */
  public void setNoviEmail(String noviEmail) {
    this.noviEmail = noviEmail;
  }

  /**
   * Checks if is korisnk dodan.
   *
   * @return true, if is korisnk dodan
   */
  public boolean isKorisnkDodan() {
    return korisnkDodan;
  }

  /**
   * Gets the poruka dodan.
   *
   * @return the poruka dodan
   */
  public String getPorukaDodan() {
    return porukaDodan;
  }

  /**
   * Gets the svi korisnici.
   *
   * @return the svi korisnici
   */
  public Korisnik[] getSviKorisnici() {
    return sviKorisnici;
  }

  /**
   * Sets the svi korisnici.
   *
   * @param sviKorisnici the new svi korisnici
   */
  public void setSviKorisnici(Korisnik[] sviKorisnici) {
    this.sviKorisnici = sviKorisnici;
  }

  /**
   * Gets the status dohvaceni korisnici.
   *
   * @return the status dohvaceni korisnici
   */
  public Integer getStatusDohvaceniKorisnici() {
    return statusDohvaceniKorisnici;
  }

  /**
   * Sets the status dohvaceni korisnici.
   *
   * @param statusDohvaceniKorisnici the new status dohvaceni korisnici
   */
  public void setStatusDohvaceniKorisnici(Integer statusDohvaceniKorisnici) {
    this.statusDohvaceniKorisnici = statusDohvaceniKorisnici;
  }

  /**
   * Gets the id trazenog korisnika.
   *
   * @return the id trazenog korisnika
   */
  public String getIdTrazenogKorisnika() {
    return idTrazenogKorisnika;
  }

  /**
   * Sets the id trazenog korisnika.
   *
   * @param idTrazenogKorisnika the new id trazenog korisnika
   */
  public void setIdTrazenogKorisnika(String idTrazenogKorisnika) {
    this.idTrazenogKorisnika = idTrazenogKorisnika;
  }

  /**
   * Gets the trazeni korisnik.
   *
   * @return the trazeni korisnik
   */
  public Korisnik getTrazeniKorisnik() {
    return trazeniKorisnik;
  }

  /**
   * Sets the trazeni korisnik.
   *
   * @param trazeniKorisnik the new trazeni korisnik
   */
  public void setTrazeniKorisnik(Korisnik trazeniKorisnik) {
    this.trazeniKorisnik = trazeniKorisnik;
  }

  /**
   * Gets the status dohvacen korisnik.
   *
   * @return the status dohvacen korisnik
   */
  public Integer getStatusDohvacenKorisnik() {
    return statusDohvacenKorisnik;
  }

  /**
   * Sets the status dohvacen korisnik.
   *
   * @param statusDohvacenKorisnik the new status dohvacen korisnik
   */
  public void setStatusDohvacenKorisnik(Integer statusDohvacenKorisnik) {
    this.statusDohvacenKorisnik = statusDohvacenKorisnik;
  }

  /**
   * Gets the ime pretrazivanje.
   *
   * @return the ime pretrazivanje
   */
  public String getImePretrazivanje() {
    return imePretrazivanje;
  }

  /**
   * Sets the ime pretrazivanje.
   *
   * @param imePretrazivanje the new ime pretrazivanje
   */
  public void setImePretrazivanje(String imePretrazivanje) {
    this.imePretrazivanje = imePretrazivanje;
  }

  /**
   * Gets the prezime pretrazivanje.
   *
   * @return the prezime pretrazivanje
   */
  public String getPrezimePretrazivanje() {
    return prezimePretrazivanje;
  }

  /**
   * Sets the prezime pretrazivanje.
   *
   * @param prezimePretrazivanje the new prezime pretrazivanje
   */
  public void setPrezimePretrazivanje(String prezimePretrazivanje) {
    this.prezimePretrazivanje = prezimePretrazivanje;
  }

  /**
   * Gets the korisnici pretrazivanje.
   *
   * @return the korisnici pretrazivanje
   */
  public List<Korisnici> getKorisniciPretrazivanje() {
    return korisniciPretrazivanje;
  }

  /**
   * Sets the korisnici pretrazivanje.
   *
   * @param korisniciPretrazivanje the new korisnici pretrazivanje
   */
  public void setKorisniciPretrazivanje(List<Korisnici> korisniciPretrazivanje) {
    this.korisniciPretrazivanje = korisniciPretrazivanje;
  }



}
