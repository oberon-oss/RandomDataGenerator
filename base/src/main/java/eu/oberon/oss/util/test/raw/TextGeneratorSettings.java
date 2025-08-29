package eu.oberon.oss.util.test.raw;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Provides generator settings for the {@link TextGenerator} class.
 *
 * @author TigerLilly64
 * @since 1.0.0
 */
@SuppressWarnings("LombokGetterMayBeUsed")
public class TextGeneratorSettings {
    private final int minLength;
    private final int maxLength;
    private @Nullable
    final CaseType caseType;
    private final List<Character> characterList;

    /**
     * Returns the minimum length of the generated data.
     *
     * @return the minimum length for generated data.
     *
     * @since 1.0.0
     */
    public int getMinLength() {
        return minLength;
    }

    /**
     * Returns the minimum length of the generated data.
     *
     * @return the minimum length for generated data.
     *
     * @since 1.0.0
     */
    public int getMaxLength() {
        return maxLength;
    }


    /**
     * Returns the minimum length of the generated data.
     *
     * @return the minimum length for generated data.
     *
     * @since 1.0.0
     */
    public @Nullable CaseType getCaseType() {
        return caseType;
    }


    /**
     * Returns the minimum length of the generated data.
     *
     * @return the minimum length for generated data.
     *
     * @since 1.0.0
     */
    public List<Character> getCharacterList() {
        return characterList;
    }


    /**
     * Constructs a settings instance form the provided builder instance.
     *
     * @param builder The builder buildr object to use as input.
     *
     * @throws IllegalArgumentException in either of the following cases:
     *                                  <ul>
     *                                      <li>The value for the minLength is less than 1</li>
     *                                      <li>The value for maxLength is less than the value for minLength</li>
     *                                      <li>The characterList is an empty string (white space is allowed, as it
     *                                      CAN be valid)</li>
     *                                  </ul>
     * @since 1.0.0
     */
    TextGeneratorSettings(@NotNull TextGeneratorSettingsBuilder builder) {
        caseType = builder.getCaseType();

        minLength = builder.getMinLength();
        if (minLength < 1) {
            throw new IllegalArgumentException("minLength must be greater than 0.");
        }

        maxLength = builder.getMaxLength();
        if (maxLength < minLength) {
            throw new IllegalArgumentException("maxLength must be >= minLength.");
        }

        characterList = TextGenerator.createCharacterListFromString(builder.getStringBuilder().toString());
        if (characterList.isEmpty()) {
            throw new IllegalArgumentException("characterList must not be empty.");
        }
    }
}
