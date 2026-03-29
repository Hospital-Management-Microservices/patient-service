package com.hms.patientservice.service;

import com.hms.patientservice.dto.PatientDTO;
import com.hms.patientservice.model.Patient;
import com.hms.patientservice.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    // ==========================================
    // CREATE - Add new patient
    // ==========================================
    public PatientDTO createPatient(PatientDTO patientDTO) {
        Patient patient = mapToEntity(patientDTO);
        patient.setIsActive(true);
        Patient savedPatient = patientRepository.save(patient);
        return mapToDTO(savedPatient);
    }

    // ==========================================
    // READ ALL - Get all patients
    // ==========================================
    public List<PatientDTO> getAllPatients() {
        return patientRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // ==========================================
    // READ ONE - Get patient by ID
    // ==========================================
    public PatientDTO getPatientById(Long id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found with ID: " + id));
        return mapToDTO(patient);
    }

    // ==========================================
    // UPDATE - Update existing patient
    // ==========================================
    public PatientDTO updatePatient(Long id, PatientDTO patientDTO) {
        Patient existingPatient = patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found with ID: " + id));

        existingPatient.setFirstName(patientDTO.getFirstName());
        existingPatient.setLastName(patientDTO.getLastName());
        existingPatient.setDateOfBirth(patientDTO.getDateOfBirth());
        existingPatient.setGender(patientDTO.getGender());
        existingPatient.setEmail(patientDTO.getEmail());
        existingPatient.setPhoneNumber(patientDTO.getPhoneNumber());
        existingPatient.setAddress(patientDTO.getAddress());
        existingPatient.setBloodGroup(patientDTO.getBloodGroup());
        existingPatient.setEmergencyContact(patientDTO.getEmergencyContact());
        existingPatient.setIsActive(patientDTO.getIsActive());

        Patient updatedPatient = patientRepository.save(existingPatient);
        return mapToDTO(updatedPatient);
    }

    // ==========================================
    // DELETE - Delete patient by ID
    // ==========================================
    public String deletePatient(Long id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found with ID: " + id));
        patientRepository.delete(patient);
        return "Patient with ID " + id + " deleted successfully.";
    }

    // ==========================================
    // SEARCH - Search patients by name
    // ==========================================
    public List<PatientDTO> searchPatientsByName(String name) {
        return patientRepository.findByLastNameContainingIgnoreCase(name)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // ==========================================
    // HELPER: Entity → DTO
    // ==========================================
    private PatientDTO mapToDTO(Patient patient) {
        PatientDTO dto = new PatientDTO();
        dto.setId(patient.getId());
        dto.setFirstName(patient.getFirstName());
        dto.setLastName(patient.getLastName());
        dto.setDateOfBirth(patient.getDateOfBirth());
        dto.setGender(patient.getGender());
        dto.setEmail(patient.getEmail());
        dto.setPhoneNumber(patient.getPhoneNumber());
        dto.setAddress(patient.getAddress());
        dto.setBloodGroup(patient.getBloodGroup());
        dto.setEmergencyContact(patient.getEmergencyContact());
        dto.setIsActive(patient.getIsActive());
        dto.setCreatedAt(patient.getCreatedAt());
        dto.setUpdatedAt(patient.getUpdatedAt());
        return dto;
    }

    // ==========================================
    // HELPER: DTO → Entity
    // ==========================================
    private Patient mapToEntity(PatientDTO dto) {
        Patient patient = new Patient();
        patient.setFirstName(dto.getFirstName());
        patient.setLastName(dto.getLastName());
        patient.setDateOfBirth(dto.getDateOfBirth());
        patient.setGender(dto.getGender());
        patient.setEmail(dto.getEmail());
        patient.setPhoneNumber(dto.getPhoneNumber());
        patient.setAddress(dto.getAddress());
        patient.setBloodGroup(dto.getBloodGroup());
        patient.setEmergencyContact(dto.getEmergencyContact());
        return patient;
    }
}
