package eu.oberon.oss.util.test.raw;

import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.random.RandomGenerator;

@Log4j2
public class TextGenerator {
    private final RandomGenerator randomGenerator;

    public TextGenerator(@NotNull RandomGenerator randomGenerator) {
        this.randomGenerator = randomGenerator;
    }

    /**
     * Creates a list of characters form the provided source string.
     *
     * @param source The source string containing the characters to store in the list.
     *
     * @return An unmodifiable list of the characters from the source string
     *
     * @since 1.0.0
     */
    public static List<Character> createCharacterListFromString(String source) {
        List<Character> chars = new ArrayList<>(source.length());
        for (char c : source.toCharArray()) {
            chars.add(c);
        }
        return List.copyOf(chars);
    }

    public static final String ALPHABETICAL = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String DIGITS = "0123456789";
    public static final String HEX_CHARACTERS = "0123456789ABCDEF";
    public static final String NON_ALPHABETIC = "!@#$%^&*()_+=-{}:<>/?|[];',.\\\"";

    /**
     *
     * @return
     */
    public static TextGeneratorSettingsBuilder getBuilder() {
        return new TextGeneratorSettingsBuilder();
    }

    /**
     *
     * @param settings
     *
     * @return
     */
    public String createText(TextGeneratorSettings settings) {

        int lengthToGenerate = settings.getMinLength() != settings.getMaxLength()
                ? randomGenerator.nextInt(settings.getMinLength(), settings.getMaxLength())
                : settings.getMaxLength();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < lengthToGenerate; i++) {

            Character c = settings.getCharacterList().get(randomGenerator.nextInt(settings.getCharacterList().size()));
            if (settings.getCaseType() != null) {
                boolean upperCase;
                if (settings.getCaseType() == CaseType.RANDOM) {
                    upperCase = randomGenerator.nextBoolean();
                } else {
                    upperCase = settings.getCaseType() == CaseType.UPPER;
                }
                sb.append(upperCase ? Character.toUpperCase(c) : Character.toLowerCase(c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

}
