package io.pinesoft.trail.api.io;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import io.pinesoft.trail.util.ExecutionError;
import io.pinesoft.trail.util.StatusCodes;
import java.nio.file.FileSystems;
import java.nio.file.Path;
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
  void getGpx1_1OneLine() {
    assertEquals(
        Formats.GPX_1_1,
        Formats.of(
            FileSystems.getDefault().getPath(".", "src/test/resources/gpx1_1_one_line.gpx")));
  }

  @Test
  void throwNotAcceptable1() {
    final Path path = FileSystems.getDefault().getPath(".", "src/test/resources/logback-test.xml");
    final ExecutionError e = assertThrows(ExecutionError.class, () -> Formats.of(path));
    assertEquals(StatusCodes.UNSUPPORTED_FORMAT, e.getErrorCode());
  }

  @Test
  void throwNotAcceptable2() {
    final Path path =
        FileSystems.getDefault().getPath(".", "src/test/resources/logback-test-short.xml");
    final ExecutionError e = assertThrows(ExecutionError.class, () -> Formats.of(path));
    assertEquals(StatusCodes.UNSUPPORTED_FORMAT, e.getErrorCode());
  }

  @Test
  void throwNotFound() {
    final Path path = FileSystems.getDefault().getPath(".", "src/test/resources/nothere.why");
    final ExecutionError e = assertThrows(ExecutionError.class, () -> Formats.of(path));
    assertEquals(StatusCodes.NOT_FOUND, e.getErrorCode());
  }
}
