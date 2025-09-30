package edu.unizg.foi.nwtis.imilanovi20.vjezba_08_dz_3.jf;

import java.io.Serializable;
import edu.unizg.foi.nwtis.imilanovi20.vjezba_08_dz_3.jpa.entiteti.Partneri;
import edu.unizg.foi.nwtis.imilanovi20.vjezba_08_dz_3.jpa.pomocnici.KorisniciFacade;
import edu.unizg.foi.nwtis.imilanovi20.vjezba_08_dz_3.jpa.pomocnici.ZapisiFacade;
import edu.unizg.foi.nwtis.podaci.Korisnik;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.security.enterprise.SecurityContext;

// TODO: Auto-generated Javadoc
/**
 * The Class PrijavaKorisnika.
 */
@SessionScoped
@Named("prijavaKorisnika")
public class PrijavaKorisnika implements Serializable {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = -1826447622277477398L;

  /** The korisnicko ime. */
  private String korisnickoIme;

  /** The lozinka. */
  private String lozinka;

  /** The korisnik. */
  private Korisnik korisnik;

  /** The prijavljen. */
  private boolean prijavljen = false;

  /** The poruka. */
  private String poruka = "";

  /** The odabrani partner. */
  private Partneri odabraniPartner;

  /** The partner odabran. */
  private boolean partnerOdabran = false;

  /** The postoji narudzba. */
  private boolean postojiNarudzba = false;

  /** The provjeri admin. */
  private boolean provjeriAdmin = false;

  /** The trazeni partner. */
  private Partneri trazeniPartner;


  /** The rest configuration. */
  @Inject
  RestConfiguration restConfiguration;

  /** The korisnici facade. */
  @Inject
  KorisniciFacade korisniciFacade;

  /** The security context. */
  @Inject
  private SecurityContext securityContext;

  /** The zapisi facade. */
  @Inject
  ZapisiFacade zapisiFacade;


  /** The partner korisnik. */
  @Inject
  PartnerKorisnik partnerKorisnik;

  /**
   * Checks if is provjeri admin.
   *
   * @return true, if is provjeri admin
   */
  public boolean isProvjeriAdmin() {
    boolean admin = securityContext.isCallerInRole("admin");
    this.provjeriAdmin = admin;
    return admin;
  }

  /**
   * Sets the provjeri admin.
   *
   * @param provjeriAdmin the new provjeri admin
   */
  public void setProvjeriAdmin(boolean provjeriAdmin) {
    this.provjeriAdmin = provjeriAdmin;
  }

  /**
   * Gets the korisnicko ime.
   *
   * @return the korisnicko ime
   */
  public String getKorisnickoIme() {
    return korisnickoIme;
  }

  /**
   * Sets the korisnicko ime.
   *
   * @param korisnickoIme the new korisnicko ime
   */
  public void setKorisnickoIme(String korisnickoIme) {
    this.korisnickoIme = korisnickoIme;
  }

  /**
   * Gets the lozinka.
   *
   * @return the lozinka
   */
  public String getLozinka() {
    return lozinka;
  }

  /**
   * Sets the lozinka.
   *
   * @param lozinka the new lozinka
   */
  public void setLozinka(String lozinka) {
    this.lozinka = lozinka;
  }

  /**
   * Gets the ime.
   *
   * @return the ime
   */
  public String getIme() {
    return this.korisnik.ime();
  }

  /**
   * Gets the prezime.
   *
   * @return the prezime
   */
  public String getPrezime() {
    return this.korisnik.prezime();
  }

  /**
   * Gets the email.
   *
   * @return the email
   */
  public String getEmail() {
    return this.korisnik.email();
  }

  /**
   * Checks if is prijavljen.
   *
   * @return true, if is prijavljen
   */
  public boolean isPrijavljen() {
    if (!this.prijavljen) {
      provjeriPrijavuKorisnika();
    }
    return this.prijavljen;
  }

  /**
   * Gets the poruka.
   *
   * @return the poruka
   */
  public String getPoruka() {
    return poruka;
  }

  /**
   * Gets the odabrani partner.
   *
   * @return the odabrani partner
   */
  public Partneri getOdabraniPartner() {
    return odabraniPartner;
  }

  /**
   * Sets the odabrani partner.
   *
   * @param odabraniPartner the new odabrani partner
   */
  public void setOdabraniPartner(Partneri odabraniPartner) {
    this.odabraniPartner = odabraniPartner;
  }

  /**
   * Checks if is partner odabran.
   *
   * @return true, if is partner odabran
   */
  public boolean isPartnerOdabran() {
    return partnerOdabran;
  }

  /**
   * Sets the partner odabran.
   *
   * @param partnerOdabran the new partner odabran
   */
  public void setPartnerOdabran(boolean partnerOdabran) {
    partnerKorisnik.dodajPartneraZaKorisnik(korisnickoIme, odabraniPartner);
    this.partnerOdabran = partnerOdabran;
  }

  /**
   * Checks if is postoji narudzba.
   *
   * @return true, if is postoji narudzba
   */
  public boolean isPostojiNarudzba() {
    return postojiNarudzba;
  }

  /**
   * Sets the postoji narudzba.
   *
   * @param postojiNarudzba the new postoji narudzba
   */
  public void setPostojiNarudzba(boolean postojiNarudzba) {
    this.postojiNarudzba = postojiNarudzba;
  }

  /**
   * Inits the.
   */
  @PostConstruct
  public void init() {
    provjeriPrijavuKorisnika();
    provjeriJeLiAdmin();
  }

  /**
   * Provjeri prijavu korisnika.
   */
  private void provjeriPrijavuKorisnika() {
    if (this.securityContext.getCallerPrincipal() != null) {
      var korIme = this.securityContext.getCallerPrincipal().getName();
      this.korisnik = this.korisniciFacade.pretvori(this.korisniciFacade.find(korIme));
      if (this.korisnik != null) {
        this.prijavljen = true;
        this.korisnickoIme = korIme;
        this.lozinka = this.korisnik.lozinka();
        zapisiFacade.dodajZapis(korisnickoIme, "prijava");
        this.odabraniPartner = partnerKorisnik.dohvatiPartneraZakorisnika(korisnickoIme);
        if (odabraniPartner != null) {
          this.partnerOdabran = true;
        }
      }
    }
  }

  /**
   * Provjeri je li admin.
   *
   * @return true, if successful
   */
  public boolean provjeriJeLiAdmin() {
    if (securityContext.isCallerInRole("admin")) {
      this.provjeriAdmin = true;
    }
    return securityContext.isCallerInRole("admin");
  }

  /**
   * Odjava korisnika.
   *
   * @return the string
   */
  public String odjavaKorisnika() {
    if (this.prijavljen) {
      this.prijavljen = false;

      FacesContext facesContext = FacesContext.getCurrentInstance();
      facesContext.getExternalContext().invalidateSession();

      zapisiFacade.dodajZapis(this.korisnickoIme, "odjava");
      return "/index.xhtml?faces-redirect=true";
    }
    return "";
  }

  /**
   * Gets the trazeni partner.
   *
   * @return the trazeni partner
   */
  public Partneri getTrazeniPartner() {
    return trazeniPartner;
  }

  /**
   * Sets the trazeni partner.
   *
   * @param trazeniPartner the new trazeni partner
   */
  public void setTrazeniPartner(Partneri trazeniPartner) {
    this.trazeniPartner = trazeniPartner;
  }

}
