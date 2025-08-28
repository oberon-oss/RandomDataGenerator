package eu.oberon.oss.util.test;

import eu.oberon.oss.tools.validators.BaseValidator;
import eu.oberon.oss.tools.validators.pc.NLPostalCodeChecker;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PostCodeGeneratorTest {
    private static PostCodeGenerator generator;
    private static BaseValidator<String> checker;

    @BeforeAll
    static void setUp() {
        generator = new PostCodeGenerator();
        checker = new NLPostalCodeChecker();
    }

    private static Stream<Arguments> generatePostCodes(int count, boolean createValidEntries) {
        List<Arguments> list = new ArrayList<>();
        for (String s : generator.generateValueList(count, createValidEntries)) {
            list.add(Arguments.of(s));
        }
        return list.stream();
    }

    public static Stream<Arguments> validGeneratedPostcodeList() {
        return generatePostCodes(10_000, true);
    }

    @ParameterizedTest
    @MethodSource
    void validGeneratedPostcodeList(String postCode) {
        assertTrue(checker.isValid(postCode));
    }

    public static Stream<Arguments> invalidGeneratedPostcodeList() {
        return generatePostCodes(10_000, false);
    }

    @ParameterizedTest
    @MethodSource
    void invalidGeneratedPostcodeList(String postCode) {
        assertFalse(checker.isValid(postCode));
    }

}