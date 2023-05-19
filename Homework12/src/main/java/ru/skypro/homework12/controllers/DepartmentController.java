package ru.skypro.homework12.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.skypro.homework12.entity.Employee;
import ru.skypro.homework12.service.DepartmentService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/departments")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    @GetMapping("/sum")
    public ResponseEntity<Integer> getAmountSalariesForDepartment(@RequestParam Integer departmentId) {
        return ResponseEntity.ok(departmentService.getDepartmentSalary(departmentId));
    }
    @GetMapping("/max")
    public ResponseEntity<Integer> getMaxSalaryForDepartment(@RequestParam Integer departmentId) {
        return ResponseEntity.ok(departmentService.getDepartmentMaxSalary(departmentId));
    }

    @GetMapping("/min")
    public ResponseEntity<Integer> getMinSalaryForDepartment(@RequestParam Integer departmentId) {
        return ResponseEntity.ok(departmentService.getDepartmentMinSalary(departmentId));
    }
    @GetMapping("/max-salary")
    public ResponseEntity<Employee> getEmployeeWithMaxSalary(@RequestParam Integer departmentId) {
        return ResponseEntity.ok(departmentService.getEmployeeWithMaxSalary(departmentId));
    }

    @GetMapping("/min-salary")
    public ResponseEntity<Employee> getEmployeeWithMinSalary(@RequestParam Integer departmentId) {
        return ResponseEntity.ok(departmentService.getEmployeeWithMinSalary(departmentId));
    }

    @GetMapping("/all")
    public ResponseEntity<Map<Integer, List<Employee>>> getAllEmployeesOfTheDepartment(Integer departmentId) {
        if (departmentId == null) {
            return ResponseEntity.ok(departmentService.getAllEmployeesByAllDepartment());
        } else {
            return ResponseEntity.ok(departmentService.getAllEmployeesOfTheDepartment(departmentId));
        }
    }
}
