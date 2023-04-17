package ru.skypro.homework8.service;

import org.springframework.stereotype.Service;
import ru.skypro.homework8.entity.Employee;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final Map<Integer, Employee> employees = new HashMap<>();

    static {
        Employee employee1 = new Employee("Рожков Гордей Станиславович", 4, 1500);
        Employee employee2 = new Employee("Павлов Фрол Лаврентьевич", 2, 1800);
        Employee employee3 = new Employee("Рогов Трофим Проклович", 5, 2000);
        Employee employee4 = new Employee("Фокин Юрий Степанович", 1, 2556);
        Employee employee5 = new Employee("Харитонов Игнатий Всеволодович", 3, 7456);
        Employee employee6 = new Employee("Носов Пантелеймон Адольфович", 4, 8372);
        Employee employee7 = new Employee("Логинов Вячеслав Геннадьевич", 1, 1267);
        Employee employee8 = new Employee("Пономарёв Клим Дмитриевич", 2, 9000);
        Employee employee9 = new Employee("Шаров Август Натанович", 5, 8296);
        Employee employee10 = new Employee("Рыбаков Влас Оскарович", 3, 4528);

        employees.put(employee1.getId(), employee1);
        employees.put(employee2.getId(), employee2);
        employees.put(employee3.getId(), employee3);
        employees.put(employee4.getId(), employee4);
        employees.put(employee5.getId(), employee5);
        employees.put(employee6.getId(), employee6);
        employees.put(employee7.getId(), employee7);
        employees.put(employee8.getId(), employee8);
        employees.put(employee9.getId(), employee9);
        employees.put(employee10.getId(), employee10);
        employees.put(null, null);
    }

    @Override
    public Employee getEmployeeWithMinSalary(int department) {       //сотрудник с минимальной зарплатой в отделе
        return employees.entrySet().stream()
                .filter(entry -> entry.getValue() != null && entry.getKey() != null)
                .filter(entry -> entry.getValue().getDepartment() == department)
                .min(Comparator.comparing(entry -> entry.getValue().getSalary()))
                .get()
                .getValue();
    }

    @Override
    public Employee getEmployeeWithMaxSalary(Integer department) {       //сотрудник с максимальной зарплатой в отделе
        return employees.entrySet().stream()
                .filter(entry -> entry.getValue() != null && entry.getKey() != null)
                .filter(entry -> entry.getValue().getDepartment() == department)
                .max(Comparator.comparing(entry -> entry.getValue().getSalary()))
                .get()
                .getValue();
    }

    @Override
    public Map<Integer, List<Employee>> getAllEmployeesOfTheDepartment(int department) {     //Все сотрудники отдела
        return employees.values().stream()
                .filter(Objects::nonNull)
                .filter(employee -> employee.getDepartment() == department)
                .collect(Collectors.groupingBy(Employee::getDepartment));


    }

    /**
     * Данный метод выведет на экран всех сотрудников для каждого отдела в порядке возрастания отдела.
     * За зависимость взяты уникальные номера отделов, имеющиеся у всех сотрудников, так как отдел сотруднику
     * присваивается после прохождения валидации в конструкторе.
     */
    @Override
    public Map<Integer, List<Employee>> getAllEmployeesByAllDepartment() {
        return employees.entrySet().stream()
                .filter(entry -> entry.getValue() != null && entry.getKey() != null)
                .map(Map.Entry::getValue)
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }
}
