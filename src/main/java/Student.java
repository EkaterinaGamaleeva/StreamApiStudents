import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Arrays.*;

class Student {
    private String name;
    private Map<String, Integer> grades;

    public String getName() {
        return name;
    }

    public Student(String name, Map<String, Integer> grades) {
        this.name = name;
        this.grades = grades;
    }

    public Map<String, Integer> getGrades() {
        return grades;
    }
}

class ParallelStreamCollectMapAdvancedExample {
    public static void main(String[] args) {
        List<Student> students = asList(
                new Student("Student1", Map.of("Math", 90, "Physics", 85)),
                new Student("Student2", Map.of("Math", 95, "Physics", 88)),
                new Student("Student3", Map.of("Math", 88, "Chemistry", 92)),
                new Student("Student4", Map.of("Physics", 78, "Chemistry", 85))
        );
             Map<Object, Double> map = students
                     .parallelStream()
                     .flatMap(e->e.getGrades().entrySet().stream())
                     .collect(Collectors.groupingBy(e->e.getKey(),Collectors.averagingInt(m->m.getValue())));

        Stream.of(map).parallel().forEach(System.out::println);
    }
}


