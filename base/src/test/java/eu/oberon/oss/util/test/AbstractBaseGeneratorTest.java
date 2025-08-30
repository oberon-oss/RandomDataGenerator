package eu.oberon.oss.util.test;

import org.junit.jupiter.api.Test;

import java.security.SecureRandom;
import java.util.List;
import java.util.Random;
import java.util.random.RandomGenerator;

import static org.junit.jupiter.api.Assertions.*;


class AbstractBaseGeneratorTest {

    @Test
    void testDefaultConstructor() {
        TestGenerator generator = new TestGenerator();
        assertInstanceOf(SecureRandom.class, generator.getRandomGenerator());
    }

    @Test
    void testDefaultConstructorWithSeed() {
        byte[] seed  = "seed".getBytes();
        TestGenerator generator = new TestGenerator(seed);
        assertInstanceOf(SecureRandom.class, generator.getRandomGenerator());
    }

    @Test
    void testDefaultConstructorWithBoolean() {
        TestGenerator generator;
        generator = new TestGenerator(true);
        assertInstanceOf(SecureRandom.class, generator.getRandomGenerator());

        generator = new TestGenerator(false);
        assertInstanceOf(Random.class, generator.getRandomGenerator());
    }

    @Test
    void testDefaultConstructorWithGeneratorInstance() {
        TestGenerator generator;
        generator = new TestGenerator(new SecureRandom());
        assertInstanceOf(SecureRandom.class, generator.getRandomGenerator());

        generator = new TestGenerator(new Random());
        assertInstanceOf(Random.class, generator.getRandomGenerator());
    }

    private static class TestGenerator extends AbstractBaseGenerator<String> {

        public TestGenerator() {
            super();
        }

        public TestGenerator(byte[] seed) {
            super(seed);
        }

        public TestGenerator(boolean useSecureRandom) {
            super(useSecureRandom);
        }

        public <G extends RandomGenerator> TestGenerator(G randomGenerator) {
            super(randomGenerator);
        }

        @Override
        public String generateSingleValue(boolean generateValidItem) {
            return "single value";
        }

        @Override
        public List<String> generateValueList(int count, boolean generateValidItems) {
            return List.of("single value");
        }
    }

}


