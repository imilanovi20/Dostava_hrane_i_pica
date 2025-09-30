package edu.unizg.foi.nwtis.imilanovi20.vjezba_08_dz_3.jf;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import edu.unizg.foi.nwtis.imilanovi20.vjezba_08_dz_3.GlobalniPodaci;
import edu.unizg.foi.nwtis.imilanovi20.vjezba_08_dz_3.jpa.entiteti.Partneri;
import edu.unizg.foi.nwtis.imilanovi20.vjezba_08_dz_3.jpa.pomocnici.PartneriFacade;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.ws.rs.WebApplicationException;

// TODO: Auto-generated Javadoc
/**
 * The Class OdabirPartnera.
 */
@RequestScoped
@Named("odabirParnera")
public class OdabirPartnera implements Serializable {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = -524581462819739622L;

  /** The prijava korisnika. */
  @Inject
  PrijavaKorisnika prijavaKorisnika;

  /** The rest configuration. */
  @Inject
  RestConfiguration restConfiguration;

  /** The globalni podaci. */
  @Inject
  GlobalniPodaci globalniPodaci;

  /** The partneri facade. */
  @Inject
  PartneriFacade partneriFacade;

  /** The partneri. */
  private List<Partneri> partneri = new ArrayList<>();

  /** The partner. */
  private int partner;

  /**
   * Gets the id trazenog partnera.
   *
   * @return the id trazenog partnera
   */
  public Integer getIdTrazenogPartnera() {
    return idTrazenogPartnera;
  }

  /**
   * Sets the id trazenog partnera.
   *
   * @param idTrazenogPartnera the new id trazenog partnera
   */
  public void setIdTrazenogPartnera(Integer idTrazenogPartnera) {
    this.idTrazenogPartnera = idTrazenogPartnera;
  }

  /** The id trazenog partnera. */
  private Integer idTrazenogPartnera;

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

  /** The trazeni partner. */
  private Partneri trazeniPartner;

  /**
   * Gets the partner.
   *
   * @return the partner
   */
  public int getPartner() {
    return partner;
  }

  /**
   * Sets the partner.
   *
   * @param partner the new partner
   */
  public void setPartner(int partner) {
    this.partner = partner;
  }

  /**
   * Gets the partneri.
   *
   * @return the partneri
   */
  public List<Partneri> getPartneri() {
    return partneri;
  }

  /**
   * Ucitaj partnere.
   */
  @PostConstruct
  public void ucitajPartnere() {
    try {
      this.partneri = partneriFacade.findAll();
    } catch (Exception e) {
    }
  }


  /**
   * Dohvati odabranog partnera.
   *
   * @return the string
   */
  public String dohvatiOdabranogPartnera() {
    try {
      this.trazeniPartner = partneriFacade.findById(idTrazenogPartnera);
      this.prijavaKorisnika.setTrazeniPartner(this.trazeniPartner);
      return "/partner.xhtml?faces-redirect=true";
    } catch (WebApplicationException e) {
      return "";
    } catch (Exception e) {
      return "";
    }
  }


  /**
   * Odaberi partnera.
   *
   * @return the string
   */
  public String odaberiPartnera() {
    if (this.partner > 0) {
      Optional<Partneri> partnerO =
          this.partneri.stream().filter((p) -> p.getId() == this.partner).findFirst();
      if (partnerO.isPresent()) {
        this.prijavaKorisnika.setOdabraniPartner(partnerO.get());
        this.prijavaKorisnika.setPartnerOdabran(true);
        globalniPodaci.postaviNarudzbe(partnerO.get().getId());
        globalniPodaci.postaviRacune(partnerO.get().getId());
      } else {
        this.prijavaKorisnika.setPartnerOdabran(false);
      }
    } else {
      this.prijavaKorisnika.setPartnerOdabran(false);
    }
    return "/index.xhtml?faces-redirect=true";
  }

}
