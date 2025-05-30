package eu.oberon.oss.util.test;

import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BaseGeneratorTest {

    @Test
    void createNonSecureRandomGeneratorTest() {
        BaseGenerator baseGenerator;

        baseGenerator = new BaseGenerator(false);
        assertFalse(baseGenerator.isSecureRandom());

        baseGenerator = new BaseGenerator();
        assertFalse(baseGenerator.isSecureRandom());

    }

    @Test
    void createNonSecureRandomGeneratorTestWithSeed() {
        BaseGenerator baseGenerator = new BaseGenerator(1234567890L);
        assertFalse(baseGenerator.isSecureRandom());
    }

    @Test
    void createSecureRandomGeneratorTest() {
        BaseGenerator baseGenerator = new BaseGenerator(true);
        assertTrue(baseGenerator.isSecureRandom());
    }

    @Test
    void createSecureRandomGeneratorTestWithSeed() {
        BaseGenerator baseGenerator = new BaseGenerator("1234567890".getBytes(StandardCharsets.UTF_8));
        assertTrue(baseGenerator.isSecureRandom());
    }
}