package io.pinesoft.trail.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

public abstract class Point3DTest<T extends Point3D> extends Point2DTest<T> {

  @Override
  @Test
  public void createInstance() {
    final double latitude = 48.5913904235;
    final double longitude = 22.9946215637;
    final double elevation = 530.28;
    final Point3D instance = newInstance(longitude, latitude, elevation);
    assertEquals(longitude, instance.getLongitude(), 0.0);
    assertEquals(latitude, instance.getLatitude(), 0.0);
    assertEquals(elevation, instance.getElevation(), 0.0);
  }

  @Override
  @Test
  public void equalsContract() {
    EqualsVerifier.forClass(Point3DImpl.class)
        .withCachedHashCode("cachedCode", "calculateHash", new Point3DImpl(56.7, 43.2, 106.12))
        .withRedefinedSuperclass()
        .verify();
  }

  @Override
  @Test
  public void toStringOutput() {
    final double latitude = 47.5913904235;
    final double longitude = 12.9946215637;
    final double elevation = 630.28;
    final Point3D instance = newInstance(longitude, latitude, elevation);
    assertEquals(
        "Point3D{longitude="
            + longitude
            + ", "
            + "latitude="
            + latitude
            + ", elevation="
            + elevation
            + '}',
        instance.toString());
  }

  protected abstract T newInstance(final double lon, final double lat, final double ele);
}
