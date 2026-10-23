package com.example.practicalfive;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class TextProcessorTest {
    private final TextProcessor processor = new TextProcessor();

    @Test
    public void acceptsAllMunicipalitiesIgnoringCaseAndOuterSpaces() {
        assertEquals("Beijing", processor.processMunicipality("Beijing"));
        assertEquals("Beijing", processor.processMunicipality("beijing"));
        assertEquals("Beijing", processor.processMunicipality("BEIJING"));
        assertEquals("Shanghai", processor.processMunicipality("SHANGHAI"));
        assertEquals("Tianjin", processor.processMunicipality("tianjin"));
        assertEquals("Chongqing", processor.processMunicipality("ChOnGqInG"));
        assertEquals("Beijing", processor.processMunicipality("  Beijing  "));
    }

    @Test
    public void rejectsMissingBlankAndUnsupportedInput() {
        assertNull(processor.processMunicipality(null));
        assertNull(processor.processMunicipality(""));
        assertNull(processor.processMunicipality(" \t\n "));
        assertNull(processor.processMunicipality("Guangzhou"));
        assertNull(processor.processMunicipality("Bei jing"));
    }
}
