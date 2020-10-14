package io.pinesoft.trail.model;

import java.time.Instant;
import java.util.ResourceBundle;

final class GpsRecordImpl extends Point3DImpl implements GpsRecord {

  private final long time;
  private final int cachedCode;
  private static final ResourceBundle msg = ResourceBundle.getBundle("messages");

  GpsRecordImpl(
      final double longitude, final double latitude, final double elevation, final long time) {
    super(longitude, latitude, elevation);
    checkTime(time);
    this.time = time;
    this.cachedCode = calculateHash();
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
      return that.canEqual(this) && this.getTime() == that.getTime() && super.equals(obj);
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
  boolean canEqual(final Object other) {
    return (other instanceof GpsRecordImpl);
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

  private void checkTime(final long in) {
    if (Instant.now().toEpochMilli() < in) {
      throw new IllegalArgumentException(String.format(msg.getString("IllegalTime"), in));
    }
  }
}
