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
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/appointment")
public class AppointmentController {
    @Autowired
    private AppoinmentService appoinmentService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllApointments() {
        List<AppointmentResponse> appointments = appoinmentService.getAllAppointments();
        return ResponseEntity.ok(appointments);
    }
    

    @PostMapping("/new")
    public ResponseEntity<?> createAppoinment(@RequestBody AppointmentRequest request) {
        Appointment newAppointment = appoinmentService.createAppointment(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(newAppointment);
    }

}
