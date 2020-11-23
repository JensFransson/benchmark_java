package de.fransson.benchmark;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nonnull;
import java.util.UUID;

import static java.lang.Runtime.getRuntime;
import static java.lang.String.format;

/**
 * @author Jens Fransson
 * @since 21.11.2020
 */
public class BenchmarkSuite<T extends Comparable<T>> {

    private static final Logger log = LogManager.getLogger(BenchmarkSuite.class);

    public static final String SIZE_KEY = "size";

    public static final int DEFAULT_SIZE = 5_000_000;


    @Nonnull
    private final String name;

    @Nonnull
    private final ListGenerator<T> listGenerator;


    public static void main(@Nonnull String[] arguments) {
        int size = Integer.getInteger(SIZE_KEY, DEFAULT_SIZE);

        ListGenerator<String> stringListGenerator = new ListGenerator<>(size, () -> UUID.randomUUID().toString());
        BenchmarkSuite<String> stringSuite = new BenchmarkSuite<>("String", stringListGenerator);
        stringSuite.run();

        ListGenerator<UUID> uuidListGenerator = new ListGenerator<>(size, UUID::randomUUID);
        BenchmarkSuite<UUID> uuidSuite = new BenchmarkSuite<>("UUID", uuidListGenerator);
        uuidSuite.run();
    }

    public BenchmarkSuite(@Nonnull String name, @Nonnull ListGenerator<T> listGenerator) {
        this.name = name;
        this.listGenerator = listGenerator;
    }

    public void run() {
        log.info("run() - Starting Benchmark Suite '{}'", name);
        log.info("run() - CPU - Number of available processors: {}",
                 () -> format("%,d", getRuntime().availableProcessors()));

        Benchmark<T> benchmark = new Benchmark<>(listGenerator);
        benchmark.run(new ListSorter<>());
        benchmark.run(new StreamSorter<>());
        benchmark.run(new ParallelStreamSorter<>());
        log.info("run() - Benchmark Suite '{}' complete.\n\n", name);
    }



}
