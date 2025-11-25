package com.example.physiocore.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.physiocore.demo.dto.ClientDto;
import com.example.physiocore.demo.services.ClientService;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/clients")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @GetMapping("/all")
    public List<ClientDto> getAllClients() {
        return clientService.getAllClients();
    }
}
