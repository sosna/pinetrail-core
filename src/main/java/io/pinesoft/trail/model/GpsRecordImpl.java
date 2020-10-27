package io.pinesoft.trail.model;

import java.time.Instant;
import java.util.ResourceBundle;

/** Note: This class has a natural ordering that is inconsistent with equals. */
final class GpsRecordImpl implements GpsRecord {

  private final double latitude;
  private final double longitude;
  private final double elevation;
  private final long time;
  private final int cachedCode;
  private static final ResourceBundle msg = ResourceBundle.getBundle("messages");
  private static final int LAT_BOUNDARY = 90;
  private static final int LON_BOUNDARY = 180;
  private static final int ODD_PRIME = 31;

  GpsRecordImpl(
      final double longitude, final double latitude, final double elevation, final long time) {
    checkLongitude(longitude);
    checkLatitude(latitude);
    checkTime(time);
    this.longitude = longitude;
    this.latitude = latitude;
    this.elevation = elevation;
    this.time = time;
    this.cachedCode = calculateHash();
  }

  @Override
  public double getLatitude() {
    return latitude;
  }

  @Override
  public double getLongitude() {
    return longitude;
  }

  @Override
  public double getElevation() {
    return elevation;
  }

  @Override
  public long getTime() {
    return time;
  }

  @Override
  public int hashCode() {
    return cachedCode;
  }

  @Override
  public boolean equals(final Object obj) {
    if (obj instanceof GpsRecordImpl) {
      final GpsRecordImpl that = (GpsRecordImpl) obj;
      return Double.compare(latitude, that.getLatitude()) == 0
          && Double.compare(longitude, that.getLongitude()) == 0
          && Double.compare(elevation, that.getElevation()) == 0
          && time == that.getTime();
    } else {
      return false;
    }
  }

  @Override
  public String toString() {
    return "GpsRecord{longitude="
        + longitude
        + ", latitude="
        + latitude
        + ", elevation="
        + elevation
        + ", time="
        + time
        + '}';
  }

  @Override
  public int compareTo(final GpsRecord o) {
    return Long.compare(time, o.getTime());
  }

  private int calculateHash() {
    int result = Double.hashCode(longitude);
    result = ODD_PRIME * result + Double.hashCode(latitude);
    result = ODD_PRIME * result + Double.hashCode(elevation);
    result = ODD_PRIME * result + Long.hashCode(time);
    return result;
  }

  private static void checkLongitude(final double in) {
    if (Double.isNaN(in)
        || Double.compare(in, LON_BOUNDARY) != -1
        || Double.compare(in, -LON_BOUNDARY) == -1) {
      throw new IllegalArgumentException(String.format(msg.getString("IllegalLongitude"), in));
    }
  }

  private static void checkLatitude(final double in) {
    if (Double.isNaN(in)
        || Double.compare(in, LAT_BOUNDARY) == 1
        || Double.compare(in, -LAT_BOUNDARY) == -1) {
      throw new IllegalArgumentException(String.format(msg.getString("IllegalLatitude"), in));
    }
  }

  private static void checkTime(final long in) {
    if (Instant.now().toEpochMilli() < in) {
      throw new IllegalArgumentException(String.format(msg.getString("IllegalTime"), in));
    }
  }
}
