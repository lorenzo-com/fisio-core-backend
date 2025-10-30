package com.example.physiocore.demo.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Appoinment {
    @Id
    @GeneratedValue
    private Long id;
    private Double price;
    private String notes;
    private LocalDateTime dateHour;
    private String appliedTreatments;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Client patient;

    @Enumerated(EnumType.STRING)
    private StatusAppointment state;
}
