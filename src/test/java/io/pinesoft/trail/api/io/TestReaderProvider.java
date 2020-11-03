package io.pinesoft.trail.api.io;

import io.pinesoft.trail.model.GpsRecord;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Properties;
import java.util.SortedSet;

public class TestReaderProvider implements ReaderProvider {

  public TestReaderProvider() {
    super();
  }

  @Override
  public Optional<Reader> newReader(final Formats format) {
    return Optional.ofNullable(Formats.GPX_1_1 == format ? new TestReader(null) : null);
  }

  @Override
  public Optional<Reader> newReader(Formats format, Properties props) {
    return Optional.ofNullable(Formats.GPX_1_1 == format ? new TestReader(props) : null);
  }

  @Override
  public Reader newReader() {
    return new TestReader(null);
  }

  @Override
  public Reader newReader(Properties props) {
    return new TestReader(props);
  }

  @Override
  public Formats getFormat() {
    return Formats.GPX_1_1;
  }

  private static final class TestReader implements Reader {

    final Properties props;

    public TestReader(final Properties props) {
      super();
      this.props = null;
    }

    @Override
    public Collection<SortedSet<GpsRecord>> apply(final Path fileLocation) {
      return Collections.emptySet();
    }
  }
}
