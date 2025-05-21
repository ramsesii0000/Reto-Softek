package com.retosoftek.reto.softek.dto;

import lombok.Data;

@Data
public class EstudianteResponseDTO {
    private Integer id;
    private String nombre;
    private String apellido;
    private String email;
    private Integer creditos;
    private Integer semestre;
    private Integer promedio;
}
