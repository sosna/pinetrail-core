package io.pinesoft.trail.util;

/**
 * The result of an action.
 *
 * <p>This list is a subset of the HTTP status codes.
 *
 * @author Xavier Sosnovsky
 */
public enum StatusCodes {
  OK(200),
  NOT_MODIFIED(304),
  SYNTAX_ERROR(400),
  UNAUTHORIZED(401),
  FORBIDDEN(403),
  NOT_FOUND(404),
  TIME_OUT(408),
  UNSUPPORTED_FORMAT(415),
  INTERNAL_ERROR(500);

  private final int code;

  StatusCodes(final int code) {
    this.code = code;
  }

  /** @return the code that summarises the result of an action. */
  public int getCode() {
    return code;
  }
}
