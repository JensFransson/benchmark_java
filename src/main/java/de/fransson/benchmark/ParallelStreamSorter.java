package de.fransson.benchmark;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Jens Fransson
 * @since 21.11.2020
 */
public class ParallelStreamSorter<T extends Comparable<T>> implements Sorter<T> {

    @Nonnull
    @Override
    public List<T> sort(@Nonnull List<T> list) {
        Stream<T> parallelStream = list.parallelStream();
        Stream<T> sorted = parallelStream.sorted();
        return sorted.collect(Collectors.toList());
    }
}
