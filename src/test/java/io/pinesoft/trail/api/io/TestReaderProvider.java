package io.pinesoft.trail.api.io;

import io.pinesoft.trail.model.GpsRecord;
import java.nio.file.Path;
import java.util.Collections;
import java.util.Set;

/** @author Xavier Sosnovsky */
public class TestReaderProvider implements ReaderProvider {

  public TestReaderProvider() {
    super();
  }

  @Override
  public Reader newReader(final Formats format) {
    return Formats.GPX_1_1 == format ? new TestReader() : null;
  }

  private static final class TestReader implements Reader {

    public TestReader() {
      super();
    }

    @Override
    public Set<GpsRecord> apply(final Path fileLocation) {
      return Collections.emptySet();
    }
  }
}
