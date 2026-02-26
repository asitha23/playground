package streamapi;

import streamapi.dtos.Person;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public class FourthHighestAge {

    private void fillPersons(List<Person> personList) {
        personList.add(new Person("Sam", 35));
        personList.add(new Person("dummy", 3));
        personList.add(new Person("name2", 27));
        personList.add(new Person("name3", 45));
        personList.add(new Person("name4", 62));
        personList.add(new Person("name5", 73));
        personList.add(new Person("name6"));
    }

    private void fillRandomPersons(List<Person> personList, int limit) {
        Random random = new Random();
        List<Person> ranList = Stream.generate(() -> new Person("Sam" + random.nextInt(), random.nextInt(200))).limit(limit).toList();
        personList.addAll(ranList);
    }

    protected void printNthAge(int position) {
        List<Person> personList = new ArrayList<>();
        fillPersons(personList);
        personList.stream().map(Person::age).sorted().skip(position - 1).findFirst().ifPresent(System.out::println);

    }
    protected void printNthAgedPerson(int position) {
        List<Person> personList = new ArrayList<>();
        fillPersons(personList);
        fillRandomPersons(personList, 100);
        int age =  personList.stream().map(Person::age).sorted().skip(position).findFirst().orElse(-1);
        personList.stream().filter(p -> p.age() == age).findFirst().ifPresent(System.out::println);
        personList.stream().sorted(Comparator.comparingInt(Person::age)).skip(position - 1).findFirst().ifPresent(System.out::println);
    }

    void main() {
        printNthAge(3);
        printNthAgedPerson(3);
    }
}
