package org.example;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class StreamVersion {


    public static void main(String[] args) {
        List<String> strings =
                Arrays.asList("LightCoral", "pink", "Orange", "Gold", "plum", "Blue", "limegreen");


        //1. filter
        for (String s : strings) {
            System.out.println("> " + s);
        }

        Stream<String> upperCase = strings.stream().filter(s -> Character.isUpperCase(s.charAt(0)));

        System.out.println("-------------------------------------------------");
        upperCase.forEach(System.out::println);


        System.out.println("--------------------MAP------------------");


        //2. Map

        strings.forEach(s -> System.out.println("> " + s));

        System.out.println("-------------------------------------------------");
        strings.stream().map(s -> s.toUpperCase())
                .forEach(System.out::println);


        //3. Using Car list
        List<Car> carList =
                Arrays.asList(
                        Car.withGasColorPassengers(6, "Red", "Fred", "Jim", "Sheila"),
                        Car.withGasColorPassengers(3, "Octarine", "Rincewind", "Ridcully"),
                        Car.withGasColorPassengers(9, "Black", "Weatherwax", "Magrat"),
                        Car.withGasColorPassengers(7, "Green", "Valentine", "Gillian", "Anne", "Dr. Mahmoud"),
                        Car.withGasColorPassengers(6, "Red", "Ender", "Hyrum", "Locke", "Bonzo")
                );
        carList.stream().
                filter(c -> c.getGasLevel() >= 6)
                .map(c -> c.getPassengers().get(0) + " is driving with lot of fuel")
                .forEach(System.out::println);

        //4. Change the car's gas level using map
        // Solution - we cannot use setGasLevel method inside map method, as map method expect as return type
        // So, we will create a new method inside Car POJO class that returns new modified car object

        carList.forEach(System.out::println);
        System.out.println("--------------------------------------------------");
        carList.stream().map(c -> c.changeGasFuelLevel(3)).forEach(System.out::println);


        System.out.println("-------------------------------------------------");

        carList.stream()
                .filter(c -> c.getPassengers().size() > 3)
                .flatMap(c -> (c.getPassengers()).stream())
                .map(s -> s.toUpperCase())
                .forEach(s -> System.out.println(s));


        System.out.println("-------------------------------------------------");

        carList.stream().map(Car::getGasLevel).forEach(System.out::println);
    }


}