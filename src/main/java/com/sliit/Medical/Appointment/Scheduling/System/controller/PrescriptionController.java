package com.sliit.Medical.Appointment.Scheduling.System.controller;

import com.sliit.Medical.Appointment.Scheduling.System.entity.Prescription;
import com.sliit.Medical.Appointment.Scheduling.System.repository.PrescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/prescriptions")
@CrossOrigin(origins = "*")
public class PrescriptionController {

    @Autowired
    private PrescriptionRepository prescriptionRepository;

    @PostMapping
    public ResponseEntity<?> addPrescription(@RequestBody Prescription prescription) {
     
        if (prescription.getPrescriptionDate() == null || prescription.getPrescriptionDate().isBlank()) {
            prescription.setPrescriptionDate(LocalDate.now().toString());
        }
        if (prescription.getDate() == null || prescription.getDate().isBlank()) {
            prescription.setDate(LocalDate.now().toString());
        }
        if (prescription.getPrescriptionTime() == null || prescription.getPrescriptionTime().isBlank()) {
            prescription.setPrescriptionTime(LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm a")));
        }
        return ResponseEntity.ok(prescriptionRepository.save(prescription));
    }

    @GetMapping("/patient/{email}")
    public ResponseEntity<List<Prescription>> getPatientPrescriptions(@PathVariable String email) {
        return ResponseEntity.ok(prescriptionRepository.findByPatientEmail(email));
    }

    @GetMapping("/doctor/{email}")
    public ResponseEntity<List<Prescription>> getDoctorPrescriptions(@PathVariable String email) {
        return ResponseEntity.ok(prescriptionRepository.findByDoctorEmail(email));
    }

    
    @GetMapping
    public ResponseEntity<List<Prescription>> getAllPrescriptionsStandard() {
        return ResponseEntity.ok(prescriptionRepository.findAll());
    }

    
    @GetMapping("/getAll")
    public ResponseEntity<List<Prescription>> getAllPrescriptions() {
        return ResponseEntity.ok(prescriptionRepository.findAll());
    }



    @PutMapping("/{id}")
    public ResponseEntity<?> updatePrescription(@PathVariable Long id, @RequestBody Prescription details) {
        Prescription p = prescriptionRepository.findById(id).orElseThrow();
        p.setMedication(details.getMedication());
        p.setDosage(details.getDosage());
        p.setInstructions(details.getInstructions());
        return ResponseEntity.ok(prescriptionRepository.save(p));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePrescription(@PathVariable Long id) {
        prescriptionRepository.deleteById(id);
        return ResponseEntity.ok().body("{\"message\": \"Deleted successfully!\"}");
    }
}
