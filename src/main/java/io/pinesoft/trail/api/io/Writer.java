package io.pinesoft.trail.api.io;

import io.pinesoft.trail.model.GpsRecord;
import java.nio.file.Path;
import java.util.Set;
import java.util.function.BiConsumer;

/**
 * Contract for services that persist GPS log records.
 *
 * <p>Implementers of this interface are expected to report any issue preventing their process to
 * complete successfully using an {@code ExecutionError}.
 *
 * @author Xavier Sosnovsky
 * @see GpsRecord
 * @see io.pinesoft.trail.util.ExecutionError
 */
public interface Writer extends BiConsumer<Set<GpsRecord>, Path> {

  /**
   * Writes the supplied trail to the supplied location.
   *
   * @param trail the collection of GpsRecord to be written
   * @param location the location where the file will be written
   * @throws io.pinesoft.trail.util.ExecutionError issue preventing the writing process to finish
   *     successfully.
   */
  @Override
  void accept(final Set<GpsRecord> trail, final Path location);
}
