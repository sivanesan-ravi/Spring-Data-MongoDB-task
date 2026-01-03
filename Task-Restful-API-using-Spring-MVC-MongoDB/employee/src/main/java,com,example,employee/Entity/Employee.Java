package com.example.employee.controller;

import com.example.employee.entity.Employee;
import com.example.employee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/")
public class EmployeeController {
   @Autowired
    private EmployeeRepository repository;

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String saveEmployee(Employee employee) {
        repository.save(employee);
        return "Employee saved! <a href='/'>Go Back</a>";
    }

    @GetMapping("/displayAll")
    public List<Employee> getAllEmployees() {
        return repository.findAll();
    }

    @GetMapping("/display/{userId}")
    public Employee getEmployeeById(@PathVariable("userId") int userId) {
        return repository.findById(String.valueOf(userId))
                .stream().filter(e -> e.getEmployeeId() == userId)
                .findFirst().orElse(null);
    }
}