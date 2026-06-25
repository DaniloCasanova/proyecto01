package com.proyecto01.employeeservice.service;

import com.proyecto01.employeeservice.dto.DepartamentDTO;
import com.proyecto01.employeeservice.dto.ResponseDTO;
import com.proyecto01.employeeservice.entity.Employee;
import com.proyecto01.employeeservice.repository.EmployeRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class EmployeService {
    private final EmployeRepository employeRepository;
    private final RestTemplate restTemplate;

    public Employee saveEmploye(Employee employee) {
        return employeRepository.save(employee);
    }

    public ResponseDTO getEmployeDeptoById(Long id) {
        ResponseDTO responseDTO = new ResponseDTO();
        Employee employee = new Employee();
        DepartamentDTO departamentDTO = new DepartamentDTO();

        /*Obtención del empleado*/
        employee = employeRepository.findById(id).get();

        /*Obtenemos depto desde el servicio de departamentos*/
        ResponseEntity<DepartamentDTO> responseEntity =
                restTemplate.getForEntity( "http://localhost:8080/api/departaments/"
                                +employee.getDepartamentId(),
                        DepartamentDTO.class);
        departamentDTO = responseEntity.getBody();

        /*Cargamos respuesta final completa*/
        responseDTO.setEmployee(employee);
        responseDTO.setDepartamentDTO(departamentDTO);

        /*Retornamos respuesta final*/
        return responseDTO;
    }
}
