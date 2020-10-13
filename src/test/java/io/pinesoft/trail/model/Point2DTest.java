package io.pinesoft.trail.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

public abstract class Point2DTest<T extends Point2D> {

  @Test
  public void createInstance() {
    final double latitude = 47.5913904235;
    final double longitude = 12.9946215637;
    final T instance = newInstance(longitude, latitude);
    assertEquals(longitude, instance.getLongitude(), 0.0);
    assertEquals(latitude, instance.getLatitude(), 0.0);
  }

  @Test
  public void equalsContract() {
    EqualsVerifier.forClass(Point2DImpl.class)
        .withCachedHashCode("cachedCode", "calculateHash", new Point2DImpl(56.7, 42.0))
        .withRedefinedSubclass(Point3DImpl.class)
        .verify();
  }

  @Test
  public void toStringOutput() {
    final double latitude = 47.5913904235;
    final double longitude = 12.9946215637;
    final T instance = newInstance(longitude, latitude);
    assertEquals(
        "Point2D{longitude=" + longitude + ", " + "latitude=" + latitude + '}',
        instance.toString());
  }

  @Test
  public final void valLatitudeMin() {
    final IllegalArgumentException e =
        assertThrows(IllegalArgumentException.class, () -> newInstance(45.0, -90.1));
    assertTrue(e.getMessage().contains("Latitude must"));
  }

  @Test
  public final void valLatitudeMax() {
    final IllegalArgumentException e =
        assertThrows(IllegalArgumentException.class, () -> newInstance(45.0, 90.1));
    assertTrue(e.getMessage().contains("Latitude must"));
  }

  @Test
  public final void valLatitudeNaN() {
    final IllegalArgumentException e =
        assertThrows(IllegalArgumentException.class, () -> newInstance(45.0, Double.NaN));
    assertTrue(e.getMessage().contains("Latitude must"));
  }

  @Test
  public final void valLongitudeMin() {
    final IllegalArgumentException e =
        assertThrows(IllegalArgumentException.class, () -> newInstance(-180.1, 45.0));
    assertTrue(e.getMessage().contains("Longitude must"));
  }

  @Test
  public final void valLongitudeMax() {
    final IllegalArgumentException e =
        assertThrows(IllegalArgumentException.class, () -> newInstance(180.0, 45.0));
    assertTrue(e.getMessage().contains("Longitude must"));
  }

  @Test
  public final void valLongitudeNaN() {
    final IllegalArgumentException e =
        assertThrows(IllegalArgumentException.class, () -> newInstance(Double.NaN, 45.0));
    assertTrue(e.getMessage().contains("Longitude must"));
  }

  protected abstract T newInstance(final double longitude, final double latitude);
}
