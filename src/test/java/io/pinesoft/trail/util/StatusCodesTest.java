package io.pinesoft.trail.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class StatusCodesTest {

  @Test
  void getCode() {
    final StatusCodes result = StatusCodes.valueOf("OK");
    assertEquals(200, result.getCode());
  }
}
