package eu.oberon.oss.util.test.raw;

import lombok.AccessLevel;
import lombok.Getter;

/**
 * Builder to create {@link TextGeneratorSettings} objects.
 *
 * @author TigerLilly64
 * @since 1.0.0
 */
@Getter(value = AccessLevel.PACKAGE)
public class TextGeneratorSettingsBuilder {
    private int minLength = -1;
    private int maxLength = -1;
    private CaseType caseType = null;
    private final StringBuilder stringBuilder = new StringBuilder();

    /**
     * Default constructor
     *
     * @since 1.0.0
     */
    TextGeneratorSettingsBuilder() {
    }

    /**
     * Sets the minimumLength of the string to be generated
     *
     * @param minLength The minium length.
     *
     * @return The builder instance.
     *
     * @since 1.0.0
     */
    public TextGeneratorSettingsBuilder setMinLength(int minLength) {
        this.minLength = minLength;
        return this;
    }

    /**
     * Sets the maximumLength of the string to be generated
     *
     * @param maxLength the maximumLength to set
     *
     * @return The builder instance.
     *
     * @since 1.0.0
     */
    public TextGeneratorSettingsBuilder setMaxLength(int maxLength) {
        this.maxLength = maxLength;
        if (minLength == -1) {
            minLength = maxLength;
        }
        return this;
    }

    /**
     * Sets the required handling of the character case of the generated data.
     *
     * @param caseType The case type to set.
     *
     * @return The builder instance.
     *
     * @since 1.0.0
     */
    public TextGeneratorSettingsBuilder setCaseType(CaseType caseType) {
        this.caseType = caseType;
        return this;
    }

    /**
     * Adds the characters from the character list to the builder.

     * @param characterList String containing the characters to be added.
     *
     * @return The builder instance.
     *
     * @since 1.0.0
     */
    public TextGeneratorSettingsBuilder addCharacterList(String characterList) {
        if (characterList != null && characterList.length() > 0) {
            stringBuilder.append(characterList);
        }
        return this;
    }

    /**
     * Creates the generator settings object.
     *
     * @return The generated text generator settings object.
     *
     * @since 1.0.0
     */
    public TextGeneratorSettings build() {
        return new TextGeneratorSettings(this);
    }
}
