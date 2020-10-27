package io.pinesoft.trail.util;

import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

/**
 * Log markers used in Pinetrail.
 *
 * <p>The markers correspond to Pinetrail modules.
 *
 * <p>For additional information regarding markers, please refer to the <a
 * href="http://www.slf4j.org/docs.html">slf4j documentation</a>.
 *
 * @author Xavier Sosnovsky
 */
public enum Markers {
  /**
   * Marker for log entries related to configuration issues, instantiation of key resources when a
   * system starts up, etc.
   */
  CONFIG(MarkerFactory.getMarker("conf")),
  /**
   * Marker for log entries related to input/output activities such as writing and reading files.
   */
  IO(MarkerFactory.getMarker("io")),
  /**
   * Marker for log entries related to the management of model objects, such as bean instantiation.
   */
  MODEL(MarkerFactory.getMarker("model")),
  /**
   * Marker for log entries related to network activities such as retrieving information from 3rd
   * party web services, etc.
   */
  NETWORK(MarkerFactory.getMarker("net")),
  /**
   * Marker for log entries related to performance issues such as the time it took to retrieve data
   * from the database.
   */
  PERFORMANCE(MarkerFactory.getMarker("perf")),
  /** Marker for log entries related to data processing. */
  PROCESS(MarkerFactory.getMarker("proc"));

  private final Marker marker;

  Markers(final Marker marker) {
    this.marker = marker;
  }

  /**
   * Gets the marker defined for the selected module.
   *
   * @return the marker for the selected module
   */
  public Marker getMarker() {
    return this.marker;
  }
}
