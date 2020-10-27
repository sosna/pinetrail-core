package io.pinesoft.trail.model;

/**
 * The coordinates of a point, expressed as longitude and latitude.
 *
 * <p>The coordinates are expressed in decimal degrees with WGS84 datum.
 *
 * <p>Longitude must be must be superior or equal to -180.0 and inferior to 180.0 degrees.
 *
 * <p>Latitude must be between or equal to -90.0 and 90.0 degrees.
 */
public interface Point2D {

  /**
   * The longitude of the point, in decimal degrees (WGS84 datum).
   *
   * <p>The value cannot be null, must be superior or equal to -180.0 and inferior to 180.0 degrees.
   *
   * @return the longitude of the point
   */
  double getLongitude();

  /**
   * The latitude of the point, in decimal degrees (WGS84 datum).
   *
   * <p>The latitude of the point, in decimal degrees. The value cannot be null and must be between
   * or equal to -90.0 and 90.0 degrees.
   *
   * @return the latitude of the point
   */
  double getLatitude();
}
