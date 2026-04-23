package com.example.physiocore.demo.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.physiocore.demo.model.AppUser;
import com.example.physiocore.demo.model.Appointment;
import com.example.physiocore.demo.model.ClinicService;
import com.example.physiocore.demo.model.StatusAppointment;

public interface AppoinmentRepository extends JpaRepository<Appointment, Long> {
        @Query("SELECT COUNT(a) FROM Appointment a WHERE a.patient.id = :patientId AND a.state = 'PENDIENTE'")
        Long countByPatientId(@Param("patientId") Long patientId);

        List<Appointment> findByPatient(AppUser patient);

        List<Appointment> findByPatientAndState(AppUser patient, StatusAppointment state);

        List<Appointment> findByProfessional(AppUser professional);

        @Query("SELECT a FROM Appointment a WHERE a.date = CAST(:date AS string) ORDER BY a.hourValue ASC")
        List<Appointment> findByDateCustom(@Param("date") LocalDate date);

        @Query("""
                            SELECT a FROM Appointment a
                            WHERE a.date = CAST(:date AS string)
                            AND a.professional.id = :professionalId
                            ORDER BY a.hourValue ASC
                        """)
        List<Appointment> findDailyAgendaByProfessional(
                        @Param("date") LocalDate date,
                        @Param("professionalId") Long professionalId);

        @Query("""
                             SELECT a FROM Appointment a
                             JOIN FETCH a.patient
                             JOIN FETCH a.professional
                             WHERE (:date IS NULL OR a.date = :date)
                             AND (:professionalId IS NULL OR a.professional.id = :professionalId)
                             AND (:state IS NULL OR a.state = :state)
                             AND (:service IS NULL OR a.service = :service)
                             AND (:patientName IS NULL OR
                        LOWER(a.patient.name) LIKE LOWER(CONCAT('%', :patientName, '%')) OR
                        LOWER(a.patient.surname) LIKE LOWER(CONCAT('%', :patientName, '%')))
                             ORDER BY a.date ASC, a.hourValue ASC
                         """)
        List<Appointment> searchAppointments(
                        @Param("date") LocalDate date,
                        @Param("professionalId") Long professionalId,
                        @Param("patientName") String patientName,
                        @Param("state") StatusAppointment state,
                        @Param("service") ClinicService service);
}
