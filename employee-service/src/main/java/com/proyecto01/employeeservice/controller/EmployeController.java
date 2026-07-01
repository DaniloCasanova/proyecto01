package com.proyecto01.employeeservice.controller;

import com.proyecto01.employeeservice.dto.EmployeeDTO;
import com.proyecto01.employeeservice.dto.ResponseDTO;
import com.proyecto01.employeeservice.entity.Employee;
import com.proyecto01.employeeservice.service.EmployeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    /*Recuperar Lista de Empleados usando filtros*/
    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> buscarEmployees (
                @RequestParam(required = false) Long departamentId,
                @RequestParam(required = false) String lastName
            ) {

        //Buscamos empleados con metodo del Servicio
        List<EmployeeDTO> employeesDTO = employeService.buscarEmployees(departamentId, lastName);

        //Verificacion Lista vacia
        if (employeesDTO.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        //Todo ok. Retornamos empleados encontrados.
        return ResponseEntity.ok(employeesDTO);
    }

}
