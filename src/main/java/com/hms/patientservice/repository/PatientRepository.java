package com.hms.patientservice.repository;

import com.hms.patientservice.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    // Find patients by last name
    List<Patient> findByLastNameContainingIgnoreCase(String lastName);

    // Find patient by email
    Optional<Patient> findByEmail(String email);

    // Find all active patients
    List<Patient> findByIsActive(Boolean isActive);
}
