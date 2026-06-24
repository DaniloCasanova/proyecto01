package com.prueba01.departamentservice.controller;

import com.prueba01.departamentservice.entity.Departament;
import com.prueba01.departamentservice.repository.DepartamentRepository;
import com.prueba01.departamentservice.service.DepartamentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departaments")
public class DepartamentController {
    private final DepartamentService departamentService;

    public DepartamentController(DepartamentService departamentService) {
        this.departamentService = departamentService;
    }

    @PostMapping
    public Departament createDepartament(@RequestBody Departament departament) {
        return departamentService.createDepartament(departament);
    }

    @GetMapping("/{id}")
    public Departament getDepartamentById(@PathVariable Integer id) {
        return departamentService.getDepartamentById(id);
    }

    @GetMapping
    public List<Departament> findAll() {
        return departamentService.findAll();
    }
}
