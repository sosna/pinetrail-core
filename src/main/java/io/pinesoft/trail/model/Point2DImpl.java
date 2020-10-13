package io.pinesoft.trail.model;

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
  public boolean equals(final Object other) {
    if (other instanceof Point2DImpl) {
      Point2DImpl that = (Point2DImpl) other;
      return that.canEqual(this)
          && Double.compare(this.latitude, that.getLatitude()) == 0
          && Double.compare(this.longitude, that.getLongitude()) == 0;
    } else {
      return false;
    }
  }

  @Override
  public String toString() {
    return "Point2D{longitude=" + longitude + ", " + "latitude=" + latitude + '}';
  }

  boolean canEqual(final Object other) {
    return (other instanceof Point2DImpl);
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
