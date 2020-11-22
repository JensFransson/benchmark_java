package de.fransson.benchmark;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nonnull;
import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static java.lang.String.format;

/**
 * @author Jens Fransson
 * @since 21.11.2020
 */
public class DataGenerator {

    private static final Logger log = LogManager.getLogger(DataGenerator.class);


    private final int size;


    public DataGenerator(int size) {
        this.size = size;
    }

    @Nonnull
    public List<String> generateRandomList() {
        log.info("generateRandomList - Building {} data elements ...", () -> format("%,d", size));
        ZonedDateTime start = ZonedDateTime.now();

        List<String> data = generateRandomListOnly();

        ZonedDateTime end = ZonedDateTime.now();
        Duration duration = Duration.between(start, end);

        log.info("generateRandomList - {} data elements built in {}.", () -> format("%,d", size), () -> duration);
        log.info("generateRandomList - First list element: '{}'.", data.get(0));
        return data;
    }

    @Nonnull
    private List<String> generateRandomListOnly() {
        List<String> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            UUID uuid = UUID.randomUUID();
            list.add(uuid.toString());
        }
        return list;
    }

}
