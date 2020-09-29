package io.pinesoft.trail.api.io;

/**
 * Contract for providers of {@code Reader}s.
 *
 * <p>This is the service provider interface (SPI), introduced with Java SE 6. Implementations
 * should register themselves, by placing a text file called
 * ws.sosna.pinetrail.api.io.ReaderProvider below META-INF/services. See Java Service Provider
 * implementations (SPI) for additional information.
 *
 * @author Xavier Sosnovsky
 */
public interface ReaderProvider {

  /**
   * Returns a new instance of a {@code Reader} implementation if the format is supported, null
   * otherwise.
   *
   * @param format the format of the file to be processed
   * @return a new instance of a {@code Reader} implementation, or null if the format is not
   *     supported by the provider
   */
  Reader newReader(final Formats format);
}
