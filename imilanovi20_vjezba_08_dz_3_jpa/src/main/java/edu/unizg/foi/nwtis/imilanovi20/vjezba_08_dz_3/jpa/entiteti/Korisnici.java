package edu.unizg.foi.nwtis.imilanovi20.vjezba_08_dz_3.jpa.entiteti;

import java.io.Serializable;
import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedQuery;


// TODO: Auto-generated Javadoc
/**
 * The persistent class for the KORISNICI database table.
 * 
 */
@Entity
@NamedQuery(name = "Korisnici.findAll", query = "SELECT k FROM Korisnici k")
public class Korisnici implements Serializable {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /** The korisnik. */
  @Id
  private String korisnik;

  /** The email. */
  private String email;

  /** The ime. */
  private String ime;

  /** The lozinka. */
  private String lozinka;

  /** The prezime. */
  private String prezime;

  /** The grupes. */
  // bi-directional many-to-many association to Grupe
  @ManyToMany
  @JoinTable(name = "ULOGE", joinColumns = {@JoinColumn(name = "KORISNIK")},
      inverseJoinColumns = {@JoinColumn(name = "GRUPA")})
  private List<Grupe> grupes;

  /**
   * Instantiates a new korisnici.
   */
  public Korisnici() {}

  /**
   * Gets the korisnik.
   *
   * @return the korisnik
   */
  public String getKorisnik() {
    return this.korisnik;
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
   * Gets the email.
   *
   * @return the email
   */
  public String getEmail() {
    return this.email;
  }

  /**
   * Sets the email.
   *
   * @param email the new email
   */
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * Gets the ime.
   *
   * @return the ime
   */
  public String getIme() {
    return this.ime;
  }

  /**
   * Sets the ime.
   *
   * @param ime the new ime
   */
  public void setIme(String ime) {
    this.ime = ime;
  }

  /**
   * Gets the lozinka.
   *
   * @return the lozinka
   */
  public String getLozinka() {
    return this.lozinka;
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
   * Gets the prezime.
   *
   * @return the prezime
   */
  public String getPrezime() {
    return this.prezime;
  }

  /**
   * Sets the prezime.
   *
   * @param prezime the new prezime
   */
  public void setPrezime(String prezime) {
    this.prezime = prezime;
  }

  /**
   * Gets the grupes.
   *
   * @return the grupes
   */
  public List<Grupe> getGrupes() {
    return this.grupes;
  }

  /**
   * Sets the grupes.
   *
   * @param grupes the new grupes
   */
  public void setGrupes(List<Grupe> grupes) {
    this.grupes = grupes;
  }

}
