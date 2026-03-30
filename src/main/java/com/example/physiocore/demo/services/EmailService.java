package com.example.physiocore.demo.services;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.example.physiocore.demo.dto.BookClientRequestDto;
import com.example.physiocore.demo.dto.QuestionClientRequestDto;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service("emailService")
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String to, String subject, String titulo, BookClientRequestDto reservation,
            QuestionClientRequestDto question) {
        String reservationBlock = "";
        String questionBlock = "";

        if (reservation != null) {

            reservationBlock = """
                        <h3 style="
                            color:#0A6ED1;
                            border-bottom:2px solid #eef5ff;
                            padding-bottom:8px;
                            margin-top:25px;
                        ">
                            Información de la reserva
                        </h3>

                        <p><b>Fecha:</b> %s</p>
                        <p><b>Hora:</b> %s</p>
                    """.formatted(
                    reservation.getDate(),
                    reservation.getHour());

        }

        if (question != null) {

            questionBlock = """
                        <h3 style="
                            color:#0A6ED1;
                            border-bottom:2px solid #eef5ff;
                            padding-bottom:8px;
                            margin-top:25px;
                        ">
                            Consulta
                        </h3>

                        <p style="
                            background:#f5f8ff;
                            padding:15px;
                            border-radius:8px;
                        ">
                            %s
                        </p>
                    """.formatted(question.getComment());

        }

        String name = reservation != null ? reservation.getName() : question.getName() + " " + question.getSurname();

        String email = reservation != null ? reservation.getUsername() : question.getUsername();

        String phone = reservation != null ? reservation.getPhone() : question.getPhone();

        String body = """
                <html>
                <body style="font-family: Arial, sans-serif; background-color:#2741a1; padding:20px;">

                <div style="
                    max-width:600px;
                    margin:auto;
                    background:white;
                    border-radius:12px;
                    box-shadow:0 5px 15px rgba(0,0,0,0.08);
                    overflow:hidden;
                ">

                <div style="
                    background:#FAFAFA;
                    padding:25px;
                    text-align:center;
                ">

                <img src="cid:logo" style="width:140px;">

                <h2 style="color:#00226b;">
                    %s
                </h2>

                </div>

                <div style="padding:30px;">

                <h3 style="
                    color:#0A6ED1;
                    border-bottom:2px solid #eef5ff;
                    padding-bottom:8px;
                ">
                    Datos del cliente
                </h3>

                <p><b>Nombre:</b> %s</p>
                <p><b>Email:</b> %s</p>
                <p><b>Teléfono:</b> %s</p>

                %s

                %s

                </div>

                <div style="
                    background:#f0f6ff;
                    padding:20px;
                    text-align:center;
                    font-size:13px;
                    color:#0A6ED1;
                ">
                Sistema automático FisioCore
                </div>

                </div>

                </body>
                </html>
                """.formatted(
                titulo,
                name,
                email,
                phone,
                reservationBlock,
                questionBlock);

        try {

            MimeMessage message = mailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(body, true);

            helper.setReplyTo(email);

            FileSystemResource file = new FileSystemResource(
                    new File("src/main/resources/static/FisioCore-vertical.png"));

            helper.addInline("logo", file);

            mailSender.send(message);

        } catch (MessagingException e) {

            throw new RuntimeException("Error al enviar el email", e);

        }
    }
}
