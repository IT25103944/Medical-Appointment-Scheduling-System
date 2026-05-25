package com.sliit.Medical.Appointment.Scheduling.System.repository;

import com.sliit.Medical.Appointment.Scheduling.System.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List; // Make sure List is imported!

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    // For loading the Doctor's Profile
    Doctor findByEmail(String email);

    // ✨ THE FIX: For powering the Admin Dashboard search bar
    List<Doctor> findByNameContainingIgnoreCaseOrDepartmentContainingIgnoreCase(String name, String department);
}