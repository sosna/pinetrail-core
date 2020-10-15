package io.pinesoft.trail.model;

/**
 * The coordinates of a point, expressed as longitude, latitude and elevation.
 *
 * <p>The longitude and latitude are expressed in decimal degrees with WGS84 datum. The elevation is
 * expressed in meters, above or below the WGS 84 reference ellipsoid.
 *
 * <p>Longitude must be must be superior or equal to -180.0 and inferior to 180.0 degrees, while
 * latitude must be between or equal to -90.0 and 90.0 degrees.
 */
public interface Point3D extends Point2D {
  /**
   * The elevation of the point, in meters, above or below the WGS 84 reference ellipsoid.
   *
   * @return the elevation of the point, in meters
   */
  double getElevation();
}
