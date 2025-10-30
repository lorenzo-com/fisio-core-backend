package com.example.physiocore.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.physiocore.demo.repository.AppoinmentRepository;

@Service
public class AppoinmentService {
    @Autowired
    private AppoinmentRepository appoinmentRepository;

}
