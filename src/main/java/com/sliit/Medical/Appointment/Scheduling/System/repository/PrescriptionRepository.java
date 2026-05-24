package com.sliit.Medical.Appointment.Scheduling.System.repository;

import com.sliit.Medical.Appointment.Scheduling.System.entity.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

    @Repository
    public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {
        List<Prescription> findByPatientEmail(String patientEmail);
        List<Prescription> findByDoctorEmail(String doctorEmail);
        List<Prescription> findAll();
    }

