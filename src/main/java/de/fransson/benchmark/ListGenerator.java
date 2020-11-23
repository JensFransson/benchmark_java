package de.fransson.benchmark;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nonnull;
import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import static java.lang.String.format;

/**
 * @author Jens Fransson
 * @since 21.11.2020
 */
public class ListGenerator<T extends Comparable<T>> {

    private static final Logger log = LogManager.getLogger(ListGenerator.class);


    private final int size;

    @Nonnull
    private final Supplier<T> elementGenerator;


    public ListGenerator(int size, @Nonnull Supplier<T> elementGenerator) {
        this.size = size;
        this.elementGenerator = elementGenerator;
    }

    @Nonnull
    public List<T> generateRandomList() {
        log.info("generateRandomList - Building {} list elements ...", () -> format("%,d", size));
        ZonedDateTime start = ZonedDateTime.now();

        List<T> data = generateRandomListOnly();

        ZonedDateTime end = ZonedDateTime.now();
        Duration duration = Duration.between(start, end);

        log.info("generateRandomList - {} list elements built in {}.", () -> format("%,d", size), () -> duration);
        log.info("generateRandomList - First list element: '{}'.", data.get(0));
        log.info("generateRandomList - Last list element: '{}'.", data.get(data.size() - 1));
        return data;
    }

    @Nonnull
    private List<T> generateRandomListOnly() {
        List<T> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            T element = elementGenerator.get();
            list.add(element);
        }
        return list;
    }

}
