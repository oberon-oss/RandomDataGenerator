package eu.oberon.oss.util.test.raw;

import nl.altindag.log.LogCaptor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TextGeneratorSettingsBuilderTest {
    private LogCaptor logCaptor;
    private static final List<Character> CHARACTER_LIST = List.of('T', 'E', 'S', 'T');
    private TextGeneratorSettingsBuilder builder;

    @BeforeEach
    void setUp() {
        builder = TextGeneratorSettings.getBuilder();
        logCaptor = LogCaptor.forClass(TextGeneratorSettingsBuilderTest.class);
    }

    @Test
    void testCorrectlyInitialized() {
        builder.setMaxLength(10);
        assertEquals(builder.getMinLength(), builder.getMaxLength());
        builder.setMinLength(5);
        builder.addCharacterList("TEST");
        builder.setCaseType(CaseType.UPPER);

        TextGeneratorSettings settings = assertDoesNotThrow(builder::build);

        assertEquals(5, settings.getMinLength());
        assertEquals(10, settings.getMaxLength());
        assertEquals(CaseType.UPPER, settings.getCaseType());
        assertEquals(CHARACTER_LIST, settings.getCharacterList());
    }

    @Test
    void testMaxMatchesMinWhenSet() {
        builder.setMaxLength(10);
        assertEquals(builder.getMinLength(), builder.getMaxLength());
    }

    @Test
    void testMinMatchesMaxWhenSet() {
        builder.setMinLength(10);
        assertEquals(builder.getMaxLength(), builder.getMinLength());
    }

    @Test
    void testMaxNotChangingMinWhenMinAlreadySet() {
        builder.setMinLength(13);
        assertEquals(13, builder.getMaxLength());
        assertEquals(13, builder.getMinLength());

        builder.setMaxLength(19);
        assertEquals(13, builder.getMinLength());
        assertEquals(19, builder.getMaxLength());
    }

    @Test
    void testMinNotChangingMaxWhenMaxAlreadySet() {
        builder.setMaxLength(13);
        assertEquals(13, builder.getMaxLength());
        assertEquals(13, builder.getMinLength());

        builder.setMinLength(19);
        assertEquals(13, builder.getMaxLength());
        assertEquals(19, builder.getMinLength());
    }

    @Test
    void testCharacterListThatIsNullOrEmpty() {
        builder.addCharacterList("");
        assertTrue(builder.getStringBuilder().isEmpty());
        assertTrue(logCaptor.getWarnLogs().contains("Parameter characterList: cannot be empty"));

        builder.addCharacterList(null);
        assertTrue(builder.getStringBuilder().isEmpty());
        assertTrue(logCaptor.getWarnLogs().contains("Parameter characterList: cannot be <null>"));
    }
}