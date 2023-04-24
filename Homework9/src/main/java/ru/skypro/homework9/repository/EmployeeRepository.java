package ru.skypro.homework9.repository;

import org.springframework.stereotype.Repository;
import ru.skypro.homework9.entity.Employee;

import java.util.HashMap;
import java.util.Map;

@Repository
public class EmployeeRepository {

    public static final Map<Integer, Employee> employees = new HashMap<>();

}
