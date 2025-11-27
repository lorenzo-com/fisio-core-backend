package com.example.physiocore.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.physiocore.demo.dto.AppointmentRequest;
import com.example.physiocore.demo.model.Appointment;
import com.example.physiocore.demo.model.Client;
import com.example.physiocore.demo.repository.AppoinmentRepository;
import com.example.physiocore.demo.repository.ClientRepository;

@Service
public class AppoinmentService {
    @Autowired
    private AppoinmentRepository appointmentRepository;
    @Autowired
    private ClientRepository clientRepository;

    public Appointment createAppointment(AppointmentRequest request) {
        Client patient = clientRepository.findById(request.getPatient_id())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID: " + request.getPatient_id()));

        Appointment appointment = new Appointment();
        appointment.setDate(request.getDate());
        appointment.setHourValue(request.getHour());
        appointment.setService(request.getService());
        appointment.setPatient(patient);

        return appointmentRepository.save(appointment);
    }
}
