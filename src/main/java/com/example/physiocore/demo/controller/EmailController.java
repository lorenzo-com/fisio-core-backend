package com.example.physiocore.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.physiocore.demo.dto.BookClientRequestDto;
import com.example.physiocore.demo.services.EmailService;

@RestController
@RequestMapping("/email")
public class EmailController {
    @Autowired
    private EmailService emailService;

    @PostMapping("/send")
    public String sendEmail(@RequestBody BookClientRequestDto reservation) {
        String body = "Nueva reserva:\n\n" +
                "Nombre: " + reservation.getName() + "\n" +
                "Email: " + reservation.getEmail() + "\n" +
                "Teléfono: " + reservation.getPhone() + "\n" +
                "Fecha: " + reservation.getDate() + "\n" +
                "Hora: " + reservation.getHour() + "\n" +
                "Servicio: " + reservation.getService();

        emailService.sendEmail(
                "fisiocorecontacto@gmail.com",
                "Nueva reserva - FisioCore",
                body
        );

        return "Email enviado";
    }
}
