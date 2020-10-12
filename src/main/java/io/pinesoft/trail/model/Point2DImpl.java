package io.pinesoft.trail.model;

import java.util.Objects;
import java.util.ResourceBundle;

class Point2DImpl implements Point2D {

  private final double latitude;
  private final double longitude;
  private final transient int cachedCode;
  private static final ResourceBundle msg = ResourceBundle.getBundle("messages");

  Point2DImpl(final double longitude, final double latitude) {
    checkLongitude(longitude);
    checkLatitude(latitude);
    this.longitude = longitude;
    this.latitude = latitude;
    this.cachedCode = calculateHash();
  }

  @Override
  public final double getLatitude() {
    return latitude;
  }

  @Override
  public final double getLongitude() {
    return longitude;
  }

  @Override
  public int hashCode() {
    return cachedCode;
  }

  @Override
  public boolean equals(final Object obj) {
    if (obj == null) {
      return false;
    }
    if (!(obj instanceof Point2D)) {
      return false;
    }
    final Point2D other = (Point2D) obj;
    return Objects.equals(this.latitude, other.getLatitude())
        && Objects.equals(this.longitude, other.getLongitude());
  }

  @Override
  public String toString() {
    return "Point2D{longitude=" + longitude + ", " + "latitude=" + latitude + '}';
  }

  private int calculateHash() {
    int result = Double.hashCode(latitude);
    result = 31 * result + Double.hashCode(longitude);
    return result;
  }

  private void checkLongitude(final double in) {
    if (Double.isNaN(in) || in >= 180.0 || in < -180) {
      throw new IllegalArgumentException(String.format(msg.getString("IllegalLongitude"), in));
    }
  }

  private void checkLatitude(final double in) {
    if (Double.isNaN(in) || in > 90.0 || in < -90.0) {
      throw new IllegalArgumentException(String.format(msg.getString("IllegalLatitude"), in));
    }
  }
}
