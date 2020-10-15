package io.pinesoft.trail.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public abstract class Point2DTest<T extends Point2D> {

  @Test
  public void createInstance() {
    final double latitude = 47.5913904235;
    final double longitude = 12.9946215637;
    final T instance = newInstance(longitude, latitude);
    assertEquals(longitude, instance.getLongitude(), 0.0);
    assertEquals(latitude, instance.getLatitude(), 0.0);
  }

  @ParameterizedTest
  @MethodSource("provideLatitude")
  public void valLatitude(final Double latitude) {
    final double longitude = 12.9946215637;
    final T instance = newInstance(longitude, latitude);
    assertEquals(longitude, instance.getLongitude(), 0.0);
    assertEquals(latitude, instance.getLatitude(), 0.0);
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

  private static Stream<Double> provideLatitude() {
    final List<Double> out =
        IntStream.range(0, 10)
            .mapToObj(num -> ThreadLocalRandom.current().nextDouble(-90.0, 90.0))
            .collect(Collectors.toList());
    out.add(-90.0);
    out.add(90.0);
    return out.stream();
  }
}
