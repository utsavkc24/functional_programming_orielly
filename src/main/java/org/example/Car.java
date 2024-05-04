package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Data
@AllArgsConstructor
public class Car {
    private final int gasLevel;
    private final String color;
    private final List<String> passengers;
    private final List<String> trunkContents;

    public static Car withGasColorPassengers(int gas, String color, String... passengers) {
        List<String> p = Collections.unmodifiableList(Arrays.asList(passengers));
        return new Car(gas, color, p, null);
    }

    @FunctionalInterface
    interface Criterion<E> {
        boolean test(E c);
    }

    public static Criterion<Car> getRedCarCriterion() {
        return RED_CAR_CRITERION;
    }

    private static final Criterion<Car> RED_CAR_CRITERION = c -> c.color.equals("Red");

    static class BlackCarCriterion implements Criterion<Car> {

        @Override
        public boolean test(Car c) {
            return c.color.equals("Black");
        }
    }

    static class GasLevelCarCriterion implements Criterion<Car> {

        private int threshold;

        public GasLevelCarCriterion(int threshold) {
            this.threshold = threshold;
        }

        @Override
        public boolean test(Car c) {
            return c.gasLevel >= threshold;
        }
    }

    public static Comparator<Car> getGasLevelComparator(){
        return GAS_LEVEL_COMPARATOR;
    }
    private static final Comparator<Car> GAS_LEVEL_COMPARATOR = (c1, c2) -> c1.gasLevel - c2.gasLevel;

}