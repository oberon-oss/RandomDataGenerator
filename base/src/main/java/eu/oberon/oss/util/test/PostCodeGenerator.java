package eu.oberon.oss.util.test;

import eu.oberon.oss.tools.validators.pc.NLPostalCodeChecker;
import eu.oberon.oss.util.test.raw.TextGenerator;
import eu.oberon.oss.util.test.raw.TextGeneratorSettings;

import java.util.ArrayList;
import java.util.List;

import static eu.oberon.oss.util.test.raw.TextGenerator.*;

/**
 * Generates Dutch 'postcode' data. The returned postcode will always be formatted like this:
 * 9999sCC where
 * <ol>
 *     <li>9999 - four digits </li>
 *     <li>s    - a single space</li>
 *     <li>CC   - Two characters</li>
 * </ol>
 * <p>
 * When valid postcode data is requested, the following applies:
 * <ol>
 *     <li>9999 - generated value in range 1000-9999 (inclusive) </li>
 *     <li>s    - a single space</li>
 *     <li>CC   - Two characters, in the range AA-ZZ, but excluding SA, SD and SS</li>
 * </ol>
 * <p>
 *  When INVALID postcode data is requested, the generated data can be partially valid (see above), but in addition,
 *  the generated postcode will include 1 or more of the following invalid data:
 * <ol>
 *     <li>9999 - generated value in range 0000-0999 (inclusive) </li>
 *     <li>s    - single character in the range A-Z, Digits or other characters</li>
 *     <li>CC   - Two characters, in the range AA-ZZ, Digits or other characters</li>
 * </ol>
 *
 * @author TigerLilly64
 * @since 1.0.0
 */
public class PostCodeGenerator extends AbstractBaseGenerator<String> {
    private final TextGenerator generator;
    private final NLPostalCodeChecker checker = new NLPostalCodeChecker();

    private static final TextGeneratorSettings VALID_FIRST_CHAR;
    private static final TextGeneratorSettings VALID_SECOND_CHAR;
    private static final TextGeneratorSettings INVALID_CHAR;

    static {
        VALID_FIRST_CHAR = TextGeneratorSettings.getBuilder()
                .setMaxLength(1)
                .addCharacterList(ALPHABETICAL)
                .build();
        VALID_SECOND_CHAR = TextGeneratorSettings.getBuilder()
                .setMaxLength(1)
                .addCharacterList(ALPHABETICAL.replaceAll("[ADS]", ""))
                .build();
        INVALID_CHAR = TextGeneratorSettings.getBuilder()
                .setMaxLength(1)
                .addCharacterList(NON_ALPHABETIC).addCharacterList(DIGITS).addCharacterList(ALPHABETICAL)
                .build();
    }

    /**
     * Default constructor
     *
     * @since 1.0.0
     */
    public PostCodeGenerator() {
        super(true);
        generator = new TextGenerator(getRandomGenerator());
    }

    @Override
    public String generateSingleValue(boolean generateValidItem) {
        return generateValidItem ? generateValidValue() : generateInvalidValue();
    }

    private String generateInvalidValue() {
        String work;
        do {
            int number = getRandomGenerator().nextBoolean()
                    ? getRandomGenerator().nextInt(1000, 9999)
                    : getRandomGenerator().nextInt(0, 999);
            String s1 = getRandomGenerator().nextBoolean()
                    ? generator.createText(INVALID_CHAR)
                    : generator.createText(VALID_FIRST_CHAR);
            String space = getRandomGenerator().nextBoolean()
                    ? generator.createText(INVALID_CHAR)
                    : " ";
            String s2 = getRandomGenerator().nextBoolean()
                    ? generator.createText(INVALID_CHAR)
                    : generator.createText(VALID_SECOND_CHAR);
            work = String.format("%04d%s%S%S", number, s1, space, s2);

        } while (checker.isValid(work));
        return work;
    }

    private String generateValidValue() {
        return String.format("%04d %S%S",
                getRandomGenerator().nextInt(1000, 9999),
                generator.createText(VALID_FIRST_CHAR),
                generator.createText(VALID_SECOND_CHAR)
        );
    }

    @Override
    public List<String> generateValueList(int count, boolean generateValidItems) {
        List<String> list = new ArrayList<>(count);
        while (list.size() < count) {
            list.add(generateSingleValue(generateValidItems));
        }
        return list;
    }

}
