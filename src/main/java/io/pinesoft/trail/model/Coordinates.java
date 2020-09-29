package io.pinesoft.trail.model;

import java.util.Objects;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

public final class Coordinates {

  private final Double latitude;
  private final Double longitude;
  private final Double elevation;

  private Coordinates(final Double longitude, final Double latitude, final Double elevation) {
    this.latitude = latitude;
    this.longitude = longitude;
    this.elevation = elevation;
  }

  /**
   * Creates an instance of Coordinates.
   *
   * @param longitude the longitude of the point, in decimal degrees (WGS84 datum).
   * @param latitude the latitude of the point, in decimal degrees (WGS84 datum).
   * @param elevation the elevation of the point, in meters.
   * @return a new instance of Coordinates
   */
  public static Coordinates of(
      final Double longitude, final Double latitude, final Double elevation) {
    final double ele = elevation == null ? Double.NaN : elevation;
    return new Coordinates(longitude, latitude, ele);
  }

  /**
   * Creates an instance of Coordinates.
   *
   * @param longitude the longitude of the point, in decimal degrees (WGS84 datum).
   * @param latitude the latitude of the point, in decimal degrees (WGS84 datum).
   * @return a new instance of Coordinates
   */
  public static Coordinates of(final Double longitude, final Double latitude) {
    return of(longitude, latitude, null);
  }

  /**
   * The latitude of the point, in decimal degrees (WGS84 datum).
   *
   * <p>The latitude of the point, in decimal degrees. The value cannot be null and must be between
   * or equal to -90.0 and 90.0 degrees.
   *
   * @return the latitude of the point
   */
  @NotNull(message = "{Model.Coordinates.Latitude.NotNull}")
  @DecimalMax(value = "90", message = "{Model.Coordinates.Latitude.MaxValue}")
  @DecimalMin(value = "-90", message = "{Model.Coordinates.Latitude.MinValue}")
  public Double getLatitude() {
    return latitude;
  }

  /**
   * The longitude of the point, in decimal degrees (WGS84 datum).
   *
   * <p>The value cannot be null, must be superior or equal to -180.0 and inferior to 180.0 degrees.
   *
   * @return the longitude of the point
   */
  @NotNull(message = "{Model.Coordinates.Longitude.NotNull}")
  @DecimalMax(value = "180", inclusive = false, message = "{Model.Coordinates.Longitude.MaxValue}")
  @DecimalMin(value = "-180", message = "{Model.Coordinates.Longitude.MinValue}")
  public Double getLongitude() {
    return longitude;
  }

  /**
   * The elevation of the point, in meters.
   *
   * @return the elevation of the point, in meters
   */
  public Double getElevation() {
    return elevation;
  }

  @Override
  public int hashCode() {
    return Objects.hash(longitude, latitude, elevation);
  }

  @Override
  public boolean equals(final Object obj) {
    if (obj == null) {
      return false;
    }
    if (!(obj instanceof Coordinates)) {
      return false;
    }
    final Coordinates other = (Coordinates) obj;
    return Objects.equals(this.latitude, other.getLatitude())
        && Objects.equals(this.longitude, other.getLongitude())
        && Objects.equals(this.elevation, other.getElevation());
  }

  @Override
  public String toString() {
    return "Coordinates{longitude="
        + longitude
        + ", "
        + "latitude="
        + latitude
        + ", elevation="
        + elevation
        + '}';
  }
}
