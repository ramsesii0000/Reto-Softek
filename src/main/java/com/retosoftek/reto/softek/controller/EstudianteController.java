package com.retosoftek.reto.softek.controller;

import com.retosoftek.reto.softek.dto.EstudianteRequestDTO;
import com.retosoftek.reto.softek.dto.EstudianteResponseDTO;
import com.retosoftek.reto.softek.service.EstudianteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/estudiantes")
@Tag(name = "Estudiantes", description = "CRUD para gestión de estudiantes")
public class EstudianteController {

    @Autowired
    private EstudianteService service;

    @GetMapping
    @Operation(summary = "Listar todos los estudiantes")
    public ResponseEntity<List<EstudianteResponseDTO>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener estudiante por ID")
    public ResponseEntity<EstudianteResponseDTO> obtener(@PathVariable Integer id) {
        return ResponseEntity.ok(service.obtenerPorId(id));
    }

    @PostMapping
    @Operation(summary = "Crear estudiante")
    public ResponseEntity<EstudianteResponseDTO> crear(@Valid @RequestBody EstudianteRequestDTO dto) {
        return ResponseEntity.status(201).body(service.crear(dto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar estudiante")
    public ResponseEntity<EstudianteResponseDTO> actualizar(@PathVariable Integer id, @Valid @RequestBody EstudianteRequestDTO dto) {
        return ResponseEntity.ok(service.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar estudiante")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}

