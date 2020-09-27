/*
 * Copyright (c) 2014, Xavier Sosnovsky <xso@sosna.ws>
 *
 * Permission to use, copy, modify, and/or distribute this software for any
 * purpose with or without fee is hereby granted, provided that the above
 * copyright notice and this permission notice appear in all copies.
 *
 * THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL WARRANTIES WITH
 * REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED WARRANTIES OF MERCHANTABILITY
 * AND FITNESS. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY SPECIAL, DIRECT,
 * INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES WHATSOEVER RESULTING FROM
 * LOSS OF USE, DATA OR PROFITS, WHETHER IN AN ACTION OF CONTRACT, NEGLIGENCE OR
 * OTHER TORTIOUS ACTION, ARISING OUT OF OR IN CONNECTION WITH THE USE OR
 * PERFORMANCE OF THIS SOFTWARE.
 */
package io.pinesoft.trail.api.io;

import io.pinesoft.trail.model.GpsRecord;
import java.nio.file.Path;
import java.util.Set;
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
 * @see io.pinesoft.trail.error.ExecutionError
 */
public interface Reader extends Function<Path, Set<GpsRecord>> {

  /**
   * Triggers the extraction of information about trails from the supplied file.
   *
   * @param fileLocation the location of the file from which the trail information will be
   *     extracted.
   * @throws io.pinesoft.trail.utils.error.ExecutionError issue preventing the extraction process to
   *     finish successfully.
   */
  @Override
  Set<GpsRecord> apply(final Path fileLocation);
}
