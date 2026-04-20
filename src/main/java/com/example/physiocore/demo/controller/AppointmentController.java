package com.example.physiocore.demo.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.physiocore.demo.dto.AppointmentDailyDTO;
import com.example.physiocore.demo.dto.AppointmentRequest;
import com.example.physiocore.demo.dto.AppointmentResponse;
import com.example.physiocore.demo.model.AppUser;
import com.example.physiocore.demo.model.Appointment;
import com.example.physiocore.demo.model.StatusAppointment;
import com.example.physiocore.demo.model.UserRole;
import com.example.physiocore.demo.services.AppoinmentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {
    @Autowired
    private AppoinmentService appoinmentService;

    // ADMIN, PROFESSIONAL y PATIENT
    @GetMapping("/{id}")
    public ResponseEntity<?> getAppointmentById(@PathVariable Long id) {
        AppointmentResponse appointment = appoinmentService.getAppointmentById(id);
        return ResponseEntity.ok(appointment);
    }

    @GetMapping
    public ResponseEntity<?> getUserAppointments(@AuthenticationPrincipal AppUser user) {
        List<AppointmentResponse> appointments = appoinmentService.findByPatient(user);

        return ResponseEntity.ok(appointments);
    }

    // ADMIN y PROFESSIONAL
    @GetMapping("/all")
    public ResponseEntity<?> getAllApointments(@AuthenticationPrincipal AppUser user) {
        if (user.getRoles().contains(UserRole.ADMIN)) {
            return ResponseEntity.ok(appoinmentService.getAllAppointments());
        } else {
            return ResponseEntity.ok(appoinmentService.getAppointmentsByProfessional(user));
        }
    }

    @PostMapping("/new")
    public ResponseEntity<?> createAppoinment(@RequestBody AppointmentRequest request) {
        Appointment newAppointment = appoinmentService.createAppointment(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(newAppointment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAppointment(@PathVariable Long id, @RequestBody AppointmentRequest data) {
        AppointmentResponse updatedAppointment = appoinmentService.updateAppointment(id, data);
        return ResponseEntity.ok(updatedAppointment);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAppointment(@PathVariable Long id) {
        appoinmentService.deleteAppointment(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/pending")
    public ResponseEntity<?> getUserPendingAppointments(@AuthenticationPrincipal AppUser user) {
        List<AppointmentResponse> appointments = appoinmentService.findByPatientAndState(user,
                StatusAppointment.PENDIENTE);

        return ResponseEntity.ok(appointments);
    }

    // PROFESSIONAL
    @GetMapping("path")
    public String getAppointments(@RequestParam String param) {
        return new String();
    }

    // ADMIN
    @GetMapping("/daily")
    public List<AppointmentDailyDTO> getDailyAgenda(
            @RequestParam String date) {

        LocalDate parsedDate = LocalDate.parse(date);
        return appoinmentService.getDailyAgenda(parsedDate);
    }
}
