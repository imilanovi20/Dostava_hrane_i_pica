/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */
package edu.unizg.foi.nwtis.imilanovi20.vjezba_08_dz_3.jf;

import java.sql.Connection;
import java.sql.DriverManager;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

// TODO: Auto-generated Javadoc
/**
 * The Class RestConfiguration.
 *
 * @author imilanovi20
 */
@ApplicationScoped
public class RestConfiguration {

  /** The korisnicko ime baza podataka. */
  @Inject
  @ConfigProperty(name = "korisnickoImeBazaPodataka")
  private String korisnickoImeBazaPodataka;

  /** The lozinka baza podataka. */
  @Inject
  @ConfigProperty(name = "lozinkaBazaPodataka")
  private String lozinkaBazaPodataka;

  /** The upravljac baza podataka. */
  @Inject
  @ConfigProperty(name = "upravljacBazaPodataka")
  private String upravljacBazaPodataka;

  /** The url baza podataka. */
  @Inject
  @ConfigProperty(name = "urlBazaPodataka")
  private String urlBazaPodataka;

  /**
   * Daj vezu.
   *
   * @return the connection
   * @throws Exception the exception
   */
  public Connection dajVezu() throws Exception {
    Class.forName(this.upravljacBazaPodataka);
    var vezaBazaPodataka = DriverManager.getConnection(this.urlBazaPodataka,
        this.korisnickoImeBazaPodataka, this.lozinkaBazaPodataka);
    return vezaBazaPodataka;
  }
}
