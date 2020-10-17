package io.pinesoft.trail.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

abstract class Point3DTest<T extends Point3D> extends Point2DTest<T> {

  @Test
  void createInstance() {
    final double latitude = 48.5913904235;
    final double longitude = 22.9946215637;
    final double elevation = 530.28;
    final Point3D instance = newInstance(longitude, latitude, elevation);
    assertEquals(longitude, instance.getLongitude(), 0.0);
    assertEquals(latitude, instance.getLatitude(), 0.0);
    assertEquals(elevation, instance.getElevation(), 0.0);
  }

  protected abstract T newInstance(final double lon, final double lat, final double ele);
}
