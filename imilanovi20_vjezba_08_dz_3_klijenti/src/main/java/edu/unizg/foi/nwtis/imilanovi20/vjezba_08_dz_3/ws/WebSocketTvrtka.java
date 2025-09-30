package edu.unizg.foi.nwtis.imilanovi20.vjezba_08_dz_3.ws;

import java.io.IOException;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import edu.unizg.foi.nwtis.imilanovi20.vjezba_08_dz_3.GlobalniPodaci;
import edu.unizg.foi.nwtis.imilanovi20.vjezba_08_dz_3.ServisTvrtkaKlijent;
import jakarta.inject.Inject;
import jakarta.websocket.CloseReason;
import jakarta.websocket.EndpointConfig;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnError;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;

// TODO: Auto-generated Javadoc
/**
 * The Class WebSocketTvrtka.
 */
@ServerEndpoint("/ws/tvrtka")
public class WebSocketTvrtka {

  /** The queue. */
  static Queue<Session> queue = new ConcurrentLinkedQueue<>();

  /** The servis tvrtka klijent. */
  @Inject
  @RestClient
  ServisTvrtkaKlijent servisTvrtkaKlijent;

  /** The globalni podaci. */
  @Inject
  GlobalniPodaci globalniPodaci;


  /**
   * Send.
   *
   * @param poruka the poruka
   */
  public static void send(String poruka) {
    try {
      for (Session session : queue) {
        if (session.isOpen()) {
          session.getBasicRemote().sendText(poruka);
        }
      }
    } catch (IOException ex) {
    }
  }

  /**
   * Kreiraj poruku.
   *
   * @param poruka the poruka
   * @return the string
   */
  public String kreirajPoruku(String poruka) {
    int brojObracuna = globalniPodaci.getBrojObracuna();
    String stanje = provjeriStatusPosluzitelja() ? "RADI" : "NE RADI";

    String porukaSlanje = stanje + ";" + brojObracuna + ";" + (poruka != null ? poruka : "");
    return porukaSlanje;
  }

  /**
   * Provjeri status posluzitelja.
   *
   * @return true, if successful
   */
  private boolean provjeriStatusPosluzitelja() {
    try {
      var odgovor = servisTvrtkaKlijent.headPosluzitelj();
      return odgovor != null && odgovor.getStatus() == 200;
    } catch (Exception e) {
      return false;
    }
  }


  /**
   * Open connection.
   *
   * @param session the session
   * @param conf the conf
   */
  @OnOpen
  public void openConnection(Session session, EndpointConfig conf) {
    queue.add(session);
  }

  /**
   * Closed connection.
   *
   * @param session the session
   * @param reason the reason
   */
  @OnClose
  public void closedConnection(Session session, CloseReason reason) {
    queue.remove(session);
  }

  /**
   * Message.
   *
   * @param session the session
   * @param poruka the poruka
   */
  @OnMessage
  public void Message(Session session, String poruka) {
    String porukaSlanje = kreirajPoruku(poruka);
    WebSocketTvrtka.send(porukaSlanje);
  }

  /**
   * Error.
   *
   * @param session the session
   * @param t the t
   */
  @OnError
  public void error(Session session, Throwable t) {
    queue.remove(session);
  }
}
