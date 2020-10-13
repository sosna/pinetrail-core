package io.pinesoft.trail.model;

public class Point2DImplTest extends Point2DTest<Point2DImpl> {

  @Override
  protected Point2DImpl newInstance(double longitude, double latitude) {
    return new Point2DImpl(longitude, latitude);
  }
}
