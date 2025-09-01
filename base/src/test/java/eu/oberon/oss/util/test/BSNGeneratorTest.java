package eu.oberon.oss.util.test;

import eu.oberon.oss.tools.validators.proofs.AvailableElevenProofs;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BSNGeneratorTest {
    @Test
    void testBounds() {
        ElevenProofGenerator<String,Integer> generator = new BSNGenerator();

        assertEquals(BSNGenerator.MINIMUM_BSN_NUMBER,generator.getLowerBound());
        assertEquals(BSNGenerator.MAXIMUM_BSN_NUMBER,generator.getUpperBound());
    }

    static Stream<Arguments> bsnNumberList() {
        List<Arguments> list = new ArrayList<>();

        for (String bsn : new BSNGenerator().generateValueList(100, true)) {
            list.add(Arguments.of(true, bsn));
        }
        for (String bsn : new BSNGenerator().generateValueList(100, false)) {
            list.add(Arguments.of(false, bsn));
        }
        return list.stream();
    }

    @ParameterizedTest
    @MethodSource
    void bsnNumberList(boolean valid, String bsn) {
        assertEquals(valid, AvailableElevenProofs.BSN.isValid(bsn));
    }
}