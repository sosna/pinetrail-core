package io.pinesoft.trail.utils.log;

import java.util.Locale;

/**
 * The type of action whose result is being logged.
 *
 * @author Xavier Sosnovsky
 */
public enum Actions {

  /** The action of creating something. */
  CREATE,
  /** The action of retrieving something. */
  GET,
  /** The action of updating something. */
  UPDATE,
  /** The action of deleting something. */
  DELETE,
  /** The action of registering something. */
  REGISTER,
  /** The action of unregistering something. */
  UNREGISTER,
  /** The action of parsing something. */
  PARSE,
  /** The action of performing some statistical analysis. */
  ANALYSE,
  /** The action of validating input. */
  VALIDATE,
  /** The action of opening something (like a connection to a service). */
  OPEN,
  /** The action of closing something (like a connection to a service). */
  CLOSE,
  /**
   * The action of persisting something to a store. Can also be used when it is not clear whether an
   * action creates or updates an object.
   */
  PERSIST;

  @Override
  public String toString() {
    return String.format("%-10s", this.name().toLowerCase(Locale.getDefault()));
  }
}
