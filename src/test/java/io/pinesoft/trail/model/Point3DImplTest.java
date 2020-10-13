package io.pinesoft.trail.model;

public class Point3DImplTest extends Point3DTest<Point3DImpl> {

  @Override
  protected Point3DImpl newInstance(double longitude, double latitude, double elevation) {
    return new Point3DImpl(longitude, latitude, elevation);
  }

  @Override
  protected Point3DImpl newInstance(double longitude, double latitude) {
    return newInstance(longitude, latitude, Double.NaN);
  }
}
