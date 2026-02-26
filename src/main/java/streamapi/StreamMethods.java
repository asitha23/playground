package streamapi;

import java.util.List;
import java.util.Map;
import java.util.Optional;
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

        streamReuseException();
    }


    void streamReduce() {
        println("streamReduce" + list.stream().reduce(Integer::sum));
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
        List<Integer> s = listOfList.stream().flatMap(List::stream).toList();
        s.forEach(System.out::println);
    }

    void mapToMap() {
        println("mapToMap");
        Map<String, Integer> originalMap = Map.of("a", 1, "b", 2, "c", 3, "d", 4);
        originalMap.forEach((k,v) -> println(k + " : " + v));
        Map<String, String> transformdMap = originalMap.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> Integer.toBinaryString(entry.getValue())));
        transformdMap.forEach((k, v) -> println(k + " : " + v));

    }
}
