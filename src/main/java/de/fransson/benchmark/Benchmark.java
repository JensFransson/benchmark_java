package de.fransson.benchmark;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nonnull;
import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.List;

import static java.lang.String.format;

/**
 * @author Jens Fransson
 * @since 21.11.2020
 */
public class Benchmark<T extends Comparable<T>> {

    private static final Logger log = LogManager.getLogger(Benchmark.class);

    @Nonnull
    private final ListGenerator<T> generator;


    public Benchmark(@Nonnull ListGenerator<T> generator) {
        this.generator = generator;
    }

    public void run(@Nonnull Sorter<T> algorithm) {
        Class<?> algorithmClass = algorithm.getClass();
        String name = algorithmClass.getSimpleName();
        log.info("########### --- {} --- ###########", name);

        List<T> data = generator.generateRandomList();
        int size = data.size();
        log.info("run - Sorting {} elements with {} ...", () -> format("%,d", size), () -> name);

        ZonedDateTime start = ZonedDateTime.now();

        List<T> sortedList = algorithm.sort(data);

        ZonedDateTime end = ZonedDateTime.now();
        Duration duration = Duration.between(start, end);

        int sortedSize = sortedList.size();
        log.info("run - First element of sorted list: '{}'.", sortedList.get(0));
        log.info("run - Last element of sorted list: '{}'.", sortedList.get(sortedList.size() - 1));
        log.info("run - Sorted list size: {}", () -> format("%,d", sortedSize));
        log.info("run - Sorting duration: {}\n\n", duration);
    }

}
