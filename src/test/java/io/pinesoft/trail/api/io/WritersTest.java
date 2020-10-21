package io.pinesoft.trail.api.io;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class WritersTest {

  @Test
  void registerProviderAndGetWriter() {
    final Writer writer = Writers.INSTANCE.newWriter(Formats.GPX_1_1);
    assertNotNull(writer);
  }

  @Test()
  void unsupportedFormat() {
    assertThrows(
        UnsupportedOperationException.class,
        () -> {
          Writers.valueOf("INSTANCE").newWriter(Formats.valueOf("KML_2_2_0"));
        });
  }
}
