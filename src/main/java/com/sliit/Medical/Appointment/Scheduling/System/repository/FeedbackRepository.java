package com.sliit.Medical.Appointment.Scheduling.System.repository;

import com.sliit.Medical.Appointment.Scheduling.System.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {

    List<Feedback> findByDoctorIdAndStatus(Long doctorId, String status);
}