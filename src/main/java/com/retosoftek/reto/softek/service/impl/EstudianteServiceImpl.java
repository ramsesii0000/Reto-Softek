package com.retosoftek.reto.softek.service.impl;


import com.retosoftek.reto.softek.dto.EstudianteRequestDTO;
import com.retosoftek.reto.softek.dto.EstudianteResponseDTO;
import com.retosoftek.reto.softek.mapper.EstudianteMapper;
import com.retosoftek.reto.softek.model.Estudiante;
import com.retosoftek.reto.softek.repository.EstudianteRepository;
import com.retosoftek.reto.softek.service.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EstudianteServiceImpl implements EstudianteService {

    @Autowired
    private EstudianteRepository repo;

    public List<EstudianteResponseDTO> listar() {
        return repo.findAll().stream().map(EstudianteMapper::toDTO).collect(Collectors.toList());
    }

    public EstudianteResponseDTO obtenerPorId(Integer id) {
        Estudiante e = repo.findById(id).orElseThrow(() -> new EntityNotFoundException("Estudiante no encontrado"));
        return EstudianteMapper.toDTO(e);
    }

    public EstudianteResponseDTO crear(EstudianteRequestDTO dto) {
        Estudiante e = EstudianteMapper.toEntity(dto);
        return EstudianteMapper.toDTO(repo.save(e));
    }

    public EstudianteResponseDTO actualizar(Integer id, EstudianteRequestDTO dto) {
        Estudiante e = repo.findById(id).orElseThrow(() -> new EntityNotFoundException("Estudiante no encontrado"));
        e.setNombre(dto.getNombre());
        e.setApellido(dto.getApellido());
        e.setEmail(dto.getEmail());
        e.setCreditos(dto.getCreditos());
        e.setSemestre(dto.getSemestre());
        e.setPromedio(dto.getPromedio());
        return EstudianteMapper.toDTO(repo.save(e));
    }

    public void eliminar(Integer id) {
        repo.deleteById(id);
    }
}
