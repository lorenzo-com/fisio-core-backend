package com.example.physiocore.demo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.physiocore.demo.dto.BookClientRequestDto;
import com.example.physiocore.demo.dto.QuestionClientRequestDto;
import com.example.physiocore.demo.services.EmailService;

@RestController
@RequestMapping("/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/send")
    public ResponseEntity<?> sendEmail(@RequestBody BookClientRequestDto reservation) {
        emailService.sendEmail(
                "fisiocorecontacto@gmail.com",
                "Nueva reserva - FisioCore",
                "Nueva Reserva",
                reservation,
                null);

        return ResponseEntity.ok(
                Map.of("message", "Mensaje enviado correctamente"));
    }

    @PostMapping("/request-cancel")
    public ResponseEntity<?> requestCancellation(@RequestBody BookClientRequestDto reservation) {

        emailService.sendEmail(
                "fisiocorecontacto@gmail.com",
                "Solicitud de cancelación - FisioCore",
                "Solicitud de Cancelación",
                reservation,
                null);

        return ResponseEntity.ok(
                Map.of("message", "Mensaje enviado correctamente"));
    }

    @PostMapping("/send/question")
    public ResponseEntity<?> sendQuestion(
            @RequestBody QuestionClientRequestDto question) {

        System.out.print(question);
        emailService.sendEmail(
                "fisiocorecontacto@gmail.com",
                "Nueva consulta - FisioCore",
                "Nueva Consulta",
                null,
                question);

        return ResponseEntity.ok(
                Map.of("message", "Mensaje enviado correctamente"));
    }

}