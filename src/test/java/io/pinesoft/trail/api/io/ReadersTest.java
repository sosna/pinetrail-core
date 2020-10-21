package io.pinesoft.trail.api.io;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class ReadersTest {

  @Test
  void registerProviderAndGetReader() {
    final Reader reader = Readers.INSTANCE.newReader(Formats.GPX_1_1);
    assertNotNull(reader);
  }

  @Test()
  void unsupportedFormat() {
    assertThrows(
        UnsupportedOperationException.class,
        () -> {
          Readers.valueOf("INSTANCE").newReader(Formats.valueOf("KML_2_2_0"));
        });
  }
}
