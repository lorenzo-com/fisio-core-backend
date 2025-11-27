package com.example.physiocore.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.physiocore.demo.dto.AppointmentRequest;
import com.example.physiocore.demo.model.Appointment;
import com.example.physiocore.demo.services.AppoinmentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {
    @Autowired
    private AppoinmentService appoinmentService;

    @PostMapping("/new")
    public ResponseEntity<?> createAppoinment(@RequestBody AppointmentRequest request) {
        Appointment newAppointment = appoinmentService.createAppointment(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(newAppointment);
    }

}
