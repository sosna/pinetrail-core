package io.pinesoft.trail.api.io;

import java.util.Optional;

/**
 * Contract for providers of {@code Writer}s.
 *
 * <p>This is the service provider interface (SPI). Implementations should register themselves, by
 * placing a text file called io.pinesoft.trail.api.WriterProvider below META-INF/services. See Java
 * Service Provider implementations (SPI) for additional information.
 *
 * @author Xavier Sosnovsky
 */
public interface WriterProvider {

  /**
   * Returns a new instance of a {@code Writer} implementation if the format is supported.
   *
   * @param format the desired output format
   * @return a new instance of a {@code Writer} implementation
   */
  Optional<Writer> newWriter(final Formats format);
}
