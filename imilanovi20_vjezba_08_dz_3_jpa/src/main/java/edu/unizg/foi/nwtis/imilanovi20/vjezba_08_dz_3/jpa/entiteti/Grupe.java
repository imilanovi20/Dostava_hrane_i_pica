package edu.unizg.foi.nwtis.imilanovi20.vjezba_08_dz_3.jpa.entiteti;

import java.io.Serializable;
import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedQuery;


// TODO: Auto-generated Javadoc
/**
 * The persistent class for the GRUPE database table.
 * 
 */
@Entity
@NamedQuery(name = "Grupe.findAll", query = "SELECT g FROM Grupe g")
public class Grupe implements Serializable {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /** The grupa. */
  @Id
  private String grupa;

  /** The naziv. */
  private String naziv;

  /** The korisnicis. */
  // bi-directional many-to-many association to Korisnici
  @ManyToMany(mappedBy = "grupes")
  private List<Korisnici> korisnicis;

  /**
   * Instantiates a new grupe.
   */
  public Grupe() {}

  /**
   * Gets the grupa.
   *
   * @return the grupa
   */
  public String getGrupa() {
    return this.grupa;
  }

  /**
   * Sets the grupa.
   *
   * @param grupa the new grupa
   */
  public void setGrupa(String grupa) {
    this.grupa = grupa;
  }

  /**
   * Gets the naziv.
   *
   * @return the naziv
   */
  public String getNaziv() {
    return this.naziv;
  }

  /**
   * Sets the naziv.
   *
   * @param naziv the new naziv
   */
  public void setNaziv(String naziv) {
    this.naziv = naziv;
  }

  /**
   * Gets the korisnicis.
   *
   * @return the korisnicis
   */
  public List<Korisnici> getKorisnicis() {
    return this.korisnicis;
  }

  /**
   * Sets the korisnicis.
   *
   * @param korisnicis the new korisnicis
   */
  public void setKorisnicis(List<Korisnici> korisnicis) {
    this.korisnicis = korisnicis;
  }

}
