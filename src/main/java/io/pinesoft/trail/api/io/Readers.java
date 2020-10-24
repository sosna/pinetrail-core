package io.pinesoft.trail.api.io;

import io.pinesoft.trail.util.ExecutionError;
import io.pinesoft.trail.util.Markers;
import io.pinesoft.trail.util.StatusCodes;
import java.util.EnumMap;
import java.util.Map;
import java.util.ServiceLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utility class that instantiates readers for one of the supported formats.
 *
 * <p>In the background, this class uses a ServiceLoader to register the {@code ReaderProviders}
 * that will be used to instantiate the {@code Readers} returned to the client.
 *
 * @author Xavier Sosnovsky
 */
public enum Readers {

  /** Singleton instance of Pinetrail readers. */
  INSTANCE;

  private static final Logger LOGGER = LoggerFactory.getLogger(Readers.class);
  private static final Map<Formats, ReaderProvider> PROVIDERS;

  static {
    PROVIDERS = new EnumMap<>(Formats.class);
    for (final ReaderProvider provider : ServiceLoader.load(ReaderProvider.class)) {
      registerProvider(provider.getFormat(), provider);
    }
  }

  /**
   * Returns a reader that will process the supplied file.
   *
   * @param format the format the file is in
   * @return a reader that will process the supplied file
   * @throws ExecutionError if there is no provider of readers for the supplied format.
   */
  public Reader newReader(final Formats format) {
    if (PROVIDERS.containsKey(format)) {
      final ReaderProvider provider = PROVIDERS.get(format);
      LOGGER.debug(
          Markers.IO.getMarker(),
          "{} | Returning a reader for {}.",
          StatusCodes.OK.getCode(),
          format);
      return provider.newReader();
    } else {
      LOGGER.warn(
          Markers.IO.getMarker(),
          "{} | Could not find a reader for {}.",
          StatusCodes.NOT_FOUND.getCode(),
          format);
      throw new ExecutionError(
          String.format("Could not find a reader for %s", format),
          new UnsupportedOperationException(),
          Markers.IO.getMarker(),
          StatusCodes.NOT_FOUND);
    }
  }

  private static void registerProvider(final Formats format, final ReaderProvider provider) {
    PROVIDERS.put(format, provider);
    LOGGER.info(
        Markers.CONFIG.getMarker(),
        "{} | Registered a provider of readers for {} ({}).",
        StatusCodes.OK.getCode(),
        format,
        provider.getClass().getCanonicalName());
  }
}
