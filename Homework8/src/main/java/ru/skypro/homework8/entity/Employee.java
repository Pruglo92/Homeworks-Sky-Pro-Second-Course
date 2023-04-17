package ru.skypro.homework8.entity;

import ru.skypro.homework8.exceptions.DepartmentIntervalException;
import ru.skypro.homework8.exceptions.SalaryNegativeException;

import java.util.Objects;

public class Employee {
    private final String firstName;
    private final String lastName;
    private Integer department;
    private Integer salary;
    private static int countId;
    private final int id;

    public Employee(String firstName, String lastName, Integer department, Integer salary) {
        countId++;
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = validate(department);
        this.salary = validateSalary(salary);
        this.id = countId;
    }

    public static int validate(int parameter) {
        if (parameter > 0 && parameter <= 5) {
            return parameter;
        } else {
            throw new DepartmentIntervalException("Номер отдела может быть только от 1 до 5 включительно.");
        }
    }

    public static int validateSalary(int salary) {
        if (salary >= 0) {
            return salary;
        } else {
            throw new SalaryNegativeException("Зарплата не может быть отрицательной");
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public static int getCountId() {
        return countId;
    }

    public int getDepartment() {
        return department;
    }

    public int getSalary() {
        return salary;
    }

    public int getId() {
        return id;
    }

    public void setDepartment(int department) {
        this.department = validate(department);
    }

    public void setSalary(int salary) {
        this.salary = validateSalary(salary);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", department=" + department +
                ", salary=" + salary +
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return department == employee.department && salary == employee.salary && id == employee.id && firstName.equals(employee.firstName) && lastName.equals(employee.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, department, salary, id);
    }
}
