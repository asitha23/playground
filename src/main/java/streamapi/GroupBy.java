package streamapi;

import streamapi.dtos.Employee;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GroupBy {
    Random random = new Random();
    private static final List<String> DEPARTMENTS = List.of("Engineering", "Sales", "HR", "Marketing", "Finance");
    private static final List<String> ROLES = List.of("Junior", "Senior", "Lead", "Manager", "Director");

    void main() {
        List<Employee> employeeList = generateEmployees();
        employeeList.forEach(System.out::println);
        Map<String, Map<String, List<Employee>>> departmentByRoleByGroup = employeeList.stream()
                .collect(Collectors.groupingBy(Employee::department, Collectors.groupingBy(Employee::role)));
        departmentByRoleByGroup.forEach((k,v) -> {
            System.out.println("department " + k + " : ");
            v.forEach((r, e) -> System.out.println("role " + r + " : employee " + e));
        });
    }

    List<Employee> generateEmployees() {
        return Stream.generate(() -> new Employee(
                UUID.randomUUID().toString(),
                generateRandomString(), // Name
                DEPARTMENTS.get(random.nextInt(DEPARTMENTS.size())), // Random Dept
                ROLES.get(random.nextInt(ROLES.size()))
        )).limit(20)
                .toList();
    }


    private String generateRandomString() {
        return  random
                .ints(6, 'a', 'z')
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}
