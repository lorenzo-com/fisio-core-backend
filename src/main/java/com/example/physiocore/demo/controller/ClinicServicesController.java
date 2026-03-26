package com.example.physiocore.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.physiocore.demo.dto.ClinicServiceDto;
import com.example.physiocore.demo.model.ClinicService;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/services-clinic")
public class ClinicServicesController {
    @GetMapping
    public List<ClinicServiceDto> getServices() {
        return Arrays.stream(ClinicService.values())
                .map(service -> new ClinicServiceDto(
                        service.name(),
                        service.getLabel()))
                .toList();
    }
}
