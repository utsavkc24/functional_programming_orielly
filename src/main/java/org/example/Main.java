package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

interface CarCriterion{
    boolean test(Car c);
}

class RedCarCriterion implements CarCriterion{

    @Override
    public boolean test(Car c) {
        return c.getColor().equals("Red");
    }
}

class BlackCarCriterion implements CarCriterion{

    @Override
    public boolean test(Car c) {
        return c.getColor().equals("Black");
    }
}
class GasLevelCarCriterion implements  CarCriterion{

    private int threshold;

    public GasLevelCarCriterion(int threshold){
        this.threshold = threshold;
    }

    @Override
    public boolean test(Car c) {
        return c.getGasLevel() >= threshold;
    }
}
public class Main {
    public static void main(String[] args) {
        List<Car> cars = Arrays.asList(
                Car.withGasColorPassengers(6, "Red", "Fred", "Jim", "Sheila"),
                Car.withGasColorPassengers(3, "Octarine", "Rincewind", "Ridcully"),
                Car.withGasColorPassengers(9, "Black", "Weatherwax", "Magrat"),
                Car.withGasColorPassengers(7, "Green", "Valentine", "Gillian", "Anne", "Dr. Mahmoud"),
                Car.withGasColorPassengers(6, "Red", "Ender", "Hyrum", "Locke", "Bonzo")
        );
//        showAll(cars);

        showAll(filter(cars, new GasLevelCarCriterion(7)));
//
//        showAll(filter(cars, Car.getGasLevelCarCriterion(7)));
//
//        cars.sort(Car.getFuelComparator());
    }

    public static <E> void showAll(List<E> lc) {
        for (E c : lc) {
            System.out.println(c);
        }
        System.out.println("-------------------------------------");
    }

    public static List<Car> filter(Iterable<Car> lc, CarCriterion criterion) {
        List<Car> rv = new ArrayList<>();
        for (Car c : lc) {
            if (criterion.test(c)) {
                rv.add(c);
            }
        }
        return rv;
    }
}

