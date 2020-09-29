package io.pinesoft.trail.api.io;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/** @author Xavier Sosnovsky */
public class WritersTest {

  @Test
  public void registerProviderAndGetWriter() {
    final Writer writer = Writers.INSTANCE.newWriter(Formats.GPX_1_1);
    assertNotNull(writer);
  }

  @Test()
  public void unsupportedFormat() {
    assertThrows(
        UnsupportedOperationException.class,
        () -> {
          Writers.valueOf("INSTANCE").newWriter(Formats.valueOf("KML_2_2_0"));
        });
  }
}
