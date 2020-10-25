package io.pinesoft.trail.api.io;

import io.pinesoft.trail.util.ExecutionError;
import io.pinesoft.trail.util.Markers;
import io.pinesoft.trail.util.StatusCodes;
import java.util.EnumMap;
import java.util.Map;
import java.util.ResourceBundle;
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
  private static final ResourceBundle msg = ResourceBundle.getBundle("messages");

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
          Markers.IO.getMarker(), msg.getString("ReaderFound"), StatusCodes.OK.getCode(), format);
      return provider.newReader();
    } else {
      final String out = String.format(msg.getString("ReaderNotFound"), format);
      LOGGER.warn(Markers.IO.getMarker(), "{} | {}", StatusCodes.NOT_FOUND.getCode(), out);
      throw new ExecutionError(
          out, new UnsupportedOperationException(), Markers.IO.getMarker(), StatusCodes.NOT_FOUND);
    }
  }

  private static void registerProvider(final Formats format, final ReaderProvider provider) {
    PROVIDERS.put(format, provider);
    LOGGER.info(
        Markers.CONFIG.getMarker(),
        msg.getString("RegisterReader"),
        StatusCodes.OK.getCode(),
        format,
        provider.getClass().getCanonicalName());
  }
}
