package edu.unizg.foi.nwtis.imilanovi20.vjezba_08_dz_3.jf;

import java.io.Serializable;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import edu.unizg.foi.nwtis.imilanovi20.vjezba_08_dz_3.ServisPartnerKlijent;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

// TODO: Auto-generated Javadoc
/**
 * The Class RadPosluziteljaPartner.
 */
@RequestScoped
@Named("radPosluziteljaPartner")
public class RadPosluziteljaPartner implements Serializable {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = -4278737802934328809L;

  /** The servis partner. */
  @Inject
  @RestClient
  private ServisPartnerKlijent servisPartner;

  /** The status. */
  private Integer status;

  /** The status poruka. */
  private String statusPoruka;

  /** The vrijeme spavanja. */
  private Long vrijemeSpavanja;

  /** The status spavanja. */
  private Integer statusSpavanja;

  /** The status prijem. */
  private Integer statusPrijem;

  /**
   * Inits the.
   */
  @PostConstruct
  public void init() {
    provjeriRadPosluzitelja();
    provjeriRadPosluziteljaPrijem();
  }

  /**
   * Provjeri rad posluzitelja.
   */
  public void provjeriRadPosluzitelja() {
    try {
      Response odgovor = servisPartner.headPosluzitelj();
      status = odgovor.getStatus();
      if (status == 200) {
        statusPoruka = "Poslužitelj Partner radi";
      } else {
        statusPoruka = "Poslužitelj Partner ne radi";
      }
    } catch (Exception e) {
      statusPoruka = "Greška u komunikaciji s poslužiteljem";
    }
  }

  /**
   * Aktiviraj spavanje partnera.
   */
  public void aktivirajSpavanjePartnera() {
    try {
      Response odgovor = servisPartner.getSpava(this.vrijemeSpavanja);
      this.statusSpavanja = odgovor.getStatus();
    } catch (WebApplicationException e) {
      this.statusSpavanja = e.getResponse().getStatus();
    } catch (Exception e) {
      this.statusSpavanja = 500;
    }

  }

  /**
   * Provjeri rad posluzitelja prijem.
   */
  public void provjeriRadPosluziteljaPrijem() {
    try {
      Response odgovor = servisPartner.headPosluziteljStatus(1);
      this.statusPrijem = odgovor.getStatus();
    } catch (WebApplicationException e) {
      this.statusPrijem = e.getResponse().getStatus();
    } catch (Exception e) {
      this.statusPrijem = 500;
    }
  }

  /**
   * Pokreni posluzitelja prijem.
   */
  public void pokreniPosluziteljaPrijem() {
    try {
      var odgovor = servisPartner.headPosluziteljStart(1);
      if (odgovor.getStatus() == 200) {
        this.statusPrijem = 200;
      }
    } catch (Exception e) {
    }
  }

  /**
   * Zaustavi posluzitelja prijem.
   */
  public void zaustaviPosluziteljaPrijem() {
    try {
      var odgovor = servisPartner.headPosluziteljPauza(1);
      if (odgovor.getStatus() == 200) {
        this.statusPrijem = 204;
      }
    } catch (Exception e) {
    }
  }

  /**
   * Zaustavi posluzitelja.
   */
  public void zaustaviPosluzitelja() {
    try {
      var odgovor = servisPartner.headPosluziteljKraj();
      if (odgovor.getStatus() == 200) {
        this.status = 204;
        this.statusPrijem = 204;
      }
    } catch (Exception e) {
    }
  }

  /**
   * Gets the status poruka.
   *
   * @return the status poruka
   */
  public String getStatusPoruka() {
    return statusPoruka;
  }

  /**
   * Gets the status.
   *
   * @return the status
   */
  public Integer getStatus() {
    return status;
  }

  /**
   * Gets the vrijeme spavanja.
   *
   * @return the vrijeme spavanja
   */
  public Long getVrijemeSpavanja() {
    return vrijemeSpavanja;
  }

  /**
   * Sets the vrijeme spavanja.
   *
   * @param vrijemeSpavanja the new vrijeme spavanja
   */
  public void setVrijemeSpavanja(Long vrijemeSpavanja) {
    this.vrijemeSpavanja = vrijemeSpavanja;
  }

  /**
   * Gets the status spavanja.
   *
   * @return the status spavanja
   */
  public Integer getStatusSpavanja() {
    return statusSpavanja;
  }

  /**
   * Sets the status spavanja.
   *
   * @param statusSpavanja the new status spavanja
   */
  public void setStatusSpavanja(Integer statusSpavanja) {
    this.statusSpavanja = statusSpavanja;
  }

  /**
   * Gets the status prijem.
   *
   * @return the status prijem
   */
  public Integer getStatusPrijem() {
    return statusPrijem;
  }

  /**
   * Sets the status prijem.
   *
   * @param statusPrijem the new status prijem
   */
  public void setStatusPrijem(Integer statusPrijem) {
    this.statusPrijem = statusPrijem;
  }

}
