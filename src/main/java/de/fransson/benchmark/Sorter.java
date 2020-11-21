package de.fransson.benchmark;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * @author Jens Fransson
 * @since 21.11.2020
 */
public interface Sorter<T extends Comparable<T>> {

    @Nonnull
    List<T> sort(@Nonnull List<T> list);

}
