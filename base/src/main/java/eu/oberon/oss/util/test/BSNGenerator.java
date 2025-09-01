package eu.oberon.oss.util.test;

import eu.oberon.oss.tools.validators.proofs.AvailableElevenProofs;

/**
 * a
 * Creates a single or list of 'Burger Service Nummer' (Dutch equivalent of a social security number) strings.
 *
 * @author TigerLilly64
 * @since 1.0.0
 */
public class BSNGenerator extends DefaultElevenProofGenerator {
    /**
     * The lowest possible valid BSN number
     */
    public static final int MINIMUM_BSN_NUMBER = 10_000_000;
    public static final int MAXIMUM_BSN_NUMBER = 999_999_999;


    /**
     * Default constructor
     *
     * @since 1.0.0
     */
    public BSNGenerator() {
        super(MINIMUM_BSN_NUMBER, MAXIMUM_BSN_NUMBER, AvailableElevenProofs.BSN);
    }

}
