package ru.skypro.homework12.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.skypro.homework12.entity.Employee;
import ru.skypro.homework12.service.DepartmentService;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    @GetMapping("/{id}/employees")
    public ResponseEntity<List<Employee>> getAllEmployeesByDepartment(@PathVariable("id")
                                                                      Integer departmentId) {
        return ResponseEntity.ok(departmentService.getAllEmployeesOfTheDepartment(departmentId));
    }

    @GetMapping("/{id}/salary/sum")
    public ResponseEntity<Integer> getAmountSalariesForDepartment(@PathVariable("id")
                                                                  Integer departmentId) {
        return ResponseEntity.ok(departmentService.getDepartmentSalary(departmentId));
    }

    @GetMapping("/{id}/salary/max")
    public ResponseEntity<Integer> getMaxSalaryForDepartment(@PathVariable("id")
                                                             Integer departmentId) {
        return ResponseEntity.ok(departmentService.getDepartmentMaxSalary(departmentId));
    }

    @GetMapping("/{id}/salary/min")
    public ResponseEntity<Integer> getMinSalaryForDepartment(@PathVariable("id")
                                                             Integer departmentId) {
        return ResponseEntity.ok(departmentService.getDepartmentMinSalary(departmentId));
    }

    @GetMapping("/employees")
    public ResponseEntity<Map<Integer, List<Employee>>> getAllEmployeesGroupedByDepartment() {
        return ResponseEntity.ok(departmentService.getAllEmployeesByAllDepartment());
    }
}
