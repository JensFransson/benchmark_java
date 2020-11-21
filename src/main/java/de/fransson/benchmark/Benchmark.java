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
public class Benchmark {

    private static final Logger log = LogManager.getLogger(Benchmark.class);

    public static final String SIZE_KEY = "size";

    public static final int DEFAULT_SIZE = 1_000_000;

    @Nonnull
    private final DataGenerator generator;


    public Benchmark() {
        int size = Integer.getInteger(SIZE_KEY, DEFAULT_SIZE);
        generator = new DataGenerator(size);
    }

    public void run(@Nonnull Sorter<String> algorithm) {
        List<String> data = generator.generateRandomList();

        Class<?> algorithmClass = algorithm.getClass();
        int size = data.size();
        String name = algorithmClass.getSimpleName();
        log.info("Sorting {} elements with {} ...", () -> format("%,d", size), () -> name);

        ZonedDateTime start = ZonedDateTime.now();

        List<String> sortedList = algorithm.sort(data);

        ZonedDateTime end = ZonedDateTime.now();
        Duration duration = Duration.between(start, end);

        int sortedSize = sortedList.size();
        log.info("Sorted list size: {}", () -> format("%,d", sortedSize));
        log.info("Sorting duration: {}", duration);
    }

}
