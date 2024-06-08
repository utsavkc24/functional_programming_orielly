package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class SuperIterable<E> implements Iterable<E> {

    private Iterable<E> self;

    public SuperIterable(Iterable<E> self) {
        this.self = self;
    }

    @Override
    public Iterator<E> iterator() {
        return self.iterator();
    }

    //1. Implementing Stream filter method
    public SuperIterable<E> filter(Predicate<E> pred) {
        List<E> output = new ArrayList<>();
        for (E e : self) {
            if (pred.test(e)) {
                output.add(e);
            }
        }
        return new SuperIterable<>(output);
    }

    //2. Implementing Stream map method.
    public <F> SuperIterable<F> map(Function<E, F> op) {
        List<F> output = new ArrayList<>();
        self.forEach(e -> output.add(op.apply(e)));

        return new SuperIterable<>(output);
    }


    //3. Flatmap
    public <F> SuperIterable<F> flatMap(Function<E, SuperIterable<F>> op) {
        List<F> results = new ArrayList<>();
        self.forEach(e -> op.apply(e).forEach(f -> results.add(f)));
        return new SuperIterable<>(results);
    }

    public static void main(String[] args) {
        SuperIterable<String> strings = new SuperIterable<>(
                Arrays.asList("LightCoral", "pink", "Orange", "Gold", "plum", "Blue", "limegreen")
        );


        //1. filter
        for (String s : strings) {
            System.out.println("> " + s);
        }

        SuperIterable<String> upperCase = strings.filter(s -> Character.isUpperCase(s.charAt(0)));

        System.out.println("-------------------------------------------------");
        for (String s : upperCase) {
            System.out.println("> " + s);
        }


        System.out.println("--------------------MAP------------------");


        //2. Map

        strings.forEach(s -> System.out.println("> " + s));

        System.out.println("-------------------------------------------------");
        strings.map(s -> s.toUpperCase())
                .forEach(s -> System.out.println(s));


        //3. Using Car list
        SuperIterable<Car> carIter = new SuperIterable<>(
                Arrays.asList(
                        Car.withGasColorPassengers(6, "Red", "Fred", "Jim", "Sheila"),
                        Car.withGasColorPassengers(3, "Octarine", "Rincewind", "Ridcully"),
                        Car.withGasColorPassengers(9, "Black", "Weatherwax", "Magrat"),
                        Car.withGasColorPassengers(7, "Green", "Valentine", "Gillian", "Anne", "Dr. Mahmoud"),
                        Car.withGasColorPassengers(6, "Red", "Ender", "Hyrum", "Locke", "Bonzo")
                )
        );
        carIter
                .filter(c -> c.getGasLevel() >= 6)
                .map(c -> c.getPassengers().get(0) + " is driving with lot of fuel")
                .forEach(c -> System.out.println(c));

        //4. Change the car's gas level using map
        // Solution - we cannot use setGasLevel method inside map method, as map method expect as return type
        // So, we will create a new method inside Car POJO class that returns new modified car object

        carIter.forEach(c -> System.out.println(c));
        System.out.println("--------------------------------------------------");
        carIter.map(c -> c.changeGasFuelLevel(3)).forEach(c -> System.out.println(c));


        System.out.println("-------------------------------------------------");

        carIter
                .filter(c -> c.getPassengers().size() > 3)
                .flatMap(c -> new SuperIterable<>(c.getPassengers()))
                .map(s -> s.toUpperCase())
                .forEach(s -> System.out.println(s));


        System.out.println("-------------------------------------------------");

        carIter.map(Car::getGasLevel).forEach(System.out::println);
    }


}