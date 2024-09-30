package com.api1;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class A {
    public static void main(String[] args) {
        List<Employee> data = Arrays.asList(
                new Employee(1, "mike", 4000),
                new Employee(2, "john", 5000),
                new Employee(3, "sam", 7000)
        );
        List<Employee> newData = data.stream().filter(e -> e.getSal() > 4000).collect(Collectors.toList());
        System.out.println(newData);
    }
}
