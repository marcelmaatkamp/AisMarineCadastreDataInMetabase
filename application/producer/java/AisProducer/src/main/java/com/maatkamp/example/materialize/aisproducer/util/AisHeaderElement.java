package com.maatkamp.example.materialize.aisproducer.util;

/**
 * https://coast.noaa.gov/data/marinecadastre/ais/data-dictionary.pdf
 * 
 * @author Marcel Maatkamp <m.maatkamp@gmail.com>
 */

import java.util.HashMap;
import java.util.Map;
public enum AisHeaderElement {

    MSSI("MMSI", 0),
    BASEDATETIME("BaseDateTime", 1),
    LAT("LAT", 2),
    LON("LON", 3),
    SOG("SOG", 4),
    COG("COG", 5),
    HEADING("Heading", 6),
    VESSELNAME("VesselName", 7),
    IMO("IMO", 8),
    CALLSIGN("CallSign", 9),
    VESSELTYPE("VesselType", 10),
    STATUS("Status", 11),
    LENGTH("Length", 12),
    WIDTH("Width", 13),
    DRAFT("Draft", 14),
    CARGO("Cargo", 15),
    TRANSCEIVERCLASS("TransceiverClass", 16);

    public final String label;
    public final int position;
    private static final Map<String, AisHeaderElement> BY_LABEL = new HashMap<>();
    private static final Map<Integer, AisHeaderElement> BY_POSITION = new HashMap<>();

    static {
        for (AisHeaderElement aisHeaderElement : values()) {
            BY_LABEL.put(aisHeaderElement.label, aisHeaderElement);
            BY_POSITION.put(aisHeaderElement.position, aisHeaderElement);
        }
    }

    /**
     * Ais Header value names and position
     * @param label of element
     * @param position of element
     */
    AisHeaderElement(String label, int position) {
        this.label = label;
        this.position = position;
    }

    public static AisHeaderElement valueOfLabel(String label) {
        return BY_LABEL.get(label);
    }
    public static AisHeaderElement valueOfPosition(int number) {
        return BY_POSITION.get(number);
    }
}
