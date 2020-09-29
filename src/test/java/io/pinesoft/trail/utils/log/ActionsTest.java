package io.pinesoft.trail.utils.log;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/** @author Xavier Sosnovsky */
public class ActionsTest {

  @Test
  public void toStringOutput() {
    final Actions action = Actions.valueOf("CREATE");
    assertEquals("create    ", action.toString());
  }
}
