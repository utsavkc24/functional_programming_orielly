package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.*;
import java.util.function.Predicate;

@Data
@AllArgsConstructor
public class Car {
    private final int gasLevel;
    private final String color;
    private final List<String> passengers;
    private final List<String> trunkContents;


    public Car changeGasFuelLevel(int g) {
        return new Car(gasLevel + g, color, passengers, trunkContents);
    }

    public static Car withGasColorPassengers(int gas, String color, String... passengers) {
        List<String> p = Collections.unmodifiableList(Arrays.asList(passengers));
        return new Car(gas, color, p, null);
    }


    public static Car withGasColorPassengersAndTrunk(int gas, String color, String... passengers) {
        List<String> p = Collections.unmodifiableList(Arrays.asList(passengers));
        Car self = new Car(gas, color, p, Arrays.asList("jack", "wrench", "spare wheel"));
        return self;
    }


    public static Predicate<Car> getRedCarCriterion() {
        return RED_CAR_CRITERION;
    }

    private static final Predicate<Car> RED_CAR_CRITERION = c -> c.color.equals("Red");

    static class BlackCarCriterion implements Predicate<Car> {

        @Override
        public boolean test(Car c) {
            return c.color.equals("Black");
        }
    }

    public static Predicate<Car> getGasLevelCriterion(int threshold) {
        return c -> c.gasLevel >= threshold;
    }

    public static Predicate<Car> getColorCriterion(String... color) {
        Set<String> set = new HashSet<>(Arrays.asList(color));
        return c -> set.contains(c.getColor());
    }


    // combining existing behaviour to new behaviour
    //1. Invert the input behaviour
    public static <E> Predicate<E> getInvertedBehaviour(Predicate<E> criterion) {
        return x -> !criterion.test(x);
    }

}