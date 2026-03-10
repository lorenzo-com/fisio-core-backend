package com.example.physiocore.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.physiocore.demo.model.AppUser;
import com.example.physiocore.demo.model.Appointment;
import com.example.physiocore.demo.model.StatusAppointment;

public interface AppoinmentRepository extends JpaRepository<Appointment, Long> {
    @Query("SELECT COUNT(a) FROM Appointment a WHERE a.patient.id = :patientId AND a.state = 'PENDIENTE'") Long countByPatientId(@Param("patientId") Long patientId);

    List<Appointment> findByPatient(AppUser patient);

    List<Appointment> findByPatientAndState(AppUser patient, StatusAppointment state);
}
