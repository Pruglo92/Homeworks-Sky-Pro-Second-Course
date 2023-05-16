package ru.skypro.homework12.repository;

import lombok.Getter;
import org.springframework.stereotype.Repository;
import ru.skypro.homework12.entity.Employee;

import java.util.HashMap;
import java.util.Map;

@Getter
@Repository
public class EmployeeRepository {
    private final Map<Integer, Employee> employees = new HashMap<>();
}
