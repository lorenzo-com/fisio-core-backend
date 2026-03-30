package com.example.physiocore.demo.model;

import lombok.Getter;

@Getter
public enum ClinicService {
    FISIOTERAPIA_DEPORTIVA("Fisioterapia Deportiva"),
    REHABILITACION("Rehabilitación"),
    MASAJE_TERAPEUTICO("Masaje Terapéutico"),
    ESCUELA_ESPALDA("Escuela de Espalda");

    private final String label;

    ClinicService(String label){
        this.label = label;
    }
}
