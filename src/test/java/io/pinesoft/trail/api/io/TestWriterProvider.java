package io.pinesoft.trail.api.io;

import io.pinesoft.trail.model.GpsRecord;
import java.nio.file.Path;
import java.util.Optional;
import java.util.Set;

public class TestWriterProvider implements WriterProvider {

  public TestWriterProvider() {
    super();
  }

  @Override
  public Optional<Writer> newWriter(final Formats format) {
    return Optional.ofNullable(Formats.GPX_1_1 == format ? new TestWriter() : null);
  }

  private static final class TestWriter implements Writer {

    public TestWriter() {
      super();
    }

    @Override
    public void accept(final Set<GpsRecord> trail, final Path location) {
      // do nothing
    }
  }
}
