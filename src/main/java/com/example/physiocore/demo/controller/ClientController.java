package com.example.physiocore.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.physiocore.demo.dto.ClientDto;
import com.example.physiocore.demo.dto.ClientUpdateDTO;
import com.example.physiocore.demo.model.Client;
import com.example.physiocore.demo.services.ClientService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/clients")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @GetMapping("/all")
    public List<ClientDto> getAllClients() {
        return clientService.getAllClients();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getClientById(@PathVariable Long id) {
        return clientService.getClientById(id)
                .map(client -> ResponseEntity.ok(client))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/update/{id}")
	public ResponseEntity<Client> updateClient(@PathVariable Long id, @RequestBody ClientUpdateDTO clientData) {
		Client updatedClient = clientService.updateClient(id, clientData);
		return ResponseEntity.ok(updatedClient);
	}

	@DeleteMapping("/delete/{id}")
	public String deleteClient(@PathVariable Long id) {

		return "Deleted";
	}
}
