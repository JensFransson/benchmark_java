package de.fransson.benchmark;

import javax.annotation.Nonnull;
import java.util.Comparator;
import java.util.List;

/**
 * @author Jens Fransson
 * @since 21.11.2020
 */
public class ListSorter<T extends Comparable<T>> implements Sorter<T> {

    @Nonnull
    @Override
    public List<T> sort(@Nonnull List<T> list) {
        list.sort(Comparator.naturalOrder());
        return list;
    }
}
