package com.example.physiocore.demo.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.physiocore.demo.dto.StatusAppointmentDto;
import com.example.physiocore.demo.model.StatusAppointment;

@RestController
@RequestMapping("/status-appoinment")
public class StatusAppoinmentController {
    @GetMapping
    public List<StatusAppointmentDto> getStatusAppointment() {
        return Arrays.stream(StatusAppointment.values())
                .map(status -> new StatusAppointmentDto(
                        status.name(),
                        status.getLabel()))
                .toList();
    }
}
