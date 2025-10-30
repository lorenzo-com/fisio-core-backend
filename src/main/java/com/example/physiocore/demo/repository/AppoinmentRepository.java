package com.example.physiocore.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.physiocore.demo.model.Appoinment;

public interface AppoinmentRepository extends JpaRepository<Appoinment, Long> {
    @Query("SELECT COUNT(a) FROM Appoinment a WHERE a.patient.id = :patientId") Long countByPatientId(@Param("patientId") Long patientId);
}
