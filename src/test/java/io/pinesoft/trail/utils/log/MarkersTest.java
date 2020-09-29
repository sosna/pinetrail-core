package io.pinesoft.trail.utils.log;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.slf4j.Marker;

/** @author Xavier Sosnovsky */
public class MarkersTest {

  @Test
  public void getMarker() {
    final Markers audit = Markers.valueOf("DB");
    final Marker marker = audit.getMarker();
    assertEquals("db", marker.getName());
  }

  @Test
  public void sameInstance() {
    final Marker marker1 = Markers.MODEL.getMarker();
    final Marker marker2 = Markers.MODEL.getMarker();
    assertSame(marker1, marker2);
  }
}
