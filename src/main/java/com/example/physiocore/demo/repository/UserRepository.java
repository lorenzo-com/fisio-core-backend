package com.example.physiocore.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.physiocore.demo.model.AppUser;
import com.example.physiocore.demo.model.UserRole;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByUsername(@Param("value") String value);

    @Query("SELECT u FROM AppUser u JOIN u.roles r WHERE r = :role")
    List<AppUser> findAllByRole(@Param("role") UserRole role);
}
