package io.pinesoft.trail.api.io;

import io.pinesoft.trail.model.GpsRecord;
import java.nio.file.Path;
import java.util.Set;
import java.util.function.BiConsumer;

/**
 * Contract for services that write information about trails to a file.
 *
 * <p>A writer service typically outputs information about a trail to a file in a particular format
 * (GPX, KML, GeoJSON, etc.).
 *
 * <p>Implementers of this interface are expected to report any issue preventing their process to
 * complete successfully using an {@code ExecutionError}.
 *
 * @author Xavier Sosnovsky
 * @see GpsRecord
 * @see io.pinesoft.trail.utils.error.ExecutionError
 */
public interface Writer extends BiConsumer<Set<GpsRecord>, Path> {

  /**
   * Writes the supplied trail to the supplied location.
   *
   * @param trail the collection of GpsRecord to be written
   * @param location the location where the file will be written
   * @throws io.pinesoft.trail.utils.error.ExecutionError issue preventing the writing process to
   *     finish successfully.
   */
  @Override
  void accept(final Set<GpsRecord> trail, final Path location);
}
