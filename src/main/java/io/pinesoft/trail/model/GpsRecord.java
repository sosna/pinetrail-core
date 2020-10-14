package io.pinesoft.trail.model;

/**
 * A GPS Log Record (time and 3D coordinates).
 *
 * <p>The longitude and latitude are expressed in decimal degrees with WGS84 datum. The elevation is
 * expressed in meters, above or below the WGS 84 reference ellipsoid.
 *
 * <p>Longitude must be must be superior or equal to -180.0 and inferior to 180.0 degrees, while
 * latitude must be between or equal to -90.0 and 90.0 degrees.
 *
 * <p>Use the {@link #of(double, double, double, long)} method to create an immutable instance of a
 * GpsRecord.
 *
 * @author Xavier Sosnovsky
 */
public interface GpsRecord extends Point3D, Comparable<GpsRecord> {

  /**
   * The time when the coordinates were measured, as number of milliseconds from the * epoch of
   * 1970-01-01T00:00:00Z
   *
   * <p>The time must be in the past (no kidding).
   *
   * @return the point in time when the coordinates were measured
   */
  long getTime();

  /**
   * Creates an immutable instance of GpsRecord.
   *
   * @param longitude the longitude of the point, in decimal degrees (WGS84 datum).
   * @param latitude the latitude of the point, in decimal degrees (WGS84 datum).
   * @param elevation the elevation of the point, in meters.
   * @param time the time when the coordinates were measured, as number of milliseconds from the
   *     epoch of 1970-01-01T00:00:00Z
   * @throws IllegalArgumentException in case longitude or latitude are not within the expected
   *     range, or in case time is in the future.
   * @return a new GpsRecord
   */
  static GpsRecord of(
      final double longitude, final double latitude, final double elevation, final long time) {
    return new GpsRecordImpl(longitude, latitude, elevation, time);
  }
}
