package io.pinesoft.trail.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/** @author Xavier Sosnovsky */
public class CoordinatesTest {

  private static Validator validator;

  @BeforeAll
  public static void setUp() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  public void createInstance() {
    final double latitude = 47.5913904235;
    final double longitude = 12.9946215637;
    final double elevation = 630.28;
    final Coordinates instance = newCoordinates(longitude, latitude, elevation);
    assertEquals(longitude, instance.getLongitude(), 0.0);
    assertEquals(latitude, instance.getLatitude(), 0.0);
    assertEquals(elevation, instance.getElevation(), 0.0);
  }

  @Test
  public void elevationDefaultToNan() {
    final Coordinates instance = Coordinates.of(12.9946215637, 47.5913904235);
    assertEquals(Double.NaN, instance.getElevation(), 0.0);
  }

  @Test
  public void nullElevationDefaultToNan() {
    final Coordinates instance = newCoordinates(12.9946215637, 47.5913904235, null);
    assertEquals(Double.NaN, instance.getElevation(), 0.0);
  }

  @Test
  public void equalsContract() {
    EqualsVerifier.forClass(Coordinates.class).verify();
  }

  @Test
  public void toStringOutput() {
    final double latitude = 47.5913904235;
    final double longitude = 12.9946215637;
    final double elevation = 630.28;
    final Coordinates instance = newCoordinates(longitude, latitude, elevation);
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

  @Test
  public void valOK() {
    final double latitude = 47.5913904235;
    final double longitude = 12.9946215637;
    final double elevation = 630.28;
    final Coordinates instance = newCoordinates(longitude, latitude, elevation);
    Set<ConstraintViolation<Coordinates>> constraintViolations = validator.validate(instance);
    assertEquals(0, constraintViolations.size());
  }

  @Test
  public void valLatitudeMin() {
    final double latitude = -90.1;
    final double longitude = 12.9946215637;
    final double elevation = 630.28;
    final Coordinates instance = newCoordinates(longitude, latitude, elevation);
    Set<ConstraintViolation<Coordinates>> constraintViolations = validator.validate(instance);
    assertEquals(1, constraintViolations.size());
    constraintViolations.forEach(
        (prob) ->
            assertEquals(
                "Latitude must be superior or equal to -90 degrees. Got -90.1.",
                prob.getMessage()));
  }

  @Test
  public void valLatitudeMax() {
    final double latitude = 90.1;
    final double longitude = 12.9946215637;
    final double elevation = 630.28;
    final Coordinates instance = newCoordinates(longitude, latitude, elevation);
    Set<ConstraintViolation<Coordinates>> constraintViolations = validator.validate(instance);
    assertEquals(1, constraintViolations.size());
    constraintViolations.forEach(
        (prob) ->
            assertEquals(
                "Latitude must be inferior or equal to 90 degrees. Got 90.1.", prob.getMessage()));
  }

  @Test
  public void valLatitudeNull() {
    final double longitude = 12.9946215637;
    final double elevation = 630.28;
    final Coordinates instance = newCoordinates(longitude, null, elevation);
    Set<ConstraintViolation<Coordinates>> constraintViolations = validator.validate(instance);
    assertEquals(1, constraintViolations.size());
    constraintViolations.forEach(
        (prob) -> assertEquals("Latitude must be supplied.", prob.getMessage()));
  }

  @Test
  public void valLatitudeNaN() {
    final double longitude = 12.9946215637;
    final double elevation = 630.28;
    final Coordinates instance = newCoordinates(longitude, Double.NaN, elevation);
    Set<ConstraintViolation<Coordinates>> constraintViolations = validator.validate(instance);
    assertEquals(2, constraintViolations.size());
  }

  @Test
  public void valLongitudeMin() {
    final double latitude = -90.0;
    final double longitude = -180.1;
    final double elevation = 630.28;
    final Coordinates instance = newCoordinates(longitude, latitude, elevation);
    Set<ConstraintViolation<Coordinates>> constraintViolations = validator.validate(instance);
    assertEquals(1, constraintViolations.size());
    constraintViolations.forEach(
        (prob) ->
            assertEquals(
                "Longitude must be superior or equal to -180 degrees. Got -180.1.",
                prob.getMessage()));
  }

  @Test
  public void valLongitudeMax() {
    final double latitude = 90.0;
    final double longitude = 180.0;
    final double elevation = 630.28;
    final Coordinates instance = newCoordinates(longitude, latitude, elevation);
    Set<ConstraintViolation<Coordinates>> constraintViolations = validator.validate(instance);
    assertEquals(1, constraintViolations.size());
    constraintViolations.forEach(
        (prob) ->
            assertEquals(
                "Longitude must be inferior to 180 degrees. Got 180.0.", prob.getMessage()));
  }

  @Test
  public void valLongitudeNull() {
    final double latitude = 90.0;
    final double elevation = 630.28;
    final Coordinates instance = newCoordinates(null, latitude, elevation);
    Set<ConstraintViolation<Coordinates>> constraintViolations = validator.validate(instance);
    assertEquals(1, constraintViolations.size());
    constraintViolations.forEach(
        (prob) -> assertEquals("Longitude must be supplied.", prob.getMessage()));
  }

  @Test
  public void valLongitudeNaN() {
    final double latitude = 90.0;
    final double elevation = 630.28;
    final Coordinates instance = newCoordinates(Double.NaN, latitude, elevation);
    Set<ConstraintViolation<Coordinates>> constraintViolations = validator.validate(instance);
    assertEquals(2, constraintViolations.size());
  }

  private Coordinates newCoordinates(
      final Double longitude, final Double latitude, final Double elevation) {
    return Coordinates.of(longitude, latitude, elevation);
  }
}
