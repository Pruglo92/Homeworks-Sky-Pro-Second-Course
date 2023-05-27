package ru.skypro.homework12;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.skypro.homework12.entity.Employee;
import ru.skypro.homework12.exceptions.departmentExceptions.DepartmentNotFoundException;
import ru.skypro.homework12.service.impl.DepartmentServiceImpl;
import ru.skypro.homework12.service.impl.EmployeeServiceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceImplTest {
    @Mock
    private EmployeeServiceImpl employeeService;
    @InjectMocks
    private DepartmentServiceImpl departmentService;

    @BeforeEach
    public void setup() {
        when(employeeService.getEmployees()).thenReturn(
                Map.of(
                        1, new Employee("Сильвестр", "Сталлоне", 1, 1000),
                        2, new Employee("Арнольд", "Шварценеггер", 1, 2000),
                        3, new Employee("Джеки", "Чан", 2, 3000)
                )
        );
    }


    @Test
    @DisplayName("Тест суммы зарплат по отделу")
    public void testGetDepartmentSalary_ReturnsTotalSalary() {

        var totalSalary = departmentService.getDepartmentSalary(1);
        var totalZeroSalary = departmentService.getDepartmentSalary(3);

        assertEquals(3000, totalSalary);
        assertEquals(0, totalZeroSalary);
    }

    @Test
    @DisplayName("Тест максимальной зарплаты по отделу")
    public void testGetDepartmentMaxSalary_ReturnsMaxSalary() {

        var maxSalary = departmentService.getDepartmentMaxSalary(1);

        assertEquals(2000, maxSalary);
    }

    @Test
    @DisplayName("Тест на отсутствие отдела при поиске max зарплаты")
    public void testGetDepartmentMaxSalary_ThrowsExceptionWhenNoEmployees() {

        assertThrows(DepartmentNotFoundException.class, () ->
                departmentService.getDepartmentMaxSalary(3)
        );
    }

    @Test
    @DisplayName("Тест минимальной зарплаты по отделу")
    public void testGetDepartmentMinSalary_ReturnsMaxSalary() {

        var minSalary = departmentService.getDepartmentMinSalary(1);

        assertEquals(1000, minSalary);
    }

    @Test
    @DisplayName("Тест на отсутствие отдела при поиске min зарплаты")
    public void testGetDepartmentMinSalary_ThrowsExceptionWhenNoEmployees() {

        assertThrows(DepartmentNotFoundException.class, () ->
                departmentService.getDepartmentMinSalary(3)
        );
    }

    @Test
    @DisplayName("Тест на поиск сотрудников по отделу")
    public void testGetAllEmployeesOfDepartment_ReturnsEmployeesList() {

        var employees = departmentService.getAllEmployeesOfTheDepartment(1);

        assertEquals(2, employees.size());
        assertTrue(employees.contains(new Employee("Сильвестр", "Сталлоне", 1, 1000)));
        assertTrue(employees.contains(new Employee("Арнольд", "Шварценеггер", 1, 2000)));
    }

    @Test
    @DisplayName("Тест на поиск всех сотрудников по всем отделам")
    public void testGetAllEmployeesByAllDepartment_ReturnsEmployeesByDepartmentMap() {

        Employee employee1 = new Employee("Арнольд", "Шварценеггер", 1, 2000);
        Employee employee2 = new Employee("Сильвестр", "Сталлоне", 1, 1000);
        Employee employee3 = new Employee("Джеки", "Чан", 2, 3000);

        Map<Integer, Employee> employeesMap = new HashMap<>();
        employeesMap.put(1, employee1);
        employeesMap.put(2, employee2);
        employeesMap.put(3, employee3);

        when(employeeService.getEmployees()).thenReturn(employeesMap);

        var employeesByDepartment = departmentService.getAllEmployeesByAllDepartment();

        assertEquals(2, employeesByDepartment.size());
        assertTrue(employeesByDepartment.containsKey(1));
        assertTrue(employeesByDepartment.containsKey(2));
        assertEquals(List.of(employee1, employee2), employeesByDepartment.get(1));
        assertEquals(List.of(employee3), employeesByDepartment.get(2));
    }
}
