package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Data @AllArgsConstructor
public class Car {
    private final int gasLevel;
    private final String color;
    private final List<String> passengers;
    private final List<String> trunkContents;

    public static Car withGasColorPassengers(int gas, String color, String... passengers) {
        List<String> p = Collections.unmodifiableList(Arrays.asList(passengers));
        return new Car(gas, color, p, null);
    }

    interface CarCriterion{
        boolean test(Car c);
    }

    static class RedCarCriterion implements CarCriterion{

        @Override
        public boolean test(Car c) {
            return c.getColor().equals("Red");
        }
    }

    public static CarCriterion getRedCarCriterion(){
        return RED_CAR_CRITERION;
    }

    private static final CarCriterion RED_CAR_CRITERION = new RedCarCriterion();
    static class BlackCarCriterion implements CarCriterion{

        @Override
        public boolean test(Car c) {
            return c.color.equals("Black");
        }
    }
    static class GasLevelCarCriterion implements  CarCriterion{

        private int threshold;

        public GasLevelCarCriterion(int threshold){
            this.threshold = threshold;
        }

        @Override
        public boolean test(Car c) {
            return c.gasLevel >= threshold;
        }
    }
}