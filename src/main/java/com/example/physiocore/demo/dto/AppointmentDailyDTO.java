package com.example.physiocore.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentDailyDTO {
    private String hourValue;
    private String professionalFullName;
    private String patientFullName;
    private String service;
    private String state;
}