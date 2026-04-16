package streamapi;

import streamapi.dtos.Person;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PeopleGenerator {
    Random rand = new Random();
    void main() {
        Map<String, List<Person>> grouped = generatePeople().stream().collect(Collectors.groupingBy(Person::name));
        grouped.forEach((k,v)-> System.out.println("key " + k + " value " + v));
    }

    List<Person> generatePeople () {
        return Stream.generate(() -> new Person(genRandomString(), rand.nextInt(0, 90))).limit(10)
                .toList();

    }

    private String genRandomString() {
        return rand.ints('a', 'z')
                .limit(1000)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}
