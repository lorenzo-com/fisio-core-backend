package com.example.physiocore.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.physiocore.demo.dto.UserDto;
import com.example.physiocore.demo.dto.UserUpdateDTO;
import com.example.physiocore.demo.model.AppUser;
import com.example.physiocore.demo.services.UserService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/clients")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public List<UserDto> getAllClients() {
        return userService.getAllClients();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getClientById(@PathVariable Long id) {
        return userService.getClientById(id)
                .map(client -> ResponseEntity.ok(client))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/update/{id}")
	public ResponseEntity<AppUser> updateClient(@PathVariable Long id, @RequestBody UserUpdateDTO clientData) {
		AppUser updatedClient = userService.updateClient(id, clientData);
		return ResponseEntity.ok(updatedClient);
	}

	@DeleteMapping("/delete/{id}")
	public String deleteClient(@PathVariable Long id) {

		return "Deleted";
	}
}
