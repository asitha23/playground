package streamapi.dtos;

public record Person(String name, int age) {

    public Person(String name) {
        this(name, 25);
    }
}
