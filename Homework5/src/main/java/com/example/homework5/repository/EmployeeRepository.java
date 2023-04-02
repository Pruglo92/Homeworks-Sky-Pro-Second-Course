package com.example.homework5.repository;

import com.example.homework5.dto.EmployeeDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeRepository {

    @Value("${very.important.constant}")
    private static final Integer SIZE_ARRAY = 20; //допустим, что наш лист имеет ограниченный размер.
    public final static List<EmployeeDto> employees = new ArrayList<>(SIZE_ARRAY);

    static {
        employees.addAll(List.of(
                new EmployeeDto("Рожков", "Гордей"), new EmployeeDto("Павлов", "Фрол"),
                new EmployeeDto("Рогов", "Трофим"), new EmployeeDto("Фокин", "Юрий"),
                new EmployeeDto("Харитонов", "Игнатий"), new EmployeeDto("Носов", "Пантелеймон"),
                new EmployeeDto("Логинов", "Вячеслав"), new EmployeeDto("Пономарёв", "Клим"),
                new EmployeeDto("Шаров", "Август"), new EmployeeDto("Рыбаков", "Влас")));
    }
}
