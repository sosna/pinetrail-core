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
 * Utility class that instantiates writers for the supported formats.
 *
 * <p>In the background, this class uses a ServiceLoader to register the {@code WriterProviders}
 * that will be used to instantiate the {@code Writers} returned to the client.
 *
 * @author Xavier Sosnovsky
 */
public enum Writers {

  /** Singleton instance of Pinetrail writers. */
  INSTANCE;

  private static final Logger LOGGER = LoggerFactory.getLogger(Writers.class);
  private final Map<Formats, WriterProvider> providers;
  private final ServiceLoader<WriterProvider> loader;

  Writers() {
    providers = new EnumMap<>(Formats.class);
    loader = ServiceLoader.load(WriterProvider.class);
  }

  /**
   * Returns a writer that will process the work item.
   *
   * @param format the output format
   * @return a writer that will perform the supplied work
   * @throws io.pinesoft.trail.util.ExecutionError if there is no provider of writers for the
   *     supplied format.
   */
  public Writer newWriter(final Formats format) {
    if (!(providers.containsKey(format))) {
      for (final WriterProvider tmpProvider : loader) {
        if (tmpProvider.newWriter(format).isPresent()) {
          registerProvider(format, tmpProvider);
          break;
        }
      }
    }

    if (providers.containsKey(format)) {
      final WriterProvider provider = providers.get(format);
      LOGGER.debug(
          Markers.IO.getMarker(),
          "{} | Returning a writer for {}.",
          StatusCodes.OK.getCode(),
          format);
      return provider.newWriter(format).get();
    } else {
      LOGGER.warn(
          Markers.IO.getMarker(),
          "{} | Could not find a writer for {}.",
          StatusCodes.NOT_FOUND.getCode(),
          format);
      throw new ExecutionError(
          String.format("Could not find a writer for %s", format),
          new UnsupportedOperationException(),
          Markers.IO.getMarker(),
          StatusCodes.NOT_FOUND);
    }
  }

  private void registerProvider(final Formats format, final WriterProvider provider) {
    providers.put(format, provider);
    LOGGER.info(
        Markers.CONFIG.getMarker(),
        "{} | Registered a provider of writers for {} ({}).",
        StatusCodes.OK.getCode(),
        format,
        provider.getClass().getCanonicalName());
  }
}
