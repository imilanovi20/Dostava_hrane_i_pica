package edu.unizg.foi.nwtis.imilanovi20.vjezba_08_dz_3.jf.konfig;

import jakarta.faces.context.ExceptionHandler;
import jakarta.faces.context.ExceptionHandlerFactory;

// TODO: Auto-generated Javadoc
/**
 * A factory for creating UpravljanjeIznimkom objects.
 */
public class UpravljanjeIznimkomFactory extends ExceptionHandlerFactory {

  /** The parent. */
  private final ExceptionHandlerFactory parent;

  /**
   * Instantiates a new upravljanje iznimkom factory.
   *
   * @param parent the parent
   */
  public UpravljanjeIznimkomFactory(ExceptionHandlerFactory parent) {
    super(parent);
    this.parent = parent;
  }

  /**
   * Gets the exception handler.
   *
   * @return the exception handler
   */
  @Override
  public ExceptionHandler getExceptionHandler() {
    return new UpravljanjeIznimkom(parent.getExceptionHandler());
  }
}

