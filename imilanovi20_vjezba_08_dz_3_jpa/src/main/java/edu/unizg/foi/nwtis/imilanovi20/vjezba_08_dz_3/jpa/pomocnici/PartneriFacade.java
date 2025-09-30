package edu.unizg.foi.nwtis.imilanovi20.vjezba_08_dz_3.jpa.pomocnici;

import java.io.Serializable;
import java.util.List;
import edu.unizg.foi.nwtis.imilanovi20.vjezba_08_dz_3.jpa.entiteti.Partneri;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.Stateless;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;

// TODO: Auto-generated Javadoc
/**
 * The Class PartneriFacade.
 */
@Stateless
public class PartneriFacade extends EntityManagerProducer implements Serializable {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = -325695883238495745L;

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
   * Find all.
   *
   * @return the list
   */
  public List<Partneri> findAll() {
    CriteriaQuery<Partneri> cq = cb.createQuery(Partneri.class);
    cq.select(cq.from(Partneri.class));
    return getEntityManager().createQuery(cq).getResultList();
  }

  /**
   * Find by id.
   *
   * @param id the id
   * @return the partneri
   */
  public Partneri findById(int id) {
    return getEntityManager().find(Partneri.class, id);
  }


}
