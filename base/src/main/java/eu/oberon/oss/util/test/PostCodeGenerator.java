package eu.oberon.oss.util.test;

import eu.oberon.oss.tools.validators.pc.NLPostalCodeChecker;
import eu.oberon.oss.util.test.raw.TextGenerator;
import eu.oberon.oss.util.test.raw.TextGeneratorSettings;

import java.util.ArrayList;
import java.util.List;

import static eu.oberon.oss.util.test.raw.TextGenerator.ALPHABETICAL;
import static eu.oberon.oss.util.test.raw.TextGenerator.DIGITS;

public class PostCodeGenerator extends AbstractBaseGenerator<String> {
    private final TextGenerator generator;
    private final NLPostalCodeChecker checker = new NLPostalCodeChecker();

    private static final TextGeneratorSettings VALID_FIRST_CHAR;
    private static final TextGeneratorSettings VALID_SECOND_CHAR;
    private static final TextGeneratorSettings INVALID_CHAR;

    static {
        VALID_FIRST_CHAR = TextGenerator.getBuilder()
                .setMaxLength(1)
                .addCharacterList(ALPHABETICAL)
                .build();
        VALID_SECOND_CHAR = TextGenerator.getBuilder()
                .setMaxLength(1)
                .addCharacterList(ALPHABETICAL.replaceAll("[ADS]", ""))
                .build();
        INVALID_CHAR = TextGenerator.getBuilder()
                .setMaxLength(1)
                .addCharacterList(" 0123456789!@#$%^&*()_+=-{}:<>/?|\\[];',.").addCharacterList(DIGITS)
                .build();
    }

    public PostCodeGenerator() {
        super(true);
        generator = new TextGenerator(getRandomGenerator());
    }

    @Override
    public String generateSingleValue(boolean generateValidItem) {
        String postCode;
        if (generateValidItem) {
            postCode = String.format("%04d %S%S",
                    getRandomGenerator().nextInt(1000, 9999),
                    generator.createText(VALID_FIRST_CHAR),
                    generator.createText(VALID_SECOND_CHAR)
            );
        } else {
            do {
                int number = getRandomGenerator().nextBoolean()
                        ? getRandomGenerator().nextInt(1000, 9999)
                        : getRandomGenerator().nextInt(0, 999);
                String s1 = getRandomGenerator().nextBoolean()
                        ? generator.createText(INVALID_CHAR)
                        : generator.createText(VALID_FIRST_CHAR);
                String s2 = getRandomGenerator().nextBoolean()
                        ? generator.createText(INVALID_CHAR)
                        : generator.createText(VALID_SECOND_CHAR);
                postCode = String.format("%04d %S%S", number, s1, s2);

            } while (checker.isValid(postCode));
        }

        return postCode;
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
