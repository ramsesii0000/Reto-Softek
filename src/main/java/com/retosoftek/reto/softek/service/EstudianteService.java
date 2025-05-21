package com.retosoftek.reto.softek.service;


import com.retosoftek.reto.softek.dto.EstudianteRequestDTO;
import com.retosoftek.reto.softek.dto.EstudianteResponseDTO;

import java.util.List;

public interface EstudianteService {
    List<EstudianteResponseDTO> listar();
    EstudianteResponseDTO obtenerPorId(Integer id);
    EstudianteResponseDTO crear(EstudianteRequestDTO dto);
    EstudianteResponseDTO actualizar(Integer id, EstudianteRequestDTO dto);
    void eliminar(Integer id);
}
