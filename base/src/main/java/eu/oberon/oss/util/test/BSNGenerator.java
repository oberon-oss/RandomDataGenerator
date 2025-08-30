package eu.oberon.oss.util.test;

import eu.oberon.oss.tools.validators.proofs.AvailableElevenProofs;

import java.util.ArrayList;
import java.util.List;

/**
 * a
 * Creates a single or list of 'Burger Service Nummer' (Dutch equivalent of a social security number) strings.
 *
 * @author TigerLilly64
 * @since 1.0.0
 */
public class BSNGenerator extends AbstractBaseGenerator<String> {
    private static final int MINIMUM_BSN_NUMBER = 10_000_000;
    private static final int MAXIMUM_BSN_NUMBER = 999_999_999;

    /**
     * Default constructor
     *
     * @since 1.0.0
     */
    public BSNGenerator() {
        super();
    }

    @Override
    public String generateSingleValue(boolean generateValidItem) {
        String bsn;
        do {
            bsn = String.format("%09d", getRandomGenerator().nextInt(MINIMUM_BSN_NUMBER, MAXIMUM_BSN_NUMBER + 1));
        } while (generateValidItem != AvailableElevenProofs.BSN.isValid(bsn));
        return bsn;
    }

    @Override
    public List<String> generateValueList(int count, boolean generateValidItems) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            list.add(generateSingleValue(generateValidItems));
        }
        return list;
    }
}
