package com.proyecto01.employeeservice.controller;

import com.proyecto01.employeeservice.dto.ResponseDTO;
import com.proyecto01.employeeservice.entity.Employee;
import com.proyecto01.employeeservice.service.EmployeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employes")
@AllArgsConstructor
public class EmployeController {
    private final EmployeService employeService;

    @PostMapping
    public Employee saveEmploye(@RequestBody Employee employee) {
        return employeService.saveEmploye(employee);
    }

    @GetMapping("/{id}")
    public ResponseDTO getEmployeDeptoById(@PathVariable Long id) {
        return employeService.getEmployeDeptoById(id);
    }

}
