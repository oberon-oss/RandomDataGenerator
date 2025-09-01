package eu.oberon.oss.util.test;

import eu.oberon.oss.tools.validators.BaseValidator;
import lombok.extern.log4j.Log4j2;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Default {@link ElevenProofGenerator} implementation, using {@link Integer} to describe the boundaries, and generates
 * objects of type {@link String}
 *
 * @author TigerLilly64
 * @since 1.0.0
 */
@Log4j2
public abstract class DefaultElevenProofGenerator extends AbstractBaseGenerator<String> implements ElevenProofGenerator<String, Integer> {
    private final int lowerBound;
    private final int upperBound;
    private final BaseValidator<String> validator;

    /**
     * Constructs a new generator using a user supplied randomGenerator generator.
     *
     * @param randomGenerator The user supplied generator
     * @param lowerBound      The lower bound (inclusive) of the generated data.
     * @param upperBound      The upper bound (inclusive) of the generated data.
     * @param validator       The validator to use to determine if generated value(s) are valid or not.
     *
     * @since 1.0.0
     */
    protected DefaultElevenProofGenerator(int lowerBound, int upperBound, BaseValidator<String> validator,
                                  Random randomGenerator) {
        super(randomGenerator);
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        this.validator = validator;
    }

    /**
     * Constructs a new generator using an instance of {@link SecureRandom} generator.
     *
     * @param lowerBound The lower bound (inclusive) of the generated data.
     * @param upperBound The upper bound (inclusive) of the generated data.
     * @param validator  The validator to use to determine if generated value(s) are valid or not.
     *
     * @since 1.0.0
     */
    protected DefaultElevenProofGenerator(int lowerBound, int upperBound, BaseValidator<String> validator) {
        this(lowerBound, upperBound, validator, new SecureRandom());
    }

    @Override
    public Integer getLowerBound() {
        return lowerBound;
    }

    @Override
    public Integer getUpperBound() {
        return upperBound;
    }

    @Override
    public String generateSingleValue(boolean generateValidItem) {
        String bsn;
        do {
            bsn = String.format("%09d", getRandomGenerator().nextInt(lowerBound, upperBound + 1));
        } while ((generateValidItem != validator.isValid(bsn)));

        LOGGER.debug("{} bsn: {}", generateValidItem ? "valid" : "invalid", bsn);
        return bsn;
    }

    @Override
    public List<String> generateValueList(int count, boolean generateValidItems) {
        List<String> result = new ArrayList<>();

        while (result.size() != count) {
            result.add(generateSingleValue(generateValidItems));
        }
        return result;
    }
}
