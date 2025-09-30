package edu.unizg.foi.nwtis.imilanovi20.vjezba_08_dz_3.jpa.pomocnici;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import edu.unizg.foi.nwtis.imilanovi20.vjezba_08_dz_3.jpa.entiteti.Obracuni;
import edu.unizg.foi.nwtis.imilanovi20.vjezba_08_dz_3.jpa.entiteti.Obracuni_;
import edu.unizg.foi.nwtis.imilanovi20.vjezba_08_dz_3.jpa.entiteti.Partneri;
import edu.unizg.foi.nwtis.imilanovi20.vjezba_08_dz_3.jpa.entiteti.Partneri_;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.Stateless;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

// TODO: Auto-generated Javadoc
/**
 * The Class ObracuniFacade.
 */
@Stateless
public class ObracuniFacade extends EntityManagerProducer implements Serializable {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 3220345902979497047L;

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
  public List<Obracuni> findAll() {
    CriteriaQuery<Obracuni> cq = cb.createQuery(Obracuni.class);
    cq.select(cq.from(Obracuni.class));
    return getEntityManager().createQuery(cq).getResultList();
  }

  /**
   * Find by partner and vrijeme.
   *
   * @param partnerId the partner id
   * @param odVr the od vr
   * @param doVr the do vr
   * @return the list
   */
  public List<Obracuni> findByPartnerAndVrijeme(Integer partnerId, Long odVr, Long doVr) {
    CriteriaQuery<Obracuni> cq = cb.createQuery(Obracuni.class);
    Root<Obracuni> obracun = cq.from(Obracuni.class);

    List<Predicate> uvjeti = new ArrayList<>();

    if (partnerId != null) {
      Join<Obracuni, Partneri> partner = obracun.join(Obracuni_.partneri);
      Expression<Integer> partnerIdExpression = partner.get(Partneri_.id);
      uvjeti.add(cb.equal(partnerIdExpression, partnerId));
    }

    if (odVr != null) {
      Timestamp timestampOd = new Timestamp(odVr);
      Expression<Timestamp> vrijemeExpression = obracun.get(Obracuni_.vrijeme);
      uvjeti.add(cb.greaterThanOrEqualTo(vrijemeExpression, timestampOd));
    }

    if (doVr != null) {
      Timestamp timestampDo = new Timestamp(doVr);
      Expression<Timestamp> vrijemeExpression = obracun.get(Obracuni_.vrijeme);
      uvjeti.add(cb.lessThanOrEqualTo(vrijemeExpression, timestampDo));
    }

    if (!uvjeti.isEmpty()) {
      cq.where(cb.and(uvjeti.toArray(new Predicate[0])));
    }

    TypedQuery<Obracuni> query = getEntityManager().createQuery(cq);
    return query.getResultList();
  }

  /**
   * Find by vrijeme.
   *
   * @param odVr the od vr
   * @param doVr the do vr
   * @return the list
   */
  public List<Obracuni> findByVrijeme(Long odVr, Long doVr) {
    CriteriaQuery<Obracuni> cq = cb.createQuery(Obracuni.class);
    Root<Obracuni> obracun = cq.from(Obracuni.class);

    List<Predicate> uvjeti = new ArrayList<>();

    if (odVr != null) {
      Timestamp timestampOd = new Timestamp(odVr);
      Expression<Timestamp> vrijemeExpression = obracun.get(Obracuni_.vrijeme);
      uvjeti.add(cb.greaterThanOrEqualTo(vrijemeExpression, timestampOd));
    }

    if (doVr != null) {
      Timestamp timestampDo = new Timestamp(doVr);
      Expression<Timestamp> vrijemeExpression = obracun.get(Obracuni_.vrijeme);
      uvjeti.add(cb.lessThanOrEqualTo(vrijemeExpression, timestampDo));
    }

    if (!uvjeti.isEmpty()) {
      cq.where(cb.and(uvjeti.toArray(new Predicate[0])));
    }

    TypedQuery<Obracuni> query = getEntityManager().createQuery(cq);
    return query.getResultList();
  }

}
