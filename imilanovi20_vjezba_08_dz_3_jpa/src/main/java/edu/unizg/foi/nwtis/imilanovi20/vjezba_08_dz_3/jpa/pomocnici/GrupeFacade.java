package edu.unizg.foi.nwtis.imilanovi20.vjezba_08_dz_3.jpa.pomocnici;

import java.io.Serializable;
import edu.unizg.foi.nwtis.imilanovi20.vjezba_08_dz_3.jpa.entiteti.Grupe;
import edu.unizg.foi.nwtis.imilanovi20.vjezba_08_dz_3.jpa.entiteti.Korisnici;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.Stateless;
import jakarta.persistence.criteria.CriteriaBuilder;

// TODO: Auto-generated Javadoc
/**
 * The Class GrupeFacade.
 */

@Stateless
public class GrupeFacade extends EntityManagerProducer implements Serializable {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = -1165442033604064722L;

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
   * @param grupe the grupe
   */
  public void create(Grupe grupe) {
    getEntityManager().persist(grupe);
  }

  public Grupe find(String grupaId) {
    return getEntityManager().find(Grupe.class, grupaId);
  }

  public void pridruziKorisnikaGrupi(Korisnici korisnik, String grupaId) {
    Grupe grupa = find(grupaId);

    if (grupa != null && korisnik != null) {
      if (korisnik.getGrupes() != null && !korisnik.getGrupes().contains(grupa)) {
        korisnik.getGrupes().add(grupa);
      }
      if (grupa.getKorisnicis() != null && !grupa.getKorisnicis().contains(korisnik)) {
        grupa.getKorisnicis().add(korisnik);
      }
      getEntityManager().merge(korisnik);
      getEntityManager().merge(grupa);
    }
  }

}
