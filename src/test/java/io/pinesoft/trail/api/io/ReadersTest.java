package io.pinesoft.trail.api.io;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/** @author Xavier Sosnovsky */
public class ReadersTest {

  @Test
  public void registerProviderAndGetReader() {
    final Reader reader = Readers.INSTANCE.newReader(Formats.GPX_1_1);
    assertNotNull(reader);
  }

  @Test()
  public void unsupportedFormat() {
    assertThrows(
        UnsupportedOperationException.class,
        () -> {
          Readers.valueOf("INSTANCE").newReader(Formats.valueOf("KML_2_2_0"));
        });
  }
}
