package de.fransson.benchmark;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nonnull;

import static java.lang.Runtime.getRuntime;
import static java.lang.String.format;

/**
 * @author Jens Fransson
 * @since 21.11.2020
 */
public class BenchmarkSuite {

    private static final Logger log = LogManager.getLogger(BenchmarkSuite.class);

    public static void main(@Nonnull String[] arguments) {
        BenchmarkSuite suite = new BenchmarkSuite();
        suite.run();
    }

    public void run() {
        log.info("run() - Starting Benchmark Suite");
        log.info("run() - CPU - Number of available processors: {}",
                 () -> format("%,d", getRuntime().availableProcessors()));
        Benchmark benchmark = new Benchmark();
        benchmark.run(new ListSorter<>());
        benchmark.run(new StreamSorter<>());
        benchmark.run(new ParallelStreamSorter<>());
        log.info("run() - Benchmark Suite complete.");
    }

}
