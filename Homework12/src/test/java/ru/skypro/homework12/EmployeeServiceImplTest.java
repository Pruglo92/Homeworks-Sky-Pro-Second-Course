package ru.skypro.homework12;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.skypro.homework12.entity.Employee;
import ru.skypro.homework12.exceptions.SalaryNegativeException;
import ru.skypro.homework12.exceptions.departmentExceptions.DepartmentIntervalException;
import ru.skypro.homework12.exceptions.employeeExceptions.*;
import ru.skypro.homework12.service.impl.EmployeeServiceImpl;

import java.util.Random;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class EmployeeServiceImplTest {
    private EmployeeServiceImpl employeeService;

    @BeforeEach
    public void beforeEach() {
        employeeService = new EmployeeServiceImpl();
        employeeService.addEmployee("Сильвестр", "Сталлоне", 1, 1000);
        employeeService.addEmployee("Арнольд", "Шварценеггер", 2, 2000);
        employeeService.addEmployee("Джеки", "Чан", 3, 3000);
    }

    public static Stream<Arguments> incorrectFirstNameTestParams() {
        return Stream.of(
                Arguments.of("Олег1"),
                Arguments.of("Олег$"),
                Arguments.of("Оле г")
        );
    }

    public static Stream<Arguments> incorrectLastNameTestParams() {
        return Stream.of(
                Arguments.of("Олег1"),
                Arguments.of("Олег$"),
                Arguments.of("Оле г")
        );
    }

    @Test
    @DisplayName("Тест на корректное добавление сотрудника")
    void testAddEmployee_ValidData_Success() {
        var expected = new Employee("Василий", "Петров", 1, 1000);
        var actual = employeeService.addEmployee("Василий", "Петров", 1, 1000);
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("incorrectFirstNameTestParams")
    @DisplayName("Тест на некорректное имя")
    public void testAddEmployee_InvalidFirstName_ExceptionThrown(String incorrectFirstName) {
        assertThrows(EmployeeIncorrectFirstNameException.class, () ->
                employeeService.addEmployee(incorrectFirstName, "Петров", 1, 1000)
        );
    }

    @ParameterizedTest
    @MethodSource("incorrectLastNameTestParams")
    @DisplayName("Тест на некорректную фамилию")
    public void testAddEmployee_InvalidLastName_ExceptionThrown(String incorrectLastName) {
        assertThrows(EmployeeIncorrectLastNameException.class, () ->
                employeeService.addEmployee("Василий", incorrectLastName, 1, 1000)
        );
    }

    @Test
    @DisplayName("Тест на добавление существующего сотрудника")
    public void testAddEmployee_AlreadyAdded_ExceptionThrown() {
        assertThrows(EmployeeAlreadyAddedException.class, () ->
                employeeService.addEmployee("Сильвестр", "Сталлоне", 1, 1000)
        );
    }

    @Test
    @DisplayName("Тест на переполнение репозитория")
    public void testAddEmployee_StorageIsFull_ExceptionThrown() {
        Random r = new Random();
        for (int i = 0; i < employeeService.getSIZE_MAP() - 3; i++) {
            char c = (char) (r.nextInt(26) + 'a');
            employeeService.addEmployee("Employee" + c, "Lastname" + c, r.nextInt(1, 6), i * 1000);
        }

        assertThrows(EmployeeStorageIsFullException.class, () ->
                employeeService.addEmployee("Борис", "Бритва", 1, 1000)
        );
    }

    @Test
    @DisplayName("Тест на корректность номера отдела")
    public void testAddEmployee_DepartmentInterval_ExceptionThrown() {
        assertThrows(DepartmentIntervalException.class, () ->
                employeeService.addEmployee("Иван", "Иванов", 6, 1000)
        );
    }

    @Test
    @DisplayName("Тест на корректность зарплаты")
    public void testAddEmployee_SalaryNegative_ExceptionThrown() {
        assertThrows(SalaryNegativeException.class, () ->
                employeeService.addEmployee("Иван", "Иванов", 1, -1)
        );
    }

    @Test
    @DisplayName("Тест на получение существующего сотрудника")
    public void testGetEmployee_ExistingEmployee_ReturnsEmployee() {
        String firstName = "Сильвестр";
        String lastName = "Сталлоне";

        var employee = employeeService.getEmployee(firstName, lastName);

        assertEquals(firstName, employee.getFirstName());
        assertEquals(lastName, employee.getLastName());
    }

    @Test
    @DisplayName("Тест на получение несуществующего сотрудника")
    public void testGetEmployee_NonExistingEmployee_ThrowsException() {
        String firstName = "Доктор";
        String lastName = "Зло";

        assertThrows(EmployeeNotFoundException.class, () ->
                employeeService.getEmployee(firstName, lastName)
        );
    }

    @Test
    @DisplayName("Тест на удаление существующего сотрудника")
    public void testDeleteEmployee_ExistingEmployee_ReturnsDeletedEmployee() {
        String firstName = "Сильвестр";
        String lastName = "Сталлоне";

        var employee = employeeService.deleteEmployee(firstName, lastName);

        assertEquals(firstName, employee.getFirstName());
        assertEquals(lastName, employee.getLastName());
    }

    @Test
    @DisplayName("Тест на удаление несуществующего сотрудника")
    public void testDeleteEmployee_NonExistingEmployee_ThrowsException() {
        String firstName = "Джо";
        String lastName = "Байден";

        assertThrows(EmployeeNotFoundException.class, () -> {
            employeeService.deleteEmployee(firstName, lastName);
        });
    }

    @Test
    @DisplayName("Тест на получение всех сотрудников")
    public void testGetAllEmployee_ReturnsAllEmployees() {
        Assertions.assertThat(employeeService.getAllEmployee())
                .hasSize(3)
                .containsValues(
                        new Employee("Арнольд", "Шварценеггер", 2, 2000),
                        new Employee("Сильвестр", "Сталлоне", 1, 1000),
                        new Employee("Джеки", "Чан", 3, 3000));
    }
}
