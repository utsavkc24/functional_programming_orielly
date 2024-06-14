package org.example;

import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.DoubleStream;

class Averager {
    private double total;
    private long count;

    public Averager() {
    }

    public void include(double d) {
        total += d;
        count++;
    }

    public void merge(Averager other) {
        total += other.total;
        count += other.count;
    }

    public double get() {
        return total / count;
    }
}

public class CollectAverage {
    public static void main(String[] args) {
        long start = System.nanoTime();
        Averager result = DoubleStream.generate(() -> ThreadLocalRandom.current().nextDouble(-Math.PI, Math.PI))
                .parallel()
                .limit(4_000_000_000L)
                .collect(() -> new Averager(),
                        (b, i) -> b.include(i),
                        (b1, b2) -> b1.merge(b2));

        long end = System.nanoTime();
        System.out.println("Average is " + result.get() + " - computation took " + (end - start) / 1_000_000 + " ms");


        long start_non_parallel = System.nanoTime();
        Averager result_non_parallel = DoubleStream.generate(() -> ThreadLocalRandom.current().nextDouble(-Math.PI, Math.PI))
                .limit(4_000_000_000L)
                .collect(() -> new Averager(),
                        (b, i) -> b.include(i),
                        (b1, b2) -> b1.merge(b2));

        long end_non_parallel = System.nanoTime();
        System.out.println("Average is " + result_non_parallel.get() + " - computation took " + (end_non_parallel - start_non_parallel) / 1_000_000 + " ms");
    }
}