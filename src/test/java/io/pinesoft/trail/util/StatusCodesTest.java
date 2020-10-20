package io.pinesoft.trail.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/** @author Xavier Sosnovsky */
public class StatusCodesTest {

  @Test
  public void getCode() {
    final StatusCodes result = StatusCodes.valueOf("OK");
    assertEquals(200, result.getCode());
  }
}
