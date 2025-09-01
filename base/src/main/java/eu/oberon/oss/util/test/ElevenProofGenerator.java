package eu.oberon.oss.util.test;

/**
 * Template for generators that create data for Eleven proof type validations
 *
 * @param <S> The object type that contains the payload (data to validate)
 * @param <B> The class type that contains the boundaries/limitations of the data being generated.
 *
 * @author TigerLilly64
 * @since 1.0.0
 */
public interface ElevenProofGenerator<S, B> extends BaseGenerator<S> {

    /**
     * Returns the lower bound of the data that can be generated (inclusive)
     *
     * @return the lower bound observed by the generator.
     *
     * @since 1.0.0
     */
    B getLowerBound();

    /**
     * Returns the upper bound of the data that can be generated (inclusive)
     *
     * @return the upper bound observed by the generator.
     *
     * @since 1.0.0
     */
    B getUpperBound();

}
