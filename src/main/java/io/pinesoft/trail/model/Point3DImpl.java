package io.pinesoft.trail.model;

class Point3DImpl extends Point2DImpl implements Point3D {

  private final double elevation;
  private final transient int cachedCode;

  Point3DImpl(final double longitude, final double latitude, final double elevation) {
    super(longitude, latitude);
    this.elevation = elevation;
    this.cachedCode = calculateHash();
  }

  public final double getElevation() {
    return elevation;
  }

  @Override
  public int hashCode() {
    return cachedCode;
  }

  @Override
  public boolean equals(final Object obj) {
    if (obj instanceof Point3DImpl) {
      final Point3DImpl that = (Point3DImpl) obj;
      return that.canEqual(this)
          && Double.compare(this.elevation, that.getElevation()) == 0
          && super.equals(obj);
    } else {
      return false;
    }
  }

  @Override
  public String toString() {
    return "Point3D{longitude="
        + this.getLongitude()
        + ", "
        + "latitude="
        + this.getLatitude()
        + ", elevation="
        + elevation
        + '}';
  }

  @Override
  boolean canEqual(final Object other) {
    return (other instanceof Point3DImpl);
  }

  private int calculateHash() {
    int result = Double.hashCode(this.getLongitude());
    result = 31 * result + Double.hashCode(this.getLatitude());
    result = 31 * result + Double.hashCode(elevation);
    return result;
  }
}
