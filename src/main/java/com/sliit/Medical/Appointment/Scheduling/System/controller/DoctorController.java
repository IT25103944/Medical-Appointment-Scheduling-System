package com.sliit.Medical.Appointment.Scheduling.System.controller;

import com.sliit.Medical.Appointment.Scheduling.System.entity.Doctor;
import com.sliit.Medical.Appointment.Scheduling.System.entity.User;
import com.sliit.Medical.Appointment.Scheduling.System.repository.DoctorRepository;
import com.sliit.Medical.Appointment.Scheduling.System.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/doctors")
@CrossOrigin(origins = "*")
public class DoctorController {

    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private UserRepository userRepository;

    // Get ALL Doctors (Used by Patient "Find a Doctor" grid)
    @GetMapping
    public ResponseEntity<List<Doctor>> getAllDoctors() {
        try {
            List<Doctor> doctors = doctorRepository.findAll();
            doctors.forEach(d -> {
                if (d.getEmail() != null) {
                    Optional<User> u = userRepository.findByEmailIgnoreCase(d.getEmail().trim());
                    u.ifPresent(user -> d.setPassword(user.getPassword()));
                }
            });
            return ResponseEntity.ok(doctors);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Create New Doctor (Used by Admin Dashboard)
    @PostMapping
    public ResponseEntity<?> createDoctor(@RequestBody Doctor doctor) {
        try {
            if (doctor.getConsultationFee() == null) doctor.setConsultationFee(0.0);
            return ResponseEntity.ok(doctorRepository.save(doctor));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }

    // Update Doctor by ID (Used by Admin Edit Modal)
    @PutMapping("/{id}")
    public ResponseEntity<?> updateDoctor(@PathVariable Long id, @RequestBody Doctor updatedDoctor) {
        try {
            Doctor existing = doctorRepository.findById(id).orElse(null);
            if (existing == null) {
                return ResponseEntity.badRequest().body("{\"error\": \"Doctor not found\"}");
            }

            existing.setName(updatedDoctor.getName());
            existing.setDepartment(updatedDoctor.getDepartment());
            existing.setPosition(updatedDoctor.getPosition());
            existing.setContactNumber(updatedDoctor.getContactNumber());
            existing.setBio(updatedDoctor.getBio() != null ? updatedDoctor.getBio() : existing.getBio());
            existing.setConsultationFee(updatedDoctor.getConsultationFee() != null ? updatedDoctor.getConsultationFee() : existing.getConsultationFee());

            if (updatedDoctor.getEmail() != null && !updatedDoctor.getEmail().isBlank()) {
                User user = userRepository.findByEmail(updatedDoctor.getEmail()).orElse(null);
                if (user != null && updatedDoctor.getPassword() != null && !updatedDoctor.getPassword().isBlank()) {
                    user.setPassword(updatedDoctor.getPassword());
                    userRepository.save(user);
                }
            }

            return ResponseEntity.ok(doctorRepository.save(existing));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }

    // ✨ NEW: Get Doctor by Email (Loads the Doctor Profile)
    @GetMapping("/email/{email}")
    public ResponseEntity<?> getDoctorByEmail(@PathVariable String email) {
        try {
            Doctor doctor = doctorRepository.findByEmail(email);
            if (doctor == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(doctor);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // ✨ NEW: Update Doctor Profile
    @PutMapping("/email/{email}")
    public ResponseEntity<?> updateDoctorProfile(@PathVariable String email, @RequestBody Doctor updatedDoctor) {
        try {
            Doctor existing = doctorRepository.findByEmail(email);
            if (existing == null) {
                return ResponseEntity.badRequest().body("{\"error\": \"Doctor not found\"}");
            }

            String oldEmail = existing.getEmail();
            existing.setName(updatedDoctor.getName());
            existing.setDepartment(updatedDoctor.getDepartment());
            existing.setPosition(updatedDoctor.getPosition());
            existing.setBio(updatedDoctor.getBio());
            if (updatedDoctor.getContactNumber() != null && !updatedDoctor.getContactNumber().isBlank()) {
                existing.setContactNumber(updatedDoctor.getContactNumber().trim());
            }
            if (updatedDoctor.getConsultationFee() != null) {
                existing.setConsultationFee(updatedDoctor.getConsultationFee());
            }

            doctorRepository.save(existing);

            // Sync doctor user account (name + optional password)
            User user = null;
            if (oldEmail != null && !oldEmail.isBlank()) {
                user = userRepository.findByEmailIgnoreCase(oldEmail.trim()).orElse(null);
            }
            if (user == null && email != null && !email.isBlank()) {
                user = userRepository.findByEmailIgnoreCase(email.trim()).orElse(null);
            }
            if (user != null) {
                if (updatedDoctor.getName() != null && !updatedDoctor.getName().isBlank()) {
                    user.setFullName(updatedDoctor.getName().trim());
                }
                if (updatedDoctor.getPassword() != null && !updatedDoctor.getPassword().isBlank()) {
                    user.setPassword(updatedDoctor.getPassword());
                }
                userRepository.save(user);
            }

            return ResponseEntity.ok("{\"message\": \"Profile updated!\"}");
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Delete Doctor
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDoctor(@PathVariable Long id) {
        try {
            doctorRepository.deleteById(id);
            return ResponseEntity.ok().body("{\"message\": \"Deleted successfully!\"}");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }
}
