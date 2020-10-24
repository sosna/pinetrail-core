package io.pinesoft.trail.api.io;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import io.pinesoft.trail.util.ExecutionError;
import org.junit.jupiter.api.Test;

class ReadersTest {

  @Test
  void registerProviderAndGetReader() {
    final Reader reader = Readers.INSTANCE.newReader(Formats.GPX_1_1);
    assertNotNull(reader);
  }

  @Test()
  void unsupportedFormat() {
    final Formats format = Formats.KML_2_2_0;
    assertThrows(ExecutionError.class, () -> Readers.INSTANCE.newReader(format));
  }
}
