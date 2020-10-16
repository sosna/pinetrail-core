package io.pinesoft.trail.model;

import java.time.Instant;
import java.util.ResourceBundle;

final class GpsRecordImpl implements GpsRecord {

  private final double latitude;
  private final double longitude;
  private final double elevation;
  private final long time;
  private final transient int cachedCode;
  private static final ResourceBundle msg = ResourceBundle.getBundle("messages");

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
  public final double getLatitude() {
    return latitude;
  }

  @Override
  public final double getLongitude() {
    return longitude;
  }

  @Override
  public final double getElevation() {
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
    if (obj instanceof GpsRecord) {
      final GpsRecord that = (GpsRecord) obj;
      return Double.compare(this.latitude, that.getLatitude()) == 0
          && Double.compare(this.longitude, that.getLongitude()) == 0
          && Double.compare(this.elevation, that.getElevation()) == 0
          && this.getTime() == that.getTime();
    } else {
      return false;
    }
  }

  @Override
  public String toString() {
    return "GpsRecord{longitude="
        + getLongitude()
        + ", latitude="
        + getLatitude()
        + ", elevation="
        + getElevation()
        + ", time="
        + getTime()
        + '}';
  }

  @Override
  public int compareTo(final GpsRecord o) {
    return Long.compare(this.time, o.getTime());
  }

  private int calculateHash() {
    int result = Double.hashCode(this.getLongitude());
    result = 31 * result + Double.hashCode(this.getLatitude());
    result = 31 * result + Double.hashCode(this.getElevation());
    result = 31 * result + Long.hashCode(time);
    return result;
  }

  private void checkLongitude(final double in) {
    if (Double.isNaN(in)
        || Double.compare(in, 180) != -1
        || Double.compare(in, -180) == -1) {
      throw new IllegalArgumentException(String.format(msg.getString("IllegalLongitude"), in));
    }
  }

  private void checkLatitude(final double in) {
    if (Double.isNaN(in) || Double.compare(in, 90) == 1 || Double.compare(in, -90) == -1) {
      throw new IllegalArgumentException(String.format(msg.getString("IllegalLatitude"), in));
    }
  }

  private void checkTime(final long in) {
    if (Instant.now().toEpochMilli() < in) {
      throw new IllegalArgumentException(String.format(msg.getString("IllegalTime"), in));
    }
  }
}
