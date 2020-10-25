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
  private static final Map<Formats, WriterProvider> PROVIDERS;
  private static final ResourceBundle msg = ResourceBundle.getBundle("messages");

  static {
    PROVIDERS = new EnumMap<>(Formats.class);
    for (final WriterProvider provider : ServiceLoader.load(WriterProvider.class)) {
      registerProvider(provider.getFormat(), provider);
    }
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
    if (PROVIDERS.containsKey(format)) {
      final WriterProvider provider = PROVIDERS.get(format);
      LOGGER.debug(
          Markers.IO.getMarker(), msg.getString("WriterFound"), StatusCodes.OK.getCode(), format);
      return provider.newWriter();
    } else {
      final String out = String.format(msg.getString("WriterNotFound"), format);
      LOGGER.warn(Markers.IO.getMarker(), "{} | {}", StatusCodes.NOT_FOUND.getCode(), out);
      throw new ExecutionError(
          out, new UnsupportedOperationException(), Markers.IO.getMarker(), StatusCodes.NOT_FOUND);
    }
  }

  private static void registerProvider(final Formats format, final WriterProvider provider) {
    PROVIDERS.put(format, provider);
    LOGGER.info(
        Markers.CONFIG.getMarker(),
        msg.getString("RegisterWriter"),
        StatusCodes.OK.getCode(),
        format,
        provider.getClass().getCanonicalName());
  }
}
