package com.proyecto01.employeeservice.service;

import com.proyecto01.employeeservice.dto.DepartamentDTO;
import com.proyecto01.employeeservice.dto.EmployeeDTO;
import com.proyecto01.employeeservice.dto.ResponseDTO;
import com.proyecto01.employeeservice.entity.Employee;
import com.proyecto01.employeeservice.mapper.EmployeeMapper;
import com.proyecto01.employeeservice.repository.EmployeRepository;
import com.proyecto01.employeeservice.repository.EmployeeSpecs;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.PredicateSpecification;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeService {
    private final EmployeRepository employeRepository;
    private final RestTemplate restTemplate;

    public Employee saveEmploye(Employee employee) {
        return employeRepository.save(employee);
    }

    /*Recupera Empleado con su Depto por Id*/
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

    /*Recupera Lista de Empleados usando filtros*/
    public List<EmployeeDTO> buscarEmployees (Long departamentId) {

        //Se construye la consulta dinámica
        //Se inicializa con una especificacion que siempre es verdadera

        //Obsoleta, ya no se puede asignar null directo al metodo where de Specification
        //Specification<Employee> spec = Specification.where(null);
        Specification<Employee> spec = Specification.unrestricted();

        if (departamentId != null) {
            spec = spec.and(EmployeeSpecs.tieneDepartamentId(departamentId));
        }

        //Se ejecuta la consulta en el repositorio
        List<Employee> employees = employeRepository.findAll(spec);

        //Se mapea a DTO para proteger la entidad
        return employees.stream()
                .map(EmployeeMapper::toDTO)
                .collect(Collectors.toList());
    }
}
