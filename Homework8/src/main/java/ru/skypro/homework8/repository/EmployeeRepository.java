package ru.skypro.homework8.repository;

import org.springframework.stereotype.Repository;
import ru.skypro.homework8.entity.Employee;

import java.util.HashMap;
import java.util.Map;

@Repository
public class EmployeeRepository {

    public static final Map<Integer, Employee> employees = new HashMap<>();

}
