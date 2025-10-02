package org.unizg.foi.nwtis.imilanovi20.rest;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

// TODO: Auto-generated Javadoc
/**
 * The Interface TvrtkaInfoKlijent.
 */
@RegisterRestClient(configKey = "klijentTvrtkaInfo")
@Path("api/tvrtka")
public interface TvrtkaInfoKlijent {

  /**
   * Gets the posluzitelj kraj info.
   *
   * @return the posluzitelj kraj info
   */
  @Path("kraj/info")
  @GET
  public Response getPosluziteljKrajInfo();

  /**
   * Gets the obracun WS.
   *
   * @return the obracun WS
   */
  @Path("obracun/ws")
  @GET
  public Response getObracunWS();

}
