package eu.oberon.oss.util.test.raw;

import lombok.AccessLevel;
import lombok.Getter;

@Getter(value = AccessLevel.PACKAGE)
public class TextGeneratorSettingsBuilder {
    private int minLength = -1;
    private int maxLength = -1;
    private CaseType caseType = null;
    private final StringBuilder stringBuilder = new StringBuilder();

    TextGeneratorSettingsBuilder() {
    }

    public TextGeneratorSettingsBuilder setMinLength(int minLength) {
        this.minLength = minLength;
        return this;
    }

    public TextGeneratorSettingsBuilder setMaxLength(int maxLength) {
        this.maxLength = maxLength;
        if (minLength == -1) {
            minLength = maxLength;
        }
        return this;
    }

    public TextGeneratorSettingsBuilder setCaseType(CaseType caseType) {
        this.caseType = caseType;
        return this;
    }

    public TextGeneratorSettingsBuilder addCharacterList(String characterList) {
        if (characterList != null && characterList.length() > 0) {
            stringBuilder.append(characterList);
        }
        return this;
    }

    public TextGeneratorSettings build() {
        return new TextGeneratorSettings(this);
    }
}
