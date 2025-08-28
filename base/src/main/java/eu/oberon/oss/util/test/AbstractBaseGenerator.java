package eu.oberon.oss.util.test;

import lombok.Getter;

import java.security.SecureRandom;
import java.util.Random;
import java.util.random.RandomGenerator;

/**
 * Standard implementation of the {@link BaseGenerator} interface
 *
 * @param <S> The type of objects that the generator will provide
 *
 * @author TigerLilly64
 * @since 1.0.0
 */
public abstract class AbstractBaseGenerator<S> implements BaseGenerator<S> {
    @Getter
    private final RandomGenerator randomGenerator;

    /**
     * Creates a base generator instance, using a {@link SecureRandom} randomizer
     */
    protected AbstractBaseGenerator() {
        randomGenerator = new SecureRandom();
    }

    /**
     * Creates a base generator using a {@link SecureRandom} instance, and applying the provided seed.
     *
     * @param seed The seed bytes to initialize the secure randomizer.
     *
     * @since 1.0.0
     */
    protected AbstractBaseGenerator(byte[] seed) {
        randomGenerator = new SecureRandom(seed);
    }


    /**
     * Creates a new instance with either a secure or standard randomizer.
     *
     * @param useSecureRandom Specifies if a {@link SecureRandom} (if <b>true</b>) should be created or a
     *                        pseudo-random randomizer ({@link Random} (if <b>false</b>
     *
     * @since 1.0.0
     */
    protected AbstractBaseGenerator(boolean useSecureRandom) {
        this.randomGenerator = useSecureRandom ? new SecureRandom() : new Random();
    }

    /**
     * Creates a generator using a user provided randomizer.
     *
     * @param randomGenerator The user defined randomizer
     * @param <G>             An instance of the {@link RandomGenerator} interface.
     *
     * @since 1.0.0
     */
    protected <G extends RandomGenerator> AbstractBaseGenerator(G randomGenerator) {
        this.randomGenerator = randomGenerator;
    }
}
