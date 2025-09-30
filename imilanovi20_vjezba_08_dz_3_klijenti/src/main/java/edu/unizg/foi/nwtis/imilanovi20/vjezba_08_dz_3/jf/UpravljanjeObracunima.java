package edu.unizg.foi.nwtis.imilanovi20.vjezba_08_dz_3.jf;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import edu.unizg.foi.nwtis.imilanovi20.vjezba_08_dz_3.jpa.entiteti.Obracuni;
import edu.unizg.foi.nwtis.imilanovi20.vjezba_08_dz_3.jpa.pomocnici.ObracuniFacade;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

// TODO: Auto-generated Javadoc
/**
 * The Class UpravljanjeObracunima.
 */
@RequestScoped
@Named("upravljanjeObracunima")
public class UpravljanjeObracunima implements Serializable {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = -4345333732831565821L;

  /** The obracuni facade. */
  @Inject
  ObracuniFacade obracuniFacade;

  /** The partner id. */
  private Integer partnerId;

  /** The vrijeme od. */
  private String vrijemeOd;

  /** The vrijeme do. */
  private String vrijemeDo;

  /** The trazeni obracuni. */
  private List<Obracuni> trazeniObracuni;

  /**
   * Dohvati po vremenu.
   */
  public void dohvatiPoVremenu() {
    Long odVr = null;
    Long doVr = null;
    try {
      if (vrijemeOd != null && !vrijemeOd.trim().isEmpty()) {
        LocalDateTime dateTimeOd = LocalDateTime.parse(vrijemeOd);
        odVr = dateTimeOd.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
      }
      if (vrijemeDo != null && !vrijemeDo.trim().isEmpty()) {
        LocalDateTime dateTimeDo = LocalDateTime.parse(vrijemeDo);
        doVr = dateTimeDo.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
      }
    } catch (Exception e) {
    }

    this.trazeniObracuni = obracuniFacade.findByVrijeme(odVr, doVr);
  }

  /**
   * Dohvati po vremenu I partneru.
   */
  public void dohvatiPoVremenuIPartneru() {
    Long odVr = null;
    Long doVr = null;
    try {
      if (vrijemeOd != null && !vrijemeOd.trim().isEmpty()) {
        LocalDateTime dateTimeOd = LocalDateTime.parse(vrijemeOd);
        odVr = dateTimeOd.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
      }
      if (vrijemeDo != null && !vrijemeDo.trim().isEmpty()) {
        LocalDateTime dateTimeDo = LocalDateTime.parse(vrijemeDo);
        doVr = dateTimeDo.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
      }
    } catch (Exception e) {
    }

    this.trazeniObracuni = obracuniFacade.findByPartnerAndVrijeme(partnerId, odVr, doVr);
  }

  /**
   * Gets the partner id.
   *
   * @return the partner id
   */
  public Integer getPartnerId() {
    return partnerId;
  }

  /**
   * Sets the partner id.
   *
   * @param partnerId the new partner id
   */
  public void setPartnerId(Integer partnerId) {
    this.partnerId = partnerId;
  }

  /**
   * Gets the vrijeme od.
   *
   * @return the vrijeme od
   */
  public String getVrijemeOd() {
    return vrijemeOd;
  }

  /**
   * Sets the vrijeme od.
   *
   * @param vrijemeOd the new vrijeme od
   */
  public void setVrijemeOd(String vrijemeOd) {
    this.vrijemeOd = vrijemeOd;
  }

  /**
   * Gets the vrijeme do.
   *
   * @return the vrijeme do
   */
  public String getVrijemeDo() {
    return vrijemeDo;
  }

  /**
   * Sets the vrijeme do.
   *
   * @param vrijemeDo the new vrijeme do
   */
  public void setVrijemeDo(String vrijemeDo) {
    this.vrijemeDo = vrijemeDo;
  }

  /**
   * Gets the trazeni obracuni.
   *
   * @return the trazeni obracuni
   */
  public List<Obracuni> getTrazeniObracuni() {
    return trazeniObracuni;
  }

  /**
   * Sets the trazeni obracuni.
   *
   * @param trazeniObracuni the new trazeni obracuni
   */
  public void setTrazeniObracuni(List<Obracuni> trazeniObracuni) {
    this.trazeniObracuni = trazeniObracuni;
  }



}
