package com.example.physiocore.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.example.physiocore.demo.dto.UserDto;
import com.example.physiocore.demo.dto.UserUpdateDTO;
import com.example.physiocore.demo.model.AppUser;
import com.example.physiocore.demo.services.UserService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    // ADMIN, PROFESSIONAL y CLIENT
    @GetMapping("/client/{id}")
    public ResponseEntity<?> getClientById(@PathVariable Long id) {
        return userService.getClientById(id)
                .map(client -> ResponseEntity.ok(client))
                .orElse(ResponseEntity.notFound().build());
    }

    // ADMIN y PATIENT
    @PutMapping("/client/update/{id}")
    public ResponseEntity<AppUser> updateClient(@PathVariable Long id, @RequestBody UserUpdateDTO clientData) {
        AppUser updatedClient = userService.updateClient(id, clientData);
        return ResponseEntity.ok(updatedClient);
    }

    // ADMIN y PROFESSIONAL
    @GetMapping("/client/all")
    public List<UserDto> getAllClients() {
        return userService.getAllClients();
    }

    // ADMIN
    @GetMapping("/professionals")
    public List<UserDto> getAllProfessionals() {
        return userService.getAllProfessionals();
    }

    @PostMapping("/professionals/new")
    public ResponseEntity<AppUser> createProfessional(@RequestBody AppUser professional) {
        AppUser newProfessional = userService.createProfessional(professional);
        return ResponseEntity.status(HttpStatus.CREATED).body(newProfessional);
    }

    @PutMapping("/professionals/update/{id}")
    public ResponseEntity<AppUser> updateProfessional(
            @PathVariable Long id,
            @RequestBody AppUser professional) {

        return ResponseEntity.ok(
                userService.updateProfessional(id, professional));
    }

    @DeleteMapping("/professionals/delete/{id}")
    public ResponseEntity<?> deleteProfessional(@PathVariable Long id) {

        userService.deleteProfessional(id);

        return ResponseEntity.ok(Map.of("message", "Profesional eliminado correctamente"));
    }

    @GetMapping("/professionals/{id}")
    public ResponseEntity<?> getProfessionalById(@PathVariable Long id) {

        return userService.getProfessionalById(id)
                .map(professional -> ResponseEntity.ok(professional))
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/professionals/{id}/estado")
    public ResponseEntity<AppUser> cambiarEstado(@PathVariable Long id, @RequestBody Map<String, Boolean> body) {
        boolean active = body.get("active");
        return ResponseEntity.ok(userService.cambiarEstado(id, active));
    }

    @GetMapping("/administrators")
    public ResponseEntity<List<AppUser>> getAdmins() {
        return ResponseEntity.ok(userService.findAllAdmins());
    }

    @DeleteMapping("/client/delete/{id}")
    public ResponseEntity<?> deleteClient(@PathVariable Long id) {
        userService.deleteClient(id);
        return ResponseEntity.noContent().build();
    }
}
