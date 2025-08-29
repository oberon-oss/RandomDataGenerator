package eu.oberon.oss.util.test;

import java.util.List;

/**
 * Basic generator contract. allows
 *
 * @param <S> The class type of the data objects being generated.
 *
 * @author TigerLilly64
 * @since 1.0.0
 */
public interface BaseGenerator<S> {
    /**
     * Generates a single value.
     *
     * @param generateValidItem Specifies if valid (<b>true</b>) or invalid (<b>false</b>) data must be generated
     *
     * @return The generated value.
     *
     * @since 1.0.0
     */
    S generateSingleValue(boolean generateValidItem);

    /**
     * Generates a list of 0 or more values.
     *
     * @param count              Number of entries to generate
     * @param generateValidItems Specifies if valid (<b>true</b>) or invalid (<b>false</b>) data must be generated
     *
     * @return A list of values.
     *
     * @since 1.0.0
     */
    List<S> generateValueList(int count, boolean generateValidItems);
}
