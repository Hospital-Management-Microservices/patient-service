package com.hms.patientservice.controller;

import com.hms.patientservice.dto.PatientDTO;
import com.hms.patientservice.service.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
@CrossOrigin(origins = "*")
@Tag(name = "Patient API", description = "Endpoints for managing patients in the Hospital Management System")
public class PatientController {

    @Autowired
    private PatientService patientService;

    // ==========================================
    // POST /patients — Create new patient
    // ==========================================
    @PostMapping
    @Operation(summary = "Create a new patient", description = "Adds a new patient record to the system")
    public ResponseEntity<PatientDTO> createPatient(@RequestBody PatientDTO patientDTO) {
        PatientDTO created = patientService.createPatient(patientDTO);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    // ==========================================
    // GET /patients — Get all patients
    // ==========================================
    @GetMapping
    @Operation(summary = "Get all patients", description = "Retrieves a list of all registered patients")
    public ResponseEntity<List<PatientDTO>> getAllPatients() {
        List<PatientDTO> patients = patientService.getAllPatients();
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }

    // ==========================================
    // GET /patients/{id} — Get patient by ID
    // ==========================================
    @GetMapping("/{id}")
    @Operation(summary = "Get patient by ID", description = "Retrieves a specific patient using their ID")
    public ResponseEntity<PatientDTO> getPatientById(@PathVariable Long id) {
        PatientDTO patient = patientService.getPatientById(id);
        return new ResponseEntity<>(patient, HttpStatus.OK);
    }

    // ==========================================
    // PUT /patients/{id} — Update patient
    // ==========================================
    @PutMapping("/{id}")
    @Operation(summary = "Update patient", description = "Updates an existing patient's information by ID")
    public ResponseEntity<PatientDTO> updatePatient(@PathVariable Long id,
                                                     @RequestBody PatientDTO patientDTO) {
        PatientDTO updated = patientService.updatePatient(id, patientDTO);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    // ==========================================
    // DELETE /patients/{id} — Delete patient
    // ==========================================
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete patient", description = "Removes a patient record from the system by ID")
    public ResponseEntity<String> deletePatient(@PathVariable Long id) {
        String message = patientService.deletePatient(id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    // ==========================================
    // GET /patients/search?name= — Search by name
    // ==========================================
    @GetMapping("/search")
    @Operation(summary = "Search patients by name", description = "Search for patients by their last name")
    public ResponseEntity<List<PatientDTO>> searchPatients(@RequestParam String name) {
        List<PatientDTO> patients = patientService.searchPatientsByName(name);
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }
}
