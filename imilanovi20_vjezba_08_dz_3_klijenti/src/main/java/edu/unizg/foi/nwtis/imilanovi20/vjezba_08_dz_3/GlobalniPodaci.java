package edu.unizg.foi.nwtis.imilanovi20.vjezba_08_dz_3;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import jakarta.enterprise.context.ApplicationScoped;

// TODO: Auto-generated Javadoc
/**
 * The Class GlobalniPodaci.
 */
@ApplicationScoped
public class GlobalniPodaci {

  /** The broj obracuna. */
  private int brojObracuna = 0;

  /** The broj otvorenih narudzbi. */
  private Map<Integer, Integer> brojOtvorenihNarudzbi = new ConcurrentHashMap<>();

  /** The broj racuna. */
  private Map<Integer, Integer> brojRacuna = new ConcurrentHashMap<>();


  /**
   * Gets the broj obracuna.
   *
   * @return the broj obracuna
   */
  public int getBrojObracuna() {
    return brojObracuna;
  }

  /**
   * Sets the broj obracuna.
   *
   * @param brojObracuna the new broj obracuna
   */
  public void setBrojObracuna(int brojObracuna) {
    this.brojObracuna = brojObracuna;
  }

  /**
   * Gets the broj otvorenih narudzbi.
   *
   * @return the broj otvorenih narudzbi
   */
  public Map<Integer, Integer> getBrojOtvorenihNarudzbi() {
    return brojOtvorenihNarudzbi;
  }

  /**
   * Sets the broj otvorenih narudzbi.
   *
   * @param brojOtvorenihNarudzbi the broj otvorenih narudzbi
   */
  public void setBrojOtvorenihNarudzbi(Map<Integer, Integer> brojOtvorenihNarudzbi) {
    this.brojOtvorenihNarudzbi = brojOtvorenihNarudzbi;
  }

  /**
   * Gets the broj racuna.
   *
   * @return the broj racuna
   */
  public Map<Integer, Integer> getBrojRacuna() {
    return brojRacuna;
  }

  /**
   * Sets the broj racuna.
   *
   * @param brojRacuna the broj racuna
   */
  public void setBrojRacuna(Map<Integer, Integer> brojRacuna) {
    this.brojRacuna = brojRacuna;
  }

  /**
   * Povecaj broj obracuna.
   */
  public void povecajBrojObracuna() {
    this.brojObracuna++;
  }


  /**
   * Smanji broj obracuna.
   */
  public void smanjiBrojObracuna() {
    if (this.brojObracuna > 0) {
      this.brojObracuna--;
    }
  }

  /**
   * Povecaj broj otvorenih narudzbi.
   *
   * @param partnerId the partner id
   */
  public void povecajBrojOtvorenihNarudzbi(int partnerId) {
    if (brojOtvorenihNarudzbi.containsKey(partnerId)) {
      brojOtvorenihNarudzbi.put(partnerId, brojOtvorenihNarudzbi.get(partnerId) + 1);
    } else {
      brojOtvorenihNarudzbi.put(partnerId, 1);
    }
  }

  /**
   * Smanji broj otvorenih narudzbi.
   *
   * @param partnerId the partner id
   */
  public void smanjiBrojOtvorenihNarudzbi(int partnerId) {
    Integer trenutni = brojOtvorenihNarudzbi.get(partnerId);
    if (trenutni != null) {
      if (trenutni > 0) {
        brojOtvorenihNarudzbi.put(partnerId, trenutni - 1);
      }
    }
  }

  /**
   * Povecaj broj racuna.
   *
   * @param partnerId the partner id
   */
  public void povecajBrojRacuna(int partnerId) {
    if (brojRacuna.containsKey(partnerId)) {
      brojRacuna.put(partnerId, brojRacuna.get(partnerId) + 1);
    } else {
      brojRacuna.put(partnerId, 1);
    }
  }

  /**
   * Smanji broj racuna.
   *
   * @param partnerId the partner id
   */
  public void smanjiBrojRacuna(int partnerId) {
    Integer trenutni = brojRacuna.get(partnerId);
    if (trenutni != null) {
      if (trenutni > 0) {
        brojRacuna.put(partnerId, trenutni - 1);
      }
    }
  }

  /**
   * Postavi racune.
   *
   * @param partnerId the partner id
   */
  public void postaviRacune(int partnerId) {
    if (!brojOtvorenihNarudzbi.containsKey(partnerId)) {
      brojOtvorenihNarudzbi.put(partnerId, 0);
    }
  }

  /**
   * Postavi narudzbe.
   *
   * @param partnerId the partner id
   */
  public void postaviNarudzbe(int partnerId) {
    if (!brojRacuna.containsKey(partnerId)) {
      brojRacuna.put(partnerId, 0);
    }
  }



}
