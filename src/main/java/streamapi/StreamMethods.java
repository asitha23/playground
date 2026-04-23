package streamapi;

import streamapi.dtos.City;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.IO.println;

public class StreamMethods {


    List<Integer> list = List.of(1, 2, 3, 4, 5);
    List<List<Integer>> listOfList = List.of(List.of(1, 2, 3), List.of(4, 5, 6),  List.of(7, 8, 9));


    void main() {
        streamReduce();
        streamMaxMin();
        streamIterate();
        streamGatherSlidingWindow();
        mapToMap();
        streamFaltList();
        listToMap();
        mapToList();
        flatMapToMap();

        streamReuseException();
    }


    void streamReduce() {
        println("streamReduce" + list.stream().reduce(Integer::sum).orElse(0));
    }

    void streamMaxMin() {
        println("streamMax : " + list.stream().max(Integer::compareTo));
        println("streamMin : " + list.stream().min(Integer::compareTo));
    }

    void streamIterate() {
        println("streamIterate : ");
        List<Integer> list = Stream.iterate(0, i -> i + 1).limit(5).toList();
        println("list size " + list.size());
        list.forEach(System.out::println);
    }

    void streamReuseException() {
        List<String> elements =
                Stream.of("a", "b", "c").filter(element -> element.contains("b"))
                        .toList();
        Stream<String> stream = elements.stream();
        Optional<String> anyElement = stream.findAny();
        Optional<String> firstElement = stream.findFirst(); // Exception : stream has already been operated upon or closed
        println("anyElement : " + anyElement);
        println("firstElement : " + firstElement);
    }

    void streamGatherSlidingWindow() {
        println("streamGatherSlidingWindow");
        List<Integer> list = Stream.iterate(0, a -> a + 1).limit(400).toList();
        println("list size " + list.size());
        list.stream()
                .gather(PrimeBatcher.gatherPrimes(4))
                .forEach(System.out::println);
    }

    void streamFaltList() {
        println("streamFaltMap");
        listOfList.forEach(System.out::println);
        Set<Set<Integer>> setOfSet = Set.of(Set.of(1, 2, 3), Set.of(4, 5, 6), Set.of(7, 8, 9));
        List<Integer> s = listOfList.stream().flatMap(List::stream).toList();
        s.forEach(System.out::println);
        setOfSet.stream().flatMap(Set::stream).forEach(System.out::println);
    }

    void mapToMap() {
        println("mapToMap");
        Map<String, Integer> originalMap = Map.of("a", 1, "b", 2, "c", 3, "d", 4);
        originalMap.forEach((k,v) -> println(k + " : " + v));
        Map<String, String> transformdMap = originalMap.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> Integer.toBinaryString(entry.getValue())));
        transformdMap.forEach((k, v) -> println(k + " : " + v));

    }

    void listToMap() {
        println("listToMap");
        Map<Integer, Long> countMap =  list.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        countMap.forEach((k,v) -> println(k + " : " + v));
        Map<String, Set<City>> groupedByCountry = getListOfCity().stream()
                .collect(Collectors.groupingBy(City::country, Collectors.toSet()));
        groupedByCountry.forEach((k,v) -> println(k + " : " + v));
    }

    List<City> getListOfCity() {
        return List.of(new City("Nice", "France", 123),
                new  City("Amesterdam", "Netherlands", 123),
                new City("Colombo", "Sri lanka", 123),
                new City("New york", "USA", 123),
                new City("London", "UK", 123),
                new City("Singapore", "Singapore", 123),
                new City("Holland", "Netherlands", 123));
    }

    void mapToList() {
        println("mapToList");
        Map<String, Set<City>> groupedByCountry = getListOfCity().stream()
                .collect(Collectors.groupingBy(City::country, Collectors.toSet()));
        List<City> listOfCities = groupedByCountry.values().stream().flatMap(Set::stream).toList();
        listOfCities.forEach(System.out::println);
    }

    void flatMapToMap() {
        println("flatMapToMap");
        List<Map<String, String>> listMaps = Arrays.asList(
                Map.of("key1", "value1", "key2", "value2"),
                Map.of("key3", "value3", "key4", "value4"),
                Map.of("key1", "newValue1") // Duplicate key "key1"
        );
        Map<String, String> falattenMap = listMaps.stream().
                flatMap(entry -> entry.entrySet().stream()).
                collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, String::concat));
        println("falattenMap : " + falattenMap);
    }
}
