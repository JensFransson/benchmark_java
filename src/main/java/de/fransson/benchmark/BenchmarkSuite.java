package de.fransson.benchmark;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nonnull;

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
        log.info("run() - ENTER");
        Benchmark benchmark = new Benchmark();
        benchmark.run(new ListSorter<>());
        benchmark.run(new StreamSorter<>());
        benchmark.run(new ParallelStreamSorter<>());
        log.info("run() - LEAVE");
    }

}
