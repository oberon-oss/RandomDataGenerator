package eu.oberon.oss.util.test;

import lombok.Getter;

import java.security.SecureRandom;
import java.util.Random;
import java.util.random.RandomGenerator;

public class BaseGenerator {
    @Getter
    private final RandomGenerator randomGenerator;

    public BaseGenerator() {
        randomGenerator = new Random();
    }

    public BaseGenerator(boolean useSecureRandom) {
        randomGenerator = useSecureRandom ? new SecureRandom() : new Random();
    }

    public BaseGenerator(long seed) {
        randomGenerator = new Random(seed);
    }

    public BaseGenerator(byte[] seed) {
        randomGenerator = new SecureRandom(seed);
    }

    public boolean isSecureRandom() {
        return randomGenerator instanceof SecureRandom;
    }

}
