package eu.oberon.oss.util.test;

import eu.oberon.oss.tools.validators.pc.NLPostalCodeChecker;
import eu.oberon.oss.util.test.raw.TextGenerator;
import eu.oberon.oss.util.test.raw.TextGeneratorSettings;

import java.util.List;

import static eu.oberon.oss.util.test.raw.TextGenerator.ALPHABETICAL;

public class PostCodeGenerator extends AbstractBaseGenerator<String> {
    private final TextGenerator textStringGenerator;
    private final NLPostalCodeChecker checker = new NLPostalCodeChecker();

    private final TextGeneratorSettings firstChar;
    private final TextGeneratorSettings secondChar;


    public PostCodeGenerator() {
        super(true);
        textStringGenerator = new TextGenerator(getRandomGenerator());
        firstChar = TextGenerator.getBuilder()
                .setMaxLength(1)
                .addCharacterList(ALPHABETICAL)
                .build();
        secondChar = TextGenerator.getBuilder()
                .setMaxLength(1)
                .addCharacterList(ALPHABETICAL.replace("S", ""))
                .build();
    }

    @Override
    public String generateSingleValue(boolean generateValidItem) {
        boolean validFound = false;

        while (!validFound) {
            int number = getRandomGenerator().nextInt(1000, 9999);
        }
        return "";
    }

    @Override
    public List<String> generateValueList(int count, boolean generateValidItems) {
        return List.of();
    }


}
