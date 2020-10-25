package io.pinesoft.trail.api.io;

import io.pinesoft.trail.util.ExecutionError;
import io.pinesoft.trail.util.Markers;
import io.pinesoft.trail.util.StatusCodes;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ResourceBundle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A list of formats supplying {@code GpsRecord}s.
 *
 * @author Xavier Sosnovsky
 */
public enum Formats {

  /** Version 1.0 of the GPS Exchange Format standard, maintained by TopoGrafix. */
  GPX_1_0,
  /** Version 1.1 of the GPS Exchange Format standard, maintained by TopoGrafix. */
  GPX_1_1,
  /** A geospatial data interchange format based on JavaScript Object Notation. */
  GEOJSON_1_0,
  /**
   * Version 2.1.0 of the Keyhole Markup Language, an international standard of the Open Geospatial
   * Consortium, developed by Keyhole Inc and Google.
   */
  KML_2_1_0,
  /**
   * Version 2.2.0 of the Keyhole Markup Language, an international standard of the Open Geospatial
   * Consortium, developed by Keyhole Inc and Google.
   */
  KML_2_2_0;

  private static final Logger LOGGER = LoggerFactory.getLogger(Formats.class);
  private static final ResourceBundle msg = ResourceBundle.getBundle("messages");

  /**
   * Guess the format of the file stored at the supplied location.
   *
   * <p>This is a helper method so as to be able to call the {@link Readers#newReader(Formats)}
   * method when the format is unknown.
   *
   * @param fileLocation the file whose format needs to be guessed
   * @return the format of the file stored at the supplied location
   * @throws ExecutionError if the format cannot be guessed or the file is not found.
   */
  public static Formats of(final Path fileLocation) {
    try (BufferedReader br = Files.newBufferedReader(fileLocation)) {
      String line;
      int count = 0;
      while ((line = br.readLine()) != null && count < 5) {
        if (line.contains("http://www.topografix.com/GPX/1/1")) {
          return GPX_1_1;
        } else if (line.contains("http://www.topografix.com/GPX/1/0")) {
          return GPX_1_0;
        } else {
          count++;
        }
      }
      final String out =
          String.format(msg.getString("UnknownFormat"), fileLocation.toAbsolutePath().normalize());
      LOGGER.warn(Markers.IO.getMarker(), "{} | {}", StatusCodes.UNSUPPORTED_FORMAT, out);
      throw new ExecutionError(out, null, Markers.IO.getMarker(), StatusCodes.UNSUPPORTED_FORMAT);
    } catch (final IOException ex) {
      final String out =
          String.format(msg.getString("FileNotFound"), fileLocation.toAbsolutePath().normalize());
      LOGGER.warn(Markers.IO.getMarker(), "{} | {}", StatusCodes.NOT_FOUND, out);
      throw new ExecutionError(out, ex, Markers.IO.getMarker(), StatusCodes.NOT_FOUND);
    }
  }
}
