package com.example.physiocore.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentResponse {
    private String name;
    private String surname;
    private String phone;

    @JsonProperty("patient_id") // Esto convierte patientId -> patient_id en el JSON
    private Long patientId;

    private String date;
    private String hour;
    private String state;
}
