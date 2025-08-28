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

    private final TextGeneratorSettings validFirstChar;
    private final TextGeneratorSettings validSecondChar;
    private final TextGeneratorSettings invalidChar;

    public PostCodeGenerator() {
        super(true);
        generator = new TextGenerator(getRandomGenerator());
        validFirstChar = TextGenerator.getBuilder().setMaxLength(1).addCharacterList(ALPHABETICAL).build();
        validSecondChar =
                TextGenerator.getBuilder().setMaxLength(1).addCharacterList(ALPHABETICAL.replaceAll("[ADS]", "")).build();

        invalidChar = TextGenerator.getBuilder()
                .setMaxLength(1)
                .addCharacterList(" !@#$%^&*()_+=-{}:<>/?|\\[];',.").addCharacterList(DIGITS)
                .build();
    }

    @Override
    public String generateSingleValue(boolean generateValidItem) {
        String postCode = null;
        if (generateValidItem) {
            postCode = String.format("%04d %S%S",
                    getRandomGenerator().nextInt(1000, 9999),
                    generator.createText(validFirstChar),
                    generator.createText(validSecondChar)
            );
        } else {
            do {
                int number = getRandomGenerator().nextBoolean()
                        ? getRandomGenerator().nextInt(1000, 9999)
                        : getRandomGenerator().nextInt(0, 999);
                String s1 = getRandomGenerator().nextBoolean()
                        ? generator.createText(invalidChar)
                        : generator.createText(validSecondChar);
                String s2 = getRandomGenerator().nextBoolean()
                        ? generator.createText(invalidChar)
                        : generator.createText(validSecondChar);
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
