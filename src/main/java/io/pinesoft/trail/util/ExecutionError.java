package io.pinesoft.trail.util;

import org.slf4j.Marker;

/**
 * Error to be used by Pinetrail processes.
 *
 * @author Xavier Sosnovsky
 */
public final class ExecutionError extends RuntimeException {

  private static final long serialVersionUID = 6191256467469854130L;
  private final Marker marker;
  private final StatusCodes errorCode;

  /**
   * Instantiates a new execution error.
   *
   * @param message the error message
   * @param cause the error cause
   * @param marker the module where the error happened
   * @param errorCode the error code
   */
  public ExecutionError(
      final String message,
      final Throwable cause,
      final Marker marker,
      final StatusCodes errorCode) {
    super(message, cause);
    this.marker = marker;
    this.errorCode = errorCode;
  }

  /** @return the module where the error occurred */
  public Marker getMarker() {
    return marker;
  }

  /** @return the code describing the error */
  public StatusCodes getErrorCode() {
    return errorCode;
  }
}
