package com.example.physiocore.demo.model;

import lombok.Getter;

@Getter
public enum StatusAppointment {
    PENDIENTE("Pendiente"),
    CANCELADA("Cancelada"),
    COMPLETADA("Completada"),
    AUSENTE("Ausente");

    private final String label;

    StatusAppointment(String label){
        this.label = label;
    }
}