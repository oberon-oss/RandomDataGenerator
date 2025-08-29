package eu.oberon.oss.util.test.raw;

import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.random.RandomGenerator;

/**
 * Generic text data generator
 * Notes:
 * <ul>
 * <li>If {@link TextGeneratorSettings#getMinLength()} returns a different value than
 * {@link TextGeneratorSettings#getMaxLength()} , a random number will be generated between these to values. The
 * generated value is provided by the specified randomizer ({@link RandomGenerator#nextInt(int, int)})
 *
 * </li>
 * <li>
 * If the settings object's {@link TextGeneratorSettings#getCaseType() caseType} is {@literal <null>}, then the
 * generated characters are passed 'as-is'
 * </li>
 * </ul>
 *
 * @author TigerLilly64
 * @since 1.0.0
 */
@SuppressWarnings("ClassCanBeRecord")
@Log4j2
public class TextGenerator {

    /**
     * String containing alphabetical characters
     *
     * @since 1.0.0
     */
    public static final String ALPHABETICAL = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    /**
     * String containing digits
     *
     * @since 1.0.0
     */
    public static final String DIGITS = "0123456789";
    /**
     * String containing non-alphabetic characters
     *
     * @since 1.0.0
     */
    public static final String NON_ALPHABETIC = "!@#$%^&*()_+=-{}:<>/?|[];',.\\\"";

    /**
     * String containing hexadecimal characters
     *
     * @since 1.0.0
     */
    @SuppressWarnings("unused")
    public static final String HEX_CHARACTERS = "0123456789ABCDEF";

    private final RandomGenerator randomGenerator;

    /**
     * Creates a new generator instance.
     *
     * @param randomGenerator the randomizer to be used for generating text data.
     *
     * @since 1.0.0
     */
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

    /**
     * Returns a builder that can be used to create text generator settings.
     *
     * @return A builder instance
     *
     * @since 1.0.0
     */
    public static TextGeneratorSettingsBuilder getBuilder() {
        return new TextGeneratorSettingsBuilder();
    }

    /**
     * Create textual data according to the specified TextGeneratorSettings.
     *
     * @param settings The settings describing the data to be generated.
     *
     * @return the generated text data
     *
     * @since 1.0.0
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
