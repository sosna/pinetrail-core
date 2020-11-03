package io.pinesoft.trail.api.io;

import java.util.Optional;
import java.util.Properties;

/**
 * Contract for providers of {@code Reader}, that is, parsers of GPS log files.
 *
 * <p>This is the service provider interface (SPI). Implementations should register themselves, by
 * placing a text file called io.pinesoft.trail.api.io.ReaderProvider below META-INF/services. See
 * Java Service Provider implementations (SPI) for additional information.
 *
 * <p>Readers can be configured using Properties. The following properties are currently supported:
 *
 * <ul>
 *   <li><strong>pinetrail.reader.groupSubtrails</strong>: Whether subtrails should be grouped into
 *       one trail. Certain formats allow intermediary groupings between the trail and the GPS log
 *       records. Gpx for example has an additional level, called segments between tracks and
 *       waypoints. If <em>groupSubtrails</em> is true, these intermediary levels will be merged
 *       into one trail. If false, each of these levels will appear as a separate trail. Defaults to
 *       false.
 * </ul>
 *
 * @author Xavier Sosnovsky
 */
public interface ReaderProvider {

  /**
   * Returns a new instance of a {@code Reader} implementation if the format is supported.
   *
   * @param format the format of the file to be processed
   * @return a new instance of a {@code Reader} implementation
   */
  Optional<Reader> newReader(final Formats format);

  /**
   * Returns a new instance of a {@code Reader} implementation if the format is supported, and
   * configure it with the supplied properties.
   *
   * @param format the format of the file to be processed
   * @param props the reader properties
   * @return a new instance of a {@code Reader} implementation
   */
  Optional<Reader> newReader(final Formats format, final Properties props);

  /**
   * Returns a new instance of a {@code Reader} implementation.
   *
   * @return a new instance of a {@code Reader} implementation
   */
  Reader newReader();

  /**
   * Returns a new instance of a {@code Reader} implementation, configured with the supplied
   * properties.
   *
   * @param props the reader properties
   * @return a new instance of a {@code Reader} implementation
   */
  Reader newReader(final Properties props);

  /**
   * Returns the format supported by this provider.
   *
   * @return the format supported by this provider.
   */
  Formats getFormat();
}
