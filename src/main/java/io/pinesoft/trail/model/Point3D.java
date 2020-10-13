package io.pinesoft.trail.model;

/**
 * The coordinates of a point, expressed as longitude, latitude and elevation.
 *
 * <p>The longitude and latitude are expressed in decimal degrees with WGS84 datum. The elevation is
 * expressed in meters, above or below the WGS 84 reference ellipsoid.
 *
 * <p>Longitude must be must be superior or equal to -180.0 and inferior to 180.0 degrees, while
 * latitude must be between or equal to -90.0 and 90.0 degrees.
 *
 * <p>Use the {@link #of(double, double, double)} or {@link #of(Point2D, double}) methods to create
 * an immutable instance of a point.
 */
public interface Point3D extends Point2D {
  /**
   * The elevation of the point, in meters, above or below the WGS 84 reference ellipsoid.
   *
   * @return the elevation of the point, in meters
   */
  double getElevation();

  /**
   * Creates an immutable instance of Point3D.
   *
   * <p>An IllegalArgumentException will be thrown unless longitude is superior or equal to -180.0 *
   * and inferior to 180.0 degrees, and latitude is between or equal to -90.0 and 90.0 degrees.
   *
   * @param longitude the longitude of the point, in decimal degrees (WGS84 datum).
   * @param latitude the latitude of the point, in decimal degrees (WGS84 datum).
   * @param elevation the elevation of the point, in meters.
   * @throws IllegalArgumentException in case longitude or latitude are not within the expected *
   *     range.
   * @return a new instance of Point3D
   */
  static Point3D of(final double longitude, final double latitude, final double elevation) {
    return new Point3DImpl(longitude, latitude, elevation);
  }

  /**
   * Creates an instance of Point3D, out of a Point2D and its elevation.
   *
   * @param point A 2D point.
   * @param elevation the elevation of the point, in meters.
   * @return a new instance of Point3D
   */
  static Point3D of(final Point2D point, final double elevation) {
    return new Point3DImpl(point.getLongitude(), point.getLatitude(), elevation);
  }
}
