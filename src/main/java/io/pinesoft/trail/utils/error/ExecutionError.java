package io.pinesoft.trail.utils.error;

import io.pinesoft.trail.utils.log.Actions;
import io.pinesoft.trail.utils.log.StatusCodes;
import org.slf4j.Marker;

/**
 * Error thrown when one of the Pinetrail processes fails to complete successfully.
 *
 * <p>The error should contain enough information so as to be useful to the upper layers (e.g.:
 * GUI).
 *
 * @author Xavier Sosnovsky
 */
public final class ExecutionError extends RuntimeException {

  private static final long serialVersionUID = -2958938847028125286L;
  private final Marker marker;
  private final Actions action;
  private final StatusCodes errorCode;

  /**
   * Instantiates a new execution error with the specified message and cause.
   *
   * @param message the error message
   * @param cause the error cause
   * @param marker the module where the error happened
   * @param action the action performed when the error happened
   * @param errorCode the errorCode
   */
  public ExecutionError(
      final String message,
      final Throwable cause,
      final Marker marker,
      final Actions action,
      final StatusCodes errorCode) {
    super(message, cause);
    this.marker = marker;
    this.action = action;
    this.errorCode = errorCode;
  }

  /**
   * Returns the module where the error occurred.
   *
   * @return the module where the error occurred
   */
  public Marker getMarker() {
    return marker;
  }

  /**
   * Returns the action performed when the error occurred.
   *
   * @return the action performed when the error occurred
   */
  public Actions getAction() {
    return action;
  }

  /**
   * Returns the code describing the error.
   *
   * @return the code describing the error
   */
  public StatusCodes getErrorCode() {
    return errorCode;
  }
}
