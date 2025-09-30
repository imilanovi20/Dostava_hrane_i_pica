/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */
package edu.unizg.foi.nwtis.imilanovi20.vjezba_08_dz_3;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import edu.unizg.foi.nwtis.podaci.Obracun;
import edu.unizg.foi.nwtis.podaci.Partner;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.mvc.Controller;
import jakarta.mvc.Models;
import jakarta.mvc.View;
import jakarta.mvc.binding.BindingResult;
import jakarta.security.enterprise.SecurityContext;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.Response;

// TODO: Auto-generated Javadoc
/**
 * The Class Kontroler.
 *
 * @author NWTiS
 */
@Controller
@Path("tvrtka")
@RequestScoped
public class Kontroler {

  /** The model. */
  @Inject
  private Models model;

  /** The binding result. */
  @Inject
  private BindingResult bindingResult;

  /** The servis tvrtka. */
  @Inject
  @RestClient
  ServisTvrtkaKlijent servisTvrtka;

  /** The security context. */
  @Inject
  private SecurityContext securityContext;


  /**
   * Pocetak.
   */
  @GET
  @Path("pocetak")
  @View("index.jsp")
  public void pocetak() {}

  /**
   * Pocetak privatno.
   */
  @GET
  @Path("privatno/pocetak")
  @View("index.jsp")
  public void pocetakPrivatno() {}


  /**
   * Provjeri rad posluzitelja.
   */
  @GET
  @Path("provjera")
  @View("provjera.jsp")
  public void provjeriRadPosluzitelja() {
    try {
      var status = this.servisTvrtka.headPosluzitelj().getStatus();
      this.model.put("statusPosluzitelja", status);
      if (status == 200) {
        this.model.put("poruka", "Poslužitelj Tvrtka radi ispravno");
      } else {
        this.model.put("poruka", "Poslužitelj Tvrtka ne radi");
      }
    } catch (Exception e) {
      this.model.put("poruka", "Greška pri povezivanju s poslužiteljem");
    }
  }

  /**
   * Kraj.
   */
  @GET
  @Path("kraj")
  @View("status.jsp")
  public void kraj() {
    try {
      var status = this.servisTvrtka.headPosluziteljKraj().getStatus();
      this.model.put("statusOperacije", status);
      dohvatiStatuse();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Status.
   */
  @GET
  @Path("status")
  @View("status.jsp")
  public void status() {
    dohvatiStatuse();
  }

  /**
   * Start id.
   *
   * @param id the id
   */
  @GET
  @Path("start/{id}")
  @View("status.jsp")
  public void startId(@PathParam("id") int id) {
    var status = this.servisTvrtka.headPosluziteljStart(id).getStatus();
    this.model.put("status", status);
    this.model.put("samoOperacija", true);
  }

  /**
   * Pauzat id.
   *
   * @param id the id
   */
  @GET
  @Path("pauza/{id}")
  @View("status.jsp")
  public void pauzatId(@PathParam("id") int id) {
    var status = this.servisTvrtka.headPosluziteljPauza(id).getStatus();
    this.model.put("status", status);
    this.model.put("samoOperacija", true);
  }

  /**
   * Partneri.
   */
  @GET
  @Path("partner")
  @View("partneri.jsp")
  public void partneri() {
    var odgovor = this.servisTvrtka.getPartneri();
    var status = odgovor.getStatus();

    if (status == 200) {
      var partneri = odgovor.readEntity(new GenericType<List<Partner>>() {});
      this.model.put("status", status);
      this.model.put("partneri", partneri);
    } else {
      this.model.put("status", status);
    }
  }

  /**
   * Partner.
   *
   * @param id the id
   */
  @GET
  @Path("partner/{id}")
  @View("partner.jsp")
  public void partner(@PathParam("id") int id) {
    var odgovor = this.servisTvrtka.getPartner(id);
    var status = odgovor.getStatus();

    if (status == 200) {
      var partner = odgovor.readEntity(new GenericType<Partner>() {});
      this.model.put("status", status);
      this.model.put("partner", partner);
    } else {
      this.model.put("status", status);
    }
  }

  /**
   * Obracuni.
   *
   * @param vrstaObracuna the vrsta obracuna
   * @param datumOdString the datum od string
   * @param datumDoString the datum do string
   */
  @GET
  @Path("privatno/obracuni")
  @View("obracuni.jsp")
  public void obracuni(@QueryParam("vrstaObracuna") String vrstaObracuna,
      @QueryParam("odVr") String datumOdString, @QueryParam("doVr") String datumDoString) {

    if (vrstaObracuna == null || vrstaObracuna.isEmpty()) {
      this.model.put("status", 200);
      this.model.put("vrstaDohvacanja", "bez");
      return;
    }

    Long odVr = null;
    Long doVr = null;

    try {
      if (datumOdString != null && !datumOdString.trim().isEmpty()) {
        LocalDateTime dateTimeOd = LocalDateTime.parse(datumOdString);
        odVr = dateTimeOd.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
      }

      if (datumDoString != null && !datumDoString.trim().isEmpty()) {
        LocalDateTime dateTimeDo = LocalDateTime.parse(datumDoString);
        doVr = dateTimeDo.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
      }
    } catch (Exception e) {
      this.model.put("status", 400);
      this.model.put("vrstaDohvacanja", "greska");
      this.model.put("poruka", "Neispravni format datuma: " + e.getMessage());
      return;
    }
    Response odgovor = dohvatiPotrebneObr(vrstaObracuna, odVr, doVr);

    if (odgovor != null) {
      var status = odgovor.getStatus();
      if (status == 200) {
        var obracuni = odgovor.readEntity(new GenericType<List<Obracun>>() {});
        this.model.put("status", status);
        this.model.put("obracuni", obracuni);
      } else {
        this.model.put("status", status);
      }
    }
  }

  /**
   * Dohvati potrebne obr.
   *
   * @param vrstaObracuna the vrsta obracuna
   * @param odVr the od vr
   * @param doVr the do vr
   * @return the response
   */
  private Response dohvatiPotrebneObr(String vrstaObracuna, Long odVr, Long doVr) {
    Response odgovor = null;
    switch (vrstaObracuna) {
      case "svi":
        this.model.put("vrstaDohvacanja", "obracuni");
        odgovor = this.servisTvrtka.getObracuniOdDo(odVr, doVr);
        break;

      case "jela":
        this.model.put("vrstaDohvacanja", "jela");
        odgovor = this.servisTvrtka.getObrJeloOdDo(odVr, doVr);
        break;

      case "pica":
        this.model.put("vrstaDohvacanja", "pica");
        odgovor = this.servisTvrtka.getObrPiceOdDo(odVr, doVr);
        break;
    }
    return odgovor;
  }

  /**
   * Obracuni partner.
   *
   * @param idPartnera the id partnera
   * @param datumOdString the datum od string
   * @param datumDoString the datum do string
   */
  @GET
  @Path("privatno/obracuniPartnera")
  @View("obracuniPartnera.jsp")
  public void obracuniPartner(@QueryParam("idPartnera") Integer idPartnera,
      @QueryParam("odVr") String datumOdString, @QueryParam("doVr") String datumDoString) {

    var partneriOdgovor = this.servisTvrtka.getPartneri();
    if (partneriOdgovor.getStatus() == 200) {
      var partneri = partneriOdgovor.readEntity(new GenericType<List<Partner>>() {});
      this.model.put("partneri", partneri);
    }

    if (idPartnera == null || idPartnera <= 0) {
      this.model.put("status", 200);
      this.model.put("vrstaDohvacanja", "bez");
      return;
    }

    Long odVr = null;
    Long doVr = null;

    try {
      if (datumOdString != null && !datumOdString.trim().isEmpty()) {
        LocalDateTime dateTimeOd = LocalDateTime.parse(datumOdString);
        odVr = dateTimeOd.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
      }

      if (datumDoString != null && !datumDoString.trim().isEmpty()) {
        LocalDateTime dateTimeDo = LocalDateTime.parse(datumDoString);
        doVr = dateTimeDo.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
      }
    } catch (Exception e) {
      this.model.put("status", 400);
      this.model.put("vrstaDohvacanja", "greska");
      this.model.put("poruka", "Neispravni format datuma: " + e.getMessage());
      return;
    }

    postaviStatuse(idPartnera, odVr, doVr);
  }

  /**
   * Postavi statuse.
   *
   * @param idPartnera the id partnera
   * @param odVr the od vr
   * @param doVr the do vr
   */
  private void postaviStatuse(Integer idPartnera, Long odVr, Long doVr) {
    this.model.put("vrstaDohvacanja", "partner");
    this.model.put("odabraniPartnerId", idPartnera);

    var odgovor = this.servisTvrtka.getObrPartnerOdDo(odVr, doVr, idPartnera);

    if (odgovor != null) {
      var status = odgovor.getStatus();
      if (status == 200) {
        var obracuni = odgovor.readEntity(new GenericType<List<Obracun>>() {});
        this.model.put("status", status);
        this.model.put("obracuni", obracuni);

        var partnerOdgovor = this.servisTvrtka.getPartner(idPartnera);
        if (partnerOdgovor.getStatus() == 200) {
          var partner = partnerOdgovor.readEntity(Partner.class);
          this.model.put("odabraniPartner", partner);
        }
      } else {
        this.model.put("status", status);
        this.model.put("poruka", "Greška pri dohvaćanju obračuna partnera.");
      }
    } else

    {
      this.model.put("status", 500);
      this.model.put("vrstaDohvacanja", "greska");
      this.model.put("poruka", "Nema odgovora od servisa.");
    }
  }

  /**
   * Obracuni jela.
   *
   * @param odVr the od vr
   * @param doVr the do vr
   */
  @GET
  @Path("privatno/obracuni/jela")
  @View("obracuni.jsp")
  public void obracuniJela(@QueryParam("od") Long odVr, @QueryParam("do") Long doVr) {
    this.model.put("vrstaDohvacanja", "jela");
    var odgovor = this.servisTvrtka.getObrJeloOdDo(odVr, doVr);
    var status = odgovor.getStatus();
    if (status == 200) {
      var obracuni = odgovor.readEntity(new GenericType<List<Obracun>>() {});
      this.model.put("status", status);
      this.model.put("obracuni", obracuni);
    } else {
      this.model.put("status", status);
    }
  }

  /**
   * Obracuni pica.
   *
   * @param odVr the od vr
   * @param doVr the do vr
   */
  @GET
  @Path("privatno/obracuni/pica")
  @View("obracuni.jsp")
  public void obracuniPica(@QueryParam("od") Long odVr, @QueryParam("do") Long doVr) {
    this.model.put("vrstaDohvacanja", "pica");
    var odgovor = this.servisTvrtka.getObrPiceOdDo(odVr, doVr);
    var status = odgovor.getStatus();
    if (status == 200) {
      var obracuni = odgovor.readEntity(new GenericType<List<Obracun>>() {});
      this.model.put("status", status);
      this.model.put("obracuni", obracuni);
    } else {
      this.model.put("status", status);
    }
  }

  /**
   * Dodaj partnera.
   *
   * @param id the id
   * @param naziv the naziv
   * @param vrstaKuhinje the vrsta kuhinje
   * @param adresa the adresa
   * @param mreznaVrata the mrezna vrata
   * @param gpsSirina the gps sirina
   * @param gpsDuzina the gps duzina
   * @param mreznaVrataKraj the mrezna vrata kraj
   * @param adminKod the admin kod
   */
  @POST
  @Path("admin/dodajPartnera")
  @View("dodajPartnera.jsp")
  public void dodajPartnera(@FormParam("id") int id, @FormParam("naziv") String naziv,
      @FormParam("vrstaKuhinje") String vrstaKuhinje, @FormParam("adresa") String adresa,
      @FormParam("mreznaVrata") int mreznaVrata, @FormParam("gpsSirina") float gpsSirina,
      @FormParam("gpsDuzina") float gpsDuzina, @FormParam("mreznaVrataKraj") int mreznaVrataKraj,
      @FormParam("adminKod") String adminKod) {

    try {
      Partner noviPartner = new Partner(id, naziv, vrstaKuhinje, adresa, mreznaVrata,
          mreznaVrataKraj, gpsSirina, gpsDuzina, "", adminKod);
      var odgovor = this.servisTvrtka.postPartner(noviPartner);

      this.model.put("status", odgovor.getStatus());

      if (odgovor.getStatus() == 201) {
        this.model.put("poruka", "Partner je uspješno dodan!");
      } else if (odgovor.getStatus() == 409) {
        this.model.put("poruka", "Partner s tim ID-jem već postoji!");
      } else {
        this.model.put("poruka", "Greška prilikom dodavanja partnera!");
      }
    } catch (Exception e) {
      this.model.put("poruka", "Greška: " + e.getMessage());
    }

  }


  /**
   * Dodaj partnera prikaz.
   */
  @GET
  @Path("admin/dodajPartnera")
  @View("dodajPartnera.jsp")
  public void dodajPartneraPrikaz() {}

  /**
   * Spavanje prikaz.
   *
   * @param vrijeme the vrijeme
   */
  @GET
  @Path("admin/spavanje")
  @View("spavanje.jsp")
  public void spavanjePrikaz(@QueryParam("vrijeme") Long vrijeme) {
    try {
      if (vrijeme != null) {
        if (vrijeme > 0) {
          var odgovor = this.servisTvrtka.getSpava(vrijeme);
          this.model.put("status", odgovor.getStatus());

          if (odgovor.getStatus() == 200) {
            this.model.put("poruka", "Spavanje uspiješno " + vrijeme + " milisekundi");
          } else {
            this.model.put("poruka", "Greška prilikom spavanja!");
          }
        } else {
          this.model.put("poruka", "Vrijeme mora biti veće od 0!");
        }
      }
    } catch (Exception e) {
      this.model.put("poruka", "Greška: " + e.getMessage());
    }
  }


  /**
   * Nadzorna konzola tvrtka.
   *
   * @param zaustavi the zaustavi
   * @param pokreni the pokreni
   * @param kraj the kraj
   */
  @GET
  @Path("admin/nadzornaKonzolaTvrtka")
  @View("nadzornaKonzolaTvrtka.jsp")
  public void nadzornaKonzolaTvrtka(@QueryParam("zaustavi") Integer zaustavi,
      @QueryParam("pokreni") Integer pokreni, @QueryParam("kraj") Boolean kraj) {
    try {
      if (zaustavi != null)
        this.servisTvrtka.headPosluziteljPauza(zaustavi);
      if (pokreni != null)
        this.servisTvrtka.headPosluziteljStart(pokreni);
      if (kraj != null && kraj == true)
        this.servisTvrtka.headPosluziteljKraj();
      var statusPosluzitelja = this.servisTvrtka.headPosluzitelj().getStatus();
      this.model.put("statusPosluzitelja", statusPosluzitelja);
      dohvatiStatuse();
    } catch (Exception e) {

    }

  }

  /**
   * Dohvati statuse.
   */
  private void dohvatiStatuse() {
    this.model.put("samoOperacija", false);
    var statusT = this.servisTvrtka.headPosluzitelj().getStatus();
    this.model.put("statusT", statusT);
    var statusT1 = this.servisTvrtka.headPosluziteljStatus(1).getStatus();
    this.model.put("statusT1", statusT1);
    var statusT2 = this.servisTvrtka.headPosluziteljStatus(2).getStatus();
    this.model.put("statusT2", statusT2);
  }

}
