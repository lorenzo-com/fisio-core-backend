package com.example.physiocore.demo.model;

import lombok.Getter;

@Getter
public enum ClinicService {
    FISIOTERAPIA_DEPORTIVA("Fisioterapia deportiva"),
    REHABILITACION("Rehabilitación"),
    MASAJE_TERAPEUTICO("Masaje terapéutico"),
    ESCUELA_ESPALDA("Escuela de espalda");

    private final String label;

    ClinicService(String label){
        this.label = label;
    }
}
