package io.pinesoft.trail.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.slf4j.Marker;

/** @author Xavier Sosnovsky */
public class ExecutionErrorTest {

  @Test
  public void getLoggingInfo() {
    final String message = "Sorry, my mistake.";
    final Marker marker = Markers.CONFIG.getMarker();
    final StatusCodes errorCode = StatusCodes.INTERNAL_ERROR;
    final IllegalArgumentException cause = new IllegalArgumentException();
    final ExecutionError error = new ExecutionError(message, cause, marker, errorCode);
    assertEquals(marker, error.getMarker());
    assertEquals(errorCode, error.getErrorCode());
    assertEquals(cause, error.getCause());
    assertEquals(message, error.getMessage());
  }
}
