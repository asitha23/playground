package streamapi;

import streamapi.dtos.Person;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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

    protected void printNthAge(int position) {
        List<Person> personList = new ArrayList<>();
        fillPersons(personList);
        int skip = Math.max(position, position - 1);
        personList.stream().map(Person::age).sorted().skip(skip).findFirst().ifPresent(System.out::println);

    }
    protected void printNthAgedPerson(int position) {
        List<Person> personList = new ArrayList<>();
        fillPersons(personList);
        int skip = Math.max(position, position - 1);
        personList.stream().filter(p -> p.age() == personList.get(position).age()).findFirst().ifPresent(System.out::println);
    }

    void main() {
        printNthAge(3);
        printNthAgedPerson(3);
    }
}
