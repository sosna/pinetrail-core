package io.pinesoft.trail.api.io;

import io.pinesoft.trail.utils.log.Actions;
import io.pinesoft.trail.utils.log.Markers;
import io.pinesoft.trail.utils.log.StatusCodes;
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

  private final Map<Formats, WriterProvider> providers;
  private final Logger LOGGER = LoggerFactory.getLogger(Writers.class);
  private final ServiceLoader<WriterProvider> loader;

  Writers() {
    LOGGER.info(
        Markers.CONFIG.getMarker(),
        "{} | {} | Created a registry for accessing writers services.",
        Actions.CREATE,
        StatusCodes.OK.getCode());
    this.providers = new EnumMap<>(Formats.class);
    loader = ServiceLoader.load(WriterProvider.class);
  }

  /**
   * Returns a writer that will process the work item.
   *
   * @param format the output format
   * @return a writer that will perform the supplied work
   * @throws UnsupportedOperationException if there is no provider of writers for the supplied
   *     format.
   */
  public Writer newWriter(final Formats format) {
    if (!(providers.containsKey(format))) {
      for (final WriterProvider tmpProvider : loader) {
        if (null != tmpProvider.newWriter(format)) {
          registerProvider(format, tmpProvider);
          break;
        }
      }
    }
    final WriterProvider provider = providers.get(format);
    if (null == provider) {
      LOGGER.warn(
          Markers.IO.getMarker(),
          "{} | {} | Could not find a writer for {}.",
          Actions.GET,
          StatusCodes.NOT_FOUND.getCode(),
          format);
      throw new UnsupportedOperationException("Could not find a writer for " + format);
    } else {
      LOGGER.debug(
          Markers.IO.getMarker(),
          "{} | {} | Returning a writer for {}.",
          Actions.GET,
          StatusCodes.OK.getCode(),
          format);
      return provider.newWriter(format);
    }
  }

  private void registerProvider(final Formats format, final WriterProvider provider) {
    providers.putIfAbsent(format, provider);
    LOGGER.info(
        Markers.CONFIG.getMarker(),
        "{} | {} | Registered a provider of writers for {} ({}).",
        Actions.REGISTER,
        StatusCodes.OK.getCode(),
        format,
        provider.getClass().getCanonicalName());
  }
}
