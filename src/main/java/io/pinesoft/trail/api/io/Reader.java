package io.pinesoft.trail.api.io;

import io.pinesoft.trail.model.GpsRecord;
import java.nio.file.Path;
import java.util.Collection;
import java.util.SortedSet;
import java.util.function.Function;

/**
 * Contract for services that extract information about trails.
 *
 * <p>A reader service typically processes a file in a particular format (GPX, KML, GeoJSON, etc.)
 * and returns the extracted {@code GpsRecord}s found.
 *
 * <p>Implementers of this interface are expected to report any issue preventing their process to
 * complete successfully using an {@code ExecutionError}.
 *
 * @author Xavier Sosnovsky
 * @see GpsRecord
 * @see io.pinesoft.trail.util.ExecutionError
 */
public interface Reader extends Function<Path, Collection<SortedSet<GpsRecord>>> {

  /**
   * Triggers the extraction of information about trails from the supplied file.
   *
   * @param fileLocation the location of the file from which the trail information will be
   *     extracted.
   * @throws io.pinesoft.trail.util.ExecutionError issue preventing the extraction process to finish
   *     successfully.
   */
  @Override
  Collection<SortedSet<GpsRecord>> apply(final Path fileLocation);
}
