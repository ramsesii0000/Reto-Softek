package com.retosoftek.reto.softek.mapper;


import com.retosoftek.reto.softek.dto.EstudianteRequestDTO;
import com.retosoftek.reto.softek.dto.EstudianteResponseDTO;
import com.retosoftek.reto.softek.model.Estudiante;

public class EstudianteMapper {

    public static EstudianteResponseDTO toDTO(Estudiante e) {
        EstudianteResponseDTO dto = new EstudianteResponseDTO();
        dto.setId(e.getId());
        dto.setNombre(e.getNombre());
        dto.setApellido(e.getApellido());
        dto.setEmail(e.getEmail());
        dto.setCreditos(e.getCreditos());
        dto.setSemestre(e.getSemestre());
        dto.setPromedio(e.getPromedio());
        return dto;
    }

    public static Estudiante toEntity(EstudianteRequestDTO dto) {
        Estudiante e = new Estudiante();
        e.setNombre(dto.getNombre());
        e.setApellido(dto.getApellido());
        e.setEmail(dto.getEmail());
        e.setCreditos(dto.getCreditos());
        e.setSemestre(dto.getSemestre());
        e.setPromedio(dto.getPromedio());
        return e;
    }
}
