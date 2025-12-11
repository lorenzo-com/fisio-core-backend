package com.example.physiocore.demo.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.physiocore.demo.dto.AppointmentRequest;
import com.example.physiocore.demo.dto.AppointmentResponse;
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

    public AppointmentResponse updateAppointment(Long id, AppointmentRequest data) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cita no encontrada con ID: " + id));

        appointment.setDate(data.getDate());
        appointment.setHourValue(data.getHour());
        appointment.setService(data.getService());
        appointment.setState(data.getState());

        Appointment updatedAppointment = appointmentRepository.save(appointment);

        return AppointmentResponse.builder()
                .id(updatedAppointment.getId())
                .patientId(updatedAppointment.getPatient().getId())
                .username(updatedAppointment.getPatient().getUsername())
                .name(updatedAppointment.getPatient().getName())
                .surname(updatedAppointment.getPatient().getSurname())
                .phone(updatedAppointment.getPatient().getPhone())
                .date(updatedAppointment.getDate())
                .hour(updatedAppointment.getHourValue())
                .service(updatedAppointment.getService())
                .state(updatedAppointment.getState())
                .build();
    }

    public AppointmentResponse getAppointmentById(Long id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cita no encontrada con ID: " + id));

        return AppointmentResponse.builder()
                .id(appointment.getId())
                .patientId(appointment.getPatient().getId())
                .username(appointment.getPatient().getUsername())
                .name(appointment.getPatient().getName())
                .surname(appointment.getPatient().getSurname())
                .phone(appointment.getPatient().getPhone())
                .date(appointment.getDate())
                .hour(appointment.getHourValue())
                .service(appointment.getService())
                .state(appointment.getState())
                .build();
    }

    public List<AppointmentResponse> getAllAppointments() {
        List<Appointment> appointments = appointmentRepository.findAll();

        return appointments.stream().map(data -> {
            return AppointmentResponse.builder()
                .id(data.getId())
                .name(data.getPatient().getName())
                .surname(data.getPatient().getSurname())
                .phone(data.getPatient().getPhone())
                .date(data.getDate())
                .hour(data.getHourValue())
                .state(data.getState())
                .build();
        }).collect(Collectors.toList());
    }

    public void deleteAppointment(Long id) {
        appointmentRepository.deleteById(id);
    }
}
