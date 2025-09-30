package edu.unizg.foi.nwtis.imilanovi20.vjezba_08_dz_3.jf.konfig;

import java.io.IOException;
import java.util.Iterator;
import jakarta.faces.FacesException;
import jakarta.faces.application.ViewExpiredException;
import jakarta.faces.context.ExceptionHandler;
import jakarta.faces.context.ExceptionHandlerWrapper;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.ExceptionQueuedEvent;

// TODO: Auto-generated Javadoc
/**
 * The Class UpravljanjeIznimkom.
 */
public class UpravljanjeIznimkom extends ExceptionHandlerWrapper {

  /** The wrapped. */
  private final ExceptionHandler wrapped;

  /**
   * Instantiates a new upravljanje iznimkom.
   *
   * @param wrapped the wrapped
   */
  public UpravljanjeIznimkom(ExceptionHandler wrapped) {
    super(wrapped);
    this.wrapped = wrapped;
  }

  /**
   * Gets the wrapped.
   *
   * @return the wrapped
   */
  @Override
  public ExceptionHandler getWrapped() {
    return wrapped;
  }

  /**
   * Handle.
   *
   * @throws FacesException the faces exception
   */
  @Override
  public void handle() throws FacesException {
    for (Iterator<ExceptionQueuedEvent> i = getUnhandledExceptionQueuedEvents().iterator(); i
        .hasNext();) {
      ExceptionQueuedEvent event = i.next();
      Throwable t = event.getContext().getException();

      if (t instanceof ViewExpiredException) {
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        try {
          ec.redirect(ec.getRequestContextPath() + "/index.xhtml");
          fc.responseComplete();
          i.remove();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
    wrapped.handle();
  }
}
