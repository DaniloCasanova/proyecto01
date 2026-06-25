package com.proyecto01.employeeservice.dto;

import com.proyecto01.employeeservice.entity.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDTO {
    private Employee employee;
    private DepartamentDTO departamentDTO;
}
