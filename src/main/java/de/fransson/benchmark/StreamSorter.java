package de.fransson.benchmark;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Jens Fransson
 * @since 21.11.2020
 */
public class StreamSorter<T extends Comparable<T>> implements Sorter<T> {

    @Nonnull
    @Override
    public List<T> sort(@Nonnull List<T> list) {
        Stream<T> stream = list.stream();
        Stream<T> sorted = stream.sorted();
        return sorted.collect(Collectors.toList());
    }
}
