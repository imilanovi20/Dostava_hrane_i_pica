package edu.unizg.foi.nwtis.imilanovi20.vjezba_08_dz_3.jpa.pomocnici;

import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Timestamp;
import edu.unizg.foi.nwtis.imilanovi20.vjezba_08_dz_3.jpa.entiteti.Zapisi;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.Stateless;
import jakarta.faces.context.FacesContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.servlet.http.HttpServletRequest;

// TODO: Auto-generated Javadoc
/**
 * The Class ZapisiFacade.
 *
 * @author Ivan Milanović-Litre
 */
@Stateless
public class ZapisiFacade extends EntityManagerProducer implements Serializable {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = -8347943057058039005L;

  /** The cb. */
  private CriteriaBuilder cb;

  /**
   * Inits the.
   */
  @PostConstruct
  private void init() {
    cb = getEntityManager().getCriteriaBuilder();
  }

  /**
   * Creates the.
   *
   * @param zapisi the zapisi
   */
  public void create(Zapisi zapisi) {
    getEntityManager().persist(zapisi);
  }

  /**
   * Dodaj zapis.
   *
   * @param korisnickoIme the korisnicko ime
   * @param opisRada the opis rada
   */
  public void dodajZapis(String korisnickoIme, String opisRada) {
    Zapisi zapis = new Zapisi();
    zapis.setKorisnickoime(korisnickoIme);
    zapis.setOpisrada(opisRada);
    zapis.setVrijeme(new Timestamp(System.currentTimeMillis()));

    FacesContext facesContext = FacesContext.getCurrentInstance();
    if (facesContext != null) {
      HttpServletRequest zahtjev =
          (HttpServletRequest) facesContext.getExternalContext().getRequest();
      String ipadresa = zahtjev.getRemoteAddr();
      String adresa;
      try {
        InetAddress addr = InetAddress.getByName(ipadresa);
        adresa = addr.getHostName();
      } catch (UnknownHostException e) {
        adresa = ipadresa;
      }
      zapis.setIpadresaracunala(ipadresa);
      zapis.setAdresaracunala(adresa);
    }


    create(zapis);
  }

}
