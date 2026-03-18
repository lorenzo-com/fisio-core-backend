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
                            <img src="cid:logo" style="width:140px; margin-bottom:10px;">

                            <h2 style="
                                color:#00226b;
                                margin:0;
                                font-weight:600;
                            ">
                                Nueva Reserva
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
                            <p><b>Servicio:</b> %s</p>

                        </div>

                        <div style="
                            background:#f0f6ff;
                            padding:20px;
                            text-align:center;
                            font-size:13px;
                            color:#0A6ED1;
                        ">
                            Sistema automático de reservas FisioCore
                        </div>

                    </div>

                </body>
                </html>
                """.formatted(
                reservation.getName(),
                reservation.getEmail(),
                reservation.getPhone(),
                reservation.getDate(),
                reservation.getHour(),
                reservation.getService());

        emailService.sendEmail(
                "fisiocorecontacto@gmail.com",
                "Nueva reserva - FisioCore",
                body);

        return "Email enviado correctamente";
    }
}