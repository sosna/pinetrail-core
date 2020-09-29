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

  private final Map<Formats, ReaderProvider> providers;
  private final Logger LOGGER = LoggerFactory.getLogger(Readers.class);
  private final ServiceLoader<ReaderProvider> loader;

  Readers() {
    LOGGER.info(
        Markers.CONFIG.getMarker(),
        "{} | {} | Created a registry of readers services.",
        Actions.CREATE,
        StatusCodes.OK.getCode());
    this.providers = new EnumMap<>(Formats.class);
    loader = ServiceLoader.load(ReaderProvider.class);
  }

  /**
   * Returns a reader that will process the supplied file.
   *
   * @param format the format the file is in
   * @return a reader that will process the supplied file
   * @throws UnsupportedOperationException if there is no provider of readers for the supplied
   *     format.
   */
  public Reader newReader(final Formats format) {
    if (!(providers.containsKey(format))) {
      for (final ReaderProvider tmpProvider : loader) {
        if (null != tmpProvider.newReader(format)) {
          registerProvider(format, tmpProvider);
          break;
        }
      }
    }
    final ReaderProvider provider = providers.get(format);
    if (null == provider) {
      LOGGER.warn(
          Markers.IO.getMarker(),
          "{} | {} | Could not find a reader for {}.",
          Actions.GET,
          StatusCodes.NOT_FOUND.getCode(),
          format);
      throw new UnsupportedOperationException("Could not find a reader for " + format);
    } else {
      LOGGER.debug(
          Markers.IO.getMarker(),
          "{} | {} | Returning a reader for {}.",
          Actions.GET,
          StatusCodes.OK.getCode(),
          format);
      return provider.newReader(format);
    }
  }

  private void registerProvider(final Formats format, final ReaderProvider provider) {
    providers.putIfAbsent(format, provider);
    LOGGER.info(
        Markers.CONFIG.getMarker(),
        "{} | {} | Registered a provider of readers for {} ({}).",
        Actions.REGISTER,
        StatusCodes.OK.getCode(),
        format,
        provider.getClass().getCanonicalName());
  }
}
