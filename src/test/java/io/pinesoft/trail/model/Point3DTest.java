package io.pinesoft.trail.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

public class CoordinatesTest {

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

  @Test
  public void equalsContract() {
    EqualsVerifier.forClass(Point3D.class)
        .withCachedHashCode("cachedCode", "calculateHash", Point3D.of(56.7, 43.2, 106.12))
        .verify();
  }

  @Test
  public void toStringOutput() {
    final double latitude = 47.5913904235;
    final double longitude = 12.9946215637;
    final double elevation = 630.28;
    final Point3D instance = newInstance(longitude, latitude, elevation);
    assertEquals(
        "Coordinates{longitude="
            + longitude
            + ", "
            + "latitude="
            + latitude
            + ", elevation="
            + elevation
            + '}',
        instance.toString());
  }

  private Point3D newInstance(final Double lon, final Double lat, final Double ele) {
    return Point3D.of(lon, lat, ele);
  }
}
