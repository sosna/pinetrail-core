package io.pinesoft.trail.api.io;

import static org.junit.jupiter.api.Assertions.*;

import io.pinesoft.trail.util.ExecutionError;
import io.pinesoft.trail.util.StatusCodes;
import java.nio.file.FileSystems;
import org.junit.jupiter.api.Test;

class FormatsTest {

  @Test
  void getGpx1_0() {
    assertEquals(
        Formats.GPX_1_0,
        Formats.of(FileSystems.getDefault().getPath(".", "src/test/resources/gpx1_0.gpx")));
  }

  @Test
  void getGpx1_1() {
    assertEquals(
        Formats.GPX_1_1,
        Formats.of(FileSystems.getDefault().getPath(".", "src/test/resources/gpx1_1.gpx")));
  }

  @Test
  void throwNotAcceptable() {
    try {
      Formats.of(FileSystems.getDefault().getPath(".", "src/test/resources/logback-test.xml"));
      fail("Expected 406");
    } catch (final ExecutionError e) {
      if (StatusCodes.UNSUPPORTED_FORMAT != e.getErrorCode()) {
        fail("Expected 406 but got " + e.getErrorCode());
      }
    }
  }

  @Test
  void throwNotFound() {
    try {
      Formats.of(FileSystems.getDefault().getPath(".", "src/test/resources/nothere.why"));
      fail("Expected 404");
    } catch (final ExecutionError e) {
      if (StatusCodes.NOT_FOUND != e.getErrorCode()) {
        fail("Expected 404 but got " + e.getErrorCode());
      }
    }
  }
}
