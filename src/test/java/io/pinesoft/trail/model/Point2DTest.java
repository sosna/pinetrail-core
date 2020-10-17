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

abstract class Point2DTest<T extends Point2D> {

  // Sanity checks
  @ParameterizedTest
  @MethodSource("createValidLatitude")
  void valLatitude(final double latitude) {
    final double longitude = 12.9946215637;
    final T instance = newInstance(longitude, latitude);
    assertEquals(longitude, instance.getLongitude(), 0.0);
    assertEquals(latitude, instance.getLatitude(), 0.0);
  }

  @ParameterizedTest
  @MethodSource("createValidLongitude")
  void valLongitude(final double longitude) {
    final double latitude = 12.9946215637;
    final T instance = newInstance(longitude, latitude);
    assertEquals(longitude, instance.getLongitude(), 0.0);
    assertEquals(latitude, instance.getLatitude(), 0.0);
  }

  // Invalid latitude. Must be between [-90.0, 90.0]!
  @ParameterizedTest
  @MethodSource("createInvalidLatitudeMin")
  final void valLatitudeMin(final double latitude) {
    final IllegalArgumentException e =
        assertThrows(IllegalArgumentException.class, () -> newInstance(45.0, latitude));
    assertTrue(e.getMessage().contains("Latitude must"));
  }

  @ParameterizedTest
  @MethodSource("createInvalidLatitudeMax")
  final void valLatitudeMax(final double latitude) {
    final IllegalArgumentException e =
        assertThrows(IllegalArgumentException.class, () -> newInstance(45.0, latitude));
    assertTrue(e.getMessage().contains("Latitude must"));
  }

  @Test
  final void valLatitudeNaN() {
    final IllegalArgumentException e =
        assertThrows(IllegalArgumentException.class, () -> newInstance(45.0, Double.NaN));
    assertTrue(e.getMessage().contains("Latitude must"));
  }

  // Invalid longitude. Must be between [-180.0, 180.0[!
  @ParameterizedTest
  @MethodSource("createInvalidLongitudeMin")
  final void valLongitudeMin(final double longitude) {
    final IllegalArgumentException e =
        assertThrows(IllegalArgumentException.class, () -> newInstance(longitude, 45.0));
    assertTrue(e.getMessage().contains("Longitude must"));
  }

  @ParameterizedTest
  @MethodSource("createInvalidLongitudeMax")
  final void valLongitudeMax(final double longitude) {
    final IllegalArgumentException e =
        assertThrows(IllegalArgumentException.class, () -> newInstance(longitude, 45.0));
    assertTrue(e.getMessage().contains("Longitude must"));
  }

  @Test
  final void valLongitudeNaN() {
    final IllegalArgumentException e =
        assertThrows(IllegalArgumentException.class, () -> newInstance(Double.NaN, 45.0));
    assertTrue(e.getMessage().contains("Longitude must"));
  }

  protected abstract T newInstance(final double longitude, final double latitude);

  private static Stream<Double> createValidLatitude() {
    return createStream(-90, 90, 2);
  }

  private static Stream<Double> createValidLongitude() {
    return createStream(-180, 179.99999, 2);
  }

  private static Stream<Double> createInvalidLatitudeMin() {
    return createStream(-1000.0, -90.00001, 0);
  }

  private static Stream<Double> createInvalidLatitudeMax() {
    return createStream(90.00001, 1000.0, 1);
  }

  private static Stream<Double> createInvalidLongitudeMin() {
    return createStream(-1000.0, -180.00001, 0);
  }

  private static Stream<Double> createInvalidLongitudeMax() {
    return createStream(180.0, 1000.0, 1);
  }

  private static Stream<Double> createStream(final double min, final double max, final int type) {
    final List<Double> out =
        IntStream.range(0, 10)
            .mapToObj(num -> ThreadLocalRandom.current().nextDouble(min, max))
            .collect(Collectors.toList());
    switch (type) {
      case 0:
        out.add(max);
        break;
      case 1:
        out.add(min);
        break;
      case 2:
        out.add(min);
        out.add(max);
        break;
      default:
        throw new IllegalArgumentException("Invalid type");
    }
    return out.stream();
  }
}
