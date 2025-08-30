package eu.oberon.oss.util.test.raw;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.security.SecureRandom;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class TextGeneratorTest {
    private TextGenerator textGenerator;

    @BeforeEach
    void setUp() {
        textGenerator = new TextGenerator(new SecureRandom());
    }

    @Test
    void testGeneratingWithFixedLengthStringOnly() {
        TextGeneratorSettings settings = TextGeneratorSettings.getBuilder()
                .setMinLength(10)
                .addCharacterList("A")
                .build();

        String text = textGenerator.createText(settings);
        assertEquals("A".repeat(10), text);
    }

    @Test
    void testWithRandomStringLength() {
        LOGGER.info("Running random String length generation 200x");
        for (int l = 0; l < 200; l++) {
            TextGeneratorSettings settings = TextGeneratorSettings.getBuilder()
                    .setMinLength(5)
                    .setMaxLength(10)
                    .addCharacterList("A")
                    .build();
            String text = textGenerator.createText(settings);
            assertTrue(text.length() >= 5 && text.length() <= 10);
        }
    }


    @Test
    void testWithNoCaseTypeUpper() {
        TextGeneratorSettings settings = TextGeneratorSettings.getBuilder()
                .setMinLength(10)
                .setCaseType(CaseType.UPPER)
                .addCharacterList("A")
                .build();

        assertEquals("A".repeat(10), textGenerator.createText(settings));
    }

    @Test
    void testWithNoCaseTypeLower() {
        TextGeneratorSettings settings = TextGeneratorSettings.getBuilder()
                .setMinLength(10)
                .setCaseType(CaseType.LOWER)
                .addCharacterList("A")
                .build();

        assertEquals("a".repeat(10), textGenerator.createText(settings));
    }

    @Test
    void testWithNoCaseTypeRandom() {
        TextGeneratorSettings settings = TextGeneratorSettings.getBuilder()
                .setMinLength(200)
                .setCaseType(CaseType.RANDOM)
                .addCharacterList("A")
                .build();
        assertEquals(0, "a".repeat(200).compareToIgnoreCase(textGenerator.createText(settings)));
        assertNotEquals("A".repeat(200), textGenerator.createText(settings));
        assertNotEquals("a".repeat(200), textGenerator.createText(settings));
    }
}