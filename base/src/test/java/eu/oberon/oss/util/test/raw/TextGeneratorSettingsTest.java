package eu.oberon.oss.util.test.raw;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TextGeneratorSettingsTest {
    private TextGeneratorSettingsBuilder builder;

    @BeforeEach
    void setup() {
        builder = TextGeneratorSettings.getBuilder();
    }

    @Test
    void testWithUninitializedBuilder() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> builder.build());
        assertEquals("minLength must be greater than 0.", exception.getMessage());
    }

    @Test
    void testWithMaxLessThanMin() {
        builder.setMinLength(10).setMaxLength(5);
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class, () -> builder.build());
        assertEquals("maxLength must be >= minLength.", exception.getMessage());
    }

    @Test
    void testWithoutCharacterListContents() {
        builder.setMaxLength(5);
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class, () -> builder.build());
        assertEquals("characterList must not be empty.", exception.getMessage());
    }


}