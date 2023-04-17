package ru.skypro.homework8.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.skypro.homework8.entity.Employee;
import ru.skypro.homework8.service.EmployeeService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/departments")
public class EmployeeController {

    private final EmployeeService employeeService;


    private EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(value = "/max-salary")
    public Employee getEmployeeWithMaxSalary(@RequestParam Integer departmentId) {
        return employeeService.getEmployeeWithMaxSalary(departmentId);
    }

    @GetMapping(value = "/min-salary")
    public Employee getEmployeeWithMinSalary(@RequestParam Integer departmentId) {
        return employeeService.getEmployeeWithMinSalary(departmentId);
    }

    @GetMapping(value = "/all")
    public Map<Integer, List<Employee>> getAllEmployeesOfTheDepartment(Integer departmentId) {
        if (departmentId == null) {
            return employeeService.getAllEmployeesByAllDepartment();
        } else {
            return employeeService.getAllEmployeesOfTheDepartment(departmentId);
        }
    }
}
