package io.pinesoft.trail.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Instant;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/** @author Xavier Sosnovsky */
public class GpsRecordTest {

  private static Validator validator;

  @BeforeAll
  public static void setUp() {
    final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  public void createInstance() {
    final Instant time = Instant.EPOCH;
    final double latitude = 47.5913904235;
    final double longitude = 12.9946215637;
    final double elevation = 630.28;
    final GpsRecord instance = newGpsRecord(time, longitude, latitude, elevation);
    assertEquals(time, instance.getTime());
    assertEquals(longitude, instance.getLongitude(), 0.0);
    assertEquals(latitude, instance.getLatitude(), 0.0);
    assertEquals(elevation, instance.getElevation(), 0.0);
  }

  @Test
  public void equalsContract() {
    EqualsVerifier.forClass(GpsRecord.class).verify();
  }

  @Test
  public void toStringOutput() {
    final Instant time = Instant.EPOCH;
    final double latitude = 47.5913904235;
    final double longitude = 12.9946215637;
    final double elevation = 630.28;
    final GpsRecord instance = newGpsRecord(time, longitude, latitude, elevation);
    assertEquals(
        "GpsRecord{time="
            + time
            + ", longitude="
            + longitude
            + ", "
            + "latitude="
            + latitude
            + ", elevation="
            + elevation
            + '}',
        instance.toString());
  }

  @Test
  public void valOK() {
    final Instant time = Instant.EPOCH;
    final double latitude = 47.5913904235;
    final double longitude = 12.9946215637;
    final double elevation = 630.28;
    final GpsRecord instance = newGpsRecord(time, longitude, latitude, elevation);
    Set<ConstraintViolation<GpsRecord>> constraintViolations = validator.validate(instance);
    assertEquals(0, constraintViolations.size());
  }

  @Test
  public void valTimePast() {
    final Instant time = Instant.MAX;
    final double latitude = -89;
    final double longitude = 12.9946215637;
    final double elevation = 630.28;
    final GpsRecord instance = newGpsRecord(time, longitude, latitude, elevation);
    Set<ConstraintViolation<GpsRecord>> constraintViolations = validator.validate(instance);
    assertEquals(1, constraintViolations.size());
    constraintViolations.forEach(
        (prob) -> {
          assertEquals(
              "The time the waypoint was recorded must be in the past. Got " + time + ".",
              prob.getMessage());
        });
  }

  @Test
  public void valNullCoordinates() {
    final Instant time = Instant.EPOCH;
    final GpsRecord instance = GpsRecord.of(time, null);
    Set<ConstraintViolation<GpsRecord>> constraintViolations = validator.validate(instance);
    assertEquals(1, constraintViolations.size());
    constraintViolations.forEach(
        (prob) -> {
          assertEquals("The coordinates of the GpsRecord must be supplied.", prob.getMessage());
        });
  }

  @Test
  public void compareBefore() {
    final Instant t1 = Instant.EPOCH;
    final Instant t2 = Instant.MIN;
    final double latitude = 90.0;
    final double elevation = 630.28;
    final GpsRecord pt1 = newGpsRecord(t1, Double.NaN, latitude, elevation);
    final GpsRecord pt2 = newGpsRecord(t2, Double.NaN, latitude, elevation);
    assertEquals(1, pt1.compareTo(pt2));
  }

  @Test
  public void compareAfter() {
    final Instant t1 = Instant.EPOCH;
    final Instant t2 = Instant.MAX;
    final double latitude = 90.0;
    final double elevation = 630.28;
    final GpsRecord pt1 = newGpsRecord(t1, Double.NaN, latitude, elevation);
    final GpsRecord pt2 = newGpsRecord(t2, Double.NaN, latitude, elevation);
    assertEquals(-1, pt1.compareTo(pt2));
  }

  @Test
  public void compareSame() {
    final Instant t1 = Instant.EPOCH;
    final Instant t2 = Instant.EPOCH;
    final double latitude = 90.0;
    final double elevation = 630.28;
    final GpsRecord pt1 = newGpsRecord(t1, Double.NaN, latitude, elevation);
    final GpsRecord pt2 = newGpsRecord(t2, Double.NaN, latitude, elevation);
    assertEquals(0, pt1.compareTo(pt2));
  }

  private GpsRecord newGpsRecord(
      final Instant time, final Double longitude, final Double latitude, final Double elevation) {
    return GpsRecord.of(time, newCoordinates(longitude, latitude, elevation));
  }

  private Coordinates newCoordinates(
      final Double longitude, final Double latitude, final Double elevation) {
    return Coordinates.of(longitude, latitude, elevation);
  }
}
