package streamapi;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static java.lang.IO.println;

public class StreamMethods {


    List<Integer> list = List.of(1, 2, 3, 4, 5);

    void main() {
        streamReduce();
        streamMaxMin();
        streamIterate();
        streamGatherSlidingWindow();

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
}
