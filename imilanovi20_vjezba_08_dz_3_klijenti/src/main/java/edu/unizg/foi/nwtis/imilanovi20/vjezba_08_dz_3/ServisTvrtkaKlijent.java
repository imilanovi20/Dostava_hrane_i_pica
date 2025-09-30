package edu.unizg.foi.nwtis.imilanovi20.vjezba_08_dz_3;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import edu.unizg.foi.nwtis.podaci.Partner;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HEAD;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

// TODO: Auto-generated Javadoc
/**
 * The Interface ServisTvrtkaKlijent.
 */
@RegisterRestClient(configKey = "klijentTvrtka")
@Path("api/tvrtka")
public interface ServisTvrtkaKlijent {

  /**
   * Head posluzitelj.
   *
   * @return the response
   */
  @HEAD
  public Response headPosluzitelj();

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

  /**
   * Head posluzitelj kraj info.
   *
   * @return the response
   */
  @Path("kraj/info")
  @HEAD
  public Response headPosluziteljKrajInfo();

  /**
   * Gets the partneri.
   *
   * @return the partneri
   */
  @Path("partner")
  @GET
  @Produces({MediaType.APPLICATION_JSON})
  public Response getPartneri();

  /**
   * Gets the partner.
   *
   * @param id the id
   * @return the partner
   */
  @Path("partner/{id}")
  @GET
  @Produces({MediaType.APPLICATION_JSON})
  public Response getPartner(@PathParam("id") int id);

  /**
   * Gets the obracuni od do.
   *
   * @param odVr the od vr
   * @param doVr the do vr
   * @return the obracuni od do
   */
  @Path("obracun")
  @GET
  @Produces({MediaType.APPLICATION_JSON})
  public Response getObracuniOdDo(@QueryParam("od") Long odVr, @QueryParam("do") Long doVr);

  /**
   * Gets the obr jelo od do.
   *
   * @param odVr the od vr
   * @param doVr the do vr
   * @return the obr jelo od do
   */
  @Path("obracun/jelo")
  @GET
  @Produces({MediaType.APPLICATION_JSON})
  public Response getObrJeloOdDo(@QueryParam("od") Long odVr, @QueryParam("do") Long doVr);

  /**
   * Gets the obr pice od do.
   *
   * @param odVr the od vr
   * @param doVr the do vr
   * @return the obr pice od do
   */
  @Path("obracun/pice")
  @GET
  @Produces({MediaType.APPLICATION_JSON})
  public Response getObrPiceOdDo(@QueryParam("od") Long odVr, @QueryParam("do") Long doVr);

  /**
   * Gets the obr partner od do.
   *
   * @param odVr the od vr
   * @param doVr the do vr
   * @param id the id
   * @return the obr partner od do
   */
  @Path("obracun/{id}")
  @GET
  @Produces({MediaType.APPLICATION_JSON})
  public Response getObrPartnerOdDo(@QueryParam("od") Long odVr, @QueryParam("do") Long doVr,
      @PathParam("id") int id);

  /**
   * Post partner.
   *
   * @param partner the partner
   * @return the response
   */
  @Path("partner")
  @POST
  @Consumes({MediaType.APPLICATION_JSON})
  @Produces({MediaType.APPLICATION_JSON})
  public Response postPartner(Partner partner);

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

}
