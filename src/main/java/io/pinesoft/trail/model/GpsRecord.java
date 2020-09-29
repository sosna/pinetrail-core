package io.pinesoft.trail.model;

import java.time.Instant;
import java.util.Objects;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

/**
 * An immutable instance of a GPS Log Record (time and coordinates).
 *
 * @author Xavier Sosnovsky
 */
public final class GpsRecord implements Comparable<GpsRecord> {

  private final Instant time;

  @NotNull(message = "{Model.GpsRecord.Coordinates.NotNull}")
  private final Coordinates coordinates;

  private GpsRecord(final Instant time, final Coordinates coordinates) {
    this.time = time;
    this.coordinates = coordinates;
  }

  /**
   * Creates an instance of GpsRecord.
   *
   * @param time the point in time when the coordinates were measured.
   * @param coordinates the coordinates of the point, in decimal degrees (WGS84 datum).
   * @return a new GpsRecord
   */
  public static GpsRecord of(final Instant time, final Coordinates coordinates) {
    return new GpsRecord(time, coordinates);
  }

  /**
   * The point in time when the coordinates were measured.
   *
   * <p>The timestamp cannot be null and must be in the past (no kidding).
   *
   * @return the point in time when the coordinates were measured
   */
  @Past(message = "{Model.GpsRecord.Time.Past}")
  @NotNull(message = "{Model.GpsRecord.Time.NotNull}")
  public Instant getTime() {
    return time;
  }

  /**
   * /** The latitude of the point, in decimal degrees (WGS84 datum).
   *
   * <p>The latitude of the point, in decimal degrees. The value cannot be null and must be between
   * or equal to -90.0 and 90.0 degrees.
   *
   * @return the latitude of the point
   */
  public Double getLatitude() {
    return coordinates == null ? null : coordinates.getLatitude();
  }

  /**
   * The longitude of the point, in decimal degrees (WGS84 datum).
   *
   * <p>The value cannot be null, must be superior or equal to -180.0 and inferior to 180.0 degrees.
   *
   * @return the longitude of the point
   */
  public Double getLongitude() {
    return coordinates == null ? null : coordinates.getLongitude();
  }

  /**
   * The elevation of the point, in meters.
   *
   * @return the elevation of the point, in meters
   */
  public Double getElevation() {
    return coordinates == null ? null : coordinates.getElevation();
  }

  @Override
  public int hashCode() {
    return Objects.hash(time, coordinates);
  }

  @Override
  public boolean equals(final Object obj) {
    if (obj == null) {
      return false;
    }
    if (!(obj instanceof GpsRecord)) {
      return false;
    }
    final GpsRecord other = (GpsRecord) obj;
    return Objects.equals(this.time, other.getTime())
        && Objects.equals(this.getLongitude(), other.getLongitude())
        && Objects.equals(this.getLatitude(), other.getLatitude())
        && Objects.equals(this.getElevation(), other.getElevation());
  }

  @Override
  public String toString() {
    return "GpsRecord{time="
        + time
        + ", longitude="
        + coordinates.getLongitude()
        + ", latitude="
        + coordinates.getLatitude()
        + ", elevation="
        + coordinates.getElevation()
        + '}';
  }

  @Override
  public int compareTo(final GpsRecord o) {
    return this.time.compareTo(o.getTime());
  }
}
