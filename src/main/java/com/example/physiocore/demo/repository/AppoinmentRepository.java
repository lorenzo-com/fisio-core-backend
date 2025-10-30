package com.example.physiocore.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.physiocore.demo.model.Appoinment;

public interface AppoinmentRepository extends JpaRepository<Appoinment, Long> {

}
