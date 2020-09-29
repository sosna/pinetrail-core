package io.pinesoft.trail.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class ValidationServiceTest {

  @Test
  public void getValidator() {
    final ValidationService service = ValidationService.valueOf("INSTANCE");
    assertNotNull(service.getValidator());
  }
}
