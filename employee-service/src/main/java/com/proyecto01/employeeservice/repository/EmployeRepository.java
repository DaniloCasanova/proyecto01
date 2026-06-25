package com.proyecto01.employeeservice.repository;

import com.proyecto01.employeeservice.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeRepository extends JpaRepository<Employee, Long> {
}
