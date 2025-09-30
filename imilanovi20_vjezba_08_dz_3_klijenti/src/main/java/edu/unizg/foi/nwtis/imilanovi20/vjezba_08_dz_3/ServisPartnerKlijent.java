package edu.unizg.foi.nwtis.imilanovi20.vjezba_08_dz_3;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import edu.unizg.foi.nwtis.podaci.Korisnik;
import edu.unizg.foi.nwtis.podaci.Narudzba;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HEAD;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

// TODO: Auto-generated Javadoc
/**
 * The Interface ServisPartnerKlijent.
 */
@RegisterRestClient(configKey = "klijentPartner")
@Path("api/partner")
public interface ServisPartnerKlijent {

  /**
   * Head posluzitelj.
   *
   * @return the response
   */
  @HEAD
  public Response headPosluzitelj();

  /**
   * Post korisnik.
   *
   * @param korisnik the korisnik
   * @return the response
   */
  @Path("korisnik")
  @POST
  @Produces({MediaType.APPLICATION_JSON})
  public Response postKorisnik(Korisnik korisnik);

  /**
   * Gets the jelovnik.
   *
   * @param korisnik the korisnik
   * @param lozinka the lozinka
   * @return the jelovnik
   */
  @Path("jelovnik")
  @GET
  @Produces({MediaType.APPLICATION_JSON})
  public Response getJelovnik(@HeaderParam("korisnik") String korisnik,
      @HeaderParam("lozinka") String lozinka);

  /**
   * Gets the karta pica.
   *
   * @param korisnik the korisnik
   * @param lozinka the lozinka
   * @return the karta pica
   */
  @Path("kartapica")
  @GET
  @Produces({MediaType.APPLICATION_JSON})
  public Response getKartaPica(@HeaderParam("korisnik") String korisnik,
      @HeaderParam("lozinka") String lozinka);

  /**
   * Post narudzba.
   *
   * @param korisnik the korisnik
   * @param lozinka the lozinka
   * @return the response
   */
  @Path("narudzba")
  @POST
  @Produces({MediaType.APPLICATION_JSON})
  public Response postNarudzba(@HeaderParam("korisnik") String korisnik,
      @HeaderParam("lozinka") String lozinka);

  /**
   * Gets the narudzbe.
   *
   * @param korisnik the korisnik
   * @param lozinka the lozinka
   * @return the narudzbe
   */
  @Path("narudzba")
  @GET
  @Produces({MediaType.APPLICATION_JSON})
  public Response getNarudzbe(@HeaderParam("korisnik") String korisnik,
      @HeaderParam("lozinka") String lozinka);

  /**
   * Post jelo.
   *
   * @param korisnik the korisnik
   * @param lozinka the lozinka
   * @param narudzba the narudzba
   * @return the response
   */
  @Path("jelo")
  @POST
  @Produces({MediaType.APPLICATION_JSON})
  public Response postJelo(@HeaderParam("korisnik") String korisnik,
      @HeaderParam("lozinka") String lozinka, Narudzba narudzba);

  /**
   * Post pice.
   *
   * @param korisnik the korisnik
   * @param lozinka the lozinka
   * @param narudzba the narudzba
   * @return the response
   */
  @Path("pice")
  @POST
  @Produces({MediaType.APPLICATION_JSON})
  public Response postPice(@HeaderParam("korisnik") String korisnik,
      @HeaderParam("lozinka") String lozinka, Narudzba narudzba);

  /**
   * Post racun.
   *
   * @param korisnik the korisnik
   * @param lozinka the lozinka
   * @return the response
   */
  @Path("racun")
  @POST
  @Produces({MediaType.APPLICATION_JSON})
  public Response postRacun(@HeaderParam("korisnik") String korisnik,
      @HeaderParam("lozinka") String lozinka);

  /**
   * Gets the spava.
   *
   * @param vrijeme the vrijeme
   * @return the spava
   */
  @Path("spava")
  @GET
  @Produces({MediaType.APPLICATION_JSON})
  public Response getSpava(@QueryParam("vrijeme") Long vrijeme);

  /**
   * Gets the korisnici.
   *
   * @return the korisnici
   */
  @Path("korisnik")
  @GET
  @Produces({MediaType.APPLICATION_JSON})
  public Response getKorisnici();

  /**
   * Gets the korisnik.
   *
   * @param korisnikId the korisnik id
   * @return the korisnik
   */
  @Path("korisnik/{id}")
  @GET
  @Produces({MediaType.APPLICATION_JSON})
  public Response getKorisnik(@PathParam("id") String korisnikId);

  /**
   * Head posluzitelj status.
   *
   * @param id the id
   * @return the response
   */
  @Path("status/{id}")
  @HEAD
  public Response headPosluziteljStatus(@PathParam("id") int id);

  /**
   * Head posluzitelj pauza.
   *
   * @param id the id
   * @return the response
   */
  @Path("pauza/{id}")
  @HEAD
  public Response headPosluziteljPauza(@PathParam("id") int id);

  /**
   * Head posluzitelj start.
   *
   * @param id the id
   * @return the response
   */
  @Path("start/{id}")
  @HEAD
  public Response headPosluziteljStart(@PathParam("id") int id);

  /**
   * Head posluzitelj kraj.
   *
   * @return the response
   */
  @Path("kraj")
  @HEAD
  public Response headPosluziteljKraj();

}
