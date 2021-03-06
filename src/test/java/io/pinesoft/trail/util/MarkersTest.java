package io.pinesoft.trail.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;
import org.slf4j.Marker;

class MarkersTest {

  @Test
  void getMarker() {
    final Markers audit = Markers.IO;
    final Marker marker = audit.getMarker();
    assertEquals("io", marker.getName());
  }

  @Test
  void sameInstance() {
    final Marker marker1 = Markers.MODEL.getMarker();
    final Marker marker2 = Markers.MODEL.getMarker();
    assertSame(marker1, marker2);
  }
}
