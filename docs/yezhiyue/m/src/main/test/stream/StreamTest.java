package stream;

import lombok.Builder;
import lombok.Data;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTest {
    @Test
    public void test01() {
        List<Person> list = Arrays.asList(
                new Person("A", 23),
                // 故意放入null元素
                null,
                null,
                new Person("C", 28),
                new Person("C", 28),
                new Person("D", 25),
                new Person("E", 13)
        );
    }
}

/*
        List<Person> list = Arrays.asList(
                new Person("A", 23),
                new Person("B", 22),
                new Person("C", 28),
                new Person("C", 28),
                new Person("D", 25),
                new Person("E", 13)
        );


 */
@Data
@Builder
class Person {
    private String name;
    private Integer age;
}