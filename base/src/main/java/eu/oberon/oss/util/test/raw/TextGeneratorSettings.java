package eu.oberon.oss.util.test.raw;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@Getter
public class TextGeneratorSettings {
    private final int minLength;
    private final int maxLength;
    private final CaseType caseType;
    private final List<Character> characterList;

    TextGeneratorSettings(@NotNull TextGeneratorSettingsBuilder settings) {
        caseType = settings.getCaseType();

        minLength = settings.getMinLength();
        if (minLength < 1) {
            throw new IllegalArgumentException("minLength must be greater than 0.");
        }

        maxLength = settings.getMaxLength();
        if (maxLength < minLength) {
            throw new IllegalArgumentException("maxLength must be >= minLength.");
        }

        characterList = TextGenerator.createCharacterListFromString(settings.getStringBuilder().toString());
        if (characterList.isEmpty()) {
            throw new IllegalArgumentException("characterList must not be empty.");
        }
    }
}
