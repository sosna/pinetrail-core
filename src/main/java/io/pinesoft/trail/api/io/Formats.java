package io.pinesoft.trail.api.io;

/**
 * A list of formats supplying {@code GpsRecord}s.
 *
 * @author Xavier Sosnovsky
 */
public enum Formats {

  /** Version 1.0 of the GPS Exchange Format standard, maintained by TopoGrafix. */
  GPX_1_0,
  /** Version 1.1 of the GPS Exchange Format standard, maintained by TopoGrafix. */
  GPX_1_1,
  /** A geospatial data interchange format based on JavaScript Object Notation. */
  GEOJSON_1_0,
  /**
   * Version 2.1.0 of the Keyhole Markup Language, an international standard of the Open Geospatial
   * Consortium, developed by Keyhole Inc and Google.
   */
  KML_2_1_0,
  /**
   * Version 2.2.0 of the Keyhole Markup Language, an international standard of the Open Geospatial
   * Consortium, developed by Keyhole Inc and Google.
   */
  KML_2_2_0;
}
