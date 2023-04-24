package ru.skypro.homework9.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.skypro.homework9.entity.Employee;
import ru.skypro.homework9.service.DepartmentService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/departments")
public class DepartmentController {

    private final DepartmentService departmentService;


    private DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping(value = "/max-salary")
    public Employee getEmployeeWithMaxSalary(@RequestParam Integer departmentId) {
        return departmentService.getEmployeeWithMaxSalary(departmentId);
    }

    @GetMapping(value = "/min-salary")
    public Employee getEmployeeWithMinSalary(@RequestParam Integer departmentId) {
        return departmentService.getEmployeeWithMinSalary(departmentId);
    }

    @GetMapping(value = "/all")
    public Map<Integer, List<Employee>> getAllEmployeesOfTheDepartment(Integer departmentId) {
        if (departmentId == null) {
            return departmentService.getAllEmployeesByAllDepartment();
        } else {
            return departmentService.getAllEmployeesOfTheDepartment(departmentId);
        }
    }
}
