package io.pinesoft.trail.model;

/**
 * The coordinates of a point, expressed as longitude and latitude (aka easting and northing).
 *
 * <p>The coordinates are expressed in decimal degrees with WGS84 datum.
 *
 * <p>Longitude must be must be superior or equal to -180.0 and inferior to 180.0 degrees, while
 * latitude must be between or equal to -90.0 and 90.0 degrees.
 *
 * <p>Use the {@link #of(double, double)} method to create an immutable instance of a point.
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

  /**
   * Creates an immutable instance of Point2D.
   *
   * <p>An IllegalArgumentException will be thrown unless longitude is superior or equal to -180.0
   * and inferior to 180.0 degrees, and latitude is between or equal to -90.0 and 90.0 degrees.
   *
   * @param longitude the longitude of the point, in decimal degrees (WGS84 datum).
   * @param latitude the latitude of the point, in decimal degrees (WGS84 datum).
   * @throws IllegalArgumentException in case longitude or latitude are not within the expected
   *     range.
   * @return a new instance of Point2D
   */
  static Point2D of(final double longitude, final double latitude) {
    return new Point2DImpl(longitude, latitude);
  }
}
