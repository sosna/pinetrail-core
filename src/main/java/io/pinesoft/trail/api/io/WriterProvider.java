package io.pinesoft.trail.api.io;

/**
 * Contract for providers of {@code Writer}s.
 *
 * <p>This is the service provider interface (SPI), introduced with Java SE 6. Implementations
 * should register themselves, by placing a text file called ws.sosna.pinetrail.api.WriterProvider
 * below META-INF/services. See Java Service Provider implementations (SPI) for additional
 * information.
 *
 * @author Xavier Sosnovsky
 */
public interface WriterProvider {

  /**
   * Returns a new instance of a {@code Writer} implementation if the format is supported, null
   * otherwise.
   *
   * @param format the desired output format
   * @return a new instance of a {@code Writer} implementation, or null if the output format is not
   *     supported by the provider
   */
  Writer newWriter(final Formats format);
}
