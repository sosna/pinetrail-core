package io.pinesoft.trail.api.io;

import java.util.Optional;

/**
 * Contract for providers of {@code Reader}s.
 *
 * <p>This is the service provider interface (SPI). Implementations should register themselves, by
 * placing a text file called io.pinesoft.trail.api.io.ReaderProvider below META-INF/services. See
 * Java Service Provider implementations (SPI) for additional information.
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
   * Returns a new instance of a {@code Reader} implementation.
   *
   * @return a new instance of a {@code Reader} implementation
   */
  Reader newReader();

  /**
   * Returns the format supported by this provider.
   *
   * @return the format supported by this provider.
   */
  Formats getFormat();
}
