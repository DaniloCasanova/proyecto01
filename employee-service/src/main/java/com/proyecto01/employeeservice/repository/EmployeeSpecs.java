package com.proyecto01.employeeservice.repository;

import com.proyecto01.employeeservice.entity.Employee;
import org.springframework.data.jpa.domain.Specification;

public class EmployeeSpecs {

    /*Metodo para filtrar por Departamento*/
    public static Specification<Employee> tieneDepartamentId(Long departamentId) {
        return (root, query, criteriaBuilder) ->
                departamentId == null ? null : criteriaBuilder.equal(root.get("departamentId"), departamentId);
    }
}
