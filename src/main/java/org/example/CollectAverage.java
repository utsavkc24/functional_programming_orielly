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
        Averager result = DoubleStream.generate(() -> ThreadLocalRandom.current().nextDouble(-Math.PI, Math.PI))
                .limit(1_000)
                .collect(() -> new Averager(),
                        (b, i) -> b.include(i),
                        (b1, b2) -> b1.merge(b2));

        System.out.println("Average is " + result.get());
    }
}