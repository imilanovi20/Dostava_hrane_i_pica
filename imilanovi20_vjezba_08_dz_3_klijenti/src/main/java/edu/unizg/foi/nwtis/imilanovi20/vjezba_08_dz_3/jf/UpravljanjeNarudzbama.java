package edu.unizg.foi.nwtis.imilanovi20.vjezba_08_dz_3.jf;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import edu.unizg.foi.nwtis.imilanovi20.vjezba_08_dz_3.GlobalniPodaci;
import edu.unizg.foi.nwtis.imilanovi20.vjezba_08_dz_3.ServisPartnerKlijent;
import edu.unizg.foi.nwtis.imilanovi20.vjezba_08_dz_3.jpa.pomocnici.ZapisiFacade;
import edu.unizg.foi.nwtis.podaci.Jelovnik;
import edu.unizg.foi.nwtis.podaci.KartaPica;
import edu.unizg.foi.nwtis.podaci.Narudzba;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.Response;

// TODO: Auto-generated Javadoc
/**
 * The Class UpravljanjeNarudzbama.
 */
@SessionScoped
@Named("upravljanjeNarudzbama")
public class UpravljanjeNarudzbama implements Serializable {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = -5620422345201270664L;


  /** The servis partner. */
  @Inject
  @RestClient
  private ServisPartnerKlijent servisPartner;

  /** The prijava korisnika. */
  @Inject
  @Named("prijavaKorisnika")
  private PrijavaKorisnika prijavaKorisnika;

  /** The zapisi facade. */
  @Inject
  ZapisiFacade zapisiFacade;

  /** The globalni podaci. */
  @Inject
  GlobalniPodaci globalniPodaci;

  /** The korisnik. */
  private String korisnik;

  /** The lozinka. */
  private String lozinka;

  /** The jelovnik partnera. */
  private Jelovnik[] jelovnikPartnera;

  /** The karta pica partnera. */
  private KartaPica[] kartaPicaPartnera;

  /** The status jelovnik. */
  private Integer statusJelovnik;

  /** The status karta pica. */
  private Integer statusKartaPica;

  /** The status postoji narudzba. */
  private Integer statusPostojiNarudzba;

  /** The stavke narudzbe. */
  private Narudzba[] stavkeNarudzbe;

  /** The nova narudzba. */
  private Narudzba novaNarudzba;

  /** The status kreirana narudzba. */
  private Integer statusKreiranaNarudzba;

  /** The nova narudzba id. */
  private String novaNarudzbaId;

  /** The nova narudzba tip. */
  private boolean novaNarudzbaTip;

  /** The nova narudzba kolicina. */
  private Float novaNarudzbaKolicina = 0.0f;

  /** The kolicine. */
  private Map<String, Float> kolicine = new HashMap<>();

  /** The nova narudzba cijena. */
  private Float novaNarudzbaCijena;

  /** The status dodano jelo. */
  private Integer statusDodanoJelo;

  /** The status dodano pice. */
  private Integer statusDodanoPice;

  /** The status placen racun. */
  private Integer statusPlacenRacun;

  /**
   * Inits the.
   */
  @PostConstruct
  public void init() {
    if (prijavaKorisnika.isPrijavljen()) {
      this.korisnik = prijavaKorisnika.getKorisnickoIme();
      this.lozinka = prijavaKorisnika.getLozinka();
      dohvatiJelovnik();
      dohvatiKartuPica();
      dohvatiStavkeNarudzbe();
    }
  }

  /**
   * Dohvati jelovnik.
   */
  public void dohvatiJelovnik() {
    try {
      if (this.korisnik == null && prijavaKorisnika.isPrijavljen()) {
        this.korisnik = prijavaKorisnika.getKorisnickoIme();
        this.lozinka = prijavaKorisnika.getLozinka();
      }

      Response odgovor = servisPartner.getJelovnik(this.korisnik, this.lozinka);
      statusJelovnik = odgovor.getStatus();

      if (statusJelovnik == 200) {
        this.jelovnikPartnera = odgovor.readEntity(new GenericType<Jelovnik[]>() {});
      }
    } catch (WebApplicationException e) {
      statusJelovnik = e.getResponse().getStatus();
    } catch (Exception e) {
      statusJelovnik = 500;
    }
  }

  /**
   * Dohvati kartu pica.
   */
  public void dohvatiKartuPica() {
    try {
      if (this.korisnik == null && prijavaKorisnika.isPrijavljen()) {
        this.korisnik = prijavaKorisnika.getKorisnickoIme();
        this.lozinka = prijavaKorisnika.getLozinka();
      }

      Response odgovor = servisPartner.getKartaPica(this.korisnik, this.lozinka);
      statusKartaPica = odgovor.getStatus();

      if (statusKartaPica == 200) {
        this.kartaPicaPartnera = odgovor.readEntity(new GenericType<KartaPica[]>() {});
      }
    } catch (WebApplicationException e) {
      statusKartaPica = e.getResponse().getStatus();
    } catch (Exception e) {
      statusKartaPica = 500;
    }
  }


  /**
   * Dohvati stavke narudzbe.
   */
  public void dohvatiStavkeNarudzbe() {
    try {
      if (this.korisnik == null && prijavaKorisnika.isPrijavljen()) {
        this.korisnik = prijavaKorisnika.getKorisnickoIme();
        this.lozinka = prijavaKorisnika.getLozinka();
      }
      Response odgovor = servisPartner.getNarudzbe(korisnik, lozinka);
      this.statusPostojiNarudzba = odgovor.getStatus();
      if (statusPostojiNarudzba == 200) {
        this.stavkeNarudzbe = odgovor.readEntity(new GenericType<Narudzba[]>() {});
      }
    } catch (WebApplicationException e) {
      this.statusPostojiNarudzba = e.getResponse().getStatus();
    } catch (Exception e) {
      this.statusPostojiNarudzba = 500;
    }
  }

  /**
   * Dodaj novu narudzbu.
   */
  public void dodajNovuNarudzbu() {
    try {
      if (this.korisnik == null && prijavaKorisnika.isPrijavljen()) {
        this.korisnik = prijavaKorisnika.getKorisnickoIme();
        this.lozinka = prijavaKorisnika.getLozinka();
      }
      Response odgovor = servisPartner.postNarudzba(this.korisnik, this.lozinka);
      this.statusKreiranaNarudzba = odgovor.getStatus();
      if (odgovor.getStatus() == 201) {
        zapisiFacade.dodajZapis(korisnik, "Nova narudžba kreirana");
        globalniPodaci.povecajBrojOtvorenihNarudzbi(prijavaKorisnika.getOdabraniPartner().getId());
      }
      dohvatiStavkeNarudzbe();
    } catch (WebApplicationException e) {
      this.statusKreiranaNarudzba = e.getResponse().getStatus();
    } catch (Exception e) {
      this.statusKreiranaNarudzba = 500;
    }
  }

  /**
   * Dodaj jelo.
   */
  public void dodajJelo() {
    try {
      if (this.korisnik == null && prijavaKorisnika.isPrijavljen()) {
        this.korisnik = prijavaKorisnika.getKorisnickoIme();
        this.lozinka = prijavaKorisnika.getLozinka();
      }
      Narudzba novaNarudzba = new Narudzba(this.korisnik, this.novaNarudzbaId, this.novaNarudzbaTip,
          this.novaNarudzbaKolicina, this.novaNarudzbaCijena, System.currentTimeMillis());;

      Response odgovor = servisPartner.postJelo(this.korisnik, this.lozinka, novaNarudzba);
      this.statusDodanoJelo = odgovor.getStatus();
      dohvatiStavkeNarudzbe();
      ocistiNarudzbu();
    } catch (WebApplicationException e) {
      this.statusDodanoJelo = e.getResponse().getStatus();
    } catch (Exception e) {
      this.statusDodanoJelo = 500;
    }
  }

  /**
   * Dodaj pice.
   */
  public void dodajPice() {
    try {
      if (this.korisnik == null && prijavaKorisnika.isPrijavljen()) {
        this.korisnik = prijavaKorisnika.getKorisnickoIme();
        this.lozinka = prijavaKorisnika.getLozinka();
      }
      Narudzba novaNarudzba = new Narudzba(this.korisnik, this.novaNarudzbaId, this.novaNarudzbaTip,
          this.novaNarudzbaKolicina, this.novaNarudzbaCijena, System.currentTimeMillis());;

      Response odgovor = servisPartner.postPice(this.korisnik, this.lozinka, novaNarudzba);
      this.statusDodanoPice = odgovor.getStatus();
      dohvatiStavkeNarudzbe();
      ocistiNarudzbu();
    } catch (WebApplicationException e) {
      this.statusDodanoPice = e.getResponse().getStatus();
    } catch (Exception e) {
      this.statusDodanoPice = 500;
    }
  }

  /**
   * Plati racun.
   *
   * @return the string
   */
  public String platiRacun() {
    try {
      if (this.korisnik == null && prijavaKorisnika.isPrijavljen()) {
        this.korisnik = prijavaKorisnika.getKorisnickoIme();
        this.lozinka = prijavaKorisnika.getLozinka();
      }
      Response odgovor = servisPartner.postRacun(this.korisnik, this.lozinka);
      this.statusPlacenRacun = odgovor.getStatus();
      this.statusPlacenRacun = odgovor.getStatus();
      if (odgovor.getStatus() == 201) {
        this.statusPostojiNarudzba = 500;
        ocistiNarudzbu();
        dohvatiStavkeNarudzbe();
        zapisiFacade.dodajZapis(korisnik, "Plačena narudžba");
        globalniPodaci.povecajBrojRacuna(prijavaKorisnika.getOdabraniPartner().getId());
        globalniPodaci.smanjiBrojOtvorenihNarudzbi(prijavaKorisnika.getOdabraniPartner().getId());
        return "/privatno/uspjesnoPlacanje.xhtml?faces-redirect=true";
      }
      return "/privatno/neuspjesnoPlacanje.xhtml?faces-redirect=true";
    } catch (WebApplicationException e) {
      this.statusPlacenRacun = e.getResponse().getStatus();
      return "/privatno/neuspjesnoPlacanje.xhtml?faces-redirect=true";
    } catch (Exception e) {
      this.statusPlacenRacun = 500;
      return "/privatno/neuspjesnoPlacanje.xhtml?faces-redirect=true";
    }
  }

  /**
   * Ocisti narudzbu.
   */
  private void ocistiNarudzbu() {
    this.novaNarudzbaId = "";
    this.novaNarudzbaKolicina = 0.0f;
    this.novaNarudzbaCijena = 0.0f;
  }

  /**
   * Gets the korisnik.
   *
   * @return the korisnik
   */
  public String getKorisnik() {
    return korisnik;
  }

  /**
   * Sets the korisnik.
   *
   * @param korisnik the new korisnik
   */
  public void setKorisnik(String korisnik) {
    this.korisnik = korisnik;
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
   * Gets the jelovnik partnera.
   *
   * @return the jelovnik partnera
   */
  public Jelovnik[] getJelovnikPartnera() {
    return jelovnikPartnera;
  }

  /**
   * Sets the jelovnik partnera.
   *
   * @param jelovnikPartnera the new jelovnik partnera
   */
  public void setJelovnikPartnera(Jelovnik[] jelovnikPartnera) {
    this.jelovnikPartnera = jelovnikPartnera;
  }

  /**
   * Gets the karta pica partnera.
   *
   * @return the karta pica partnera
   */
  public KartaPica[] getKartaPicaPartnera() {
    return kartaPicaPartnera;
  }

  /**
   * Sets the karta pica partnera.
   *
   * @param kartaPicaPartnera the new karta pica partnera
   */
  public void setKartaPicaPartnera(KartaPica[] kartaPicaPartnera) {
    this.kartaPicaPartnera = kartaPicaPartnera;
  }

  /**
   * Gets the status jelovnik.
   *
   * @return the status jelovnik
   */
  public Integer getStatusJelovnik() {
    return statusJelovnik;
  }

  /**
   * Sets the status jelovnik.
   *
   * @param statusJelovnik the new status jelovnik
   */
  public void setStatusJelovnik(Integer statusJelovnik) {
    this.statusJelovnik = statusJelovnik;
  }

  /**
   * Gets the status karta pica.
   *
   * @return the status karta pica
   */
  public Integer getStatusKartaPica() {
    return statusKartaPica;
  }

  /**
   * Sets the status karta pica.
   *
   * @param statusKartaPica the new status karta pica
   */
  public void setStatusKartaPica(Integer statusKartaPica) {
    this.statusKartaPica = statusKartaPica;
  }

  /**
   * Gets the status postoji narudzba.
   *
   * @return the status postoji narudzba
   */
  public Integer getStatusPostojiNarudzba() {
    return statusPostojiNarudzba;
  }

  /**
   * Sets the status postoji narudzba.
   *
   * @param statusPostojiNarudzba the new status postoji narudzba
   */
  public void setStatusPostojiNarudzba(Integer statusPostojiNarudzba) {
    this.statusPostojiNarudzba = statusPostojiNarudzba;
  }

  /**
   * Gets the stavke narudzbe.
   *
   * @return the stavke narudzbe
   */
  public Narudzba[] getStavkeNarudzbe() {
    return stavkeNarudzbe;
  }

  /**
   * Sets the stavke narudzbe.
   *
   * @param stavkeNarudzbe the new stavke narudzbe
   */
  public void setStavkeNarudzbe(Narudzba[] stavkeNarudzbe) {
    this.stavkeNarudzbe = stavkeNarudzbe;
  }

  /**
   * Gets the status kreirana narudzba.
   *
   * @return the status kreirana narudzba
   */
  public Integer getStatusKreiranaNarudzba() {
    return statusKreiranaNarudzba;
  }

  /**
   * Sets the status kreirana narudzba.
   *
   * @param statusKreiranaNarudzba the new status kreirana narudzba
   */
  public void setStatusKreiranaNarudzba(Integer statusKreiranaNarudzba) {
    this.statusKreiranaNarudzba = statusKreiranaNarudzba;
  }

  /**
   * Gets the nova narudzba.
   *
   * @return the nova narudzba
   */
  public Narudzba getNovaNarudzba() {
    return novaNarudzba;
  }

  /**
   * Sets the nova narudzba.
   *
   * @param novaNarudzba the new nova narudzba
   */
  public void setNovaNarudzba(Narudzba novaNarudzba) {
    this.novaNarudzba = novaNarudzba;
  }

  /**
   * Gets the nova narudzba id.
   *
   * @return the nova narudzba id
   */
  public String getNovaNarudzbaId() {
    return novaNarudzbaId;
  }

  /**
   * Sets the nova narudzba id.
   *
   * @param novaNarudzbaId the new nova narudzba id
   */
  public void setNovaNarudzbaId(String novaNarudzbaId) {
    this.novaNarudzbaId = novaNarudzbaId;
  }

  /**
   * Checks if is nova narudzba tip.
   *
   * @return true, if is nova narudzba tip
   */
  public boolean isNovaNarudzbaTip() {
    return novaNarudzbaTip;
  }

  /**
   * Sets the nova narudzba tip.
   *
   * @param novaNarudzbaTip the new nova narudzba tip
   */
  public void setNovaNarudzbaTip(boolean novaNarudzbaTip) {
    this.novaNarudzbaTip = novaNarudzbaTip;
  }

  /**
   * Gets the nova narudzba kolicina.
   *
   * @return the nova narudzba kolicina
   */
  public float getNovaNarudzbaKolicina() {
    return novaNarudzbaKolicina;
  }

  /**
   * Sets the nova narudzba kolicina.
   *
   * @param novaNarudzbaKolicina the new nova narudzba kolicina
   */
  public void setNovaNarudzbaKolicina(float novaNarudzbaKolicina) {
    this.novaNarudzbaKolicina = novaNarudzbaKolicina;
  }

  /**
   * Gets the nova narudzba cijena.
   *
   * @return the nova narudzba cijena
   */
  public float getNovaNarudzbaCijena() {
    return novaNarudzbaCijena;
  }

  /**
   * Sets the nova narudzba cijena.
   *
   * @param novaNarudzbaCijena the new nova narudzba cijena
   */
  public void setNovaNarudzbaCijena(float novaNarudzbaCijena) {
    this.novaNarudzbaCijena = novaNarudzbaCijena;
  }

  /**
   * Gets the status dodano jelo.
   *
   * @return the status dodano jelo
   */
  public Integer getStatusDodanoJelo() {
    return statusDodanoJelo;
  }

  /**
   * Sets the status dodano jelo.
   *
   * @param statusDodanoJelo the new status dodano jelo
   */
  public void setStatusDodanoJelo(Integer statusDodanoJelo) {
    this.statusDodanoJelo = statusDodanoJelo;
  }

  /**
   * Gets the status dodano pice.
   *
   * @return the status dodano pice
   */
  public Integer getStatusDodanoPice() {
    return statusDodanoPice;
  }

  /**
   * Sets the status dodano pice.
   *
   * @param statusDodanoPice the new status dodano pice
   */
  public void setStatusDodanoPice(Integer statusDodanoPice) {
    this.statusDodanoPice = statusDodanoPice;
  }

  /**
   * Gets the status placen racun.
   *
   * @return the status placen racun
   */
  public Integer getStatusPlacenRacun() {
    return statusPlacenRacun;
  }

  /**
   * Sets the status placen racun.
   *
   * @param statusPlacenRacun the new status placen racun
   */
  public void setStatusPlacenRacun(Integer statusPlacenRacun) {
    this.statusPlacenRacun = statusPlacenRacun;
  }

  /**
   * Gets the kolicine.
   *
   * @return the kolicine
   */
  public Map<String, Float> getKolicine() {
    return kolicine;
  }

  /**
   * Sets the kolicine.
   *
   * @param kolicine the kolicine
   */
  public void setKolicine(Map<String, Float> kolicine) {
    this.kolicine = kolicine;
  }

  /**
   * Postavi kolicinu po id.
   *
   * @param kolicina the kolicina
   * @param id the id
   * @return the float
   */
  public Float postaviKolicinuPoId(Float kolicina, String id) {
    if (this.kolicine == null) {
      this.kolicine = new HashMap<>();
    }
    this.kolicine.put(id, kolicina);
    return this.kolicine.get(id);
  }


}
