package com.example.physiocore.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.physiocore.demo.dto.AppointmentRequest;
import com.example.physiocore.demo.dto.AppointmentResponse;
import com.example.physiocore.demo.model.Appointment;
import com.example.physiocore.demo.services.AppoinmentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {
    @Autowired
    private AppoinmentService appoinmentService;

    // ADMIN y PROFESSIONAL
    @GetMapping("/all")
    public ResponseEntity<?> getAllApointments() {
        List<AppointmentResponse> appointments = appoinmentService.getAllAppointments();
        return ResponseEntity.ok(appointments);
    }

    // ADMIN, PROFESSIONAL y PATIENT
    @GetMapping("/{id}")
    public ResponseEntity<?> getAppointmentById(@PathVariable Long id) {
        AppointmentResponse appointment = appoinmentService.getAppointmentById(id);
        return ResponseEntity.ok(appointment);
    }

    // ADMIN, PROFESSIONAL y PATIENT
    @PostMapping("/new")
    public ResponseEntity<?> createAppoinment(@RequestBody AppointmentRequest request) {
        Appointment newAppointment = appoinmentService.createAppointment(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(newAppointment);
    }

    // ADMIN y PROFESSIONAL
    @PutMapping("/{id}")
    public ResponseEntity<?> updateAppointment(@PathVariable Long id, @RequestBody AppointmentRequest data) {
        AppointmentResponse updatedAppointment = appoinmentService.updateAppointment(id, data);
        return ResponseEntity.ok(updatedAppointment);
    }

    // ADMIN y PROFESSIONAL
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAppointment(@PathVariable Long id) {
        appoinmentService.deleteAppointment(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
