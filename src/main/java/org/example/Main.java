package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


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

//        showAll(filter(cars, new Car.GasLevelCarCriterion(7)));
//
//        showAll(filter(cars, Car.getGasLevelCarCriterion(7)));
//
//        cars.sort(Car.getFuelComparator());

//        showAll(filter(cars, Car.getRedCarCriterion()));
//        showAll(getByCriterion(cars, Car.getGasLevelCriterion(5)));

//        showAll(getByCriterion(cars, Car.getColorCriterion("Red", "Black")));
        showAll(getByCriterion(cars, Car.getInvertedBehaviour(Car.getRedCarCriterion())));
    }


    public static <E> void showAll(List<E> lc) {

        for (E c : lc) {
            System.out.println(c);
        }
        System.out.println("-------------------------------------");
    }

    public static <E> List<E> filter(Iterable<E> lc, Car.Criterion<E> criterion) {
        List<E> rv = new ArrayList<>();
        for (E c : lc) {
            if (criterion.test(c)) {
                rv.add(c);
            }
        }
        return rv;
    }

    public static <E> List<E> getByCriterion(Iterable<E> in, Car.Criterion<E> crit) {
        List<E> output = new ArrayList<>();

        for (E c : in) {
            if (crit.test(c)) {
                output.add(c);
            }
        }
        return output;
    }
}

