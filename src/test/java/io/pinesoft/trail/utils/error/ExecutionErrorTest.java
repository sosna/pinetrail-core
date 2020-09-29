package io.pinesoft.trail.utils.error;

import static org.junit.jupiter.api.Assertions.*;

import io.pinesoft.trail.utils.log.Actions;
import io.pinesoft.trail.utils.log.Markers;
import io.pinesoft.trail.utils.log.StatusCodes;
import org.junit.jupiter.api.Test;
import org.slf4j.Marker;

/** @author Xavier Sosnovsky */
public class ExecutionErrorTest {

  @Test
  public void getLoggingInfo() {
    final String message = "Sorry, my mistake.";
    final Marker marker = Markers.DB.getMarker();
    final Actions action = Actions.ANALYSE;
    final StatusCodes errorCode = StatusCodes.INTERNAL_ERROR;
    final IllegalArgumentException cause = new IllegalArgumentException();
    final ExecutionError error = new ExecutionError(message, cause, marker, action, errorCode);
    assertEquals(marker, error.getMarker());
    assertEquals(action, error.getAction());
    assertEquals(errorCode, error.getErrorCode());
    assertEquals(cause, error.getCause());
    assertEquals(message, error.getMessage());
  }
}
