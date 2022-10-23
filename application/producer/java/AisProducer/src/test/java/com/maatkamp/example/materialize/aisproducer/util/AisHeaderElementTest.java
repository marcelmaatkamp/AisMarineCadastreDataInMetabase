package com.maatkamp.example.materialize.aisproducer.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AisHeaderElementTest {

    @Test
    void testHeaderMSSI() {
        assertEquals("MMSI", AisHeaderElement.MSSI.label, "MMSI.label");
        assertEquals(0, AisHeaderElement.MSSI.position, "MMSI.position");
    }
}