/*
 * To change this template, choose Tools | Templates and open the template in the editor.
 */
package edu.unizg.foi.nwtis.imilanovi20.vjezba_08_dz_3.jpa.pomocnici;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import edu.unizg.foi.nwtis.imilanovi20.vjezba_08_dz_3.jpa.entiteti.Korisnici;
import edu.unizg.foi.nwtis.imilanovi20.vjezba_08_dz_3.jpa.entiteti.Korisnici_;
import edu.unizg.foi.nwtis.podaci.Korisnik;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.Stateless;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

// TODO: Auto-generated Javadoc
/**
 * The Class KorisniciFacade.
 *
 * @author Ivan Milanović-Litre
 */
@Stateless
public class KorisniciFacade extends EntityManagerProducer implements Serializable {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 3595041786540495885L;

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
   * @param korisnici the korisnici
   */
  public void create(Korisnici korisnici) {
    getEntityManager().persist(korisnici);
  }

  /**
   * Edits the.
   *
   * @param korisnici the korisnici
   */
  public void edit(Korisnici korisnici) {
    getEntityManager().merge(korisnici);
  }

  /**
   * Removes the.
   *
   * @param korisnici the korisnici
   */
  public void remove(Korisnici korisnici) {
    getEntityManager().remove(getEntityManager().merge(korisnici));
  }

  /**
   * Find.
   *
   * @param id the id
   * @return the korisnici
   */
  public Korisnici find(Object id) {
    return getEntityManager().find(Korisnici.class, id);
  }

  /**
   * Find all.
   *
   * @return the list
   */
  public List<Korisnici> findAll() {
    CriteriaQuery<Korisnici> cq = cb.createQuery(Korisnici.class);
    cq.select(cq.from(Korisnici.class));
    return getEntityManager().createQuery(cq).getResultList();
  }

  /**
   * Find range.
   *
   * @param range the range
   * @return the list
   */
  public List<Korisnici> findRange(int[] range) {
    CriteriaQuery<Korisnici> cq = cb.createQuery(Korisnici.class);
    cq.select(cq.from(Korisnici.class));
    TypedQuery<Korisnici> q = getEntityManager().createQuery(cq);
    q.setMaxResults(range[1] - range[0]);
    q.setFirstResult(range[0]);
    return q.getResultList();
  }

  /**
   * Find.
   *
   * @param korisnickoIme the korisnicko ime
   * @param lozinka the lozinka
   * @return the korisnici
   */
  public Korisnici find(String korisnickoIme, String lozinka) {
    CriteriaQuery<Korisnici> cq = cb.createQuery(Korisnici.class);
    Root<Korisnici> korisnici = cq.from(Korisnici.class);
    Expression<String> zaKorisnik = korisnici.get(Korisnici_.korisnik);
    Expression<String> zaLozinku = korisnici.get(Korisnici_.lozinka);
    cq.where(cb.and(cb.equal(zaKorisnik, korisnickoIme), cb.equal(zaLozinku, lozinka)));
    TypedQuery<Korisnici> q = getEntityManager().createQuery(cq);
    return q.getResultList().getFirst();
  }

  /**
   * Find all.
   *
   * @param prezime the prezime
   * @param ime the ime
   * @return the list
   */
  public List<Korisnici> findAll(String prezime, String ime) {
    CriteriaQuery<Korisnici> cq = cb.createQuery(Korisnici.class);
    Root<Korisnici> korisnici = cq.from(Korisnici.class);
    Expression<String> zaPrezime = korisnici.get(Korisnici_.prezime);
    Expression<String> zaIme = korisnici.get(Korisnici_.ime);
    cq.where(cb.and(cb.like(zaPrezime, prezime), cb.like(zaIme, ime)));
    TypedQuery<Korisnici> q = getEntityManager().createQuery(cq);
    return q.getResultList();
  }

  /**
   * Count.
   *
   * @return the int
   */
  public int count() {
    CriteriaQuery<Long> cq = cb.createQuery(Long.class);
    cq.select(cb.count(cq.from(Korisnici.class)));
    return ((Long) getEntityManager().createQuery(cq).getSingleResult()).intValue();
  }

  /**
   * Pretvori.
   *
   * @param k the k
   * @return the korisnik
   */
  public Korisnik pretvori(Korisnici k) {
    if (k == null) {
      return null;
    }
    var kObjekt =
        new Korisnik(k.getKorisnik(), k.getLozinka(), k.getPrezime(), k.getIme(), k.getEmail());

    return kObjekt;
  }

  /**
   * Pretvori.
   *
   * @param k the k
   * @return the korisnici
   */
  public Korisnici pretvori(Korisnik k) {
    if (k == null) {
      return null;
    }
    var kE = new Korisnici();
    kE.setKorisnik(k.korisnik());
    kE.setLozinka(k.lozinka());
    kE.setPrezime(k.prezime());
    kE.setIme(k.ime());
    kE.setEmail(k.email());

    return kE;
  }

  /**
   * Pretvori.
   *
   * @param korisniciE the korisnici E
   * @return the list
   */
  public List<Korisnik> pretvori(List<Korisnici> korisniciE) {
    List<Korisnik> korisnici = new ArrayList<>();
    for (Korisnici kEntitet : korisniciE) {
      var kObjekt = pretvori(kEntitet);

      korisnici.add(kObjekt);
    }

    return korisnici;
  }

  /**
   * Find by prezime ime.
   *
   * @param prezimeFilter the prezime filter
   * @param imeFilter the ime filter
   * @return the list
   */
  public List<Korisnici> findByPrezimeIme(String prezimeFilter, String imeFilter) {
    CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
    CriteriaQuery<Korisnici> cq = cb.createQuery(Korisnici.class);
    Root<Korisnici> root = cq.from(Korisnici.class);

    List<Predicate> uvjeti = new ArrayList<>();

    if (prezimeFilter != null && !prezimeFilter.isBlank()) {
      uvjeti.add(cb.like(root.get(Korisnici_.prezime), "%" + prezimeFilter + "%"));
    }
    if (imeFilter != null && !imeFilter.isBlank()) {
      uvjeti.add(cb.like(root.get(Korisnici_.ime), "%" + imeFilter + "%"));
    }

    if (!uvjeti.isEmpty()) {
      cq.where(cb.and(uvjeti.toArray(new Predicate[0])));
    }

    return getEntityManager().createQuery(cq).getResultList();
  }

}
