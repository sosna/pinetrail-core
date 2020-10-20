package io.pinesoft.trail.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class GpsRecordTest extends Point3DTest<GpsRecord> {

  @Test
  void createInstance() {
    final long time = Instant.EPOCH.toEpochMilli();
    final double latitude = 47.5913904235;
    final double longitude = 12.9946215637;
    final double elevation = 630.28;
    final GpsRecord instance = newGpsRecord(longitude, latitude, elevation, time);
    assertEquals(time, instance.getTime());
    assertEquals(longitude, instance.getLongitude(), 0.0);
    assertEquals(latitude, instance.getLatitude(), 0.0);
    assertEquals(elevation, instance.getElevation(), 0.0);
  }

  @Test
  void equalsContract() {
    EqualsVerifier.forClass(GpsRecordImpl.class)
        .withCachedHashCode(
            "cachedCode",
            "calculateHash",
            new GpsRecordImpl(56.7, 43.2, 106.12, Instant.now().toEpochMilli()))
        .verify();
  }

  // Check against conventions of Effective Java
  @Test
  void followsHashCodeConventions() {
    final long time = Instant.now().toEpochMilli();
    final double latitude = 47.5913904235;
    final double longitude = 12.9946215637;
    final double elevation = 630.28;
    final GpsRecord instance = newGpsRecord(longitude, latitude, elevation, time);
    int expected = Double.hashCode(instance.getLongitude());
    expected = 31 * expected + Double.hashCode(instance.getLatitude());
    expected = 31 * expected + Double.hashCode(instance.getElevation());
    expected = 31 * expected + Long.hashCode(instance.getTime());
    assertEquals(expected, instance.hashCode());
  }

  @Test
  void toStringOutput() {
    final long time = Instant.EPOCH.toEpochMilli();
    final double latitude = 47.5913904235;
    final double longitude = 12.9946215637;
    final double elevation = 630.28;
    final GpsRecord instance = newGpsRecord(longitude, latitude, elevation, time);
    assertEquals(
        "GpsRecord{longitude="
            + longitude
            + ", "
            + "latitude="
            + latitude
            + ", elevation="
            + elevation
            + ", time="
            + time
            + '}',
        instance.toString());
  }

  @ParameterizedTest
  @MethodSource("createValidTime")
  void validTime(final long time) {
    final double latitude = -89;
    final double longitude = 12.9946215637;
    final double elevation = 530.28;
    final GpsRecord rec = newGpsRecord(longitude, latitude, elevation, time);
    assertEquals(time, rec.getTime());
  }

  @Test
  void invalidTime() {
    final double latitude = -89;
    final double longitude = 12.9946215637;
    final double elevation = 530.28;
    final IllegalArgumentException e =
        assertThrows(
            IllegalArgumentException.class,
            () ->
                newGpsRecord(
                    longitude,
                    latitude,
                    elevation,
                    Instant.now().plus(2, ChronoUnit.SECONDS).toEpochMilli()));
    assertTrue(e.getMessage().contains("Time must"));
  }

  @Test
  void compareBefore() {
    final long t1 = Instant.now().toEpochMilli();
    final long t2 = Instant.EPOCH.toEpochMilli();
    final double latitude = 90.0;
    final double longitude = 42.0;
    final double elevation = 630.28;
    final GpsRecord pt1 = newGpsRecord(longitude, latitude, elevation, t1);
    final GpsRecord pt2 = newGpsRecord(longitude, latitude, elevation, t2);
    assertEquals(1, pt1.compareTo(pt2));
  }

  @Test
  void compareAfter() {
    final long t1 = Instant.EPOCH.toEpochMilli();
    final long t2 = Instant.now().toEpochMilli();
    final double latitude = 90.0;
    final double longitude = 42.0;
    final double elevation = 630.28;
    final GpsRecord pt1 = newGpsRecord(longitude, latitude, elevation, t1);
    final GpsRecord pt2 = newGpsRecord(longitude, latitude, elevation, t2);
    assertEquals(-1, pt1.compareTo(pt2));
  }

  @Test
  void compareSame() {
    final long t1 = Instant.EPOCH.toEpochMilli();
    final long t2 = Instant.EPOCH.toEpochMilli();
    final double latitude = 90.0;
    final double longitude = 42.0;
    final double elevation = 630.28;
    final GpsRecord pt1 = newGpsRecord(longitude, latitude, elevation, t1);
    final GpsRecord pt2 = newGpsRecord(longitude, latitude, elevation, t2);
    assertEquals(0, pt1.compareTo(pt2));
  }

  @Override
  protected GpsRecord newInstance(double longitude, double latitude) {
    return newInstance(longitude, latitude, 100);
  }

  @Override
  protected GpsRecord newInstance(double longitude, double latitude, double elevation) {
    return newGpsRecord(longitude, latitude, elevation, Instant.EPOCH.toEpochMilli());
  }

  private GpsRecord newGpsRecord(double longitude, double latitude, double elevation, long time) {
    return GpsRecord.of(longitude, latitude, elevation, time);
  }

  private static Stream<Long> createValidTime() {
    final long now = Instant.now().toEpochMilli();
    final long min =
        LocalDate.now()
            .minus(Period.ofYears(100))
            .atStartOfDay()
            .toInstant(ZoneOffset.UTC)
            .toEpochMilli();
    return IntStream.range(0, 10).mapToObj(num -> ThreadLocalRandom.current().nextLong(min, now));
  }
}
